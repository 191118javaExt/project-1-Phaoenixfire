package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Reinbursement;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeServices {

	EmployeeDAO repository = new EmployeeDAOImpl();

	public Reinbursement submitRequest() {
		return repository.submitRequest();
	}

	public Reinbursement viewPastRequest() {
		return repository.viewPastRequest();
	}

	public Reinbursement viewPendingRequests(int ERS_USERS_ID) {
		return repository.viewPendingRequests(ERS_USERS_ID);
	}

	public List<Employee> findAllEmployees() {
		return repository.findAllEmployees();
	}

	public Employee findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Employee confirmLogin(String username, String password) {
		Employee e = findByUsername(username);
		if (e == null) {
			return null;
		}
		if (e.getErs_password().equals(password)) {
			return e;
		} else {
			return null;
		}
	}
}
