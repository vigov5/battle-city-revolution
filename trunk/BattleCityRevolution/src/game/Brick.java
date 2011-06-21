package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick extends Sprite {

	private static int[] frameStrip = { 0, 1, 2, 3 };
	private boolean isDestroyed = false;
	private int index;

	public Brick(BufferedImage image, int frameHeight, int frameWidth, int index) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setFrameStrip(frameStrip);
		this.index = index;
		this.setFrame(0);
		this.setBound(0, 0, frameWidth, frameHeight);
	}

	public static boolean isBrick(int frameNumber) {
		for (int i = 0; i < frameStrip.length; i++)
			if (frameNumber == frameStrip[i])
				return true;
		return false;
	}

	public void update() {
		if (this.getCurrentFrame() != frameStrip.length - 1) {
			this.nextFrame();
		} else {
			isDestroyed = true;
			MainCanvas.tm.cleanBrick(this.index);
		}
	}
}
