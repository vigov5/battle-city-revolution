package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainCanvas extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1270488992849705712L;
	private long sleepTime = 20;
	private PlayerTank playerTank;
	private AITank AITank;
	public static int animationClock = 0;
	public static Tools t;
	public static TileManager tm;
	public static ArrayList<Explosion> explosionArray;
	public static ArrayList<Tank> tankArray;
	public final int SCREEN_WIDTH = 800;
	public final int SCREEN_HEIGHT = 512;

	public MainCanvas() {
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		t = new Tools(this);
		try {
			tm = new TileManager("01.map");
			
			// init tank and explosion array
			explosionArray = new ArrayList<Explosion>(20);
			explosionArray.clear();
			tankArray = new ArrayList<Tank>(20);
			tankArray.clear();
			
			// create tanks and add to array 
			playerTank = new PlayerTank(t.getTankOneImage(), 32, 32);
			playerTank.setPositionAndBound(0, 0);
			tankArray.add(playerTank);
			AITank = new AITank(t.getTankTwoImage(), 32, 32);
			AITank.setPositionAndBound(128, 0);
			tankArray.add(AITank);
			
			
			Thread t = new Thread(this);
			t.start();
			
			
			this.setFocusable(true);
			this.addKeyListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			animationClock++;
			if (animationClock == 2147483647) {
				animationClock = 0;
			}
			// Update tank state 
			playerTank.update();
			AITank.think();
			AITank.update();
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
		playerTank.render(g);
		AITank.render(g);
		if (!explosionArray.isEmpty()){
			for (int i=0; i<explosionArray.size(); i++){
				explosionArray.get(i).render(g);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		playerTank.move(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		playerTank.setRunning(false);
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
	
}
