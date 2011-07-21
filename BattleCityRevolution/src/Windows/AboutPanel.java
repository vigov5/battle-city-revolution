package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JPanel;

import Function.ButtonCreater;
import Function.ImageManager;
import Function.LabelCreater;


public class AboutPanel extends JPanel {
	
	private LinkedList listbutton = new LinkedList();
	private Image bg;
	
	public AboutPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(1000,450));
		ImageManager im = new ImageManager("Resources/Image/background_about.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left + 100;
		int y = i.top;
		LabelCreater group = new LabelCreater("<html><b><font size = \"6\" color = \"#FF0000\"><centre> ABOUT US </centre></font></b></html>",300,50,x+260,y+60);
		ButtonCreater back = new ButtonCreater("<html><b><font size = \"3\"> BACK </font></b></html>",150,30,x+390,y+360);
		add(group);
		add(back);
		listbutton.add(back);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 100, 0, null);
	  }
	
	public LinkedList GetListButton(){
		return listbutton;
	}
}
