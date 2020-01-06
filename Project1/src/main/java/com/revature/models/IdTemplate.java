package com.revature.models;

import java.io.Serializable;

public class IdTemplate implements Serializable {

	private static final long serialVersionUID = 2839997849592320131L;

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdTemplate other = (IdTemplate) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdTemplate [userId=" + userId + "]";
	}

	public IdTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdTemplate(int userId) {
		super();
		this.userId = userId;
	}
	
	
	
}
