package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tank extends Sprite {

	private int[] upFrameStrip = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] downFrameStrip = { 7, 8, 9, 10, 11, 12, 13 };
	private int[] leftFrameStrip = { 14, 15, 16, 17, 18, 19, 20 };
	private int[] rightFrameStrip = { 21, 22, 23, 24, 25, 26, 27 };
	protected int totalHealth;
	protected int currentHealth;
	private boolean isDestroyed;
	private boolean isRunning = false;
	private int speedStep = 2;
	private final int MAX_BULLET = 100;
	private ArrayList<Bullet> bulletArray;
	protected int bulletType;
	protected long bulletDelayTime = 50;
	protected long lastBulletTime = 0;
	protected boolean pathBlocked = false;
	protected int receivedDamage = 0;

	public Tank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setBound(0, 0, frameWidth, frameHeight);
		bulletType = Bullet.SMALL_BULLET;
		// create a null bullet array
		bulletArray = new ArrayList<Bullet>(MAX_BULLET);
		bulletArray.clear();
	}

	public synchronized void setRunning(boolean value) {
		this.isRunning = value;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public boolean isFiring() {
		for (int i = 0; i < bulletArray.size(); i++)
			if (bulletArray.get(i) != null)
				return true;
		return false;
	}

	public void render(Graphics g) {
		if (isFiring()) {
			for (int i = bulletArray.size() - 1; i >= 0; i--) {
				((Bullet) bulletArray.get(i)).render(g);
			}
		}
		this.drawHealthBar(g);
		this.drawBound(g);
		super.render(g);
	}
	
	private void drawHealthBar(Graphics g) {
		// TODO Auto-generated method stub
		 /*
		  * health bar width = frameWidth
		  * health bar height = 4
		  */
		// draw background
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getBoundY() - 10, this.frameWidth, 4);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX(), this.getBoundY() - 10, this.frameWidth, 4);
		// draw current health
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(), this.getBoundY() - 10, this.currentHealth/this.totalHealth*this.frameWidth, 4);
	}

	public void setTotalHealth(int health){
		this.totalHealth = health;
	}

	void addBullet(Tank parent, int type) throws IOException {
		/*
		 * 
		 * Use ArrayList to manager bullet insert at bulletArrayEnd, remove at
		 * bulletArrayStart
		 */
		Bullet tmp = new Bullet(MainCanvas.t.getBulletImage(), 32, 32, parent);
		tmp.setType(type);
		bulletArray.add(tmp);
	}

	public void update() {
		int lastX = this.getX();
		int lastY = this.getY();
		if (isRunning) {
			switch (this.getCurrentDirection()) {
			case Sprite.UP:
				this.setPositionAndBound(this.getX(), this.getY() - speedStep);
				break;
			case Sprite.DOWN:
				this.setPositionAndBound(this.getX(), this.getY() + speedStep);
				break;
			case Sprite.LEFT:
				this.setPositionAndBound(this.getX() - speedStep, this.getY());
				break;
			case Sprite.RIGHT:
				this.setPositionAndBound(this.getX() + speedStep, this.getY());
				break;
			}
		}

		if (MainCanvas.tm.isCollisionWithBricks(this)
				|| MainCanvas.t.isOutScreen(this)
				|| isCollisonWithAnotherTank()) {
			this.pathBlocked = true;
			this.setPositionAndBound(lastX, lastY);
		} else
			this.pathBlocked = false;
		
		isCollisionWithBullets(this);

		if (MainCanvas.animationClock % 5 == 0 && this.isRunning())
			this.nextFrame();
		if (isFiring()) {
			for (int i = 0; i < bulletArray.size(); i++) {
				bulletArray.get(i).update();
				if (bulletArray.get(i).isDestroyed()) {
					//Bullet tmp = bulletArray.get(i); 
					bulletArray.remove(i);
					//tmp = null;
				}
			}
		}
		
		if (receivedDamage != 0){
			this.currentHealth -= receivedDamage;
		}
		
		if (this.currentHealth <= 0) this.isDestroyed = true;
	}

	public boolean isCollisonWithAnotherTank() {
		for (int i = 0; i < MainCanvas.tankArray.size(); i++) {
			if (MainCanvas.tankArray.indexOf(this) != i
					&& MainCanvas.t.isCollision(this, MainCanvas.tankArray
							.get(i))) {
				return true;
			}
		}

		return false;
	}

	public void setPositionAndBound(int x, int y) {
		this.x = x;
		this.y = y;
		switch (this.getCurrentDirection()) {
		case Sprite.DOWN:
		case Sprite.UP:
			this.setBound(4, 1, 24, 30);
			break;
		case Sprite.LEFT:
		case Sprite.RIGHT:
			this.setBound(1, 4, 30, 24);
			break;
		}
	}

	protected void setCurrentDirection(int direct) {
		super.setCurrentDirection(direct);
		switch (direct) {
		case Sprite.UP:
			this.setFrameStrip(upFrameStrip);
			break;
		case Sprite.DOWN:
			this.setFrameStrip(downFrameStrip);
			break;
		case Sprite.LEFT:
			this.setFrameStrip(leftFrameStrip);
			break;
		case Sprite.RIGHT:
			this.setFrameStrip(rightFrameStrip);
			break;
		}
		this.currentFrame = this.frameStrip[currentIndex];
	}

	public boolean isCollisionWithBullets(Sprite a) {
		int i = 0;
		int j = 0;
		for (i = 0; i < MainCanvas.tankArray.size(); i++) {
			if (i != MainCanvas.tankArray.indexOf(a)) {
				//System.out.println("i = " + i + ", This = " + MainCanvas.tankArray.indexOf(a));
				Tank tmp = MainCanvas.tankArray.get(i);
				for (j = 0; j < tmp.bulletArray.size(); j++) {
					Bullet bullet = tmp.bulletArray.get(j);
					if (a instanceof PlayerTank && bullet.getParent() instanceof AITank 
							&& MainCanvas.t.isCollision(a, bullet)) {
							bullet.makeExplosion();
							this.receivedDamage += bullet.getDamage();
							System.out.println("PlayerTank hited !!!");
						return true;
					}
					
					if (a instanceof AITank && bullet.getParent() instanceof PlayerTank 
							&& MainCanvas.t.isCollision(a, bullet)) {
							bullet.makeExplosion();
							this.receivedDamage += bullet.getDamage();
							System.out.println("AITank hited !!!");
						return true;
					}
					
					if (a instanceof AITank && bullet.getParent() instanceof AITank
							&& MainCanvas.t.isCollision(a, bullet)){
						System.out.println("AITank hited each other !!!");
						bullet.makeExplosion();
						return false;
					}
				}
			}
		}
		return false;
	}
}
