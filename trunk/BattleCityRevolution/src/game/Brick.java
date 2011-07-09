package game;

import java.awt.image.BufferedImage;

public class Brick extends Sprite {

	private static int[][] frameStrip = {{ 0, 1, 2, 3 },
										{4, 5, 6, 7},
										{16, 17, 18, 19},
										{20, 21, 22, 23},
										{12, 13, 14, 15},
										{28, 29, 31, 31}};
	private int index;
	public final int RED_V_BRICK = 0;
	public final int RED_H_BRICK = 1;
	public final int BLUE_V_BRICK = 2;
	public final int BLUE_H_BRICK = 3;
	public final int WHITE_ROCK = 4;
	public final int GREEN_ROCK = 5;
	
	private int currentHealth;

	public Brick(BufferedImage image, int frameHeight, int frameWidth, int index, int type) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setFrameStrip(frameStrip[type]);
		this.index = index;
		this.setFrame(0);
		this.setBound(0, 0, frameWidth, frameHeight);
		this.currentHealth = 100;
	}

	public static boolean isBrick(int frameNumber) {
		for (int i = 0; i < frameStrip.length; i++)
			for (int j = 0; j < frameStrip[i].length; j++)
			if (frameNumber == frameStrip[i][j])
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

	public static int getBrickType(int c) {
		
		return 0;
	}
	
	
}
