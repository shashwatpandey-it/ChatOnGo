package com.shashwat.components;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LPButton extends JButton {

	private ImageIcon unselectedIcon;
	private ImageIcon selectedIcon;
	
	public LPButton(ImageIcon unselected, ImageIcon selected) {
		this.unselectedIcon = unselected;
		this.selectedIcon = selected;
		
		super.setContentAreaFilled(false);
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setFocusable(false);
		super.setBorderPainted(false);
	}
	
	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		
		if(b) {
			super.setIcon(selectedIcon);
		}
		else {
			super.setIcon(unselectedIcon);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.isSelected()) {
			g.setColor(new Color(11, 105, 199));
			g.fillRect(0, this.getHeight()-3, this.getWidth(), this.getHeight());
		}
	}
}
