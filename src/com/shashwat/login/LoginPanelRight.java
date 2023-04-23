package com.shashwat.login;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.shashwat.home.ChatPage;
import com.shashwat.models.LoginStatusModel;
import com.shashwat.models.RegistryModel;
import com.shashwat.services.ClientService;

import io.socket.client.Ack;


public class LoginPanelRight extends JPanel{
	
	//reference variables
	private JLabel loginLabel, userName, userPassword, lineLabel, messageLabel;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton;
	private Font headingFont, labelFont, fieldFont, buttonFont;
	private Color greenColor, blurBlue;
	private LoginPage reference;
	
	//constructor
	public LoginPanelRight(LoginPage rf) {
		this.reference = rf;
		super.setLayout(null);
		
		this.init();
	}
	
	private void init() {
		
		headingFont = new Font("Rockwell", Font.BOLD, 36);
		labelFont = new Font("Monospaced", Font.PLAIN, 18);
		fieldFont = new Font("Monospaced", Font.BOLD, 20);
		buttonFont = new Font("helvetica ", Font.BOLD, 24);
		greenColor = new Color(15,240,50);
		blurBlue = new Color(205,230,250);
		
		loginLabel = new JLabel("Login");
		loginLabel.setFocusable(false);
		loginLabel.setFont(headingFont);
		loginLabel.setOpaque(false);
		loginLabel.setHorizontalAlignment(JLabel.CENTER);
		loginLabel.setForeground(Color.white);
		loginLabel.setBounds(0,40,400,50);
		
		userName = new JLabel("Username");
		userName.setFocusable(false);
		userName.setFont(labelFont);
		userName.setOpaque(false);
		userName.setForeground(Color.white);
		userName.setBounds(40,130,320,30);
		
		nameField = new JTextField();
		nameField.setFont(fieldFont);
		nameField.setForeground(Color.black);
		nameField.setBorder(BorderFactory.createEmptyBorder(0,8,0,8));
		nameField.setBounds(40,170,320,40);
		
		userPassword = new JLabel("Password");
		userPassword.setFocusable(false);
		userPassword.setFont(labelFont);
		userPassword.setOpaque(false);
		userPassword.setForeground(Color.white);
		userPassword.setBounds(40,230,320,30);
		
		passwordField = new JPasswordField();
		passwordField.setFont(fieldFont);
		passwordField.setForeground(Color.black);
		passwordField.setBorder(BorderFactory.createEmptyBorder(0,8,0,8));
		passwordField.setBounds(40,270,320,40);
		
		loginButton =new JButton("Login");
		loginButton.setBackground(greenColor);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(buttonFont);
		loginButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4,new Color(110,255,130)));
		loginButton.setFocusable(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setBounds(120,350,160,50);
		loginButton.addActionListener(new login());   
		
		lineLabel = new JLabel();
		lineLabel.setFocusable(false);
		lineLabel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		lineLabel.setBounds(40,420,320,2);
		
		registerButton =new JButton("Register");
		registerButton.setBackground(blurBlue);
		registerButton.setOpaque(false);
		registerButton.setForeground(Color.LIGHT_GRAY);
		registerButton.setFont(buttonFont);
		registerButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4,blurBlue));
		registerButton.setFocusable(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setBounds(120,440,160,50);
		registerButton.addActionListener(new register());		
		
		messageLabel = new JLabel();
		messageLabel.setFocusable(false);
		messageLabel.setFont(buttonFont);
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setOpaque(false);
		messageLabel.setForeground(new Color(245,50,50));
		messageLabel.setBounds(40,520,320,30);
		
		this.add(loginLabel);
		this.add(userName);
		this.add(nameField);
		this.add(userPassword);
		this.add(passwordField);
		this.add(loginButton);
		this.add(lineLabel);
		this.add(registerButton);
		this.add(messageLabel);
	}
	
	private void proceedLogin(int userId) {
		reference.dispose();
		try {
			Thread.sleep(200);
			new ChatPage(userId);
		}
		catch (InterruptedException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	// inner class for loginButton
	class login implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String userName = nameField.getText().trim();
			String password = String.valueOf(passwordField.getPassword());
			
			if(userName.equals("")) {
				nameField.grabFocus();
			}
			else if(password.equals("")) {
				passwordField.grabFocus();
			}
			else {
				RegistryModel data = new RegistryModel(userName, password);
				ClientService.getClientService().getClient().emit("login", data.toJsonObject(), new Ack() {
					
					@Override
					public void call(Object... objects) {
						
						if (objects.length >0) {
							LoginStatusModel statusModel = new Gson().fromJson(objects[0].toString(), LoginStatusModel.class);
							System.out.println(objects[0].toString());
							System.out.println(statusModel.getMessage());
							System.out.println(statusModel.getUserId());
							
							if(statusModel.isStatus()) {
								ClientService.getClientService().setUserId(statusModel.getUserId());
								proceedLogin(statusModel.getUserId());
							}
							else if(statusModel.getMessage().equals("Invalid")) {
								messageLabel.setText("Invalid user credentials");
							}
							else {
								messageLabel.setText("Server Error");
							}

						}
						
					}
				});
			}
			
		}
		
	}
	
	// inner class for registerButton
	class register implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			reference.dispose();
			try {
				Thread.sleep(200);
				new LoginPage(true);
			} catch (InterruptedException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
			
		}
	}

}
