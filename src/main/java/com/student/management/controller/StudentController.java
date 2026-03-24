package com.student.management.controller;

import com.student.management.dto.ApiResponse;
import com.student.management.dto.StudentDTO;
import com.student.management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    // POST /api/students - Create new student
    @PostMapping
    public ResponseEntity<ApiResponse<StudentDTO.Response>> createStudent(
            @Valid @RequestBody StudentDTO.Request request) {
        log.info("POST /api/students - Creating student");
        StudentDTO.Response response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Student created successfully", response));
    }

    // GET /api/students - Get all students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDTO.Response>>> getAllStudents() {
        log.info("GET /api/students - Fetching all students");
        List<StudentDTO.Response> students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.success("Students fetched successfully", students));
    }

    // GET /api/students/{id} - Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO.Response>> getStudentById(@PathVariable Long id) {
        log.info("GET /api/students/{} - Fetching student", id);
        StudentDTO.Response student = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.success("Student fetched successfully", student));
    }

    // PUT /api/students/{id} - Update student
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO.Response>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO.Request request) {
        log.info("PUT /api/students/{} - Updating student", id);
        StudentDTO.Response updated = studentService.updateStudent(id, request);
        return ResponseEntity.ok(ApiResponse.success("Student updated successfully", updated));
    }

    // DELETE /api/students/{id} - Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        log.info("DELETE /api/students/{} - Deleting student", id);
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success("Student deleted successfully", null));
    }

    // GET /api/students/search?keyword=xyz - Search students
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentDTO.Response>>> searchStudents(
            @RequestParam String keyword) {
        log.info("GET /api/students/search?keyword={}", keyword);
        List<StudentDTO.Response> students = studentService.searchStudents(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search results", students));
    }

    // GET /api/students/course/{course} - Get by course
    @GetMapping("/course/{course}")
    public ResponseEntity<ApiResponse<List<StudentDTO.Response>>> getStudentsByCourse(
            @PathVariable String course) {
        List<StudentDTO.Response> students = studentService.getStudentsByCourse(course);
        return ResponseEntity.ok(ApiResponse.success("Students by course", students));
    }

    // GET /api/students/year/{year} - Get by year
    @GetMapping("/year/{year}")
    public ResponseEntity<ApiResponse<List<StudentDTO.Response>>> getStudentsByYear(
            @PathVariable Integer year) {
        List<StudentDTO.Response> students = studentService.getStudentsByYear(year);
        return ResponseEntity.ok(ApiResponse.success("Students by year", students));
    }

    // POST /api/students/{id}/upload - Upload profile image to S3
    @PostMapping("/{id}/upload")
    public ResponseEntity<ApiResponse<StudentDTO.Response>> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        log.info("POST /api/students/{}/upload - Uploading image", id);
        StudentDTO.Response updated = studentService.uploadProfileImage(id, file);
        return ResponseEntity.ok(ApiResponse.success("Profile image uploaded successfully", updated));
    }
}
