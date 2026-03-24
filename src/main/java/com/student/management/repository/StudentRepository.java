package com.student.management.repository;

import com.student.management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find by email
    Optional<Student> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Search by name (case-insensitive)
    List<Student> findByNameContainingIgnoreCase(String name);

    // Find all students by course
    List<Student> findByCourse(String course);

    // Find all students by year
    List<Student> findByYear(Integer year);

    // Find top students by CGPA
    @Query("SELECT s FROM Student s ORDER BY s.cgpa DESC")
    List<Student> findTopStudentsByCgpa();

    // Search by name or email
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByKeyword(@Param("keyword") String keyword);

    // Count students by course
    @Query("SELECT s.course, COUNT(s) FROM Student s GROUP BY s.course")
    List<Object[]> countByCourse();
}
