package game;

import java.awt.image.BufferedImage;

public class Explosion extends Sprite {
	
	private boolean destroyed;

	public Explosion(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setFrameStrip(new int[]{0, 1, 2, 3, 4, 5, 6});
		this.setFrame(this.frameStrip[0]);
	}
	
	public void update(){
		if (this.currentFrame == this.frameStrip.length - 1){
			this.destroyed = true;
		} else if (MainCanvas.animationClock % 5 == 0) this.nextFrame();
	}
	
	public boolean isDestroyed(){
		return this.destroyed;
	}
}
