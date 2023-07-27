import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame() {
		this.setTitle("Box Collision Calculate Pi");
		this.setResizable(!false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GamePanel());
		this.setLocation(200,100);
		this.pack();
		this.setVisible(true);
	}
}
