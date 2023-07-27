import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel implements ActionListener, Runnable {
	
	private static final int SCREEN_HEIGHT = 500;
	private static final int SCREEN_WIDTH = 1000;
	private final int delay = 0;
	private Box smallBox, bigBox;
	private JLabel collTxt;
	private JLabel cntTxt;
	private JLabel bigM, smallM;
	private Timer timer;
	private Thread gameThread;
	private Rectangle2D.Double smallRect, bigRect;
	private int cnt;
	private float timeStamp;
	private long massBig;
	private int massSmall;
	
	
	File file ;
	AudioInputStream audioStream ;
	Clip clip ;
	
	GamePanel(){
	
		this.setLayout(null);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		timeStamp = 1000;
		int digit = 5;
		float speed = 1f;
		double vel = speed/timeStamp;
		massBig = (long) Math.pow(100, digit);
		massSmall = 1;
		smallBox = new Box(50, 100, SCREEN_HEIGHT-100, 0, massSmall);
		bigBox = new Box(100, 400, SCREEN_HEIGHT-150, -vel, massBig);
		
		collTxt = new JLabel("Collision : ");
		cntTxt = new JLabel( cnt + "" );
		bigM = new JLabel(massBig + " Kg");
		smallM = new JLabel(massSmall + " Kg");
		
		this.add(collTxt);
		this.add(cntTxt);
		this.add(bigM);
		this.add(smallM);
		
		
		smallM.setBounds((int) smallBox.getX(),(int) smallBox.getY(),200,50);
		
		collTxt.setFont(new Font("Arial", Font.PLAIN, 25));
		collTxt.setForeground(Color.WHITE);
		collTxt.setBounds(100,100,200,50);
		
		cntTxt.setFont(new Font("Arial", Font.PLAIN, 25));
		cntTxt.setForeground(Color.WHITE);
		cntTxt.setBounds(250,100,200,50);
		
		
		timer = new Timer(delay, this);
		timer.start();
		gameThread = new Thread(this);
		gameThread.start();
	
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g.create();  
		
		Shape shape = smallRect = new Rectangle2D.Double(smallBox.getX(),smallBox.getY(),smallBox.getSize(),smallBox.getSize());
		Shape shape2 = bigRect = new Rectangle2D.Double(bigBox.getX(),bigBox.getY(),bigBox.getSize(),bigBox.getSize());
		
		g2d.setColor(Color.BLUE);
		g2d.fill(shape);
		g2d.setColor(Color.WHITE);
		g2d.draw(smallRect);
		
		g2d.setColor(Color.RED);
		g2d.fill(shape2);
		g2d.setColor(Color.WHITE);
		g2d.draw(bigRect);
		
		g.setColor(Color.WHITE);
		g.drawLine(50,50,50,SCREEN_HEIGHT-50); //Vertical Line
		g.drawLine(50,SCREEN_HEIGHT-50,SCREEN_WIDTH,SCREEN_HEIGHT-50);  //Horizontal Line
	
	}

	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				
				for(int i=0; i<timeStamp; i++) {
					
					smallM.setForeground(Color.WHITE);
					smallM.setBounds((int) smallBox.getX()+10,(int) smallBox.getY()-50,200,50);
					
					bigM.setForeground(Color.WHITE);
					bigM.setFont(new Font("Arial", Font.BOLD, 18));
					bigM.setBounds((int) bigBox.getX()+5,(int) bigBox.getY()-50,200,50);
					
					if(smallBox.checkCollision(bigBox)) {
						
						float v1 = bigBox.bounce(smallBox);
						float v2 = smallBox.bounce(bigBox);
						
						bigBox.setVel(v1);
						smallBox.setVel(v2);
						cnt++;
					}
					
					if(smallBox.checkHitCollision()) {
						cnt++;
					}
					bigBox.updateSpeed();
					smallBox.updateSpeed();
				}
				
				cntTxt.setText(cnt+"");
				
				repaint();
				delta--;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		if(smallBox.checkCollision(bigBox)) {
//			
//			System.out.println("here");
//			
//			float v1 = bigBox.bounce(smallBox);
//			float v2 = smallBox.bounce(bigBox);
//			
//			System.out.println(v1+"  "+v2);
//			
//			bigBox.setVel(v1);
//			smallBox.setVel(v2);
//			cnt++;
//		}
////		smallBox.checkCollision(bigBox);
//		if(smallBox.checkHitCollision()) {
//			cnt++;
//		}
//		bigBox.updateSpeed();
//		smallBox.updateSpeed();
//		System.out.println(cnt);
//		cntTxt.setText(cnt+"");
//		repaint();
	}
	
	
	
}
