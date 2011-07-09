package game;

import java.awt.image.BufferedImage;

public class Brick extends Sprite {

	private static int[] frameStrip = { 0, 1, 2, 3 };
	private int index;
	private int currentHealth;

	public Brick(BufferedImage image, int frameHeight, int frameWidth, int index) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setFrameStrip(frameStrip);
		this.index = index;
		this.setFrame(0);
		this.setBound(0, 0, frameWidth, frameHeight);
		this.currentHealth = 100;
	}

	public static boolean isBrick(int frameNumber) {
		for (int i = 0; i < frameStrip.length; i++)
			if (frameNumber == frameStrip[i])
				return true;
		return false;
	}

	public void update() {
		if (this.currentHealth > 75){
			this.setFrame(0);
		} else if (this.currentHealth > 50){
			this.setFrame(1);
		} else if (this.currentHealth > 25){
			this.setFrame(2);
		} else if (this.currentHealth > 0){
			this.setFrame(3);
		} else {
			this.setDestroyed(true);
			MainCanvas.tm.cleanBrick(this.index);
		}
	}

	public void addDamage(int damage) {
		// TODO Auto-generated method stub
		if (this.currentHealth - damage >= 0){
			this.currentHealth -= damage;
		} else this.currentHealth = 0;
	}
	
	
}
