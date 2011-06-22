package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	protected static final int UP = 0;
	protected static final int LEFT = 1;
	protected static final int DOWN = 2;
	protected static final int RIGHT = 3;
	private BufferedImage currentImage;
	private BufferedImage fullImage;
	protected int x = 0;
	protected int y = 0;
	private int frameWidth;
	private int frameHeight;
	private int frameColNum;
	protected int currentFrame;
	protected int currentIndex;
	protected int currentDirection;
	protected int[] frameStrip;
	protected int rectangleBoundX;
	protected int rectangleBoundY;
	protected int rectangleBoundHeight;
	protected int rectangleBoundWidth;
	
	public void setFrame(int frameNumber) {
		int offsetX = frameNumber%frameColNum;
		int offsetY = frameNumber/frameColNum;
		this.currentFrame = frameNumber;
		this.currentImage = fullImage.getSubimage(offsetX*frameWidth, offsetY*frameHeight, frameWidth, frameHeight);
	}
	
	public Sprite(BufferedImage image, int frameHeight, int frameWidth) {
		this.fullImage = image;
		if (fullImage.getHeight() % frameHeight != 0) {
			System.out.println("Invaild height = " + fullImage.getHeight()
					+ ", or frameHeight = " + frameHeight);
		}
		if (fullImage.getWidth() % frameWidth != 0) {
			System.out.println("Invaild width = " + fullImage.getWidth()
					+ ", or frameWidth = " + frameWidth);
		}
		this.frameColNum =  fullImage.getWidth()/frameWidth;
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.currentIndex = 0;
		this.setFrame(0);
	}

	public void render(Graphics g) {
		this.setFrame(this.currentFrame);
		g.drawImage(this.currentImage, x, y, frameWidth, frameHeight, null);
	}
	
	public void nextFrame(){
		this.currentIndex++;
		if (this.currentIndex == this.frameStrip.length) this.currentIndex = 0;
		this.currentFrame = this.frameStrip[currentIndex];
		//System.out.println("Current Frame = " + this.getCurrentFrame());
		this.setFrame(this.currentFrame);
	}
	
	public int getCurrentFrame(){
		return this.currentFrame;
	}
	
	public void setPositionAndBound(int x, int y){
		this.x = x;
		this.y = y;
		this.setBound(0, 0, frameWidth, frameHeight);
	}
	
	protected void setCurrentDirection(int direct) {
		currentDirection = direct;
		
	}
	
	protected int getCurrentDirection(){
		return currentDirection;
	}
	
	protected void setFrameStrip(int[] frames){
		this.frameStrip = frames;
	}
	
	protected void setBound(int dx, int dy, int width, int height){
		/*
		 *dx, dy = distance from x, y
		 */
		this.rectangleBoundX = this.x + dx;
		this.rectangleBoundY = this.y + dy;
		this.rectangleBoundWidth = width;
		this.rectangleBoundHeight = height;
	}
	
	public int getBoundX(){
		return this.rectangleBoundX;
	}
	
	public int getBoundY(){
		return this.rectangleBoundY;
	}
	
	public int getBoundHeight(){
		return this.rectangleBoundHeight;
	}
	
	public int getBoundWidth(){
		return this.rectangleBoundWidth;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getHeight(){
		return this.frameHeight;
	}
	
	public int getWidth(){
		return this.frameWidth;
	}
	
	public void printData(){
		System.out.println(this.getClass() + " ,x = " + this.getX() + " ,y = " + this.getY() + " ,BoundX = " + this.getBoundX()
				+ " ,BoundY = " + this.getBoundY() + " , BoundW = " + this.getBoundWidth() + " ,BoundH = " + this.getBoundHeight());
	}
	
	public void drawBound(Graphics g){
		g.drawRect(this.getBoundX(), this.getBoundY(), this.getBoundWidth(), this.getBoundHeight());
	}
}
