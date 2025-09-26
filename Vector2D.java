import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * @author murugan.kannan
 *
 */
public class Vector2D {
	
	double x, y, theta = 0.0,mag=0.0;
	Vector2D unit;
String name;
Vector2D beg,end;
	public Vector2D(double x, double y,String name,double angle,double mag) {
		this.name=name;
		this.x = x;
		this.y = y;
		theta=angle;
		this.mag=mag;
	}



	public Vector2D() {
		
	}



	public double dot(Vector2D vec2) {
		return this.x * vec2.x + this.y * vec2.y;
	}

	public Vector2D add(Vector2D vec2) {
		Vector2D res = new Vector2D();
		res.x = this.x + vec2.x;
		res.y = this.y + vec2.y;
		return res;
	}

	public Vector2D sub(Vector2D vec2) {
		Vector2D res = new Vector2D();
		res.x = this.x - vec2.x;
		res.y = this.y - vec2.y;
		res.name=vec2.name+this.name;
		res.beg=vec2;
		res.end=this;
		return res;
	}

	
	
/*	public double mod() {

		return Math.sqrt(x * x + y * y);
	}*/
	
	public void changeMod(double speed){

		mag+=speed;
		
	}

	public void changeAngle(double speed) {
		//angle();
		theta+=speed;
	
//System.out.println(x+" "+y+" "+theta);
	}
/*	public double angle() {
		double val = y / mod();
		theta = Math.toDegrees(Math.asin(val));
		return theta;
	}*/

	public void print() {
		System.out.println(x + "i + " + y + "j");
		System.out.println(theta);
		System.out.println(mag);

	}

public void move(int speed){
    y=(y+speed*Math.sin(Math.toRadians(theta)));
    x=(x+speed*Math.cos(Math.toRadians(theta)));
}

	public void changey(int speed) {
		y+=speed;
		
	}



	public void changex(int speed) {
		x+=speed;
		
	}
	
	public void setY(int y) {
		this.y=y;
		
	}
	
	public void setX(int x) {
		this.x=x;
		
	}
	
	public void drawVector(Graphics g, Vector2D vec) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		int endx, endy;
		endy = (int) (vec.y + vec.mag * Math.sin(Math.toRadians(vec.theta)));
		endx = (int) (vec.x + vec.mag * Math.cos(Math.toRadians(vec.theta)));
		g2.drawLine((int) vec.x, (int) vec.y, endx, endy);
	}
}
