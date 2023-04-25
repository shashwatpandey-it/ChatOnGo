package com.shashwat.models;

import org.json.JSONObject;

public class SendMessageModel implements ChatModel {

	private int fromUserID;
	private int toUserId;
	private String textMessage;
	private String time;
	
	public int getFromUserID() {
		return fromUserID;
	}
	public int getToUserId() {
		return toUserId;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setFromUserID(int fromUserID) {
		this.fromUserID = fromUserID;
	}
	
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public SendMessageModel() {
		
	}
	
	public SendMessageModel(int from, int to, String textMessage) {
		this.fromUserID = from;
		this.toUserId = to;
		this.textMessage = textMessage;
	}
	
	public JSONObject toJsonObject() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("fromUserID", fromUserID);
			jsonObject.put("toUserId", toUserId);
			jsonObject.put("textMessage", textMessage);
			return jsonObject;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
