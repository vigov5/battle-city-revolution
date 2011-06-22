package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
	private ArrayList<Bullet> bulletArray;
	private int bulletType;
	private long bulletDelayTime = 50;
	private long lastBulletTime = 0;

	public Tank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setBound(0, 0, frameWidth, frameHeight);
		bulletType = Bullet.SMALL_BULLET;
		// create a null bullet array
		bulletArray = new ArrayList<Bullet>(MAX_BULLET);
		bulletArray.clear();
	}

	public void setRunning(boolean value) {
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
		this.drawBound(g);
		super.render(g);
	}

	public void move(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setCurrentDirection(Sprite.UP);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setCurrentDirection(Sprite.DOWN);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.setCurrentDirection(Sprite.LEFT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.setCurrentDirection(Sprite.RIGHT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			try {
				if (System.currentTimeMillis() - lastBulletTime > bulletDelayTime) {
					this.addBullet(this, bulletType, this.getX(), this.getY());
				}
				lastBulletTime = System.currentTimeMillis();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	void addBullet(Tank parent, int type, int x, int y) throws IOException {
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
		for (int i = 0; i < MainCanvas.tm.getTotalBrick(); i++) {
			if (MainCanvas.tm.getBrickArray()[i] != null
					&& MainCanvas.t.isCollision(this, MainCanvas.tm
							.getBrickArray()[i])) {
				this.setPositionAndBound(lastX, lastY);
				break;
			}
		}
		if (MainCanvas.animationClock % 5 == 0 && this.isRunning())
			this.nextFrame();
		if (isFiring()) {
			for (int i = 0; i < bulletArray.size(); i++){
					bulletArray.get(i).update();
					if (bulletArray.get(i).isDestroyed()){
						bulletArray.remove(i);
					}
			}
		}
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
	
	protected void setCurrentDirection(int direct){
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
}
