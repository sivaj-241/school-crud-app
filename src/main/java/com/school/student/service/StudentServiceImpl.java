package com.school.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.student.entity.Student;
import com.school.student.exception.ResourceNotFoundException;
import com.school.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
	
	@Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
    	log.info("Saving student: {}", student);
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
    	log.info("Retrieving student with ID: {}", id);
        return studentRepository.findById(id);
    }

    public List<Student> getStudentsByName(String name) {
    	log.info("Retrieving students with name: {}", name);
        return studentRepository.findByName(name);
    }
    
    public List<Student> getStudentsByClassName(String className) {
    	log.info("Retrieving students in class: {}", className);
        return studentRepository.findByClassName(className);
    }
    
    public List<Student> getAllStudents() {
        log.info("Retrieving all students");
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            log.info("Updating student: {}", student);
            student.setName(studentDetails.getName());
            student.setDateOfBirth(studentDetails.getDateOfBirth());
            student.setJoiningDate(studentDetails.getJoiningDate());
            student.setClassName(studentDetails.getClassName());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public boolean deleteStudent(Long id) {
    	if (studentRepository.existsById(id)) {
            log.info("Deleting student with ID: {}", id);
            studentRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
    }
}
