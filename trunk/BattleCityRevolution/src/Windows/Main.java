package Windows;

import javax.swing.UIManager;

public class Main {
	public static void main(String argv []){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){}
		
		WindowFrame windowframe = new WindowFrame();
	}
}
