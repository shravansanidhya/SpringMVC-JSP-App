package com.technosmart.service;

import java.util.List;

import com.technosmart.model.Employee;

public interface EmployeeService {

	public void saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Long id);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Long id);
	
}
