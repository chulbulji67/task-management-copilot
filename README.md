# task-management-copilot

#Setup Instructions
#Pre-requisites
Java 8 or higher installed on your machine.
Maven as your build tool.
Spring Boot dependencies for the project (if not included already).
MySql or another database system (if applicable) configured and running.
Git installed on your machine.

#Clone the repository to your local machine:
git clone https://github.com/your-username/task-management-app.git

#Set Up Database 
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=your-username
spring.datasource.password=your-password

Run the Application
1. Open a terminal window and navigate to the project directory.
2. Build the project using Maven:
   mvn clean install

4. Run the Spring Boot application:
mvn spring-boot:run
