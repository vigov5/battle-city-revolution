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
	ButtonCreater newgame;
	ButtonCreater resume;
	ButtonCreater option;
	ButtonCreater help;
	ButtonCreater about;
	ButtonCreater quit;
	
	public MainPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(1000,575));
		ImageManager im = new ImageManager("Resources/Image/background_main.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left+225;
		int y = i.top+80;
		int count = 0;
		
		resume = new ButtonCreater("<html><i><font size = \"3\"> RESUME </font></i></html>",300,40,x+50,y+(count++)*50);
		resume.setForeground(Color.GRAY);
		resume.setEnabled(false);
		newgame = new ButtonCreater("<html><b><font size = \"3\"> NEW GAME </font></b></html>",300,40,x+50,y+(count++)*50);
		option = new ButtonCreater("<html><b><font size = \"3\"> OPTION </font></b></html>",300,40,x+50,y+(count++)*50);
		help = new ButtonCreater("<html><b><font size = \"3\"> HELP </font></b></html>",300,40,x+50,y+(count++)*50);
		about = new ButtonCreater("<html><b><font size = \"3\"> ABOUT </font></b></html>",300,40,x+50,y+(count++)*50);
		quit = new ButtonCreater("<html><b><font size = \"3\"> QUIT </font></b></html>",300,40,x+50,y+(count++)*50);
		listbutton.add(newgame);
		listbutton.add(option);
		listbutton.add(resume);
		listbutton.add(help);
		listbutton.add(about);
		listbutton.add(quit);
		add(newgame);
		add(option);
		add(resume);
		add(help);
		add(about);
		add(quit);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 100, 0, null);
	  }

	public LinkedList GetListButton(){
		return listbutton;
	}
	
	public JButton getNewGameButton(){
		return newgame;
	}
	
	public JButton getResumeButton(){
		return resume;
	}
}
