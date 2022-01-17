# User-Management-App
This project is a simple CRUD web application that manages the basic details of a collection of users.
### Features of the web app
- Create a user
- Update a user
- Delete a user
- Retrieve a user
- List all the users
## Technologies used
Java, JSP, Servlets (JSF) and bootstrap.

## MVC Overview
- Model View Controller (MVC) is a pattern used in software engineering to separate the application logic from the user interface. As the name implies, the MVC has 3 layers.
- The Model defines the business layer of the application, the Controller manages the flow of the application, and the View defines the presentation layer of the application.

Some of the key features of the pattern
- It separates the presentation layer from the business layer.
- The Controller performs the action of invoking the Model and sending data to View.
- The Model is not even aware that it is used by some web application or a desktop application.

![ScreenShot](https://github.com/skrishnan2001/User-Management-App/blob/master/images/MVC-Design-Pattern.png)

## Directory structure
### `database/UsersDB.sql`
This sql file contains the query for creating the schema of the users table.

### `src/main/java/com/usermanagement`
com.usermanagement contains the following packages:
- `model`: This package contains the JavaBean class, specifying the fields in the User object.
- `dao`: This package contains the UserDAO class, which is the data access object. This class deals with all the CRUD(Create, Read, Update and Delete) database operations.
- `web`: This package contains the UserServlet class, which handles all the CRUD requests from the client. This servlet serves as the controller.

### `src/main/webapp`
Contains the following 3 jsp pages (View layer files):
- `user-form.jsp`: A simple form to accept the details of a user.
- `user-list.jsp`: Displays all the user details, with options for creating, updating or deleting a user.
- `Error.jsp`: Displays the exception errors on the browser, if any.

### `pom.xml`
An xml file, which contains all the maven dependencies/external jar files used in the project.

## Project screenshots
### List of all the users
![ScreenShot](https://github.com/skrishnan2001/User-Management-App/blob/master/images/UserList.PNG)

### Screen to create a new user or update an existing user details 
![ScreenShot](https://github.com/skrishnan2001/User-Management-App/blob/master/images/AddNewUser.PNG)
