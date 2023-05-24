# Learning Journey
------



## Table of Contents

------

[TOC]



## Background 

------

Your team will develop a Learning Journey Application for BUPT International School  students.



## Interface preview

------

Please check [User Manual](./USER MANUAL.md)



## Quick start

------

```js

Run JavaFXApplication.java
```



## System design

------



##  Project structure

------

├─main
│  ├─java
│  │  └─com
│  │      └─javafx
│  │          └─learningjourney
│  │              │  JavaFXApplication.java
│  │              │  
│  │              ├─controller
│  │              │  │  LoginController.java
│  │              │  │  MainController.java
│  │              │  │  package-info.java
│  │              │  │  
│  │              │  ├─component
│  │              │  │      NavbarController.java
│  │              │  │      package-info.java
│  │              │  │      RectangleItemController.java
│  │              │  │      
│  │              │  ├─course
│  │              │  │      AddCourseDialogController.java
│  │              │  │      CourseController.java
│  │              │  │      CourseInformationController.java
│  │              │  │      CourseScoreController.java
│  │              │  │      package-info.java
│  │              │  │      StatisticDialogController.java
│  │              │  │      
│  │              │  ├─internship
│  │              │  │      InternshipController.java
│  │              │  │      package-info.java
│  │              │  │      
│  │              │  ├─research
│  │              │  │      package-info.java
│  │              │  │      ResearchController.java
│  │              │  │      
│  │              │  └─work
│  │              │          package-info.java
│  │              │          WorkController.java
│  │              │          
│  │              ├─dao
│  │              │  │  FileDAO.java
│  │              │  │  package-info.java
│  │              │  │  
│  │              │  └─impl
│  │              │          FileDAOImpl.java
│  │              │          
│  │              ├─entity
│  │              │      Course.java
│  │              │      Internship.java
│  │              │      package-info.java
│  │              │      Research.java
│  │              │      Work.java
│  │              │      
│  │              ├─service
│  │              │  │  package-info.java
│  │              │  │  
│  │              │  └─impl
│  │              └─util
│  │                      Cache.java
│  │                      JsonUtil.java
│  │                      package-info.java
│  │                      RootPathUtil.java
│  │                      
│  └─resources
│      ├─fxml
│      │  │  LoginView.fxml
│      │  │  MainView.fxml
│      │  │  
│      │  ├─component
│      │  │      navbar.fxml
│      │  │      RectangleItem.fxml
│      │  │      sidebar.fxml
│      │  │      
│      │  ├─course
│      │  │      addCourseDialog.fxml
│      │  │      CourseInformation.fxml
│      │  │      CourseScore.fxml
│      │  │      CourseView.fxml
│      │  │      statisticDialog.fxml
│      │  │      
│      │  ├─internship
│      │  │      InternshipView.fxml
│      │  │      
│      │  ├─research
│      │  │      ResearchView.fxml
│      │  │      
│      │  └─work
│      │          WorkView.fxml
│      │          
│      └─static
│          └─ico
│                  file.png
│                  folder.png
│                  search.png
│                  
└─test

## Contributors

------

1. [**yuhuoji**](https://github.com/yuhuoji)
1. [**askmmmm**](https://github.com/askmmmm)
1. [**Giggsss997**](https://github.com/Giggsss997)
1. [**makino-w**](https://github.com/makino-w)
1. [**MengGith**](https://github.com/MengGith)
1. [**randlyoyo**](https://github.com/randlyoyo)



## License

------

[MIT]
