package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Map file
 * 
 * 
 */
public class TileManager {
	private final int MAP_WIDTH = 25;
	private final int MAP_HEIGHT = 17;

	private static Brick[] brickArray = new Brick[100];
	private int totalBrick = 0;
	private BufferedImage background;

	public TileManager(String mapFile) throws IOException {
		background = MainCanvas.t.getTileImage().getSubimage(64, 32, 32, 32);
		for (int i = 0; i < 100; i++)
			brickArray[i] = null;
		readMap(mapFile);
	}

	public void readMap(String mapName) {
		try {
			FileInputStream in = new FileInputStream(mapName);
			for (int i = 0; i < MAP_HEIGHT; i++) {
				for (int j = 0; j < MAP_WIDTH; j++) {
					int c = in.read();
					// System.out.println("c  = " + c);
					if (Brick.isBrick(c)) {
						brickArray[totalBrick] = new Brick(MainCanvas.t
								.getTileImage(), 32, 32, totalBrick);
						brickArray[totalBrick].setPositionAndBound(j * 32,
								i * 32);
						totalBrick++;
					}
				}
			}
			// System.out.print("total = " + totalBrick);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int j = 0; j < MAP_WIDTH; j++) {
				g.drawImage(background, j * 32, i * 32, null);
			}
		}
		for (int i = 0; i < totalBrick; i++)
			if (brickArray[i] != null)
				brickArray[i].render(g);
	}

	public Brick[] getBrickArray() {
		return brickArray;
	}

	public int getTotalBrick() {
		return totalBrick;
	}

	public void cleanBrick(int index) {
		// TODO Auto-generated method stub
		this.brickArray[index] = null;
	}
}
