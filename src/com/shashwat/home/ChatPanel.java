package com.shashwat.home;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.shashwat.components.ChatBody;
import com.shashwat.components.ChatTitle;
import com.shashwat.components.ChatTypeBox;
import com.shashwat.components.MyScrollBar;
import com.shashwat.models.UserAccountModel;

import net.miginfocom.swing.MigLayout;

public class ChatPanel extends JPanel {
	
	//reference variables
	private int userId;
	private ChatTitle chatTitle;
	private ChatBody chatBody;
	private ChatTypeBox chatTypeBox;
	private JScrollPane chatBodyScroller;
	private static ChatPanel instance;
	private UserAccountModel userAccount;
	
	//constructor
	public ChatPanel(int userId) {
		instance = this;
		this.userId = userId;
		super.setBackground(new Color(246, 246, 246));
		/*Components and rows/columns will by default shrink to their minimum sizes if space is scarce. 
		A column/row's minimum size is by default the largest minimum size of its components*/
		super.setLayout(new MigLayout("wrap", "0[fill, 100%]0", "0[60!]0[fill,100%]0[shrink 0]0")); //shrink 0 -> will not shrink
		
		this.init();
	}
	public static ChatPanel getChatPanelInstance() {
		return instance;
		
	}
	
	private void init() {
		
		chatTitle = new ChatTitle();
		chatBody = new ChatBody();
		chatTypeBox = new ChatTypeBox(chatBody, userId);    //passing ChatBody object reference which is a subclass of JPanel here, So the constructor needs superclass type as parameter
		
		chatBodyScroller = new JScrollPane(chatBody);
		chatBodyScroller.setVerticalScrollBar(new MyScrollBar());
		chatBodyScroller.setOpaque(false);
		chatBodyScroller.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(246, 246, 246)));
		chatBodyScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatBodyScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(chatTitle);
		this.add(chatBodyScroller);
		this.add(chatTypeBox, "h ::25%");
	}
	
	public void scrollToBottom() {
		JScrollBar verticalBar = chatBodyScroller.getVerticalScrollBar();
		AdjustmentListener downScroller = new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
				verticalBar.removeAdjustmentListener(this);
			}
		};
		
		verticalBar.addAdjustmentListener(downScroller);
		
		repaint();
		revalidate();
	}
	
	public void setCurrentPerson(UserAccountModel userAccount) {
		chatTitle.setCuurentPerson(userAccount.getName());
		chatTypeBox.setCurrentPerson(userAccount);
	}

}
