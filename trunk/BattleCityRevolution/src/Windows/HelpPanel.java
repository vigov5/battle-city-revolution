package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.JPanel;

import Function.ButtonCreater;
import Function.ImageManager;
import Function.LabelCreater;


public class HelpPanel extends JPanel {
	
	private LinkedList listbutton = new LinkedList();
	private Image bg;
	
	public HelpPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(600,450));
		ImageManager im = new ImageManager("Resources/Image/background_help.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left;
		int y = i.top;
		LabelCreater howtoplay = new LabelCreater("<html><b><font size = \"6\" color = \"#FF0000\"><centre> HOW TO PLAY </centre></font></b></html>",300,50,x+230,y+60);
		ButtonCreater back = new ButtonCreater("<html><b><font size = \"3\"> BACK </font></b></html>",150,30,x+390,y+350);
		add(howtoplay);
		add(back);
		listbutton.add(back);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 0, 0, null);
	  }
	public LinkedList GetListButton(){
		return listbutton;
	}
}
