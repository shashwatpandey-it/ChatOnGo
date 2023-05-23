package com.shashwat.home;
import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.shashwat.components.ContactCard;
import com.shashwat.components.LPButton;
import com.shashwat.components.MyScrollBar;
import com.shashwat.models.UserAccountModel;
import com.shashwat.services.ClientService;

import net.miginfocom.swing.MigLayout;

public class LeftPanel extends JPanel {
	
	//reference variables
	private JLayeredPane header;
	private JLayeredPane menuList;
	private JScrollPane scrollerPane;
	private JButton chatButton;
	private JButton groupButton;
	private JButton boxButton;
	private int userId;
	private Set<UserAccountModel> users = new HashSet<>();
	private static LeftPanel leftPanelInstance;
	
	//constructor
	public LeftPanel(int userId) {
		leftPanelInstance = this;
		this.userId = userId;
		super.setBackground(new Color(246, 246, 246));
		super.setLayout(new MigLayout("wrap", "0[fill, 100%]0", "0[]5[fill, 100%]0"));
		
		this.init();
	}
	
	public static LeftPanel getLeftPanelInstance() {
		return leftPanelInstance;
	}
	
	public void newUsers(List<UserAccountModel> list) {
		users.clear();
		for(UserAccountModel userAccount : list) {
			users.add(userAccount);
		}
		this.showChatList();
	}
	
	//initializer method
	private void init() {
		ClientService.getClientService().getClient().emit("listUsers", userId);				//demanding for list of users
		
		chatButton = new LPButton(new ImageIcon(getClass().getResource("/chat.png")), new ImageIcon(getClass().getResource("/chat_selected.png")));
		chatButton.setSelected(true);
		chatButton.addActionListener(event -> {	chatButton.setSelected(true);
												groupButton.setSelected(false);
												boxButton.setSelected(false);
												showChatList();
		});
		
		groupButton = new LPButton(new ImageIcon(getClass().getResource("/group.png")), new ImageIcon(getClass().getResource("/group_selected.png")));
		groupButton.setSelected(false);
		groupButton.addActionListener(event -> {	chatButton.setSelected(false);
													groupButton.setSelected(true);
													boxButton.setSelected(false);
													showGroupList();
		});
		
		boxButton = new LPButton(new ImageIcon(getClass().getResource("/box.png")), new ImageIcon(getClass().getResource("/box_selected.png")));
		boxButton.setSelected(false);
		boxButton.addActionListener(event -> {	chatButton.setSelected(false);
												groupButton.setSelected(false);
												boxButton.setSelected(true);
												showBoxList();
		});
		
		header = new JLayeredPane();
		header.setLayout(new MigLayout("", "10[70!]10[70!]10[70!]10", "5[50!]5"));
		header.add(chatButton);
		header.add(groupButton);
		header.add(boxButton);
		
		menuList = new JLayeredPane();
		menuList.setOpaque(false);
		menuList.setLayout(new MigLayout("wrap", "8[fill, 240!]8", "8[fill]8"));
		
		scrollerPane = new JScrollPane(menuList);
		scrollerPane.getViewport().setBackground(new Color(246, 246, 246));
		scrollerPane.setBorder(BorderFactory.createEmptyBorder());
		scrollerPane.setVerticalScrollBar(new MyScrollBar());
		scrollerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollerPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//initial menuList display setup
		showChatList();
		
		
		this.add(header);
		this.add(scrollerPane);
	}
	
	private void showChatList() {
		//clearing the menuList i.e. setting JLayeredPane to void
		menuList.removeAll();
		
		//refilling menuList
		for(UserAccountModel userAccount : users) {
			menuList.add(new ContactCard(userAccount));
		}
		
		//refreshing it to accommodate changes
		refreshMenuList();
	}
	
	private void showGroupList() {
		//clearing the menuList i.e. setting JLayeredPane to void
		menuList.removeAll();
		
		//refilling menuList
		for(int i = 0; i<16; i++) {
			menuList.add(new ContactCard("Group " + i));
		}
		
		//refreshing it to accommodate changes
		refreshMenuList();
	}
	
	private void showBoxList() {
		//clearing the menuList i.e. setting JLayeredPane to void
		menuList.removeAll();
		
		//refilling menuList
		for(int i = 0; i<2; i++) {
			menuList.add(new ContactCard("Box " + i));
		}	
		
		//refreshing it to accommodate changes
		refreshMenuList();
	}
	
	private void refreshMenuList() {
		menuList.repaint();
		menuList.revalidate();	
	}

}
