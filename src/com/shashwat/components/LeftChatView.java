package com.shashwat.components;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLayeredPane;

public class LeftChatView extends JLayeredPane {
	
	//reference variables
	private String txt;
	private Color bgColor;
	private Color fgColor;
	
	//constructor
	public LeftChatView(String txt) {
		this.txt = txt;
		super.setLayout(new BorderLayout());
		super.setOpaque(false);
		
		this.init();
	}
	
	private void init() {
		bgColor = new Color(238, 238, 238);
		fgColor = Color.BLACK;
		this.add(new ChatItem(txt, bgColor, fgColor, "10:30 PM"), BorderLayout.CENTER);
	}

}
