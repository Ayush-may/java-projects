import javax.swing.*;

public class GameFrame extends JFrame {
	GameFrame(){
		this.add(new GamePanel());
		this.setTitle("Sorting Visualizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100,100);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
