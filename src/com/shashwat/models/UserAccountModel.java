package com.shashwat.models;

public class UserAccountModel {
	
	private int userId;
	private String name;
	private boolean status;
	
	public UserAccountModel() {
		
	}
	
	public UserAccountModel(int userId, String name, Boolean status) {
		this.userId = userId;
		this.name = name;
		this.status = status;
		
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
