package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Function.ButtonCreater;
import Function.ImageManager;

public class MainPanel extends JPanel {
	
	private LinkedList listbutton = new LinkedList();
	private Image bg;
	public ButtonCreater play;
	
	public MainPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(1000,575));
		ImageManager im = new ImageManager("Resources/Image/background_main.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left+225;
		int y = i.top+80;
		int count = 0;
		play = new ButtonCreater("<html><b><font size = \"3\"> PLAY </font></b></html>",300,50,x+50,y+(count++)*60);
		ButtonCreater option = new ButtonCreater("<html><b><font size = \"3\"> OPTION </font></b></html>",300,50,x+50,y+(count++)*60);
		//ButtonCreater ranking = new ButtonCreater("<html><b><font size = \"3\"> RANKING </font></b></html>",300,40,x+50,y+(count++)*50);
		ButtonCreater help = new ButtonCreater("<html><b><font size = \"3\"> HELP </font></b></html>",300,50,x+50,y+(count++)*60);
		ButtonCreater about = new ButtonCreater("<html><b><font size = \"3\"> ABOUT </font></b></html>",300,50,x+50,y+(count++)*60);
		ButtonCreater quit = new ButtonCreater("<html><b><font size = \"3\"> QUIT </font></b></html>",300,50,x+50,y+(count++)*60);
		add(play);
		add(option);
		//add(ranking);
		add(help);
		add(about);
		add(quit);
		listbutton.add(play);
		listbutton.add(option);
		//listbutton.add(ranking);
		listbutton.add(help);
		listbutton.add(about);
		listbutton.add(quit);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 100, 0, null);
	  }

	public LinkedList GetListButton(){
		return listbutton;
	}
	
	public JButton getPlayButton(){
		return play;
	}
}
