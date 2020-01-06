package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.EmployeeDTO;
import com.revature.models.Reinbursement;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeServices {

	static EmployeeDAO repository = new EmployeeDAOImpl();

	public Reinbursement submitRequest() {
		return repository.submitRequest();
	}

	public static List<Reinbursement> viewPastRequests(int userId) {
		return repository.viewPastRequests(userId);
	}

	public static List<Reinbursement> viewPendingRequests(int userId) {
		return repository.viewPendingRequests(userId);
	}

	public static List<Employee> findAllEmployees() {
		return repository.findAllEmployees();
	}

	public static Employee findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public static Employee confirmLogin(String username, String password) {
		Employee e = findByUsername(username);
		if (e == null) {
			return null;
		}
		if (e.getPassword().equals(password)) {
			return e;
		} else {
			return null;
		}
	}

	public static EmployeeDTO convertToDTO(Employee e) {

		return new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getUsername(), e.getPassword());
	}
}
