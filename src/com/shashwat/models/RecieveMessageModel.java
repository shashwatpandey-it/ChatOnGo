package com.shashwat.models;

public class RecieveMessageModel {
	
	private int fromUserId;
	private String textMessage;
	
	public int getFromUserId() {
		return fromUserId;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public RecieveMessageModel() {
		
	}
	
	public RecieveMessageModel(int from, String textMessage) {
		this.fromUserId = from;
		this.textMessage = textMessage;
	}

}
