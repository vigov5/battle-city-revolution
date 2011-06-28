
package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerTank extends Tank {

	public PlayerTank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setTotalHealth(100);
		this.currentHealth = this.totalHealth;
	}

	public void move(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setCurrentDirection(Sprite.UP);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setCurrentDirection(Sprite.DOWN);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.setCurrentDirection(Sprite.LEFT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.setCurrentDirection(Sprite.RIGHT);
			this.setRunning(true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
		}
	}
}
