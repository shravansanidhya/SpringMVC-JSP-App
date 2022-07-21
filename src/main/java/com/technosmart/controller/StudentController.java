package com.technosmart.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technosmart.model.Student;
import com.technosmart.service.StudentService;

@Controller
@RequestMapping
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/studentReport")
	public String loadStudentReport(Model model) {
		model.addAttribute("title", "Student Report");
		return "StudentReport";
	}
	
	@GetMapping("/studentReport/time")
	@ResponseBody
	public String getTime() {
		Date date = new Date();
		return String.valueOf(date);
	}
	
	@PostMapping("/insertStudent")
	@ResponseBody
	public String saveStudent(@ModelAttribute("insertStudent") Student student) {
		studentService.saveStudent(student);
		return "Student Saved Successfully";
	}
	
	@GetMapping("/getAllStudents")
	@ResponseBody
	public List<Student> getAllStudents() {
		 List<Student> students = studentService.getAllStudents();
		 return students;
	}
	
	@GetMapping("/getStudentById/{id}")
	@ResponseBody
	public Student getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		return student;
	}
	
	@PostMapping("/updateStudent")
	@ResponseBody
	public String updateStudent(@ModelAttribute("updateStudent") Student student) {
		
		studentService.updateStudent(student);
		return "Student Updated Succesfully";
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	@ResponseBody
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "Student Deleted Successfully";
	}
	
	@PostMapping("/placeStudent")
	@ResponseBody
	public String placeStudent(@ModelAttribute("placeStudent") Student student) throws JsonProcessingException {
		
		studentService.placeStudent(student);
		return "Student Placed Succesfully";
	}
	
}
