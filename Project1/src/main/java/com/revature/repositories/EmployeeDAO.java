package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reinbursement;

//This is the employeeDAO in project 1
public interface EmployeeDAO {
	
		public Reinbursement submitRequest();
		public Reinbursement viewPastRequest();
		public Reinbursement viewPendingRequests(int ERS_USERS_ID);
		public List<Employee> findAllEmployees();
		public Employee findByUsername(String username);
}
