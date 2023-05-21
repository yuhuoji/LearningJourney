package com.javafx.learningjourney.controller.dialog;

import com.javafx.learningjourney.dao.FileDAO;
import com.javafx.learningjourney.dao.impl.FileDAOImpl;
import com.javafx.learningjourney.util.Cache;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class statisticDialog {
    private final FileDAO fileDAO;
    TableView<ObservableList<String>> tableView;
    ObservableList<ObservableList<String>> tableData;
    @FXML
    private ToggleButton statisticButton;
    @FXML
    private ToggleButton curveButton;
    @FXML
    private ToggleButton informationButton;
    @FXML
    private Pane contentPane; //主要内容区域

    public statisticDialog() {
        fileDAO = new FileDAOImpl();
    }

    @FXML
    private void initialize() {
        // 设置ToggleButton按钮的事件处理程序
        statisticButton.setOnAction(event -> handleButtonAction(statisticButton));
        curveButton.setOnAction(event -> handleButtonAction(curveButton));
        informationButton.setOnAction(event -> handleButtonAction(informationButton));
        Cache.put("contentPane", contentPane);
    }

    /**
     * 根据按钮的选中状态切换页面内容
     *
     * @param button 选中的按钮
     */
    @FXML
    private void handleButtonAction(ToggleButton button) {
        contentPane.getChildren().clear(); // 清除主要内容区域的所有子节点
        if (button.isSelected()) { // 根据按钮的选中状态切换页面内容
            if (button == statisticButton) {
                contentPane.getChildren().add(statistic());
                tableView.setEditable(true);
            } else if (button == curveButton) {
                contentPane.getChildren().add(curve());
            } else if (button == informationButton) {
                contentPane.getChildren().add(information());
            }
        }
    }

    /**
     * 成绩统计表格
     */
    private Pane statistic() {
        HBox page = new HBox();

        tableView = new TableView<>();

        // Create ScrollPane
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        Path currentPath = (Path) Cache.get("currentPath");
        //     System.out.println("currentPath = " + currentPath);

        List<Path> csvFilePaths = new ArrayList<>();
        File folder = new File(Cache.get("currentPath").toString());
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                        csvFilePaths.add(file.toPath());
                    }
                }
            }
        }

        tableData = readCSV(csvFilePaths.get(0));
        tableView.setItems(tableData);

        // Create Load Button
        Button loadButton = new Button("Load");
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(currentPath.toString()));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                tableView.getColumns().clear();
                tableView.getItems().clear();
                tableData = readCSV(file.toPath());
                tableView.setItems(tableData);
                tableView.setEditable(true); // 设置表格可编辑
            }
        });

        // Create Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(currentPath.toString()));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                saveToCSV(file.toPath(), tableData, tableView); // 使用已经更新的tableData保存数据，用tableView保存列名
            }
        });

        // Create HBox for buttons
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(loadButton, saveButton);
        buttonBox.setSpacing(10); // Set spacing between buttons

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonBox, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // 设置 ScrollPane 填充 VBox 的剩余空间

        page.getChildren().add(vbox);
        HBox.setHgrow(vbox, Priority.ALWAYS); // 设置 VBox 填充 HBox 的剩余空间

        return page;
    }

    /**
     * 读取CSV文件并返回数据
     *
     * @param path CSV文件路径
     * @return CSV文件数据
     */
    private ObservableList<ObservableList<String>> readCSV(Path path) {
        String filePath = path.toString();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        // Create table columns dynamically
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line (header)
            TableColumn<ObservableList<String>, String>[] columns = null;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    String[] headers = line.split(",");
                    columns = new TableColumn[headers.length];
                    for (int i = 0; i < headers.length; i++) {
                        final int colIndex = i;
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(headers[i]);
                        column.setCellValueFactory(param -> {
                            ObservableList<String> rowData = param.getValue();
                            if (colIndex >= 0 && colIndex < rowData.size()) {
                                return new SimpleStringProperty(rowData.get(colIndex));
                            } else {
                                return new SimpleStringProperty("");
                            }
                        });
                        // 设置表格列的可编辑性和cellFactory
                        column.setCellFactory(TextFieldTableCell.forTableColumn()); // 这里使用TextFieldTableCell作为示例
                        column.setEditable(true);
                        columns[i] = column;

                        // 监听单元格编辑完成事件
                        column.setOnEditCommit(event -> {
                            ObservableList<String> row = event.getRowValue();
                            row.set(colIndex, event.getNewValue()); // 更新tableData的对应值
                        });
                    }
                    tableView.getColumns().addAll(columns);
                    isFirstLine = false; // Skip the first line (header)
                    continue;
                }

                String[] values = line.split(",");
                ObservableList<String> row = FXCollections.observableArrayList();
                row.addAll(Arrays.asList(values));
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    /**
     * 保存数据到CSV文件
     *
     * @param path      CSV文件路径
     * @param tableData 表格数据
     * @param tableView 表格
     */
    private void saveToCSV(Path path, ObservableList<ObservableList<String>> tableData, TableView<ObservableList<String>> tableView) {
        ObservableList<TableColumn<ObservableList<String>, ?>> columns = tableView.getColumns();

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // Write column headers as the first row
            List<String> headers = new ArrayList<>();
            for (TableColumn<ObservableList<String>, ?> column : columns) {
                headers.add(column.getText());
            }
            writer.write(String.join(",", headers));
            writer.newLine();

            // Write data rows
            for (ObservableList<String> row : tableData) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 成绩曲线
     */
    private Pane curve() {
//        Button loadButton = new Button("加载CSV");
//        loadButton.setOnAction(event -> loadCSVFile());
//
//        Button saveButton = new Button("保存");
//        saveButton.setOnAction(event -> saveCSVFile());
//
//        VBox vbox = new VBox(loadButton, saveButton, tableView);

        //TODO @date 2023-05-18 加载表格
        return new Pane();
    }

    /**
     * 统计信息
     */
    private Pane information() {
        return new Pane();
    }


}
