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
	MainCanvas maincanvas;
	JButton play;
	Dimension d;
	
	public WindowFrame () {
		super("BATTLE CITY REVOLUTION");
		setPreferredSize(new Dimension (1000,575));
		setBackground(Color.WHITE);
		play = fp.getPlayButton();
		add(fp, BorderLayout.CENTER);
		try {
			createMainCanvas();	
		} catch (Exception e){}
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remove(fp);
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

	public void callBackFunction() {
		// TODO Auto-generated method stub
		//maincanvas.setPaused(true);
		this.remove(maincanvas);
		addFramePanel();
		setBackground(Color.WHITE);
		repaint();
	}
}
