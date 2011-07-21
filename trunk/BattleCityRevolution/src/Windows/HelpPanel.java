package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	String des1 = "* Use ↑ ↓ → ← and SPACE button to move UP DOWN LEFT RIGHT and FIRE. You can change those key or reset them to default at OPTION TAB.";
	String des2 = "* At OPTION TAB you can also TURN ON or OFF the sound.";
	String des3 = "* While playing game, use ESC button to pause game and go back to main menu. At main menu, you can choose to RESUME that game or start a NEW GAME.";
	String des4 = "* In each game, you start with 3 lives and your mission is to protect you castle and destroy the enemies. After destroying all the enemies, you will go to the next state where the enemies become STRONGER, FASTER and SMARTER.";
	String des5 = "* Whenever you have no more lives or the EAGLE is destroyed, THE GAME WILL BE OVER.";
	
	
	public HelpPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(1000,575));
		ImageManager im = new ImageManager("Resources/Image/background_help.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left + 100;
		int y = i.top;
		LabelCreater howtoplay = new LabelCreater("<html><b><font size = \"6\" color = \"#FF0000\"><centre> HOW TO PLAY </centre></font></b></html>",300,50,x+250,y+60);
		ButtonCreater back = new ButtonCreater("<html><b><font size = \"3\"> BACK </font></b></html>",150,30,x+390,y+350);
		add(howtoplay);
		add(back);
		listbutton.add(back);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 100, 0, null);
	    drawDescription(g);
	  }
	
	public void drawDescription(Graphics g){
		int i = 0;
		g.setFont(new Font("Serif",Font.BOLD,15));
		g.setColor(Color.BLUE);
		g.drawString(des1.substring(0,62), 180, 120 + (i++)*20);
		g.drawString(des1.substring(62,des1.length()), 175, 120 + (i++)*20);
		g.drawString(des2, 180, 120 + (i++)*20);
		g.drawString(des3.substring(0,66), 180, 120 + (i++)*20);
		g.drawString(des3.substring(66,129), 210, 120 + (i++)*20);
		g.drawString(des3.substring(129,des3.length()), 215, 120 + (i++)*20);
		g.drawString(des4.substring(0,61), 220, 120 + (i++)*20);
		g.drawString(des4.substring(61,123), 230, 120 + (i++)*20);
		g.drawString(des4.substring(123,180), 230, 120 + (i++)*20);
		g.drawString(des4.substring(180,des4.length()), 230, 120 + (i++)*20);
		g.drawString(des5.substring(0,60), 250, 120 + (i++)*20);
		g.drawString(des5.substring(60,des5.length()), 260, 120 + (i++)*20);
	}
	public LinkedList GetListButton(){
		return listbutton;
	}
}
