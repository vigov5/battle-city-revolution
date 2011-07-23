package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	String des1 = "Childhood Rediscovering Group (CRG) is a group at the Subject named Object Oriented Programming at SFC-KEIO.";
	String des2 = "CRG has 4 members ";
	String des3 = "グエン・アイン・ティエン";
	String des4 = "チャン・ドゥック・タン";
	String des5 = "鈴木梅雨";
	String des6 = "裕樹";
	
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
	    drawdescription(g);
	  }
	
	public void drawdescription (Graphics g){
		int i = 0;
		g.setFont(new Font("Serif",Font.BOLD,17));
		g.setColor(Color.BLUE);
		g.drawString(des1.substring(0,62), 180, 140 + (i++)*20);
		g.drawString(des1.substring(62,des1.length()), 180, 140 + (i++)*20);
		g.drawString(des2, 330, 160 + (i++)*20);
		g.setFont(new Font("MS Mincho",Font.BOLD,17));
		g.setColor(Color.RED);
		g.drawString(des3, 320, 150 + (i++)*30);
		g.drawString(des4, 320, 150 + (i++)*30);
		g.drawString(des5, 320, 150 + (i++)*30);
		g.drawString(des6, 320, 150 + (i++)*30);
	}
	
	public LinkedList GetListButton(){
		return listbutton;
	}
}
