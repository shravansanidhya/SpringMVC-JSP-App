package com.technosmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technosmart.dao.StudentDao;
import com.technosmart.model.Student;
import com.technosmart.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}
	
	public Student getStudentById(Long id) {
		return studentDao.getStudentById(id);
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentDao.deleteStudent(id);
	}

	@Override
	public void placeStudent(Student student) throws JsonProcessingException {
		studentDao.placeStudent(student);
	}
	
}
