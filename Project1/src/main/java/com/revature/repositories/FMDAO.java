package com.revature.repositories;

import java.util.List;

import com.revature.models.Reinbursement;

public interface FMDAO {

	public boolean viewAllRequests();
	public List<Reinbursement> viewAllPastRequests();
	public boolean changeRequest(int reimb_id, int requestChange);
}
