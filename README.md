# Medical Consulting room System

The major objective is to improve the cuality and eficiency of each medical service

### What you need
* IntelliJ IDEA
* Java 17 or later
* MySQL

## Instructions to run all the things
### 1. Set the database
* Open MySQL Workbench and get into your local instance
* In the menu with icons select the fourth icon, it looks like this: ![image](https://github.com/DavidVelaz17/iwaid-api/assets/139006505/e6fcdab6-50e7-44f2-b933-b66e2e8a92e7)
* In the new window "new_schema - Schema" paste "consultingroomiwa" in the name field
* Finaly click the "Apply" button
### 2. Set the project on IntelliJ IDEA
* Copy the project URL
* If you don’t have any projects open when you first start IntelliJ IDEA, you’ll see a welcome screen. You’ll have an option to get a project from version control. Clicking on this gives us the Get from Version Control dialog. You can paste the repository URL into the URL input box. 
* You can then press Enter, or click Clone, and IntelliJ IDEA will clone the GitHub repository to the directory we selected.
* Then IntelliJ will render the project and will ask you to download somethings for maven, you must agree.
### 3. Set your db credentials into the project
* On your MySQL Workbench you should have a "Local instance MySQL", below the name there must specify the user(root) and the port(3306), you will use them in next steps
* Open the application.properties file, the path is: consultorio-medico > src > main > resources
* Change the username and password to yours
* Also if your bd port is not 3306 you must change it, it's in the line 2

### Run the application
* Go to Maven section on the right side of the Intellij window
* Deploy plugins folder
* Deploy spring-boot plugin
* Double click on "spring-boot:run" plugin

 > If Maven section is not available then just run the project with the "Run" button or with the command "Shift+F10"
