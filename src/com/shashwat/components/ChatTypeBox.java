package com.shashwat.components;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import com.shashwat.models.SendMessageModel;
import com.shashwat.models.UserAccountModel;
import com.shashwat.services.ChatService;
import net.miginfocom.swing.MigLayout;

public class ChatTypeBox extends JPanel implements ActionListener{
	
	//reference variables
	private UserAccountModel userAccount;
	private JScrollPane inputScroller;
	private JTextPane inputArea;
	private JButton sendButton;
	private ChatBody chatBody;
	private int userId;
	
	//constructor
	public ChatTypeBox(ChatBody chatBody, int userId) {
		this.chatBody = chatBody;    
		this.userId = userId;
		super.setLayout(new MigLayout("wrap", "8[fill, 90%]0[]8", "8[]8"));
		super.setOpaque(false);
		this.init();
	}
	
	private void init() {
		
		inputArea = new JTextPane();	
		inputArea.setEditorKit(new WrapEditorKit());
		inputArea.setForeground(Color.BLACK);
		inputArea.setBackground(Color.WHITE);	
		inputArea.setSelectionColor(new Color(255, 255, 170));
		inputArea.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
		inputArea.setFont(new Font("Typewriter", Font.PLAIN, 18));
		inputArea.grabFocus();
		inputArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				refresh();
			}
		});
	
		inputScroller = new JScrollPane(inputArea);
		inputScroller.setVerticalScrollBar(new MyScrollBar());
		inputScroller.setOpaque(false);
		inputScroller.setBorder(BorderFactory.createLineBorder(new Color(11,105,199),1));
		inputScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		inputScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
		sendButton = new JButton();
	    sendButton.setContentAreaFilled(false);
		sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendButton.setFocusable(false);
		sendButton.setBorderPainted(false);
		sendButton.setIcon(new ImageIcon("resources/send.png"));
		sendButton.addActionListener(this);
		
		
		this.add(inputScroller);
		this.add(sendButton, "bottom");
	}

	private void refresh() {
		revalidate();
	}

	public UserAccountModel getUserAccount() {
		return userAccount;
	}

	public void setCurrentPerson(UserAccountModel userAccount) {
		this.userAccount = userAccount;
		inputArea.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String txtString = inputArea.getText().trim();
			if(!txtString.equals("")) {
				//add chat item to right
				SendMessageModel message = new SendMessageModel(userId, userAccount.getUserId(), txtString);
				message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
				ChatService.getChatService().sendMessage(message.getToUserId(), message);
				inputArea.setText("");
				inputArea.grabFocus();
				refresh();
			}
			else {
				inputArea.grabFocus();
			}
	}
	
}
