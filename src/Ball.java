import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{
	
	// Declare instance variables
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Ball(int x, int y, int width, int height ) {
		super(x, y, width, height);
		// Create a random object
		random = new Random();
		
		// Set direction left or right depending on whether the random number is 0 or 1
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0) {
			randomXDirection --;		
		}
		setXDirection(randomXDirection*initialSpeed);
		
		// Set direction up or down depending on whether the random number is 0 or 1
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0) {
			randomYDirection --;		
		}
		setYDirection(randomYDirection*initialSpeed);
	}
	
	public void setXDirection(int randomXDirection) {
		// Set x direction equal to the new random direction
		xVelocity = randomXDirection;
	}
	
	public void setYDirection(int randomYDirection) {
		// Set y direction equal to the new random direction
		yVelocity = randomYDirection;
	}
	
	public void move() {
		// Move the ball by updating its x and y position
		x += xVelocity;
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		// Draw ball and color white
		g.setColor(Color.white);
		// Turn ball into a circle
		g.fillOval(x, y, height, width);
	}
}
