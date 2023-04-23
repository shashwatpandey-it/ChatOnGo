package com.shashwat.models;
import org.json.JSONObject;

public class RegistryModel {
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public RegistryModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public JSONObject toJsonObject() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userName", userName);
			jsonObject.put("password", password);
			return jsonObject;
		}
		catch (Exception e) {
			return null;
		}
	}


}
