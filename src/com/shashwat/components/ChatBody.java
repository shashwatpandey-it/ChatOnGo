package com.shashwat.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.nio.channels.NonWritableChannelException;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.synth.SynthProgressBarUI;

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
	
		/*this.addChatDate("19/12/2022");
		this.addLeftChatItem("Hi");
		this.addLeftChatItem("wassup?");
		this.addRightChatItem("Hi..\nI am fine, what about you?");
		this.addLeftChatItem("I am good!");
		this.addChatDate("Today");
		this.addRightChatItem("hey listen!\nI need your help");
		this.addLeftChatItem("go on..");
		this.addRightChatItem("Can you tell me differnce between a JTextPane and a JTextArea...");
		this.addLeftChatItem("sure");
		this.addLeftChatItem("The main difference between JTextPane and JTextArea is that JTextPane’s resources are heavy while JTextArea has lite and limited resources. JTextPane edits the content like where to make the word bold, where to put underline but JTextArea can not do that. JTextPane is a derivative of java. swing. text. Jtext component, but JTextArea is not a JEditor component. TextPane came with the possibility of wrapping while TextArea can’t wrap. JTextPane has a heavy resource, while JTextArea is a very lightweight resource. JTextArea is very employee-friendly Text Pane is not a very friendly user. For faster implementation, people use the JTextArea tool rather than the JTextPane tool. JTextPane needs more programming language in comparison to JTextArea. JTextPane is a very flexible tool, but JTextArea is not a flexible tool at all. ");
		this.addRightChatItem("mmmm... got it");
		this.addRightChatItem("so it means,\nJTextPane is more about styling and editing. It also edits images along with the content text. On the other hand, JTextArea is all about a simple multi-liner document that is very employee-friendly because it is easy to use, and its resources are lightweight.");
		*/
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
