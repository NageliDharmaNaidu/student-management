# Student Management System

A full-stack student management application built with Java and Spring Boot. This project covers database design, REST API development, and typical CRUD operations you'd see in a real system.

## What this does

Manage student records with a clean API. Create, read, update, and delete student information. Built to understand how enterprise Java applications work.

## Technology stack

- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **Build Tool**: Maven
- **Version Control**: Git

## Features

- Add new students to the system
- View student information
- Update student records
- Delete student records
- Proper error handling and validation

## How to set up

### Prerequisites
- Java 11+
- MySQL
- Maven

### Installation

1. Clone the repository
```bash
git clone https://github.com/NageliDharmaNaidu/student-management.git
cd student-management
```

2. Configure database connection
Edit `application.properties` with your MySQL details:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Create the database
```bash
mvn liquibase:update
```

4. Build and run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

- `GET /api/students` — Get all students
- `GET /api/students/{id}` — Get a specific student
- `POST /api/students` — Create a new student
- `PUT /api/students/{id}` — Update a student
- `DELETE /api/students/{id}` — Delete a student

## What this taught me

- Spring Boot project structure and best practices
- Building REST APIs with proper HTTP methods
- Database integration with JPA/Hibernate
- Request validation and error handling
- Maven project configuration

## Future improvements

- Add authentication and authorization
- Implement search and filtering
- Add pagination for large datasets
- Write comprehensive unit tests
- Add API documentation with Swagger

---

Last updated: March 2026