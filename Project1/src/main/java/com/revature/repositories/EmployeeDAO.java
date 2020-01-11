package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;

//This is the employeeDAO in project 1
public interface EmployeeDAO {
	
		public boolean submitRequest(ReimbursementTemplate rt);
		public List<Reinbursement> viewPastRequests(int userId);
		public List<Reinbursement> viewPendingRequests(int userId);
		public List<Employee> findAllEmployees();
		public Employee findByUsername(String username);
}
