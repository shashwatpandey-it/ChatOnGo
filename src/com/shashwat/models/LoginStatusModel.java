package com.shashwat.models;

public class LoginStatusModel {
	
	private boolean status = false;
	private String message;
	private int userId;
	
	public LoginStatusModel() {
		
	}
	
	public LoginStatusModel(boolean status, String message, int userId) {
		this.status = status;
		this.message = message;
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public int getUserId() {
		return userId;
	}
}

