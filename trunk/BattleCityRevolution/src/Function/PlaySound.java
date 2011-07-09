package Function;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySound {
	AudioInputStream ais;
	AudioFormat audioFormat;
	String sound;
	File soundFile;
	SourceDataLine sdl;
	
	public PlaySound(){
	}
	
	public void PlayBeginningSound(){
		Play("test.wav");
	}
	
	public void Play(String sound) {
		this.sound = sound;
		soundFile = new File ("Resources/Audio/"+sound);
		try {
			ais = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = ais.getFormat();
		    System.out.println(audioFormat);
		    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,audioFormat);
		    sdl =(SourceDataLine)AudioSystem.getLine(dataLineInfo);
		    new PlayThread().start();
		}catch (Exception e){}
	}
	
	class PlayThread extends Thread{
		  byte tempBuffer[] = new byte[10000];

		  public void run(){
		    try{
		      sdl.open(audioFormat);
		      sdl.start();
		      int cnt;
		      while((cnt = ais.read(tempBuffer,0,tempBuffer.length)) != -1){
		        if(cnt > 0){
		          sdl.write(tempBuffer, 0, cnt);
		        }
		      }
		      sdl.drain();
		      sdl.close();
		    }catch (Exception e) {
		      e.printStackTrace();
		      System.exit(0);
		    }
		  }
		}
}
