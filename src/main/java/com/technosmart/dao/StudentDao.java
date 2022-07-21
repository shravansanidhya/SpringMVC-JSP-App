package com.technosmart.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technosmart.model.Student;

public interface StudentDao {

	public void saveStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Student getStudentById(Long id);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(Long id);

	public void placeStudent(Student student) throws JsonProcessingException;
	
}
