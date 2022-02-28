import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
	
	// Set size of table at a 5/9 ratio or 0.5555
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	
	// Set size of ball and paddles
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	// Declare instance variables
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	// Constructor
	GamePanel() {
		// Create new paddles, ball and score
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		// Make panel focusable for key strokes
		this.setFocusable(true);
		// Add an action listener to listen for key strokes
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		// Initialize and start new thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		// Create a new ball and set it in the center of the window
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void newPaddles() {
		// Place left and right paddles in center of y axis
		paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	public void paint(Graphics g) {
		// Create image with same height and width as game panel
		image = createImage(getWidth(), getHeight());
		// Create a graphic
		graphics = image.getGraphics();
		// Call draw method to draw components
		draw(graphics);
		// Draw image in top left corner
		g.drawImage(image, 0, 0, this);
		
	}
	
	public void draw(Graphics g) {
		// Call the paddles and ball draw methods
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		// Call each paddles move method to be called in the game loop
		paddle1.move();
		paddle2.move();
		// Call the balls move method
		ball.move();
	}
	
	public void checkCollision() {
		
		// Bounce ball of top and bottom edges of window
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		// Bounce ball of paddle 1
		if (ball.intersects(paddle1)) {
			// Turn negative ball x velocity positive
			ball.xVelocity = Math.abs(ball.xVelocity);
			// Add more speed to x velocity - optional
			ball.xVelocity ++;
			// Add more speed to y velocity - optional
			if (ball.yVelocity > 0) {
				ball.yVelocity ++;
			} else {
				ball.yVelocity --;
			}
			// Set direction equal to velocity
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// Bounce ball of paddle 2
		if (ball.intersects(paddle2)) {
			// Turn negative ball x velocity positive
			ball.xVelocity = Math.abs(ball.xVelocity);
			// Add more speed to x velocity - optional
			ball.xVelocity ++;
			// Add more speed to y velocity - optional
			if (ball.yVelocity > 0) {
				ball.yVelocity ++;
			} else {
				ball.yVelocity --;
			}
			// Set x direction as negative
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// Stop paddles at window edges
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if (paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle1.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		}
		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if (paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
			paddle2.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		}
		
		// Give a player 1 point and create new paddles and ball
		if (ball.x <= 0) {
			// Give player 2 a point if ball hits left window border
			score.player2 ++;
			newPaddles();
			newBall();
			System.out.println("Player2: " + score.player2);
		}
		if (ball.x >= (GAME_WIDTH-BALL_DIAMETER)) {
			// Give player 1 a point if ball hits right window border
			score.player1 ++;
			newPaddles();
			newBall();
			System.out.println("Player1: " + score.player1);
		}
	}
	
	public void run() {
		// Game loop to run at 60 frames per second
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				// Calls each paddles move methods
				move();
				// Resets y if it hits window border
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// Call key pressed method with event
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			
		}
		
		public void keyReleased(KeyEvent e) {
			// Call key released method with event
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
