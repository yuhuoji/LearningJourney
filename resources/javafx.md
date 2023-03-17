# JavaFX BV1Qf4y1F7Zv

------

结构stage-scene-node...

顺序init导入数据 -  start启动 - stop关闭数据库连接

- 舞台Stage

  ```
  title
  	Stage.setTitle
  icon
      getIcon
  resizable
      setResizable
  style装饰样式
      initStyle
  Modality模态，是否能操作其他窗口
  event
      setOnCloseRequest设置关闭窗口事件
      Platform.exit退出程序
      stage.close关闭窗口
  Stage.show
  Stage.setScene
  ```

  

- 场景Scene

  ```
  切换场景
  setCursor设置鼠标样式
  
  ```

  

- Node

  ```java
  layoutX，Y； preWidth
  style设置样式 css，
  Alignment居中
  opacity设置控件透明度
  rotate旋转
  移动translateX，Y
  parent,children，scene,id
  ```

- Property属性绑定监听

  ```java
  bind绑定，双向绑定
  addListener
  ```

- Action and Handler

  ```
  P7事件驱动编程，拖拽文件获得绝对路径，导入数据
  ```

- Color

  ```
  stroke线条颜色
  fill填充
  ```

- Font字体

- Image

- P9 FXML用双标签设置布局

  ```
  布局FXMLLoader导入
  controller 变量名和标签id对应自动绑定，onAction与变量名字相同
  
  ```

- P10 ***<u>使用Scene Builder拖拽布局</u>***  java9以上新增模块化新特性
  - FXML布局切换
- controller-initialize启动时导入数据
- 多线程
- canvas画布

...