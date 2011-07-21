package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FramePanel extends JPanel implements ActionListener{
	
	private MainPanel mainpanel = new MainPanel();
	private OptionPanel optionpanel = new OptionPanel();
	private RankingPanel rankingpanel = new RankingPanel();
	private HelpPanel helppanel = new HelpPanel();
	private AboutPanel aboutpanel = new AboutPanel();
	private Insets i = getInsets();
	private LinkedList listbutton;
	private Dimension d;
	private int state;
	
	public FramePanel(){
		setLayout(null);
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.white);
		AddPanel();
		DisplayMainPanel();
	}
	
	private void AddPanel(){
		d = mainpanel.getPreferredSize();
		mainpanel.setBounds(i.left+100,i.top+50,d.width,d.height);
		listbutton = mainpanel.GetListButton();
		for (int j = 0;j<listbutton.size();j++){
			JButton but = (JButton)listbutton.get(j);
			but.addActionListener(this);
		}
		
		Dimension d = optionpanel.getPreferredSize();
		optionpanel.setBounds(i.left+100,i.top+50,d.width,d.height);
		listbutton = optionpanel.GetListButton();
		for (int j = 0;j<listbutton.size();j++){
			JButton but = (JButton)listbutton.get(j);
			but.addActionListener(this);
		}
		
		d = rankingpanel.getPreferredSize();
		rankingpanel.setBounds(i.left+100,i.top+50,d.width,d.height);
		listbutton = rankingpanel.GetListButton();
		for (int j = 0;j<listbutton.size();j++){
			JButton but = (JButton)listbutton.get(j);
			but.addActionListener(this);
		}
		
		d = helppanel.getPreferredSize();
		helppanel.setBounds(i.left+100,i.top+50,d.width,d.height);
		listbutton = helppanel.GetListButton();
		for (int j = 0;j<listbutton.size();j++){
			JButton but = (JButton)listbutton.get(j);
			but.addActionListener(this);
		}
		
		d = aboutpanel.getPreferredSize();
		aboutpanel.setBounds(i.left+100,i.top+50,d.width,d.height);
		listbutton = aboutpanel.GetListButton();
		for (int j = 0;j<listbutton.size();j++){
			JButton but = (JButton)listbutton.get(j);
			but.addActionListener(this);
		}
	}
	
	public void DisplayMainPanel(){
		state = 1;
		add(mainpanel);
	}
	
	public void DisplayOptionPanel(){
		state = 2;
		add(optionpanel);
		//optionpanel.setFocusable(true);
	}
	
	public void DisplayRankingPanel(){
		state = 3;
		add(rankingpanel);
	}
	
	public void DisplayHelpPanel(){
		state = 4;
		add(helppanel);
	}
	
	public void DisplayAboutPanel(){
		state = 5;
		add(aboutpanel);
		aboutpanel.setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton but = (JButton)e.getSource();
		String s = but.getText();
		if (s.indexOf("OPTION")>-1){
			remove(mainpanel);
			DisplayOptionPanel();
		}
		if (s.indexOf("QUIT")>-1){
			System.exit(0);
		}
		if (s.indexOf("RANKING")>-1){
			remove(mainpanel);
			DisplayRankingPanel();
		}
		if (s.indexOf("HELP")>-1){
			remove(mainpanel);
			DisplayHelpPanel();
		}
		if (s.indexOf("ABOUT")>-1){
			remove(mainpanel);
			DisplayAboutPanel();
		}
		if (s.indexOf("BACK")>-1){
			switch (state){
			case 2 : remove(optionpanel);optionpanel.writeSetting(); break;
			case 3 : remove(rankingpanel); break;
			case 4 : remove(helppanel); break;
			case 5 : remove(aboutpanel); break;
			}
			DisplayMainPanel();
		}
		repaint();
	}
	
	public JButton getNewGameButton(){
		return mainpanel.getNewGameButton();
	}
	
	public JButton getResumeButton () {
		return mainpanel.getResumeButton();
	}
}
