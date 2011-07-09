package Windows;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.UIManager;

import Function.PlaySound;

public class Main {
	public static void main(String argv []) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){}
		
		WindowFrame windowframe = new WindowFrame();
	}
}
