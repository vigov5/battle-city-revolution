package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JPanel;

import Function.ButtonCreater;
import Function.ImageManager;
import Function.LabelCreater;
import Function.ReadFile;


public class RankingPanel extends JPanel {
	
	private LinkedList listbutton = new LinkedList();
	private Image bg;
	private LabelCreater rank;
	
	public RankingPanel(){
		setLayout(null);
		setPreferredSize(new Dimension(600,450));
		ImageManager im = new ImageManager("Resources/Image/background_ranking.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left;
		int y = i.top;
		LabelCreater ranking = new LabelCreater("<html><b><font size = \"6\" color = \"#FF0000\"><centre> TOP 10 BEST PLAYERS </centre></font></b></html>",300,50,x+180,y+60);
		ButtonCreater back = new ButtonCreater("<html><b><font size = \"3\"> BACK </font></b></html>",150,30,x+390,y+360);
		add(ranking);
		add(back);
		StringTokenizer token; 
		ReadFile rf = new ReadFile("Resources/Ranking.bcr");
		String s;
		int blank=1;
		while((s=rf.ReadOneLine())!=null){
			String name = blank+ "\t" + s + "   a";
			//System.out.println(name);
			rank = new LabelCreater("<html><b><font size = \"5\" color = \"#FF0000\">"+name+"</font></b></html>",300,25,x+170,y+80+25*blank);
			blank++;
			add(rank);
		}
		listbutton.add(back);
	}
	
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 0, 0, null);
	  }
	
	public LinkedList GetListButton(){
		return listbutton;
	}
}
