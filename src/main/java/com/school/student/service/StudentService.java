package com.school.student.service;

import java.util.List;
import java.util.Optional;

import com.school.student.entity.Student;

public interface StudentService {
	
	Student addStudent(Student student);
	
	Optional<Student> getStudentById(Long id);
	
	List<Student> getStudentsByName(String name);
	
	Student updateStudent(Long id, Student studentDetails);
	
	boolean deleteStudent(Long id);

	List<Student> getStudentsByClassName(String className);

	List<Student> getAllStudents();
}
