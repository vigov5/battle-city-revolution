package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import Function.PlaySound;
import Function.ReadFile;
import Windows.WindowFrame;

public class MainCanvas extends JPanel implements Runnable, KeyListener {

	/**
	 * TODO add right menu add stage change after clear up
	 */

	private static final long serialVersionUID = 1270488992849705712L;
	private int currentLevel = 1;
	private long sleepTime = 20;
	private PlayerTank playerTank;
	public static int animationClock = 0;
	public static Tools t;
	public static TileManager tm;
	public static ArrayList<Explosion> explosionArray;
	public static ArrayList<Tank> tankArray;
	public static ArrayList<Item> itemArray;
	public boolean soundstate;
	public final int SCREEN_WIDTH = 1000;
	public final int SCREEN_HEIGHT = 544;
	public int totalAITank = 20;
	public int currentTotalAITank = 0;
	public final int MAX_AITANK_ONSCREEN = 8;
	private boolean isRunning;
	private static boolean gameOver;
	private boolean paused = false;
	Random rnd;
	PlaySound ps;
	Thread thread;
	private WindowFrame frame;

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		MainCanvas.gameOver = gameOver;
	}

	public MainCanvas(WindowFrame frame) {
		rnd = new Random(serialVersionUID);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		t = new Tools(this);
		this.frame = frame;
		try {
			tm = new TileManager();
			ps = new PlaySound();
			// init tank, item and explosion array
			explosionArray = new ArrayList<Explosion>(20);
			explosionArray.clear();
			tankArray = new ArrayList<Tank>(20);
			tankArray.clear();
			itemArray = new ArrayList<Item>(10);
			itemArray.clear();

			// create tanks and add to array
			playerTank = new PlayerTank(t.getPlayerTankImage(), 32, 32);
			playerTank.setPositionAndBound(10 * 32, 16 * 32);
			// player tank is alway at first place of tank array
			playerTank.setTotalHealth(150);
			playerTank.setCurrentHealth(playerTank.totalHealth);
			tankArray.add(0, playerTank);
			
			GetSoundOption();
			initLevel(currentLevel);
			MainCanvas.setGameOver(false);

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

	public void initLevel(int level) {
		tm.loadMap(level);
		if (soundstate)
			ps.PlayBeginningSound();
		this.spawnAITanks();
	}

	public void cleanUpMap() {
		for (int i = tankArray.size() - 1; i > 0; i--) {
			tankArray.remove(i);
		}
		explosionArray.clear();
		tm.cleanAllBricks();
		itemArray.clear();
		currentTotalAITank = 0;
	}

	public void changeLevel(int level) {
		cleanUpMap();
		initLevel(level);
		tankArray.get(0).setPositionAndBound(10 * 32, 16 * 32);
		playerTank.setCurrentDirection(Sprite.UP);
	}

	public void spawnItem(int x, int y) {
		Item tmp;
		try {
			tmp = new Item(MainCanvas.t.getItemImage(), 32, 32);
			tmp.setType(rnd.nextInt(8));
			tmp.setPositionAndBound(x, y);
			itemArray.add(tmp);
			tmp = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void spawnAITanks() {
		// TODO Auto-generated method stub
		Timer aitankTimer = new Timer();
		aitankTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (currentTotalAITank != totalAITank) {
					if (MainCanvas.tankArray.size() - 1 < MAX_AITANK_ONSCREEN) {
						int pos = -1;
						do {
							pos = rnd.nextInt(3);
							if (isPlaceSpawnable(pos * 12)) {
								pos *= 12;
							}
						} while (pos == -1);
						currentTotalAITank++;
						try {
							AITank tmp;
							if (currentTotalAITank % 1 == 0) {
								tmp = new AITank(t.getRedTankImage(), 32, 32,
										AITank.RED_TANK);
							} else
								tmp = new AITank(t.getAITankImage(), 32, 32,
										AITank.BLUE_TANK);
							tmp.setPositionAndBound(pos * 32, 0);
							tankArray.add(tmp);
							tmp = null;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else
					this.cancel();
			}
		}, 0, 5000);
	}

	private boolean isPlaceSpawnable(int pos) {
		// TODO Auto-generated method stub
		for (int i = 0; i < MainCanvas.tankArray.size(); i++) {
			if (MainCanvas.t.isInBound(tankArray.get(i), pos * 32, 0, 96, 96)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
		}
		while (true) {
			animationClock++;
			if (animationClock == 2147483647) {
				animationClock = 0;
			}
			if (isAllAITankDestroyed() && !isGameOver()) {
				currentLevel++;
				changeLevel(currentLevel);
			}

			if (!isGameOver()){
				if (!isPaused()) {
					// Update tank state
					for (int i = 0; i < tankArray.size(); i++) {
						Tank tmp = tankArray.get(i);
						if (tmp instanceof AITank)
							((game.AITank) tmp).think();
						tmp.update();
						if (tmp instanceof AITank && tmp.isDestroyed) {
							if (((AITank) tmp).getType() == AITank.RED_TANK) {
								this.spawnItem(tmp.getX(), tmp.getY());
							}
							tankArray.remove(tmp);
							tmp = null;
						}
					}
	
					// update item
					for (int i = 0; i < itemArray.size(); i++) {
						if (itemArray.get(i).isDestroyed()) {
							itemArray.remove(i);
						}
					}
	
					// Update explosion array
					if (!explosionArray.isEmpty()) {
						for (int i = 0; i < explosionArray.size(); i++) {
							explosionArray.get(i).update();
							if (explosionArray.get(i).isDestroyed())
								explosionArray.remove(i);
						}
					}
					// Update 
					tm.update();
				} else {
					this.frame.callBackFunction();
				}
			} else {
				cleanUpMap();
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

		if (!isGameOver()) {
			tm.render(g);
			// render tanks
			for (int i = 0; i < tankArray.size(); i++) {
				tankArray.get(i).render(g);
			}

			// render items
			for (int i = 0; i < itemArray.size(); i++) {
				itemArray.get(i).render(g);
			}

			// render explosion effects
			if (!explosionArray.isEmpty()) {
				for (int i = 0; i < explosionArray.size(); i++) {
					explosionArray.get(i).render(g);
				}
			}

			// render right menu
			drawRightMenu(g);
		} else {
			g.setColor(Color.WHITE);
			Font f = new Font("Courier New", Font.BOLD, 48);
			g.setFont(f);
			g.drawString("GAME OVER", SCREEN_WIDTH/2, SCREEN_HEIGHT / 2);
		}
	}

	private void drawRightMenu(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(800, 0, 200, 544);
		g.translate(800, 0);
		g.setColor(Color.WHITE);
		g.drawString("LEVEL:      " + String.valueOf(currentLevel), 10, 20);
		g.drawString("SCORE:", 10, 40);
		g.drawString(
				String.valueOf(((PlayerTank) tankArray.get(0)).getScore()),
				100, 40);
		g.drawString("HEALTH:", 10, 80);
		g.drawString(String
				.valueOf(((PlayerTank) tankArray.get(0)).currentHealth)
				+ "/"
				+ String.valueOf(((PlayerTank) tankArray.get(0)).totalHealth),
				100, 80);

		g.drawString("LIVES:", 10, 120);
		try {
			BufferedImage lives = MainCanvas.t.getItemImage().getSubimage(64,
					0, 32, 32);
			for (int i = 0; i < ((PlayerTank) tankArray.get(0)).getLives(); i++) {
				g.drawImage(lives, 20 * i, 120, 32, 32, null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawString("ENEMY TANK LEFTS: "
				+ String.valueOf(totalAITank - currentTotalAITank), 10, 180);
		g.translate(0, 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER){
			this.frame.callBackFunctionWhenGameOver();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			setPaused(true);
		}
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
			Explosion tmp = new Explosion(MainCanvas.t.getExplosionImage(), 32,
					32);
			tmp.setPositionAndBound(x, y);
			explosionArray.add(tmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean GetSoundOption(){
		ReadFile rf = new ReadFile("Resources/Setting.bcr");
		String s;
		while((s=rf.ReadOneLine())!=null){
			if (s.indexOf("Sound")>-1){
				if (s.indexOf("OFF")>-1) soundstate = false;
				else soundstate = true;
			}
			else soundstate = true;
		}
		return soundstate;
	}
	
	public void GetNewSetting(){
		playerTank.getKey();
		GetSoundOption();
		
	}

	public void setRunning(boolean running) {
		this.isRunning = running;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public boolean isAllAITankDestroyed() {
		for (int i = 1; i < tankArray.size(); i++) {
			if (!tankArray.get(i).isDestroyed())
				return false;
		}
		return true;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
