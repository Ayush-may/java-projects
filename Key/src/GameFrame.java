import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GameFrame(){
		this.setTitle("KeyPractice");
		this.add(new GamePanel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
		
//		//THIS IS HIDES THE CURSOR 
//		// just for Experiments 
//		
//		// Transparent 16 x 16 pixel cursor image.
//		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
//
//		// Create a new blank cursor.
//		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//		    cursorImg, new Point(0, 0), "blank cursor");
//
//		// Set the blank cursor to the JFrame.
//		this.getContentPane().setCursor(blankCursor);
	}
	
}
