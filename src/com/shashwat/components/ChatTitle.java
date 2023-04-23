package com.shashwat.components;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ChatTitle extends JPanel {
	//reference variables
	private JLayeredPane layer;
	private JLabel nameLabel;
	private JLabel statusLabel;
	private Font nameFont;
	private Font statusFont;

	//constructor
	public ChatTitle() {
		super.setLayout(new MigLayout("wrap", "8[fill, 100%]8", "2[]2[]2"));
		super.setOpaque(false);
		this.init();
	}
	
	private void init() {
		nameFont = new Font("Verdana", Font.BOLD, 18);
		statusFont = new Font("Verdana", Font.PLAIN, 14);
		
		nameLabel = new JLabel("Name");
		nameLabel.setFont(nameFont);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setBorder(BorderFactory.createEmptyBorder(1,8,1,8));
		
		statusLabel = new JLabel("Active");
		statusLabel.setFont(statusFont);
		statusLabel.setForeground(new Color(0, 185, 2));
		statusLabel.setBorder(BorderFactory.createEmptyBorder(1,8,1,8));
		
		this.add(nameLabel);
		this.add(statusLabel);
		
	}
	
	public void setCuurentPerson(String currentPerson) {
		nameLabel.setText(currentPerson);
	}
	
}
