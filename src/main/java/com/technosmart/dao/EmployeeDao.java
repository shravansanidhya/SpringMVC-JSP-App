package com.technosmart.dao;

import java.util.List;

import com.technosmart.model.Employee;

public interface EmployeeDao {

	public void saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Long id);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Long id);
	
}
