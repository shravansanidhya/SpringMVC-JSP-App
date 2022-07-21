package com.technosmart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technosmart.dao.EmployeeDao;
import com.technosmart.model.Employee;
import com.technosmart.util.SessionUtil;
@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionUtil sessionUtil;
	
	@Transactional
	@Override
	public void saveEmployee(Employee employee) {

		sessionUtil.getSession().persist(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		@SuppressWarnings("unchecked")
		List<Employee> empList = sessionUtil.getSession().createQuery("From Employee").list();
		
		return empList;
	}
	
	public Employee getEmployeeById(Long id) {
		
		Employee emp = sessionUtil.getSession().get(Employee.class, id);
		
		return emp;
	}

	@Transactional
	@Override
	public void updateEmployee(Employee employee) {
		
		sessionUtil.getSession().update(employee);
	}

	@Transactional
	@Override
	public void deleteEmployee(Long id) {
		
		Session session = sessionUtil.getSession();
		Employee emp = session.get(Employee.class, id);
		
		if(emp!=null) {
			session.delete(emp);
		}
	}

}
