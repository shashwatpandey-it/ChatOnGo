package com.shashwat.components;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLayeredPane;

public class RightChatView extends JLayeredPane{
	
	//reference variables
	private String txt;
	private Color bgColor;
	private Color fgColor;
		
	//constructor
	public RightChatView(String txt) {
		this.txt = txt;
		super.setLayout(new BorderLayout());
		super.setOpaque(false);
			
		this.init();
	}
		
	private void init() {
		bgColor = new Color(220, 200, 255);
		fgColor = Color.BLACK;
		this.add(new ChatItem(txt, bgColor, fgColor, "10:35 PM"), BorderLayout.CENTER);
	}

}
