# Learning Journey
------



## Table of Contents

------
- [Learning Journey](#learning-journey)
  - [Table of Contents](#table-of-contents)
  - [Background](#background)
  - [Languages and Tools](#languages-and-tools)
  - [Interface preview](#interface-preview)
  - [Quick start](#quick-start)
  - [Project structure](#project-structure)
  - [Contributors](#contributors)
  - [License](#license)



## Background 

------

Your team will develop a Learning Journey Application for BUPT International School  students.



## Languages and Tools

------


<p align="left"> 
    <a href="https://git-scm.com/" target="_blank" rel="noreferrer">
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/>  </a> 
    <a href="https://www.java.com" target="_blank" rel="noreferrer"> 
    <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/>
    </a>
</p>



## Interface preview

------

Please check [User Manual](./USER MANUAL.md)



## Quick start

------

1. Make sure your development environment meets the following requirements:

   - Java Development Kit (JDK) 8 or higher.

   - Apache Maven 3.0 or higher.

2. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/yuhuoji/LearningJourney
   ```

3. Extract the file "doc/LearningJourneyFiles.rar" to the "target" folder. After extraction, the "Course", "Internship", and other folders should be located in "target/LearningJourneyFiles".

4. Open a terminal or command line interface and navigate to the project's root directory.

5. Execute the following Maven command to compile and run the project:

   ```shell
   mvn javafx:run
   ```

   This command will use the Maven plugin to launch the JavaFX application.

6. Once the program starts successfully, you will see the user interface of the JavaFX application.



##  Project structure

------

```
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
│                  
└─test
```



## Contributors

------

1. [**askmmmm**](https://github.com/askmmmm)
1. [**Giggsss997**](https://github.com/Giggsss997)
1. [**makino-w**](https://github.com/makino-w)
1. [**MengGith**](https://github.com/MengGith)
1. [**randlyoyo**](https://github.com/randlyoyo)
1. [**yuhuoji**](https://github.com/yuhuoji)

## License

------

[MIT](./LICENSE)
