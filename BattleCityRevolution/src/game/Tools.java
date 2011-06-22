package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tools {
	
	public static BufferedImage tankImage = null;
	public static BufferedImage bulletImage = null;
	private static BufferedImage tileImage = null;
	private static BufferedImage explosionImage = null;
	
	public BufferedImage getTankImage() throws IOException {
		if (tankImage == null){
			tankImage = ImageIO.read(new File("tank1.png"));
		}
		return tankImage;
	}

	public BufferedImage getBulletImage() throws IOException {
		if (bulletImage == null){
			bulletImage = ImageIO.read(new File ("bullet.png"));
		}
		return bulletImage;
	}
	
	public BufferedImage getTileImage() throws IOException {
		if (tileImage  == null){
			tileImage = ImageIO.read(new File("tileset1.png"));
		}
		return tileImage;
	}
	
	public BufferedImage getExplosionImage() throws IOException {
		if (explosionImage  == null){
			explosionImage = ImageIO.read(new File("explosion1.png"));
		}
		return explosionImage;
	}
	
	public boolean isCollision(Sprite a, Sprite b){
		//System.out.println("a = " + a.getClass() + ", b = " + b.getClass());
		//a.printData();
		//b.printData();
		if (a.getBoundX() + a.getBoundWidth() < b.getBoundX() ||
			a.getBoundX() > b.getBoundX() + b.getBoundWidth() ||
			a.getBoundY() + a.getBoundHeight() < b.getBoundY() ||
			a.getBoundY() > b.getBoundY() + b.getBoundHeight() 
			) return false;		
		return true;
	}
}




