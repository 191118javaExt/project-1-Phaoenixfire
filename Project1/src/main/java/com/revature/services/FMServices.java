package com.revature.services;

import com.revature.repositories.FMDAO;
import com.revature.repositories.FMDAOImpl;

public class FMServices {

	FMDAO repository = new FMDAOImpl();
	
	public boolean viewAllRequests() {
		return repository.viewAllRequests();
	}
	public boolean viewAllPastRequests() {
		return repository.viewAllPastRequests();
	}
	public boolean approveRequest() {
		return repository.approveRequest();
	}
	public boolean denyRequest() {
		return repository.denyRequest();
	}
	
}
