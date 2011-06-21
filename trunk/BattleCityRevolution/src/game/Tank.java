package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank extends Sprite {

	private int totalHealth;
	private int[] upFrameStrip = { 0, 1, 2, 3, 4, 5, 6 };
	private int[] downFrameStrip = { 7, 8, 9, 10, 11, 12, 13 };
	private int[] leftFrameStrip = { 14, 15, 16, 17, 18, 19, 20 };
	private int[] rightFrameStrip = { 21, 22, 23, 24, 25, 26, 27 };
	private int currentHealth;
	private boolean isDestroyed;
	private boolean isRunning = false;
	private int speedStep = 2;
	private final int MAX_BULLET = 100;
	private Bullet[] bulletArray = new Bullet[MAX_BULLET];
	private int bulletArrayEnd = 0;
	private int bulletArrayStart = 0;
	private int totalBullet = 0;
	private int bulletType;

	public Tank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setBound(0, 0, frameWidth, frameHeight);
		bulletType = Bullet.SMALL_BULLET;
		for (int i = 0; i < MAX_BULLET; i++)
			bulletArray[i] = null;
	}

	public void setRunning(boolean value) {
		this.isRunning = value;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public boolean isFiring() {
		for (int i = bulletArrayStart; i < MAX_BULLET; i++)
			if (bulletArray[i] != null)
				return true;
		return false;
	}

	public void render(Graphics g) {
		if (isFiring()) {
			for (int i = bulletArrayEnd - 1; i >= 0; i--) {
				bulletArray[i].render(g);
			}
		}
		this.drawBound(g);
		super.render(g);
	}

	public void move(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setCurrentDirection(Sprite.UP);
			setFrameStrip(upFrameStrip);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setCurrentDirection(Sprite.DOWN);
			setFrameStrip(downFrameStrip);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.setCurrentDirection(Sprite.LEFT);
			setFrameStrip(leftFrameStrip);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.setCurrentDirection(Sprite.RIGHT);
			setFrameStrip(rightFrameStrip);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			try {
				this.addBullet(this, bulletType, this.getX(), this.getY());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	void addBullet(Tank parent, int type, int x, int y) throws IOException {
		/*
		 * 
		 * Use circle queue to manager bullet array, insert at bulletArrayEnd,
		 * remove at bulletArrayStart
		 */
		Bullet tmp = new Bullet(MainCanvas.t.getBulletImage(), 32, 32, parent, bulletArrayEnd);
		tmp.setType(type);
		bulletArray[bulletArrayEnd] = tmp;
		totalBullet++;
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
		for (int i=0; i<MainCanvas.tm.getTotalBrick(); i++) {
			if (MainCanvas.tm.getBrickArray()[i]!= null &&
					MainCanvas.t.isCollision(this, MainCanvas.tm.getBrickArray()[i])) {
				this.setPositionAndBound(lastX, lastY);
				break;
			}
		}
		if (MainCanvas.animationClock % 5 == 0 && this.isRunning())
			this.nextFrame();
		if (isFiring()) {
			for (int i = bulletArrayStart; i < totalBullet; i++)
				if (bulletArray[i] != null) bulletArray[i].update();
		}
	}
	
	public void setPositionAndBound(int x, int y){
		this.x = x;
		this.y = y;
		switch (this.getCurrentDirection()){
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

	public void cleanBullet(int index) {
		System.out.println("End = " + this.bulletArrayEnd);
		System.out.println("Index = " + index);
		this.bulletArray[index] = null;
		this.bulletArrayStart++;
		this.totalBullet--;
	}
	
	public int getBulletArrayStart(){
		return this.bulletArrayStart;
	}
}
