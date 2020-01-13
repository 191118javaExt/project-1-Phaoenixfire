package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.EmployeeDTO;
import com.revature.models.IdTemplate;
import com.revature.models.LoginTemplate;
import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.services.EmployeeServices;
import com.revature.services.FMServices;

public class RequestHelper {

	private static Logger logger = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		System.out.println(body);
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class);
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		logger.info("User attempted to login with username " + username);
		Employee e = EmployeeServices.confirmLogin(username, password);
		if (e != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter out = res.getWriter();
			res.setContentType("application/json");
			EmployeeDTO eDTO = EmployeeServices.convertToDTO(e);

			out.println(om.writeValueAsString(eDTO));

			logger.info(username + " has successfully logged in");

		} else {
			res.setContentType("application/json");
			res.setStatus(204);
		}
	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);

		if (session != null) {
			String username = (String) session.getAttribute("username");
			logger.info(username + "has logged out");
			session.invalidate();
		}

		res.setStatus(200);
	}

	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		List<Employee> all = EmployeeServices.findAllEmployees();
		List<EmployeeDTO> allDTO = new ArrayList<>();

		for (Employee e : all) {
			allDTO.add(new EmployeeDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getUsername(), e.getPassword(),
					e.getRoleId()));
		}

		String json = om.writeValueAsString(all);

		PrintWriter out = res.getWriter();
		out.println(json);
	}

	public static void viewPastRequests(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		IdTemplate idTemp = om.readValue(body, IdTemplate.class);
		int userId = idTemp.getUserId();

		List<Reinbursement> reimb = EmployeeServices.viewPastRequests(userId);

		String json = om.writeValueAsString(reimb);

		PrintWriter out = res.getWriter();
		out.println(json);
	}

	public static void manageRequests(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		ReimbursementTemplate rt = om.readValue(body, ReimbursementTemplate.class);
		FMServices fm = new FMServices();
		fm.changeRequest(rt);
	}

	public static void createReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		ReimbursementTemplate rt = om.readValue(body, ReimbursementTemplate.class);
		EmployeeServices.createRequest(rt);
		
	}

	public static void viewAllPastRequests(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		String body = s.toString();
		FMServices fm = new FMServices();
		
		
		List<Reinbursement> reimb = fm.viewAllPastRequests();

		String json = om.writeValueAsString(reimb);

		PrintWriter out = res.getWriter();
		out.println(json);
		
	}
}
