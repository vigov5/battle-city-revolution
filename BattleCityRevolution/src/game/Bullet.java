package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite {

	static final int SMALL_BULLET = 0;
	static final int BIG_BULLET = 1;
	static final int MISSILE_BULLET = 2;
	private int speed = 5;
	private int currentType;
	private Tank parent;
	private int index;
	private boolean destroyed = false;

	public Bullet(BufferedImage image, int frameHeight, int frameWidth,
			Tank parent, int index) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setCurrentDirection(parent.getCurrentDirection());
		this.parent = parent;
		this.setPositionAndBound(parent.getX(), parent.getY());
		this.currentType = SMALL_BULLET;
		this.setFrame(4);
		this.index = index;
	}

	public void setParent(Tank t) {
		this.parent = t;
	}

	public void render(Graphics g) {
		if (!isDestroyed()) {
			super.render(g);
			this.drawBound(g);
		}
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setType(int type) {
		this.currentType = type;
	}

	public void update() {
		switch (this.getCurrentDirection()) {
		case Sprite.UP:
			this.setPositionAndBound(this.x, this.y - speed);
			break;
		case Sprite.DOWN:
			this.setPositionAndBound(this.x, this.y + speed);
			break;
		case Sprite.LEFT:
			this.setPositionAndBound(this.x - speed, this.y);
			break;
		case Sprite.RIGHT:
			this.setPositionAndBound(this.x + speed, this.y);
			break;
		}
		if (this.index == parent.getBulletArrayStart()) {
			for (int i = 0; i < MainCanvas.tm.getTotalBrick(); i++) {
				if (MainCanvas.tm.getBrickArray()[i] != null
						&& MainCanvas.t.isCollision(this, MainCanvas.tm
								.getBrickArray()[i])) {
					this.destroy();
					MainCanvas.tm.getBrickArray()[i].update();
					break;
				}
			}
		}
	}

	public void setPositionAndBound(int x, int y) {
		this.x = x;
		this.y = y;
		this.setBound(13, 13, 6, 6);
	}

	public void destroy() {
		this.destroyed = true;
		parent.cleanBullet(this.index);
	}
}
