import java.awt.*;
import java.awt.event.*;


public class Paddle extends Rectangle{
	
	// Declare instance variables
	int id;
	int yVelocity;
	int speed = 10;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		// Call super constructor to assign values
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		// Check to see if paddle 1 or 2 are being moved
		switch(id) {
		case 1: 
			// Set padde1 y direction and call move, w for up and s for down
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2: 
			// Set paddle2 y direction and call move, up for up and down for down
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		// Stop paddles if they arent being moved
				switch(id) {
				case 1: 
					if (e.getKeyCode() == KeyEvent.VK_W) {
						setYDirection(0);
						move();
					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						setYDirection(0);
						move();
					}
					break;
				case 2: 
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						setYDirection(0);
						move();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						setYDirection(0);
						move();
					}
					break;
				}
	}
	
	public void setYDirection(int yDirection) {
		// Set velocity variable equal to direction
		yVelocity = yDirection;
	}
	
	public void move() {
		// Update the y cordinate of the paddles to move
		y= y + yVelocity;
	}
	
	public void draw(Graphics g) {
		// Make player 1 blue and player 2 red
		if (id == 1) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.red);
		}
		// Fill in rectangle
		g.fillRect(x, y, width, height );
	}
}
