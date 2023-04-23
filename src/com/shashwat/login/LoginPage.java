package com.shashwat.login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.shashwat.services.ClientService;

public class LoginPage extends JFrame {
	
	//reference variable
	private JPanel mainPanel, loginPanel, registerPanel;
	private JLabel logoLabel, titleLabel, lineLabel, versionLabel;
	private Font titleFont;
	private JButton closeButton;
	private Color themeColor;
	private boolean showRegister;
	
	//constructor
	public LoginPage(Boolean showRegister) {
		this.showRegister = showRegister;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setIconImage(new ImageIcon("resources/icon.png").getImage());
		super.setSize(800,600);
		super.setLocation(580,200);
		super.setResizable(false);
		super.setUndecorated(true);
		
		this.init();
		
		super.setVisible(true);
	}
	
	private void init() {
		
		themeColor = new Color(11,105,199);
		
		titleFont = new Font("Rockwell", Font.BOLD, 36);
		
		logoLabel = new JLabel();
		logoLabel.setOpaque(false);
		logoLabel.setIcon(new ImageIcon("resources/icon.png"));
		logoLabel.setBounds(120,120,160,160);
		
		titleLabel = new JLabel("ChatOnGo");
		titleLabel.setOpaque(false);
		titleLabel.setFocusable(false);
		titleLabel.setFont(titleFont);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		//titleLabel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		titleLabel.setForeground(themeColor);
		titleLabel.setBounds(82,300,240,60);
		
		lineLabel = new JLabel();
		lineLabel.setFocusable(false);
		lineLabel.setBorder(BorderFactory.createLineBorder(themeColor,5));
		lineLabel.setBounds(20,380,360,5);
		
		versionLabel = new JLabel("version 1.0");
		versionLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		versionLabel.setFocusable(false);
		versionLabel.setForeground(Color.GRAY);
		versionLabel.setHorizontalAlignment(JLabel.CENTER);
		versionLabel.setBounds(120,395,160,10);
		
		closeButton = new JButton();
		closeButton.setIcon(new ImageIcon("resources/exit.png"));
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.setFocusable(false);
		closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeButton.setBounds(155,415,100,100);
		closeButton.addActionListener(event -> {	ClientService.getClientService().getDispatcher().executorService().shutdown();
													ClientService.getClientService().getClient().close();
													this.dispose();
		});
		
		loginPanel = new LoginPanelRight(this);
		loginPanel.setBackground(themeColor);
		loginPanel.setBounds(400,0,400,600);
		
		
		registerPanel = new RegisterPanelRight(this);
		registerPanel.setBackground(themeColor);
		registerPanel.setBounds(400,0,400,600);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(logoLabel);
		mainPanel.add(titleLabel);
		mainPanel.add(lineLabel);
		mainPanel.add(versionLabel);
		mainPanel.add(closeButton);
		
		if(showRegister) {
			mainPanel.add(registerPanel);
			
		}
		else {
			mainPanel.add(loginPanel);
		}
		
		this.getContentPane().add(mainPanel,BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ClientService.getClientService().startClientService();
				new LoginPage(false);
			}
		});
		
	}
}
