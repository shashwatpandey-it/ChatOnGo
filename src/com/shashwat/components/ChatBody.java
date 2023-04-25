package com.shashwat.components;

import java.awt.Color;
import javax.swing.JPanel;
import com.shashwat.models.RecieveMessageModel;
import com.shashwat.models.SendMessageModel;

import net.miginfocom.swing.MigLayout;

public class ChatBody extends JPanel{

	private static ChatBody instance;
	
	//constructor 
	public ChatBody() {
		instance = this;
		super.setBackground(Color.WHITE);
		super.setLayout(new MigLayout("fillx", "", "8[]8"));
		this.init();
	}
	
	public static ChatBody getChatBodyInstance() {
		return instance;
	}
	
	private void init() {

	}
	
	public void addLeftChatItem(RecieveMessageModel data) {
		this.add(new LeftChatView(data.getTextMessage(), data.getTime()), "wrap, w ::70%");	
		this.repaint();
		this.revalidate();
	}
	
	public void addRightChatItem(SendMessageModel data) {
		this.add(new RightChatView(data.getTextMessage(), data.getTime()), "wrap, align right, w ::70%");	
		this.repaint();
		this.revalidate();
	}
	
	public void addChatDate(String date) {
		this.add(new ChatDate(date), "wrap, align center");
		this.repaint();
		this.revalidate();
	}
	
	public void clearChat() {
		this.removeAll();
		this.repaint();
		this.revalidate();
	}
}
