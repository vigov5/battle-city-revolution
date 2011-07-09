package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import Function.PlaySound;

public class MainCanvas extends JPanel implements Runnable, KeyListener {

	/**
	 * TODO
	 * add right menu
	 * add stage change after clear up
	 */
	
	private static final long serialVersionUID = 1270488992849705712L;
	private long sleepTime = 20;
	private PlayerTank playerTank;
	public static int animationClock = 0;
	public static Tools t;
	public static TileManager tm;
	public static ArrayList<Explosion> explosionArray;
	public static ArrayList<Tank> tankArray;
	public static ArrayList<Item> itemArray;
	public final int SCREEN_WIDTH = 1000;
	public final int SCREEN_HEIGHT = 544;
	private boolean isRunning;
	private static boolean gameOver;
	private Item testItem;
	PlaySound ps = new PlaySound();
	Thread thread;

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		MainCanvas.gameOver = gameOver;
	}

	public MainCanvas() {
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		t = new Tools(this);
		try {
			tm = new TileManager("Resources/Maps/04.map");
			ps.PlayBeginningSound();
			testItem = new Item(MainCanvas.t.getItemImage(), 32, 32);
			testItem.setType(testItem.CLOCK);
			testItem.setPositionAndBound(100, 100);
			
			// init tank and explosion array
			explosionArray = new ArrayList<Explosion>(20);
			explosionArray.clear();
			tankArray = new ArrayList<Tank>(20);
			tankArray.clear();
			
			// create tanks and add to array 
			playerTank = new PlayerTank(t.getTankOneImage(), 32, 32);
			playerTank.setPositionAndBound(10*32, 16*32);
			// pleyr tank is allway at first place of tank array 
			tankArray.add(playerTank);
			for (int i=0; i<3; i++){
				AITank tmp = new AITank(t.getTankTwoImage(), 32, 32);
				tmp.setPositionAndBound(64*(i+1), 0);
				tankArray.add(tmp);
			}
			
			this.setRunning(false);
			thread = new Thread(this);
			thread.start();
			this.setFocusable(true);
			this.addKeyListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
	
	public void Start(){	
	}
	
	public void Pause() throws InterruptedException{
	}
	
	@Override
	public void run() {
		try {
			thread.sleep(1500);
		}catch (Exception e){}
		while (true) {
			animationClock++;
			if (animationClock == 2147483647) {
				animationClock = 0;
			}
			
			// Update tank state
			for (int i=0; i<tankArray.size(); i++){
				if (tankArray.get(i) instanceof AITank)
					((game.AITank) tankArray.get(i)).think();
				if (MainCanvas.t.isCollision(tankArray.get(i), testItem)){
					testItem.applyEffect(tankArray.get(i));
					testItem.applyEffect();
					testItem.setDestroyed(true);
				}
				tankArray.get(i).update();
				if (tankArray.get(i) instanceof AITank && tankArray.get(i).isDestroyed){
					Tank tmp = tankArray.get(i);
					tankArray.remove(tmp);
					tmp = null;
				}
			}
			
			// Update explosion array
			if (!explosionArray.isEmpty()){
				for (int i=0; i<explosionArray.size(); i++){
					explosionArray.get(i).update();
					if (explosionArray.get(i).isDestroyed()) explosionArray.remove(i); 
				}
			}
			
			// Repaint
			repaint();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println("Can't sleep thread !!!");
				e.printStackTrace();
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		tm.render(g);
		// render item 
		if (!testItem.isDestroyed()) testItem.render(g);
		
		// render tanks
		for (int i=0; i<tankArray.size(); i++){
			tankArray.get(i).render(g);
		}
		
		// render explosion effects
		if (!explosionArray.isEmpty()){
			for (int i=0; i<explosionArray.size(); i++){
				explosionArray.get(i).render(g);
			}
		}
		
		// render right menu
		drawRightMenu(g);
	}
	
	private void drawRightMenu(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(800, 0, 200, 544);
		g.translate(800, 0);
		g.setColor(Color.WHITE);
		g.drawString("LEVEL ", 10, 20);
		g.drawString("SCORE:", 10, 40);
		g.drawString(String.valueOf(((PlayerTank) tankArray.get(0)).getScore()), 100, 40);
		g.drawString("HEALTH:", 10, 80);
		g.drawString(String.valueOf(((PlayerTank) tankArray.get(0)).currentHealth) + "/" +
				String.valueOf(((PlayerTank) tankArray.get(0)).totalHealth), 100, 80);
		
		g.drawString("LIVES:", 10, 120);
		try {
			BufferedImage lives = MainCanvas.t.getItemImage().getSubimage(64, 0, 32, 32);
			for (int i=0; i< ((PlayerTank) tankArray.get(0)).getLives(); i++){
				g.drawImage(lives, 20*i, 120, 32, 32, null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.translate(0, 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		playerTank.keyPressedReact(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		playerTank.keyReleasedReact(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void addExplosion(int x, int y) {
		try {
			Explosion tmp = new Explosion(MainCanvas.t.getExplosionImage(), 32, 32);
			tmp.setPositionAndBound(x, y);
			explosionArray.add(tmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRunning(boolean running) {
		this.isRunning = running;
	}

	public boolean isRunning() {
		return isRunning;
	}	
}
