package com.revature.services;

import java.util.List;

import com.revature.models.Reinbursement;
import com.revature.repositories.FMDAO;
import com.revature.repositories.FMDAOImpl;

public class FMServices {

	FMDAO repository = new FMDAOImpl();
	
	public boolean viewAllRequests() {
		return repository.viewAllRequests();
	}
	public List<Reinbursement> viewAllPastRequests() {
		return repository.viewAllPastRequests();
	}
	public boolean changeRequest(int reimb_id, int requestChange) {
		return repository.changeRequest(reimb_id, requestChange);
	}

	
}
