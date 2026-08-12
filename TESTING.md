# Testing Guide

## Unit Tests

```bash
mvn test
```

## API Testing with Postman

### Create Student
```
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "course": "B.Tech CSE",
  "year": 2
}
```

### Get All Students
```
GET http://localhost:8080/api/students
```

### Update Student
```
PUT http://localhost:8080/api/students/1
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
```

### Delete Student
```
DELETE http://localhost:8080/api/students/1
```