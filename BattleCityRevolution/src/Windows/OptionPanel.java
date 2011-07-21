package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import Function.ButtonCreater;
import Function.ImageManager;
import Function.LabelCreater;
import Function.ReadFile;
import Function.TextFieldCreater;
import Function.WriteFile;


public class OptionPanel extends JPanel implements ActionListener {
	
	private LinkedList listbutton = new LinkedList();
	private Image bg;
	private ButtonCreater reset;
	private ButtonCreater left_change;
	private ButtonCreater right_change;
	private ButtonCreater down_change;
	private ButtonCreater up_change;
	private ButtonCreater fire_change;
	private ButtonCreater turn;
	private TextFieldCreater key_left;
	private TextFieldCreater key_right;
	private TextFieldCreater key_down;
	private TextFieldCreater key_up;
	private TextFieldCreater key_fire;
	private ReadFile rf;
	private WriteFile wf;
	private int keyleft;
	private int keyright;
	private int keydown;
	private int keyup;
	private int keyfire;
	private int state = 0;
	private String soundstate;
	
	public OptionPanel(){
		
		setLayout(null);
		setPreferredSize(new Dimension(1000,575));
		ImageManager im = new ImageManager("Resources/Image/background_option.png", 600, 450);
		bg = im.GetImage();
		Insets i = getInsets();
		int x = i.left+270;
		int y = i.top + 20;

		getKey();
		getSetting();
		
		LabelCreater key = new LabelCreater("<html><b><font size = \"5\" color = \"#FF0000\"> KEYBOARD </font></b></html>",300,50,x,y+40);
		reset = new ButtonCreater("<html><b><font size = \"3\"> DEFAULT </font></b></html>",100,30,x+220,y+48);
		reset.addActionListener(this);
		
		LabelCreater left = new LabelCreater("<html><b><font size = \"3\"> LEFT KEY </font></b></html>",100,30,x,y+90);
		key_left = new TextFieldCreater(KeyEvent.getKeyText(keyleft),70,30,x+110,y+90);
		key_left.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC,12));
		key_left.setForeground(Color.lightGray);
		left_change = new ButtonCreater("<html><b><font size = \"3\"> CHANGE </font></b></html>",100,30,x+220,y+88);
		left_change.addActionListener(this);
		
		LabelCreater right = new LabelCreater("<html><b><font size = \"3\"> RIGHT KEY </font></b></html>",100,30,x,y+130);
		key_right = new TextFieldCreater(KeyEvent.getKeyText(keyright),70,30,x+110,y+130);
		key_right.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC,12));
		key_right.setForeground(Color.lightGray);
		right_change = new ButtonCreater("<html><b><font size = \"3\"> CHANGE </font></b></html>",100,30,x+220,y+128);
		right_change.addActionListener(this);
		
		LabelCreater down = new LabelCreater("<html><b><font size = \"3\"> DOWN KEY </font></b></html>",100,30,x,y+170);
		key_down = new TextFieldCreater(KeyEvent.getKeyText(keydown),70,30,x+110,y+170);
		key_down.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC,12));
		key_down.setForeground(Color.lightGray);
		down_change = new ButtonCreater("<html><b><font size = \"3\"> CHANGE </font></b></html>",100,30,x+220,y+168);
		down_change.addActionListener(this);
		
		LabelCreater rotate = new LabelCreater("<html><b><font size = \"3\"> UP KEY </font></b></html>",100,30,x,y+210);
		key_up = new TextFieldCreater(KeyEvent.getKeyText(keyup),70,30,x+110,y+210);
		key_up.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC,12));
		key_up.setForeground(Color.lightGray);
		up_change = new ButtonCreater("<html><b><font size = \"3\"> CHANGE </font></b></html>",100,30,x+220,y+208);
		up_change.addActionListener(this);
		
		LabelCreater pause = new LabelCreater("<html><b><font size = \"3\"> FIRE KEY </font></b></html>",100,30,x,y+260);
		key_fire = new TextFieldCreater(KeyEvent.getKeyText(keyfire),70,30,x+110,y+250);
		key_fire.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC,12));
		key_fire.setForeground(Color.lightGray);
		fire_change = new ButtonCreater("<html><b><font size = \"3\"> CHANGE </font></b></html>",100,30,x+220,y+248);
		fire_change.addActionListener(this);
		
		LabelCreater soundlabel = new LabelCreater("<html><b><font size = \"5\" color = \"#FF0000\"> SOUND </font></b></html>",300,50,x,y+290);
		turn = new ButtonCreater("<html><b><font size = \"3\" >" + soundstate + "</font></b></html>",100,30,x+220,y+295);
		
		ButtonCreater back = new ButtonCreater("<html><b><font size = \"3\"> BACK </font></b></html>",150,30,x+220,y+340);
		
		add(key);
		add(left);
		add(right);
		add(down);
		add(rotate);
		add(pause);
		add(turn);
		add(soundlabel);
		add(reset);
		add(key_left);
		add(key_right);
		add(key_down);
		add(key_up);
		add(key_fire);
		add(left_change);
		add(right_change);
		add(down_change);
		add(up_change);
		add(fire_change);
		add(back);
		listbutton.add(back);
		turn.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) {
	    g.drawImage(bg, 100, 0, null);
	  }
	
	private void getKey(){
		rf = new ReadFile("Resources/Key.bcr");
		keyleft=Integer.parseInt(rf.ReadOneLine());
		keyright=Integer.parseInt(rf.ReadOneLine());
		keydown=Integer.parseInt(rf.ReadOneLine());
		keyup=Integer.parseInt(rf.ReadOneLine());
		keyfire=Integer.parseInt(rf.ReadOneLine());
		rf.Close();
	}
	
	private void writeKey(){
		String s = keyleft + "\n" + keyright + "\n" + keydown + "\n" + keyup + "\n" + keyfire;
		wf = new WriteFile("Resources/Key.bcr",s);
	}
	
	private void getSetting(){
		rf = new ReadFile("Resources/Setting.bcr");
		String s;
		while((s=rf.ReadOneLine())!=null){
			if (s.indexOf("Sound")>-1){
				if (s.indexOf("OFF")>-1) soundstate = "TURN OFF";
				else soundstate = "TURN ON";
			}
			else soundstate = "TURN OFF";
		}
	}
	
	public void writeSetting(){
		String s = "Sound " + soundstate; 
		wf = new WriteFile("Resources/Setting.bcr",s);
	}
	
	private void resetDefault(){
		keyleft = 37;
		keyright = 39;
		keydown = 40;
		keyup = 38;
		keyfire = 32;
		key_left.setText(KeyEvent.getKeyText(keyleft));
		key_right.setText(KeyEvent.getKeyText(keyright));
		key_down.setText(KeyEvent.getKeyText(keydown));
		key_up.setText(KeyEvent.getKeyText(keyup));
		key_fire.setText(KeyEvent.getKeyText(keyfire));	
	}
	
	private Boolean CheckKey(int keycheck){
		if (Check (keycheck)) 
			return true;
		else {
			JOptionPane.showMessageDialog(this, "This key is being used. Please Enter another key !", "Input key Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private Boolean Check(int keycheck){
		switch (state){
		case 1 : if ((keycheck==keyright)||(keycheck==keydown)||(keycheck==keyup)||(keycheck==keyfire)) return false; else return true;
		case 2 : if ((keycheck==keyleft)||(keycheck==keydown)||(keycheck==keyup)||(keycheck==keyfire)) return false; else return true;
		case 3 : if ((keycheck==keyleft)||(keycheck==keyright)||(keycheck==keyup)||(keycheck==keyfire)) return false; else return true;
		case 4 : if ((keycheck==keyleft)||(keycheck==keyright)||(keycheck==keydown)||(keycheck==keyfire)) return false; else return true;
		case 5 : if ((keycheck==keyleft)||(keycheck==keyright)||(keycheck==keydown)||(keycheck==keyup)) return false; else return true;
		}
		return true;
	}
	
	public LinkedList GetListButton(){
		return listbutton;
	}
	
	private void changestate(int s){
		state = s;
		switch (state){
		case 1: setTextFieldOption(key_right, keyright, key_up, keyup, key_down, keydown, key_fire, keyfire);break;
		case 2: setTextFieldOption(key_left, keyleft, key_up, keyup, key_down, keydown, key_fire, keyfire);break;
		case 3: setTextFieldOption(key_left, keyleft, key_right, keyright, key_up, keyup, key_fire, keyfire);break;
		case 4: setTextFieldOption(key_left, keyleft, key_right, keyright, key_down, keydown, key_fire, keyfire);break;
		case 5: setTextFieldOption(key_left, keyleft, key_right, keyright, key_up, keyup, key_down, keydown);break;
		}
	}
	
	private void setTextFieldOption(TextFieldCreater tx1,int k1,TextFieldCreater tx2,int k2, TextFieldCreater tx3,int k3,TextFieldCreater tx4,int k4){
		tx1.setFocusable(false);
		tx1.setEditable(false);
		tx1.setText(KeyEvent.getKeyText(k1));
		tx2.setFocusable(false);
		tx2.setEditable(false);
		tx2.setText(KeyEvent.getKeyText(k2));
		tx3.setFocusable(false);
		tx3.setEditable(false);
		tx3.setText(KeyEvent.getKeyText(k3));
		tx4.setFocusable(false);
		tx4.setEditable(false);
		tx4.setText(KeyEvent.getKeyText(k4));
	}
	
	private void ChangeKey(final TextFieldCreater tx){
		tx.setEditable(true);
		tx.setText("");
		tx.setFocusable(true);
		tx.requestFocus();
		tx.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				
				if (CheckKey(key)){
					switch (state){
					case 1 : keyleft = key;break;
					case 2 : keyright = key; break;
					case 3 : keydown = key; break;
					case 4 : keyup = key; break;
					case 5 : keyfire = key; break;
					}
					tx.setText(KeyEvent.getKeyText(key));
					tx.setEditable(false);
					tx.setFocusable(false);
					writeKey();
				}
				else {
					switch (state){
					case 1 : key = keyleft ; break;
					case 2 : key = keyright ; break;
					case 3 : key = keydown; break;
					case 4 : key = keyup; break;
					case 5 : key = keyfire; break;
					}
					tx.setText(KeyEvent.getKeyText(key));
					tx.setEditable(false);
					tx.setFocusable(false);
				}
				tx.removeKeyListener(this);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==left_change){
			changestate(1);
			ChangeKey(key_left);
		}
		
		if (e.getSource()==right_change){
			changestate(2);
			ChangeKey(key_right);
		}
		
		if (e.getSource()==down_change){
			changestate(3);
			ChangeKey(key_down);
		}
		
		if (e.getSource()==up_change){
			changestate(4);
			ChangeKey(key_up);
		}
		
		if (e.getSource()==fire_change){
			changestate(5);
			ChangeKey(key_fire);
		}
		
		if (e.getSource()==reset){
			resetDefault();
			writeKey();
		}
		
		if (e.getSource()==turn){
			if(soundstate.indexOf("TURN ON")>-1) soundstate = "TURN OFF";
			else soundstate = "TURN ON";
			turn.setText("<html><b><font size = \"3\">" + soundstate + "</font></b></html>");
			writeSetting();
		}
	}
}
