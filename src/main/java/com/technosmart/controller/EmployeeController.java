package com.technosmart.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.technosmart.model.Employee;
import com.technosmart.model.Student;
import com.technosmart.service.EmployeeService;
import com.technosmart.util.KafkaConsumerUtil;

@Controller
@RequestMapping
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("addEmployee")
	public String addEmployee() {
		
		return "AddEmployee";
	}
	
	public String fetchEmployee() throws JsonParseException, JsonMappingException, IOException {
		
		KafkaConsumerUtil kafkaConsumerUtil = new KafkaConsumerUtil();
		Student student = kafkaConsumerUtil.recieveMessage();
		if(student!=null) {
			Employee employee = new Employee();
			employee.setName(student.getName());
			employee.setEmail(student.getEmail());
			employee.setCompany("GlocalMind");
			employee.setDesignation("Software Engineer");
			employee.setAddress("Bangalore");
			employeeService.saveEmployee(employee);
		}
		return "redirect:/employeeReport";
	}
	
	@PostMapping("/insertEmployee")
	public String insertEmployee(@ModelAttribute("insertEmployee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employeeReport";
	}
	
	@GetMapping("/employeeReport")
	public String getAllEmployees(Model model) throws JsonParseException, JsonMappingException, IOException {
		fetchEmployee();
		model.addAttribute("employee", employeeService.getAllEmployees());
		model.addAttribute("title", "Employee Report");
		
		return "EmployeeReport";
	}
	
	@GetMapping("/editEmployee/{id}")
	public String loadEditEmployeeForm(@PathVariable Long id, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		model.addAttribute("title", "Edit Employee");
		
		return "EditEmployee";
	}
	
	@PostMapping("/editEmployee/updateEmployee")
	public String updateEmployee(@ModelAttribute("updateEmployee") Employee employee) {
		
		employeeService.updateEmployee(employee);
		return "redirect:/employeeReport";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		
		employeeService.deleteEmployee(id);
		return "redirect:/employeeReport";
	}
	
}