package com.shashwat.components;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class ChatDate extends JLayeredPane {
	//reference variables
	private String date;
	private JLabel dateLabel;
	
	//constructor
	public ChatDate(String date) {
		super.setLayout(new FlowLayout(FlowLayout.CENTER));
		super.setOpaque(false);;
		this.date = date;
		this.init();
	}
	
	private void init() {
		dateLabel = new JLabel(date);
		dateLabel.setFocusable(false);
		dateLabel.setFont(new Font("serif", Font.PLAIN, 18));
		dateLabel.setForeground(Color.GRAY);
		dateLabel.setBorder(BorderFactory.createEmptyBorder(4,0,4,0));
		
		this.add(dateLabel);
		
	}

}
