package com.shashwat.home;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

class RightPanel extends JPanel {
	
	private JPanel lowerPanel;
	private JPanel upperPanel;
	private JLabel avatarLabel;
	private JLabel userNameLabel;
	private JButton logoutButton;
	private JLabel titleLabel;
	private JLabel lineLabel;
	private JLabel versionLabel;
	private JLabel loggedInLabel;
	private String userName;
	private Color themeColor;
	private Font titleFont;
	private Font userNameFont;
	private String loginTime;
	
	public RightPanel(String userName, String loginTime) {
		this.userName = userName;
		this.loginTime = loginTime;
		super.setBackground(new Color(246, 246, 246));
		super.setLayout(new MigLayout("wrap, filly", "10[fill, 100%]10", ""));
		
		this.init();
	}
	
	// initializer method
	private void init() {
		
		themeColor = new Color(11,105,199);
		titleFont = new Font("Rockwell", Font.BOLD, 36);
		userNameFont = new Font("helvetica", Font.BOLD, 30);
		
		//handling upper panel
		upperPanel = new JPanel();
		upperPanel.setLayout(new MigLayout("wrap", "0[fill, 100%]0", "14[fill]14"));
		upperPanel.setOpaque(false);
		
		// handling avatar label
		avatarLabel = new JLabel();
		avatarLabel.setIcon(new ImageIcon("resources/login-user.png"));
		avatarLabel.setHorizontalAlignment(JLabel.CENTER);
		avatarLabel.setVerticalAlignment(JLabel.CENTER);
				
		// handling userNameLabel
		userNameLabel = new JLabel(this.userName);
		userNameLabel.setHorizontalAlignment(JLabel.CENTER);
		userNameLabel.setFocusable(false);
		userNameLabel.setFont(userNameFont);
				
		//handling loggedInLabel
		loggedInLabel = new JLabel("Logged in at : "+loginTime);
		loggedInLabel.setFocusable(false);
		loggedInLabel.setHorizontalAlignment(JLabel.CENTER);
		loggedInLabel.setForeground(Color.RED);
		loggedInLabel.setFont(new Font("SansSerif",Font.BOLD,16));
		
		upperPanel.add(avatarLabel);
		upperPanel.add(userNameLabel);
		upperPanel.add(loggedInLabel);
		
		//handling lower panel
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new MigLayout("wrap", "0[fill, 100%]0", "0[fill]0"));
		lowerPanel.setOpaque(false);
		
		//handling titleLabel
		titleLabel = new JLabel("ChatOnGo");
		titleLabel.setOpaque(false);
		titleLabel.setFocusable(false);
		titleLabel.setFont(titleFont);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(themeColor);
				
		//handling lineLabel
		lineLabel = new JLabel();
		lineLabel.setFocusable(false);
		lineLabel.setBorder(BorderFactory.createLineBorder(themeColor,3));
				
		//handling versionLabel
		versionLabel = new JLabel("version 1.0");
		versionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		versionLabel.setFocusable(false);
		versionLabel.setForeground(Color.GRAY);
		versionLabel.setHorizontalAlignment(JLabel.CENTER);

		lowerPanel.add(titleLabel);
		lowerPanel.add(lineLabel);
		lowerPanel.add(versionLabel);
		//this.add(avatarLabel);
		//this.add(userNameLabel);
		//this.add(logoutButton);
		this.add(upperPanel);
		this.add(lowerPanel);
		
	}

}
