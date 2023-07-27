
public class Box {
	private int boxSize;
	private double boxVelocity;
	private float boxX, boxY;
	private long mass;
	int cnt;
	
	Box(int size, int x, float y, double d, long mass2){
		this.boxSize = size;
		this.boxX = x;
		this.boxY = y;
		this.boxVelocity = d;
		this.mass = mass2;
	}
	
	public Boolean checkCollision(Box other) {
		return !((this.getX()+this.getSize() < other.getX() || this.getX() > other.getX() + other.getSize())); 		
	}
	
	public boolean checkHitCollision() {
		if(this.boxX <= 50) {
			this.boxVelocity *= -1;
			return true;
		}
		return false;
	}
	
	public float bounce(Box other) {
		float sumM = this.getMass() + other.getMass();
		float newV = (float) ((this.getMass() - other.getMass())/sumM * this.boxVelocity);
		newV += ((2 * other.getMass())/sumM) * other.boxVelocity;
		
		return newV;
	}
	
	public float updateSpeed() {
		boxX += boxVelocity;
		return boxX; 
	}
	
//	public int getMid() {
//		return (boxX+(boxSize/2));
//	}
	
	public float getX() {
		return boxX;
	}
	
	public float getY() {
		return boxY;
	}
	
	public float getVelocity() {
		return (float)boxVelocity;
	}
	
	public long getMass() {
		return mass;
	}
	
	public int getSize() {
		return boxSize;
	}
	
	public void setVel(float x) {
		this.boxVelocity = x;
	}
}
