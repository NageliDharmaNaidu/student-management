# Student Management System
### Cloud-Based REST API | Spring Boot + MySQL + AWS EC2

---

## Tech Stack
- Java 17
- Spring Boot 3.2
- MySQL 8
- AWS EC2 + S3 + IAM
- Maven
- Git & GitHub

---

## Project Structure
```
src/main/java/com/student/management/
├── controller/       → REST API endpoints
├── service/          → Business logic
├── repository/       → Database queries
├── model/            → Student entity
├── dto/              → Request/Response DTOs
└── exception/        → Error handling
```

---

## REST API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST   | /api/students | Create new student |
| GET    | /api/students | Get all students |
| GET    | /api/students/{id} | Get student by ID |
| PUT    | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |
| GET    | /api/students/search?keyword=xyz | Search students |
| GET    | /api/students/course/{course} | Filter by course |
| GET    | /api/students/year/{year} | Filter by year |
| POST   | /api/students/{id}/upload | Upload profile image |

---

## Step 1 — Local Setup

### 1. Clone the repo
```bash
git clone https://github.com/yourusername/student-management.git
cd student-management
```

### 2. Setup MySQL
```sql
CREATE DATABASE student_db;
```
Run the file: `src/main/resources/schema.sql`

### 3. Update application.properties
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 4. Run the app
```bash
mvn spring-boot:run
```

App runs at: `http://localhost:8080`

---

## Step 2 — Test with Postman

### Create Student
```
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "name": "Dharma Naidu",
  "email": "dharma@example.com",
  "phone": "9110584381",
  "course": "B.Tech CSE",
  "year": 3,
  "cgpa": 8.5
}
```

### Get All Students
```
GET http://localhost:8080/api/students
```

### Search Students
```
GET http://localhost:8080/api/students/search?keyword=dharma
```

---

## Step 3 — AWS EC2 Deployment

### 1. Build JAR
```bash
mvn clean package -DskipTests
```

### 2. Launch EC2 Instance
- Go to AWS Console → EC2 → Launch Instance
- Choose: Ubuntu 22.04 LTS
- Instance type: t2.micro (free tier)
- Security Group: Allow port 22 (SSH) and 8080 (HTTP)
- Create and download your key pair (.pem file)

### 3. Connect to EC2
```bash
chmod 400 your-key.pem
ssh -i your-key.pem ubuntu@YOUR_EC2_PUBLIC_IP
```

### 4. Install Java on EC2
```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
```

### 5. Install MySQL on EC2
```bash
sudo apt install mysql-server -y
sudo mysql_secure_installation
sudo mysql -u root -p
CREATE DATABASE student_db;
```

### 6. Transfer JAR to EC2
```bash
# Run this on your LOCAL machine
scp -i your-key.pem target/student-management-1.0.0.jar ubuntu@YOUR_EC2_IP:/home/ubuntu/
```

### 7. Run the app on EC2
```bash
# On EC2
java -jar student-management-1.0.0.jar \
  --spring.datasource.password=YOUR_DB_PASSWORD \
  --server.port=8080
```

### 8. Run in background (so it keeps running after you close terminal)
```bash
nohup java -jar student-management-1.0.0.jar > app.log 2>&1 &
```

### 9. Test it
```
GET http://YOUR_EC2_PUBLIC_IP:8080/api/students
```

---

## Step 4 — AWS S3 Setup (for image upload)

### 1. Create S3 Bucket
- Go to AWS Console → S3 → Create Bucket
- Name: `student-management-bucket`
- Region: `ap-south-1`
- Uncheck "Block all public access"

### 2. Create IAM Role for EC2
- Go to IAM → Roles → Create Role
- Choose: EC2
- Attach policy: `AmazonS3FullAccess`
- Name: `ec2-s3-role`
- Attach this role to your EC2 instance

### 3. Update application.properties
```properties
aws.region=ap-south-1
aws.s3.bucket-name=student-management-bucket
```

---

## GitHub Setup
```bash
git init
git add .
git commit -m "Initial commit - Student Management System"
git remote add origin https://github.com/yourusername/student-management.git
git push -u origin main
```

---

## Resume Bullet Points (copy these)
- Built RESTful APIs for Student Management with full CRUD using Spring Boot and MySQL
- Implemented input validation, exception handling, and standardized HTTP response codes
- Integrated AWS S3 for profile image storage with IAM role-based access
- Deployed application on AWS EC2 with security groups and port configuration
- Tested all endpoints using Postman; version controlled with Git and GitHub

---

Built by Dharma Naidu | Anurag University, Hyderabad
