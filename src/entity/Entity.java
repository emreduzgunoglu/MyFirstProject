package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
	
	public int x;
	public int y;
	public int speed;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, bait;
	public DirectionEnum direction;

	public Rectangle invisibleRect;
	public boolean collisionOn = false;

	public enum DirectionEnum {
		NONE, UP, DOWN, LEFT, RIGHT
	}
	
}
