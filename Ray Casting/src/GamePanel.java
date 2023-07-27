import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class GamePanel extends JPanel implements ActionListener, MouseMotionListener {
	
	public static final int SCREEN_SIZE = 500;
	private ArrayList<Boundary> bound;
	// private ArrayList<Ray> ray;
	private Timer timer;
	private int ballX, ballY;

	GamePanel(){
		this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		this.setBackground(Color.BLACK);
		this.addMouseMotionListener(this);
		bound = new ArrayList<>();
		bound.add(new Boundary(SCREEN_SIZE-100, 100, SCREEN_SIZE-100, SCREEN_SIZE-100));
		timer  = new Timer(5, this);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		for(int i=0; i<bound.size(); i++) {
			g.drawLine(bound.get(i).getX1(), bound.get(i).getY1(), bound.get(i).getX2(), bound.get(i).getY2());
		}
		g.drawLine(0,0,ballX, ballY);
		g.drawLine(SCREEN_SIZE, SCREEN_SIZE, ballX, ballY);
		g.drawLine(SCREEN_SIZE, 0, ballX, ballY);
		g.drawLine(0, SCREEN_SIZE, ballX, ballY);
		g.fillOval(ballX, ballY, 10, 10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ballX = e.getX();
		ballY = e.getY();
	}
	@Override
	public void mouseDragged(MouseEvent e) {}
}
