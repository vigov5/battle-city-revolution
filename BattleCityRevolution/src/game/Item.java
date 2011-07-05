package game;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Item extends Sprite {

	public final int BIG_BULLET = 0;
	public final int MISSILE_BULLET = 1;
	public final int LIVE = 2;
	public final int SHOVEL = 3;
	public final int WRECH = 4;
	public final int THUNDER_BOOST = 5;
	public final int CLOCK = 6;
	public final int BOMB = 7;
	private int type;

	public Item(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setFrameStrip(new int[] { 0, 1, 2, 3, 4, 5, 6 });
	}

	public void applyEffect() {
		switch (type) {
		case CLOCK:
			break;
		case BOMB:
			break;
		case SHOVEL:
			break;
		}
	}

	public void applyEffect(Tank a) {
		switch (type) {
		case BIG_BULLET:
		case MISSILE_BULLET:
			a.setBulletType(type - 1);
			break;
		case WRECH:
			if (a.getCurrentHealth() + 50 >= a.totalHealth) {
				a.setCurrentHealth(a.totalHealth);
			} else
				a.setCurrentHealth(a.getCurrentHealth() + 50);
			break;
		case THUNDER_BOOST:
			a.setSpeedStep(4);
			Timer t = new Timer();
			t.schedule(new ToDoTank(this, a), 10000);
			break;
		case CLOCK:
			if (a instanceof PlayerTank) {
				for (int i = 0; i < MainCanvas.tankArray.size(); i++) {
					if (MainCanvas.tankArray.get(i) instanceof AITank) {
						((AITank) MainCanvas.tankArray.get(i)).setFrezzed(true);
					}
				}
			}
			t = new Timer();
			t.schedule(new ToDoTank(this, a), 10000);
			break;
		case LIVE:
			System.out.println("Add one live");
			break;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.setFrame(type);
	}

	class ToDoTank extends TimerTask {

		private Item item;
		private Tank tank;

		public ToDoTank(Item x, Tank y) {
			this.item = x;
			this.tank = y;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			switch (item.type) {
			case THUNDER_BOOST:
				tank.setSpeedStep(2);
				break;
			case CLOCK:
				for (int i = 0; i < MainCanvas.tankArray.size(); i++) {
					if (MainCanvas.tankArray.get(i) instanceof AITank) {
						((AITank) MainCanvas.tankArray.get(i)).setFrezzed(false);
					}
				}
			}
		}
	}
}
