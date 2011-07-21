package Windows;

import game.MainCanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowFrame extends JFrame{
	
	FramePanel fp = new FramePanel();
	MainCanvas maincanvas = null;
	JButton newgame;
	JButton resume;
	Dimension d;
	
	public WindowFrame () {
		super("BATTLE CITY REVOLUTION");
		setPreferredSize(new Dimension (1000,575));
		setBackground(Color.WHITE);
		newgame = fp.getNewGameButton();
		resume = fp.getResumeButton();
		add(fp, BorderLayout.CENTER);

		resume.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remove(fp);
				maincanvas.GetNewSetting();
				addMaincanvas();
				repaint();
			}
		});
		
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remove(fp);
				if (maincanvas != null){
					maincanvas.setEndGame(true);
					maincanvas.cleanUpEveryThing();
				}
				try {
					createMainCanvas();	
				} catch (Exception e){}
				maincanvas.GetNewSetting();
				addMaincanvas();
				repaint();
			}
		});
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	protected void createMainCanvas() {
		// TODO Auto-generated method stub
		maincanvas = new MainCanvas(this);
		//maincanvas.setPaused(true);
	}

	private void addMaincanvas(){
		add(maincanvas, BorderLayout.CENTER);
		maincanvas.setPaused(false);
		maincanvas.setFocusable(true);
		maincanvas.requestFocusInWindow();
	}
	
	private void addFramePanel(){
		add(fp,BorderLayout.CENTER);
	}

	public void callBackFunctionWhenPaused() {
		// TODO Auto-generated method stub
		maincanvas.setPaused(true);
		this.remove(maincanvas);
		addFramePanel();
		EnableResume();
		setBackground(Color.WHITE);
		repaint();
	}
	
	public void callBackFunctionWhenGameOver(){
		this.remove(maincanvas);
		DisableResume();
		addFramePanel();
		setBackground(Color.WHITE);
		//createMainCanvas();
		repaint();
	}
	
	public void DisableResume(){
		resume.setEnabled(false);
		resume.setText("<html><i><font size = \"3\"> RESUME </font></i></html>");
		resume.setForeground(Color.GRAY);
	}
	
	public void EnableResume(){
		resume.setEnabled(true);
		resume.setText("<html><b><font size = \"3\"> RESUME </font></b></html>");
		resume.setForeground(Color.BLACK);
	}
}
