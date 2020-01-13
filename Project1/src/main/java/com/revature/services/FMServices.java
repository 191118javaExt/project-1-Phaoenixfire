package com.revature.services;

import java.util.List;

import com.revature.models.ReimbursementTemplate;
import com.revature.models.Reinbursement;
import com.revature.repositories.FMDAO;
import com.revature.repositories.FMDAOImpl;

public class FMServices {

	FMDAO repository = new FMDAOImpl();
	
	
	public List<Reinbursement> viewAllPastRequests() {
		return repository.viewAllPastRequests();
	}
	public boolean changeRequest(ReimbursementTemplate rt) {
		return repository.changeRequest(rt);
	}

	
}
