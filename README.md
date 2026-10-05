# Student Management System

> A Spring Boot REST API for managing student records with MySQL and cloud deployment practice.

## 🎯 Overview

This project demonstrates a clean backend application using:

- RESTful APIs
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Validation and exception handling
- Maven
- AWS EC2 deployment

## 🛠️ Technology Stack

| Area | Technology |
|---|---|
| Language | Java |
| Backend | Spring Boot |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL |
| Build | Maven |
| Cloud | AWS EC2 |
| API Testing | Postman / cURL |

## 🏗️ Architecture

```
Client
  │
  ▼
REST Controller
  │
  ▼
Service Layer
  │
  ▼
Repository Layer
  │
  ▼
MySQL
```

## 📚 Main API Operations

- Create a student
- Read all students
- Read a student by ID
- Update student details
- Delete a student
- Search/filter students

## 🚀 Run Locally

### Prerequisites

- Java 11+
- Maven 3.6+
- MySQL 8+
- Git

### Setup

```bash
git clone https://github.com/NageliDharmaNaidu/student-management.git
cd student-management
```

Configure your database credentials in the application's configuration, then run:

```bash
mvn clean install
mvn spring-boot:run
```

The API runs on:

```
http://localhost:8080
```

## ☁️ Deployment Practice

The project also includes notes for deploying the Spring Boot application to AWS EC2.

## 💡 What I Learned

- Designing REST APIs with Spring Boot
- Separating controller, service and repository responsibilities
- Working with JPA/Hibernate and MySQL
- Validating API input and handling errors
- Packaging Java applications with Maven
- Deploying backend applications to a cloud VM

## 🔭 Next Improvements

- JWT authentication
- Role-based access control
- Pagination
- Swagger / OpenAPI documentation
- Automated tests
- Redis caching
- React admin dashboard

## Author

**Nageli Dharma Naidu**

GitHub: https://github.com/NageliDharmaNaidu
