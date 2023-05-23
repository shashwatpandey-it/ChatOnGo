package com.shashwat.components;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shashwat.home.ChatPanel;
import com.shashwat.models.UserAccountModel;

import net.miginfocom.swing.MigLayout;

public class ContactCard extends JPanel implements MouseListener{
	
	//reference variables
	private String contactName;
	private UserAccountModel userAccount;
	private JLabel avatarLabel;
	private JLabel nameLabel;
	
	//constructor
	public ContactCard(UserAccountModel userAccount) {
		this.userAccount = userAccount;
		this.init();
		this.setName(userAccount.getName());
	}
	
	public ContactCard(String contactName) {
		this.contactName = contactName;
		this.init();
		this.setName(contactName);
	}
	
	private void init() {
		super.setLayout(new MigLayout("", "8[fill]8", "6[fill]6"));
		super.setBackground(new Color(235,235,235));
		
		avatarLabel = new JLabel();
		avatarLabel.setIcon(new ImageIcon(getClass().getResource("/user.png")));
		
		nameLabel = new JLabel();
		nameLabel.setFont(new Font("Veranda", Font.PLAIN, 18));
		
		addMouseListener(this);
		
		this.add(avatarLabel);
		this.add(nameLabel);
		
	}
	
	public void setName(String name) {
		nameLabel.setText(name);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ChatPanel.getChatPanelInstance().setCurrentPerson(userAccount);
		ChatPanel.getChatPanelInstance().setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		super.setBackground(new Color(220, 220, 220));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.setBackground(new Color(235, 235, 235));
	}

}
