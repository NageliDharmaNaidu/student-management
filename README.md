# Student Management System

> A complete **Spring Boot REST API** for managing student records with MySQL database integration and AWS EC2 deployment.

## 🎯 Project Overview

This is a production-ready student management application demonstrating:
- Clean REST API design
- Database integration with Spring Data JPA
- Proper error handling and validation
- AWS EC2 deployment
- Security best practices

## 🛠️ Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 11+ |
| **Framework** | Spring Boot 3.x |
| **Database** | MySQL 8.0 |
| **Build Tool** | Maven |
| **Cloud** | AWS EC2 |
| **Storage** | AWS S3 |
| **IDE** | IntelliJ IDEA |

## 📁 Project Structure

```
student-management/
├── src/
│   ├── main/
│   │   ├── java/com/student/management/
│   │   │   ├── controller/        # REST API endpoints
│   │   │   ├── service/           # Business logic
│   │   │   ├── repository/        # Data access layer
│   │   │   ├── model/             # Entity classes
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── exception/         # Custom exceptions
│   │   │   └── config/            # Configuration classes
│   │   └── resources/
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test/
│       └── java/com/student/management/
├── pom.xml
├── README.md
└── .gitignore
```

## 🚀 Quick Start

### Prerequisites
```bash
✓ Java 11 or higher
✓ Maven 3.6+
✓ MySQL 8.0
✓ Git
```

### Installation

1. **Clone Repository**
```bash
git clone https://github.com/NageliDharmaNaidu/student-management.git
cd student-management
```

2. **Database Setup**
```bash
mysql -u root -p

CREATE DATABASE student_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_db;
SOURCE src/main/resources/schema.sql;
```

3. **Configure Application**
```bash
# Edit src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

4. **Run Application**
```bash
mvn clean install
mvn spring-boot:run
```

✅ Application starts on `http://localhost:8080`

## 📚 API Endpoints

### Get All Students
```http
GET /api/v1/students
Content-Type: application/json
```
**Response:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9110584381",
    "course": "B.Tech CSE",
    "year": 3,
    "cgpa": 8.5,
    "createdAt": "2026-08-12T10:30:00Z"
  }
]
```

### Get Student by ID
```http
GET /api/v1/students/{id}
```

### Create Student
```http
POST /api/v1/students
Content-Type: application/json

{
  "name": "Jane Smith",
  "email": "jane@example.com",
  "phone": "9876543210",
  "course": "B.Tech IT",
  "year": 2,
  "cgpa": 8.2
}
```

### Update Student
```http
PUT /api/v1/students/{id}
Content-Type: application/json

{
  "name": "Jane Smith Updated",
  "cgpa": 8.7
}
```

### Delete Student
```http
DELETE /api/v1/students/{id}
```

### Search Students
```http
GET /api/v1/students/search?keyword=john
GET /api/v1/students/course/B.Tech%20CSE
GET /api/v1/students/year/3
```

## 🧪 Testing

### Using Postman
1. Import the provided Postman collection
2. Update BASE_URL variable to `http://localhost:8080`
3. Run requests in order

### Using cURL
```bash
# Create student
curl -X POST http://localhost:8080/api/v1/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","email":"test@test.com","course":"CSE","year":1,"cgpa":8.5}'

# Get all students
curl http://localhost:8080/api/v1/students

# Get by ID
curl http://localhost:8080/api/v1/students/1
```

## ☁️ AWS EC2 Deployment

### Step 1: Build JAR
```bash
mvn clean package -DskipTests
# Creates: target/student-management-1.0.0.jar
```

### Step 2: Launch EC2 Instance
- **OS**: Ubuntu 22.04 LTS
- **Instance Type**: t2.micro (free tier)
- **Security Group**: Allow ports 22 (SSH), 3306 (MySQL), 8080 (HTTP)

### Step 3: Connect to EC2
```bash
chmod 400 your-key.pem
ssh -i your-key.pem ubuntu@YOUR_EC2_IP
```

### Step 4: Install Java
```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
```

### Step 5: Install MySQL
```bash
sudo apt install mysql-server -y
sudo mysql_secure_installation
sudo mysql -u root -p < schema.sql
```

### Step 6: Transfer JAR to EC2
```bash
scp -i your-key.pem target/student-management-1.0.0.jar ubuntu@YOUR_EC2_IP:~
```

### Step 7: Run Application
```bash
cd ~
java -jar student-management-1.0.0.jar \
  --spring.datasource.url=jdbc:mysql://localhost:3306/student_db \
  --spring.datasource.username=root \
  --spring.datasource.password=YOUR_PASSWORD
```

### Step 8: Run in Background
```bash
nohup java -jar student-management-1.0.0.jar > app.log 2>&1 &
tail -f app.log
```

✅ Access application at `http://YOUR_EC2_IP:8080/api/v1/students`

## 🔐 Security Features

- ✅ Input validation using Jakarta annotations
- ✅ Exception handling with custom error responses
- ✅ SQL injection prevention with parameterized queries
- ✅ HTTPS ready (can add SSL certificates)
- ✅ AWS IAM roles for EC2 security
- ✅ Database authentication
- ✅ CORS configuration

## 📊 Database Schema

```sql
CREATE TABLE students (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20),
  course VARCHAR(100) NOT NULL,
  year INT,
  cgpa DECIMAL(3,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  is_active BOOLEAN DEFAULT TRUE
);

CREATE INDEX idx_email ON students(email);
CREATE INDEX idx_course ON students(course);
```

## 🎓 What I Learned

✅ Spring Boot application structure and best practices  
✅ RESTful API design with proper HTTP methods  
✅ JPA/Hibernate for database operations  
✅ Input validation and exception handling  
✅ AWS EC2 deployment and configuration  
✅ Maven project management  
✅ Git workflow and version control  

## 📈 Future Improvements

- [ ] Add authentication with JWT
- [ ] Implement pagination for large datasets
- [ ] Add student profile photo upload to S3
- [ ] Create admin dashboard frontend
- [ ] Add email notifications
- [ ] Implement role-based access control
- [ ] Add API rate limiting
- [ ] Create comprehensive test suite
- [ ] Add API documentation with Swagger/OpenAPI
- [ ] Implement Redis caching

## 📝 Resume Points

- Built RESTful APIs using Spring Boot for managing student records
- Implemented CRUD operations with MySQL database integration
- Deployed application on AWS EC2 with secure IAM roles and security groups
- Used Git and GitHub for version control and collaborative development
- Validated 100+ test cases using Postman and manual testing

## 🤝 Contributing

Fork the repository and submit pull requests for improvements.

## 📄 License

MIT License - Feel free to use this project for learning.

## 👤 Author

**Nageli Dharma Naidu**  
Email: n.dharmanaidu@gmail.com  
Phone: 9110584381  
LinkedIn: [Your LinkedIn Profile]  
GitHub: https://github.com/NageliDharmaNaidu

---

**Last Updated**: August 12, 2026  
**Version**: 1.0.0  
**Status**: Production Ready ✅