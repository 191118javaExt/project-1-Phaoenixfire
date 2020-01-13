package com.revature.models;

public class ReimbursementTemplate {
	
	private int userId;
	private double amount;
	private String description;
	private int type;
	private String receiptDataUrl;
	
	
	public String getReceiptDataUrl() {
		return receiptDataUrl;
	}


	public void setReceiptDataUrl(String receiptDataUrl) {
		this.receiptDataUrl = receiptDataUrl;
	}


	public ReimbursementTemplate(int userId, double amount, String description, int type, String receiptDataUrl) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.receiptDataUrl = receiptDataUrl;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + type;
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
		ReimbursementTemplate other = (ReimbursementTemplate) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (type != other.type)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReimbursementTemplate [userId=" + userId + ", amount=" + amount + ", description=" + description
				+ ", type=" + type + "]";
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public ReimbursementTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReimbursementTemplate(int userId, double amount, String description, int type) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.type = type;
	}

	

}
