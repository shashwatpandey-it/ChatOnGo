package com.shashwat.login;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.google.gson.Gson;
import com.shashwat.models.RegistryModel;
import com.shashwat.models.RegistryStatusModel;
import com.shashwat.services.ClientService;

import io.socket.client.Ack;

public class RegisterPanelRight extends JPanel implements ActionListener{
	
	//reference variables
	private JLabel registerLabel, userName, password, confirmPassword , lineLabel, messageLabel;
	private JTextField nameField;
	private JPasswordField passwordField, confirmPasswordField;
	private JButton registerButton;
	private Font headingFont, labelFont, fieldFont, buttonFont;
	private Color greenColor;
	private LoginPage reference;
	
	//constructor
	public RegisterPanelRight(LoginPage rf) {
		this.reference = rf;
		super.setLayout(null);
		
		this.init();
	}
	
	private void init() {
		
		headingFont = new Font("Rockwell", Font.BOLD, 36);
		labelFont = new Font("Monospaced", Font.PLAIN, 18);
		fieldFont = new Font("Monospaced", Font.BOLD, 20);
		buttonFont = new Font("helvetica", Font.BOLD, 24);
		greenColor = new Color(15,240,50);
		
		registerLabel = new JLabel("Register");
		registerLabel.setFocusable(false);
		registerLabel.setFont(headingFont);
		registerLabel.setOpaque(false);
		registerLabel.setHorizontalAlignment(JLabel.CENTER);
		registerLabel.setForeground(Color.white);
		registerLabel.setBounds(0,40,400,50);
		
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
		
		password = new JLabel("Enter Password");
		password.setFocusable(false);
		password.setFont(labelFont);
		password.setOpaque(false);
		password.setForeground(Color.white);
		password.setBounds(40,230,320,30);
		
		passwordField = new JPasswordField();
		passwordField.setFont(fieldFont);
		passwordField.setForeground(Color.black);
		passwordField.setBorder(BorderFactory.createEmptyBorder(0,8,0,8));
		passwordField.setBounds(40,270,320,40);
		
		confirmPassword = new JLabel("Confirm Password");
		confirmPassword.setFocusable(false);
		confirmPassword.setFont(labelFont);
		confirmPassword.setOpaque(false);
		confirmPassword.setForeground(Color.white);
		confirmPassword.setBounds(40,330,320,30);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(fieldFont);
		confirmPasswordField.setForeground(Color.black);
		confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(0,8,0,8));
		confirmPasswordField.setBounds(40,370,320,40);
		
		lineLabel = new JLabel();
		lineLabel.setFocusable(false);
		lineLabel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		lineLabel.setBounds(40,440,320,2);
		
		registerButton =new JButton("Register");
		registerButton.setBackground(greenColor);
		registerButton.setForeground(Color.WHITE);
		registerButton.setFont(buttonFont);
		registerButton.setBorder(BorderFactory.createMatteBorder(4,4,4,4,new Color(110,255,130)));
		registerButton.setFocusable(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setBounds(120,470,160,50);
		registerButton.addActionListener(this);
		
		messageLabel = new JLabel();
		messageLabel.setFocusable(false);
		messageLabel.setFont(buttonFont);
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setOpaque(false);
		messageLabel.setForeground(new Color(245,50,50));
		messageLabel.setBounds(40,540,320,30);
		
		this.add(registerLabel);
		this.add(userName);
		this.add(nameField);
		this.add(password);
		this.add(passwordField);
		this.add(confirmPassword);
		this.add(confirmPasswordField);
		this.add(lineLabel);
		this.add(registerButton);
		this.add(messageLabel);
	}
	
	private void switchToLogin() {
		reference.dispose();
		try {	
			Thread.sleep(200);
			new LoginPage(false);
		} catch (InterruptedException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String userNameString = nameField.getText().trim();
		String passwordString = String.valueOf(passwordField.getPassword());
		String confirmPasswordString = String.valueOf(confirmPasswordField.getPassword());

		if(userNameString.equals("")) {
			nameField.grabFocus();
		}
		else if(passwordString.equals("")) {
			passwordField.grabFocus();
		}
		else if (confirmPasswordString.equals("")) {
			confirmPasswordField.grabFocus();
		}
		else if(!passwordString.equals(confirmPasswordString)) {
			JOptionPane.showMessageDialog(this, "Password Missmatch", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			RegistryModel data =  new RegistryModel(userNameString, passwordString);
			ClientService.getClientService().getClient().emit("register", data.toJsonObject(), new Ack() {
				
				@Override
				public void call(Object... objects){
					if (objects.length>0) {
						RegistryStatusModel statusModel = new Gson().fromJson(objects[0].toString(), RegistryStatusModel.class);
						System.out.println(statusModel.getStatus());
						messageLabel.setText(statusModel.getStatus());
						if (statusModel.getStatus().equals("")) {
							switchToLogin();
						}
					}
				}
			});
		}
	}

}