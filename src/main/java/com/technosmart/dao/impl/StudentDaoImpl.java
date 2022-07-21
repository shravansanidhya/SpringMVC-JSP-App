package com.technosmart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technosmart.dao.StudentDao;
import com.technosmart.model.Student;
import com.technosmart.util.KakfaProducerUtil;
@Component
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private HibernateTemplate template;
	
	@Transactional
	@Override
	public void saveStudent(Student student) {

		template.save(student);
	}
	
	@Override
	public List<Student> getAllStudents() {
		
		return template.loadAll(Student.class);
	}
	
	public Student getStudentById(Long id) {
		return template.get(Student.class, id);
	}

	@Transactional
	@Override
	public void updateStudent(Student student) {
		template.update(student);
	}

	@Transactional
	@Override
	public void deleteStudent(Long id) {
		
		template.delete(template.get(Student.class, id));
	}

	@Override
	public void placeStudent(Student student) throws JsonProcessingException {

		KakfaProducerUtil kafkaProducerUtil = new KakfaProducerUtil();
		kafkaProducerUtil.sendMessage("student", student);
		
	}

}
