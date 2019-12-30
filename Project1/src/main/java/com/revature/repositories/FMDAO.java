package com.revature.repositories;

public interface FMDAO {

	public boolean viewAllRequests();
	public boolean viewAllPastRequests();
	public boolean approveRequest();
	public boolean denyRequest();
}
