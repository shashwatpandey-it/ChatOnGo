package com.shashwat.components;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

public class ChatItem extends JLayeredPane{
	
	//reference variables
	private JTextPane text;
	private String txt, time;
	private Color backgroundAndBorderColor;
	private Color textColor;
	private JLayeredPane timeLayerLabel;
	
	//constructor
	public ChatItem(String txt, Color bgColor, Color fgColor, String time) {
		this.txt = txt;
		this.time = time;
		this.backgroundAndBorderColor = bgColor;
		this.textColor = fgColor;
		super.setForeground(bgColor);			//this is to set the color of border same as to background color, while border required for round corners.
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		super.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));

		this.init();
	}
	
	private void init() {
		
		text = new JTextPane();
		text.setEditable(false);
		text.setForeground(textColor);
		text.setBackground(backgroundAndBorderColor);		//this sets the background color of the chat item.
		text.setSelectionColor(new Color(255, 255, 170));
		text.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		text.setFont(new Font("Typewriter", Font.PLAIN, 18));
		
		timeLayerLabel = new JLayeredPane();
		timeLayerLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel timeLabel = new JLabel(time);
		timeLabel.setFocusable(false);
		timeLabel.setFont(new Font("serif", Font.PLAIN, 12));
		timeLabel.setForeground(Color.GRAY);
		timeLayerLabel.add(timeLabel);
		
		this.add(text);
		this.add(timeLayerLabel);
		this.setText();
		
	}
	
	private void setText() {
		text.setText(txt);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setBackground(getBackground());
		graphics2d.fillRoundRect(0,0,getWidth(),getHeight(),18,18);
		super.paintComponent(g);
		
	}

}
