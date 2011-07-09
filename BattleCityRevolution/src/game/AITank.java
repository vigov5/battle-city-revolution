package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class AITank extends Tank {

	private Random rnd;

	private boolean keepDoing = false;

	private boolean frezzed = false;
	
	int level = 1;

	public AITank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		rnd = new Random(new Date().getTime());
		this.setCurrentDirection(Sprite.DOWN);
		this.bulletDelayTime = 300;
		this.setTotalHealth(100);
		this.currentHealth = this.totalHealth;
	}

	public void think() {
		if (frezzed){
			this.setRunning(false);
		}
		else if (getLuck()){
			int playerx = MainCanvas.tankArray.get(0).getBoundX();
			int playery = MainCanvas.tankArray.get(0).getBoundY();
			int tankx = this.getBoundX();
			int tanky = this.getBoundY();
			
			if (tankx - 32 <= playerx && playerx <= tankx + 32){
				if (tanky <= playery) {
					this.setCurrentDirection(Sprite.DOWN);
					this.fire();
				}
				else if (tanky > playery ){
					this.setCurrentDirection(Sprite.UP);
					this.fire();
				}
			}
			else if (tanky - 32 <= playery && playery <= tanky + 32){
				if (tankx <= playerx){
					this.setCurrentDirection(Sprite.RIGHT);
					this.fire();
				}
				else if (tankx > playerx){
					this.setCurrentDirection(Sprite.LEFT);
					this.fire();
				}
			}
		}
		else RandomAction();
	}
	
	public boolean getLuck(){
		rnd = new Random(new Date().getTime());
		if (level + rnd.nextInt(10) > 5) return true;
		return false ;
	}
	
	public void RandomAction(){
		rnd = new Random(new Date().getTime());
		int i = 0;
		for (int j = 0; j < 10; j++)
			i = rnd.nextInt(100);

		if (i % 37 == 0) {
			// change direction
			this.setKeepDoing(false);
			this.setRunning(true);
			this.setCurrentDirection(rnd.nextInt(4));
		}

		if (i < 50) {
			this.setRunning(true);
			this.setKeepDoing(true);
			if (this.pathBlocked) {
				this.fire();
				this.setCurrentDirection(rnd.nextInt(4));
			}
		}

		if (i % 11 == 0)
			fire();
	}

	public boolean isKeepDoing() {
		return keepDoing;
	}

	public void setKeepDoing(boolean keepDoing) {
		this.keepDoing = keepDoing;
	}
	
	public void setFrezzed(boolean value){
		this.frezzed = value;
	}
}
