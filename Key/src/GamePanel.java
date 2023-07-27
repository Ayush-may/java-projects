import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements MouseMotionListener , KeyListener , ActionListener{

	
	//Dimension of Window
	int height = 500;
	int width = 1000;
	
	//Dimension of paddle
	int playerX  = 10;
	int playerY = 120;
	int ballSize = 20;
	
	//Coordinate of paddle in the screen 
	int paddleX = 5;
	int paddleY = height/2;
	int ballX ;
	int ballY ;
	
	//Some stuffs
	boolean collision = false;
	Thread gameThread;
	Random random ;
	paddle player ;
	paddle ball ;
	Timer timer;
	
	GamePanel(){
		this.setLayout(null);
		this.setBackground(Color.BLACK);
//		this.addMouseMotionListener(this);
		this.setPreferredSize(new Dimension(1000,500));
		this.setFocusable(true);
		this.addKeyListener(this);
		
		timer = new Timer(5, this);
		random =  new Random();
		ballX = random.nextInt(200);
		ballY = random.nextInt(200);
		
		player = new paddle(paddleX, paddleY, playerX, playerY);
		ball = new paddle(ballX, ballY, ballSize, ballSize);
		//starting thread
//		gameThread = new Thread(this);
//		gameThread.start();
		timer.start();
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		//Drawing the Ball
		g2.setColor(Color.WHITE);
		//drawing Dashed Line in the middle of the screen
		
		Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	    g2.setStroke(dashed);
	    g2.drawLine(505, 0, 505, 505);
	    
	    //drawing paddle and ball
		g2.fillOval(ball.X,ball.Y,ballSize,ballSize);
		g2.fillRect(player.X,player.Y,playerX,playerY);
	}

	public void mouseMoved(MouseEvent e) {
		player.Y = e.getY() -60;
//		ball.X = e.getX();
//		ball.Y = e.getY();
	}

	public void checkCollision(){
		Rectangle rect1 = player.bounds();
		Rectangle rect2 = ball.bounds();
		
		if(calculateBallXDir()) {
			//right
			if(rect1.intersects(rect2)) {
				System.out.println(rect2.getY());
				ball.right = true;
				
				//down to up
				ball.dir = YCoordinateDec();
				ball.ballCollideOnPaddle(); 
			}	
		}
		else {
			//left
			if(rect1.intersects(rect2)) {
				System.out.println("Collide2");
//				ball.returnVelocityY((int)rect2.getY(),(int)rect1.getY() );
				ball.right = false;
				ball.ballCollideOnPaddle();
			}			
		}
		
	}
	
	
	//This run method renders the window/game everytime 
	//and this is override method of runnable interface
//	public void run() {
//		//game loop
//		long lastTime = System.nanoTime();
//		double amountOfTicks =60.0;
//		double ns = 1000000000 / amountOfTicks;
//		double delta = 0;
//		while(true) {
//			long now = System.nanoTime();
//			delta += (now -lastTime)/ns;
//			lastTime = now;
//			if(delta >=1) {
//				checkCollision();
//				ball.move();
//				ball.update();
////				player.movePaddle();
////				repaint();
//				delta--;
//			}
//		}
//	}
	
	public boolean YCoordinateDec() {
		if(ball.velocityY < 0) {
			return true;
		}
		return false;
	}
	
	public boolean calculateBallXDir() {
		if(ball.X < paddleX ) {
			return false;
		}
		return true;
	}
	
	public void mouseDragged(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 38) {
			player.Y -=50;
		}
		if(e.getKeyCode() == 40) {
			player.Y +=50;
		}
		if(e.getKeyCode() == 39) {
			System.out.println("39");
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38) {
				player.Y -=50;	
				repaint();
			}
		if(e.getKeyCode() == 40) {
				player.Y +=50;
				repaint();
			}
		}		

	public void keyTyped(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent arg0) {
		checkCollision();
		ball.move();
		ball.update();
		repaint();	
	}
}
