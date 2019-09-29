# people-personal-application
This application is built to display people names and details. The application have the functionality to add a new person.

Steps to run the application:
~~~~
Clone the project and perform mvn clean install 
Inside target folder a jar will be created after successful built
Go inside target folder and do command:
    java -jar people-personal-application-1.0-SNAPSHOT.jar

The application will be started. Go to your browser and hit:
    http://localhost:8080/
A screen will be displayed with list of names. Link to see the details will be displayed besided names.
Click on details and person details will be seen
Click on "Go back to person list" and again list will be displayed
Click on "Add person" and a form will be displayed with input to person info. All the fields are mandatory, fill in all and click on "Create", a new person will be added.
~~~~
Application is built using Spring boot rest api. Data is stored using in memory database h2 and db operations are performed using spring-data-jpa

Front end is built using javascript framework VueJs and UI is built using Html and bootstrap css.

Unit testing is performed using junit5, mockito and spring boot test module