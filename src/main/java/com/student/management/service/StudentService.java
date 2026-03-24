package com.student.management.service;

import com.student.management.dto.StudentDTO;
import com.student.management.exception.DuplicateEmailException;
import com.student.management.exception.StudentNotFoundException;
import com.student.management.model.Student;
import com.student.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final S3Service s3Service;

    // Create new student
    @Transactional
    public StudentDTO.Response createStudent(StudentDTO.Request request) {
        log.info("Creating student with email: {}", request.getEmail());

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .course(request.getCourse())
                .year(request.getYear())
                .cgpa(request.getCgpa())
                .build();

        Student saved = studentRepository.save(student);
        log.info("Student created with id: {}", saved.getId());
        return mapToResponse(saved);
    }

    // Get all students
    public List<StudentDTO.Response> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get student by ID
    public StudentDTO.Response getStudentById(Long id) {
        log.info("Fetching student with id: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapToResponse(student);
    }

    // Update student
    @Transactional
    public StudentDTO.Response updateStudent(Long id, StudentDTO.Request request) {
        log.info("Updating student with id: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // Check if email is being changed and already exists
        if (!student.getEmail().equals(request.getEmail()) &&
                studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setCourse(request.getCourse());
        student.setYear(request.getYear());
        student.setCgpa(request.getCgpa());

        Student updated = studentRepository.save(student);
        log.info("Student updated with id: {}", updated.getId());
        return mapToResponse(updated);
    }

    // Delete student
    @Transactional
    public void deleteStudent(Long id) {
        log.info("Deleting student with id: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
        log.info("Student deleted with id: {}", id);
    }

    // Search students
    public List<StudentDTO.Response> searchStudents(String keyword) {
        log.info("Searching students with keyword: {}", keyword);
        return studentRepository.searchByKeyword(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get students by course
    public List<StudentDTO.Response> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get students by year
    public List<StudentDTO.Response> getStudentsByYear(Integer year) {
        return studentRepository.findByYear(year)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Upload profile image to S3
    @Transactional
    public StudentDTO.Response uploadProfileImage(Long id, MultipartFile file) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        String imageUrl = s3Service.uploadFile(file, "students/" + id + "/profile");
        student.setProfileImageUrl(imageUrl);
        Student updated = studentRepository.save(student);
        return mapToResponse(updated);
    }

    // Map Student entity to Response DTO
    private StudentDTO.Response mapToResponse(Student student) {
        return StudentDTO.Response.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .course(student.getCourse())
                .year(student.getYear())
                .cgpa(student.getCgpa())
                .profileImageUrl(student.getProfileImageUrl())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
