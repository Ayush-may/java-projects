import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class paddle {
	
	int X;
	int Y;
	int width;
	int height;
	int speed;
	int velocityX ; 
	int velocityY ;
	boolean right = false;
	boolean dir = false;
	Rectangle player;
	Random random;
	
	paddle(int paddleX,int paddleY,int width,int height){
		this.X = paddleX;
		this.Y = paddleY;
		this.width = width;
		this.height = height;
		this.speed = 8;
		velocityX = speed;
		velocityY = speed;
		player = new Rectangle(paddleX, paddleY, width, height);
		random = new Random();
	}
	
	public void move() {
	
		X +=velocityX;
		Y +=velocityY;
				
		if(X > 1000 - 20) {
			velocityX = -speed;
		}
		else if(X < 0) {
			velocityX = speed;
		}
		if(Y > 500 - 20) {
			velocityY = -speed;
		}
		if(Y < 0) {
			velocityY = speed;
		}
	}
	
	public void ballCollideOnPaddle() {
		if(dir) {
			velocityX = speed;
			velocityY = -speed;
		}
		else {
			velocityX = speed;
			velocityY = speed;
		}
	}
	
	public void movePaddle() {
		if(Y < 0) {
			Y = 10;
		}
		if(Y > 370 ) {
			Y = 370;
		}
	}
	
	public void update() {
		if(Y < 0) {
		
			right = true;
		}
		if(X > 1000) {
		
			right = true;
		}
		if(Y>500) {
		
			right = true;
		}
		if(X < 0) {
		
			right = true;
		}

	}
	
	public void returnVelocityY(int bX, int paX) {
		int px = 30;
		if( paX+px*1 > bX ) {
			System.out.println("hellow 1");
			velocityY = -speed;
		}
		else if( paX+px*2 > bX ) {
			System.out.println("hellow 2");
			velocityY = -speed;
		}
		else if( paX+px*3 > bX ) {
			System.out.println("hellow 3");
			velocityY = speed;
		}
		else if( paX+px*4 > bX ) {
			System.out.println("hellow 4");
			velocityY = speed;
		} 
		
		move();
	}
	
	public Rectangle bounds() {
		return (new Rectangle(X,Y,width,height));
	}
	
}
