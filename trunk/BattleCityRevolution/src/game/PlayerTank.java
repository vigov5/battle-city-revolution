package game;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PlayerTank extends Tank {

	public boolean[] keyPressedState = { false, false, false, false };

	public PlayerTank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setTotalHealth(100);
		this.currentHealth = this.totalHealth;
	}

	public void keyPressedReact(KeyEvent e) {
		//System.out.println("Key pressed = " + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keyPressedState[Sprite.UP] = true;
			setCurrentDirection(Sprite.UP);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keyPressedState[Sprite.DOWN] = true;
			this.setCurrentDirection(Sprite.DOWN);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keyPressedState[Sprite.LEFT] = true;
			this.setCurrentDirection(Sprite.LEFT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyPressedState[Sprite.RIGHT] = true;
			this.setCurrentDirection(Sprite.RIGHT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.fire();
		}
	}

	public void update() {
		super.update();
		int count = 0;
		for (int i = 0; i < this.keyPressedState.length; i++) {
			if (this.keyPressedState[i]) {
				count++;
			}
		}

		for (int i = 0; i < this.keyPressedState.length; i++) {
			if (this.keyPressedState[i]) {
				if (count >= 2) {
					if (this.getCurrentDirection() != i) {
						this.setRunning(true);
					}
				}

				if (count == 1) {
					this.setCurrentDirection(i);
					this.setRunning(true);
				}
			}
		}
	}

	public void keyReleasedReact(KeyEvent e) {
		//System.out.println("Key released = " + e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.keyPressedState[Sprite.UP] = false;
			if (this.getCurrentDirection() == Sprite.UP)
				this.setRunning(false);
			break;
		case KeyEvent.VK_DOWN:
			this.keyPressedState[Sprite.DOWN] = false;
			if (this.getCurrentDirection() == Sprite.DOWN)
				this.setRunning(false);
			break;
		case KeyEvent.VK_LEFT:
			this.keyPressedState[Sprite.LEFT] = false;
			if (this.getCurrentDirection() == Sprite.LEFT)
				this.setRunning(false);
			break;
		case KeyEvent.VK_RIGHT:
			this.keyPressedState[Sprite.RIGHT] = false;
			if (this.getCurrentDirection() == Sprite.RIGHT)
				this.setRunning(false);
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}
}
