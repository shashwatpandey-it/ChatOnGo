package com.shashwat.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.shashwat.components.ChatBody;
import com.shashwat.models.ChatModel;
import com.shashwat.models.RecieveMessageModel;
import com.shashwat.models.SendMessageModel;

import io.socket.client.Ack;

public class ChatService {
	
	private HashMap<Integer,List<ChatModel>> conversations; 
	private static ChatService instance;
	private int currentConversation;
	
	private ChatService() {
		this.conversations = new HashMap<>();
	}
	
	public static ChatService getChatService() {
		if(instance == null) {
			instance = new ChatService();
		}
		return instance;
	}
	
	public void switchConversation(int id) {
		this.currentConversation = id; 
		ChatBody.getChatBodyInstance().clearChat();
		if(conversations.get(currentConversation) == null) return;
		List<ChatModel> chatLoaderList = conversations.get(currentConversation);
		chatLoaderList.stream().forEach(chat -> {
			if(chat instanceof RecieveMessageModel) {
				ChatBody.getChatBodyInstance().addLeftChatItem((RecieveMessageModel)chat);
			}
			else if(chat instanceof SendMessageModel) {
				ChatBody.getChatBodyInstance().addRightChatItem((SendMessageModel)chat);
			}
		});
		
	}
	
	public void receiveMessage(int fromId, ChatModel recieveMessageModel) {
		if(!conversations.containsKey(fromId)) {
			conversations.put(fromId, new ArrayList<>());
		}
		conversations.get(fromId).add(recieveMessageModel);
		if(currentConversation == fromId) {
			ChatBody.getChatBodyInstance().addLeftChatItem((RecieveMessageModel)recieveMessageModel);
		}
	}
	 
	public void sendMessage(int toId, ChatModel sendMessageModel) {
		if(!conversations.containsKey(toId)) {
			conversations.put(toId, new ArrayList<>());
		}
		conversations.get(toId).add(sendMessageModel);
		send((SendMessageModel)sendMessageModel);
		ChatBody.getChatBodyInstance().addRightChatItem((SendMessageModel)sendMessageModel);
	}

	private void send(SendMessageModel message) {
		System.out.println(new Gson().toJson(message, SendMessageModel.class));
		ClientService.getClientService().getClient().emit("sendToUser", message.toJsonObject(), new Ack() {
			
			@Override
			public void call(Object... args) {
				
			}
		});
	}
}
