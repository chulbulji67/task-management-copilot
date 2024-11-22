# task-management-copilot

# Task Management Application

A Spring Boot application for managing tasks, including features like labels, priority change notifications, due date reminders, and more.

---

## **Setup Instructions**

### **Prerequisites**
1. Install [Java 21](https://openjdk.org/projects/jdk/21/).
2. Install [Maven](https://maven.apache.org/).
3. Set up a MySQL database and update the `application.properties` with your database credentials.
4. Install [Postman](https://www.postman.com/downloads/) for testing APIs (optional but recommended).

### **Configuration**
1. Clone the repository:
    ```bash
    git clone https://github.com/chulbulji67/task-management-copilot.git
    cd task-management-copilot
    ```
2. Update the `src/main/resources/application.properties` file with the required database and email configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/task_management
    spring.datasource.username=<your-db-username>
    spring.datasource.password=<your-db-password>
    
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=<your-email>
    spring.mail.password=<your-app-password>
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```
3. Build the application:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

---

## **API Documentation**

### **Postman Collection**
A Postman collection is included in the repository. You can import it to test the APIs:
1. Open Postman.
2. Import the `postman_collection.json` file located in the root of the repository.
3. Use the pre-configured requests to interact with the APIs.

---

### **Endpoints**

#### **Task Management**
1. **Create Task**
    - **POST /api/tasks**
    - Payload:
      ```json
      {
        "title": "Fix Login Bug",
        "description": "Resolve login issues for SSO users",
        "priority": "HIGH",
        "assignee": "john.doe",
        "dueDate": "2024-03-22"
      }
      ```
2. **Retrieve Tasks**
    - **GET /api/tasks**
3. **Search Tasks by Labels**
    - **GET /api/tasks/search?labels=Bug,UI**
4. **Add/Update Labels**
    - **PUT /api/tasks/{id}/labels**
    - Payload:
      ```json
      ["Bug", "Backend"]
      ```

#### **Due Date Reminders**
1. **Scheduled Reminder Notifications**
    - A cron job runs every day to send reminder emails for tasks due tomorrow.

#### **Task Priority Change Notifications**
1. Notifications are sent to the assignee when the priority of a task changes.

---

## **Testing Instructions**

### **Email Testing**
Ensure your SMTP configuration is correct to test email notifications for:
1. **Due Date Reminders**
    - Assign a task with a due date of tomorrow and wait for the reminder email.
2. **Priority Change Notifications**
    - Update a task's priority using:
      ```http
      PUT /api/tasks/{id}
      Content-Type: application/json
      
      {
        "priority": "HIGH"
      }
      ```

### **Testing Labels**
1. Add labels to a task:
    ```http
    PUT /api/tasks/{id}/labels
    Content-Type: application/json

    ["Bug", "UI"]
    ```
2. Search tasks by labels:
    ```http
    GET /api/tasks/search?labels=Bug
    ```

---

## **Features Implemented**
1. **Task Management**
    - Create, read, update, and delete tasks.
2. **Label Management**
    - Add and search tasks by labels.
3. **Email Notifications**
    - Send due date reminders.
    - Notify users when task priority changes.

---

Steps to Import Postman Collection from GitHub
Download the Postman Collection from GitHub

Navigate to the repository on GitHub where the Task-Management-Copilot.postman_collection.json file is located.
Click on the file Task-Management-Copilot.postman_collection.json to open it.
Click the "Download" button or right-click on the "Raw" button and select Save link as... to download the file to your local system.
Open Postman

If you don't have Postman installed, you can download it from Postman’s official website.
Once installed, open the Postman app.
Import the Collection into Postman

In Postman, go to the File menu at the top left and select Import.
A dialog will appear. Choose Upload Files.
Select the Task-Management-Copilot.postman_collection.json file you downloaded from GitHub and click Open.
Use the Collection

The Postman collection will now appear in the Collections tab in Postman.
Click on the collection to expand and view all the requests defined in the collection.
You can now use these requests to test your API by adjusting the necessary parameters (e.g., task ID, labels) and sending requests to your local server.


## **Additional Notes**
1. Ensure the application runs on `http://localhost:8080`.
2. Use Postman to import the collection and test the APIs interactively.


