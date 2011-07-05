package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class AITank extends Tank {

	private Random rnd;
	
	private boolean keepDoing = false;
	
	public AITank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		 rnd = new Random(new Date().getTime());
		 this.setCurrentDirection(Sprite.DOWN);
		 this.bulletDelayTime = 300;
		 this.setTotalHealth(100);
		 this.currentHealth = this.totalHealth;
	}
	
	public void think(){
		rnd = new Random(new Date().getTime());
		int i = 0;
		for (int j = 0; j<10; j++) i = rnd.nextInt(100);
		
		if (i % 37 == 0){
			// change direction
			this.setKeepDoing(false);
			this.setCurrentDirection(rnd.nextInt(4));
		}
		
		if (i < 50){
			this.setKeepDoing(true);
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

	public boolean isKeepDoing() {
		return keepDoing;
	}

	public void setKeepDoing(boolean keepDoing) {
		this.keepDoing = keepDoing;
	}
}
