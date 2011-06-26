package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class AITank extends Tank {

	Random rnd;
	
	private boolean keepDoing = false;
	
	public AITank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		 rnd = new Random(new Date().getTime());
		 this.setCurrentDirection(Sprite.DOWN);
		 this.bulletDelayTime = 300;
	}
	
	public void think(){
		int i = rnd.nextInt(100);
		
		if (i % 37 == 0){
			// change direction
			this.keepDoing = false;
			this.setCurrentDirection(rnd.nextInt(4));
		}
		
		if (i < 50){
			this.keepDoing = true;
			if (this.pathBlocked) {
				this.fire();
				this.setCurrentDirection(rnd.nextInt(4)); 
			}
		}
		
		if (i % 11 == 0) fire();
	}
	
	public void fire(){
		this.setRunning(false);
		try {
			if (System.currentTimeMillis() - lastBulletTime > bulletDelayTime) {
				this.addBullet(this, bulletType);
			}
			lastBulletTime = System.currentTimeMillis();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setRunning(true);
	}
}
