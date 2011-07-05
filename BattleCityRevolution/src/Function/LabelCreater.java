package Function;

import java.awt.Dimension;

import javax.swing.JLabel;

public class LabelCreater extends JLabel {
	
	public LabelCreater (String name){
		setText(name);
	}
	
	public LabelCreater (String name,int width,int height){
		setPreferredSize(new Dimension (width,height));
		setText(name);
	}
	
	public LabelCreater (String name,int width,int height, int left, int top){
		setPreferredSize(new Dimension (width,height));
		setText(name);
		setBounds(left,top,width,height);
	}
}
