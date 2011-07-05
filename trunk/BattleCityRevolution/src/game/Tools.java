package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tools {
	
	public static BufferedImage tankOneImage = null;
	public static BufferedImage bulletImage = null;
	private static BufferedImage tileImage = null;
	private static BufferedImage explosionImage = null;
	private static BufferedImage tankTwoImage;
	private MainCanvas mc;
	private BufferedImage itemImage;
	
	public Tools(MainCanvas mc){
		this.mc = mc;
	}
	
	public BufferedImage getTankOneImage() throws IOException {
		if (tankOneImage == null){
			tankOneImage = ImageIO.read(new File("tank1.png"));
		}
		return tankOneImage;
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
	
	public BufferedImage getTankTwoImage() throws IOException {
		if (tankTwoImage == null){
			tankTwoImage = ImageIO.read(new File("tank2.png"));
		}
		return tankTwoImage;
	}
	
	public BufferedImage getExplosionImage() throws IOException {
		if (explosionImage  == null){
			explosionImage = ImageIO.read(new File("explosion1.png"));
		}
		return explosionImage;
	}
	
	public BufferedImage getItemImage() throws IOException {
		if (itemImage  == null){
			itemImage = ImageIO.read(new File("items.png"));
		}
		return itemImage;
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
	
	public boolean isOutScreen(Sprite a){
		if (a.getBoundX() < 0 ||
			a.getBoundY() < 0 ||
			a.getBoundY()+ a.getBoundHeight() > mc.getHeight() ||
		    a.getBoundX() + a.getBoundWidth() > mc.getWidth()) {
		return true;
		}
		return false; 
	}
}




