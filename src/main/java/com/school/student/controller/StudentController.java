package com.school.student.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.student.entity.Student;
import com.school.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        log.info("Adding new student: {}", student);
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("Fetching student with ID: {}", id);
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Student with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }
    
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("Fetching all students");
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
    	log.info("Fetching students by name");
        List<Student> students = studentService.getStudentsByName(name);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/class/{className}")
    public ResponseEntity<List<Student>> getStudentsByClassName(@PathVariable String className) {
    	log.info("Fetching students by name");
        List<Student> students = studentService.getStudentsByClassName(className);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
    	log.info("Updating student with ID: {}", id);
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    	 log.info("Deleting student with ID: {}", id);
         if (studentService.deleteStudent(id)) {
             return ResponseEntity.noContent().build();
         } else {
             log.warn("Student with ID: {} not found", id);
             return ResponseEntity.notFound().build();
         }
    }
}
