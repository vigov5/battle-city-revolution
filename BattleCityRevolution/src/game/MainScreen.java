package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainScreen {
	public static final String loc = "C:\\Documents and Settings\\ASUS\\workspace\\BattleCityRevolution\\bin\\game\\";
	private JFrame myFrame;
	private MainCanvas canvas;

	public MainScreen() {
		myFrame = new JFrame("Battle City Revolution");
		/*Size
		 * Height = 17*32 = 544
		 * Width = 25*32 = 800
		 */
		myFrame.setLayout(new BorderLayout());
		myFrame.setSize(800, 598);
		canvas = new MainCanvas();
		myFrame.add(canvas, BorderLayout.CENTER);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);	
	}

	public static void main(String[] argv) {
		showGUI();
	}

	private static void showGUI() {
		// TODO Auto-generated method stub
		MainScreen myMainScreen = new MainScreen();
		myMainScreen.getFrame().setVisible(true);
	}

	public JFrame getFrame() {
		return myFrame;
	}
}
