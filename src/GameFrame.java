import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{
	
	GamePanel panel = new GamePanel();
	
	GameFrame() {
		panel = new GamePanel();
		// Add panel to frame
		this.add(panel);
		// Set title
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		// Will close out of application when hit x
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Makes window frame fit game panel
		this.pack();
		this.setVisible(true);
		// Set it to appear in centre of screen
		this.setLocationRelativeTo(null);
	}
}
