package Function;

import java.awt.Dimension;

import javax.swing.JTextField;

public class TextFieldCreater extends JTextField {
	
	public TextFieldCreater (String name){
		setText(name);
	}
	
	public TextFieldCreater (String name,int width,int height){
		setPreferredSize(new Dimension (width,height));
		setText(name);
	}
	
	public TextFieldCreater (String name,int width,int height, int left, int top){
		setPreferredSize(new Dimension (width,height));
		setText(name);
		setBounds(left,top,width,height);
		setEditable(false);
	}
}
