package com.shashwat.models;

public class LoginStatusModel {
	
	private boolean status = false;
	private String message;
	private int userId;
	private String userName;
	
	public LoginStatusModel() {
		
	}
	
	public LoginStatusModel(boolean status, String message, int userId, String userName) {
		this.status = status;
		this.message = message;
		this.userId = userId;
		this.userName = userName;
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
	
	public String getUserName() {
		return userName;
	}
}

