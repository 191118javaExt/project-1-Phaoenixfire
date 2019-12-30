package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.services.EmployeeServices;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] username = scan.nextLine().split("\\n");
		String password = scan.nextLine();
		EmployeeServices es = new EmployeeServices();
		Employee e = new Employee();
		e = es.findByUsername(username[0]);
		System.out.println(e.getErs_username() + " " + username[0]);
		if(e.getErs_username().equals(username[0])) {
			System.out.println("you are logged in");
			es.viewPendingRequests(e.getErs_users_id());
		}
		else {
			System.out.println("please try again");
		}
	}

}
