import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GameFrame(){
		this.setResizable(false);
		this.add(new GamePanel());
		this.setLocation(200,100);
		this.setTitle("Ray Casting");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
