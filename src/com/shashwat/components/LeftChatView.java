package com.shashwat.components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalTime;

import javax.swing.JLayeredPane;

public class LeftChatView extends JLayeredPane {
	
	//reference variables
	private String txt;
	private String time;
	private Color bgColor;
	private Color fgColor;
	
	//constructor
	public LeftChatView(String txt, String time) {
		this.txt = txt;
		this.time = time;
		super.setLayout(new BorderLayout());
		super.setOpaque(false);
		
		this.init();
	}
	
	private void init() {
		bgColor = new Color(238, 238, 238);
		fgColor = Color.BLACK;
		
		String timeString = time.toString();
		this.add(new ChatItem(txt, bgColor, fgColor, timeString), BorderLayout.CENTER);
	}

}
