package Function;

import java.awt.Dimension;

import javax.swing.JButton;

public class ButtonCreater extends JButton {
	
	public ButtonCreater (String name){
		setText(name);
	}
	
	public ButtonCreater (String name,int width,int height){
		setPreferredSize(new Dimension (width,height));
		setText(name);
	}
	
	public ButtonCreater (String name,int width,int height, int left, int top){
		setPreferredSize(new Dimension (width,height));
		setText(name);
		setBounds(left,top,width,height);
	}
}
