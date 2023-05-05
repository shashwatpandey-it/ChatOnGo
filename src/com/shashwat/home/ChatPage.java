package com.shashwat.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import com.shashwat.services.ClientService;

import io.socket.client.Ack;
import net.miginfocom.swing.MigLayout;

public class ChatPage extends JFrame{
	
	//reference variables
	private JLayeredPane mainPane;
	private int userId;
	private String userName;
	private ChatPanel chatPanel;
	private String loginTime;
	
	//constructor
	public ChatPage(int userId, String userName, String loginTime){
		this.userId = userId;
		this.userName = userName;
		this.loginTime = loginTime;
		super.setTitle("ChatOnGo");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setIconImage(new ImageIcon("resources/icon.png").getImage());
		super.setLocation(200,150);
		super.setSize(1400,700);
		super.getContentPane().setBackground(Color.white);
		
		this.init();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				logout(e);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				userExits(e);
			}
			
		});
		
		super.setVisible(true);
	}
	
	//initializer method
	private void init() {
		
		chatPanel = new ChatPanel(userId);
		chatPanel.setVisible(false);
		
		mainPane = new JLayeredPane();
		mainPane.setLayout(new MigLayout("fillx, filly", "10[fill,260!]10[fill, 100%]10[fill, 260!]10", "10[fill, 100%]10"));
		mainPane.setOpaque(false);
		mainPane.add(new LeftPanel(userId));
		mainPane.add(chatPanel);
		mainPane.add(new RightPanel(userName, loginTime));
		
		
		this.getContentPane().add(mainPane, BorderLayout.CENTER);
	}
	
	private void logout(WindowEvent e) {
		System.out.println(userId);
		ClientService.getClientService().getClient().emit("logout", userId, new Ack() {
			
			@Override
			public void call(Object... args) {
				// TODO Auto-generated method stub
				if(args.length > 0) {
					System.out.println("logged out");
				}
			}
		});
	}
	
	private void userExits(WindowEvent e) {
		ClientService.getClientService().getDispatcher().executorService().shutdown();
		ClientService.getClientService().getClient().close();
	}
	
}
