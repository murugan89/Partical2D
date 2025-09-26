
/**
 * @author murugan.kannan
 *
 */

import java.awt.Color;
import java.awt.Graphics;


public class bullet extends Vector2D {
	char bulletType;
	int size;
	double v=3,vx,vy,t=0.0,g=-0.98,incTime=0.1;
	//double g=-0.98;
public bullet(double x, double y, double theta, double mag, char bulletType,int size,double t) {
		this.x=x;
		this.y=y;
		this.theta=theta;
		this.mag=mag;
		this.bulletType=bulletType;
		this.size=size;
		this.incTime=t;
	}

public void paint(Graphics g,double speed) {
	g.fillOval((int)x,(int) y, size, size);
	g.setColor(Color.white);
	g.fillOval((int)x,(int) y, size/2, size/2);
	g.setColor(Color.black);
	move(speed);
}

public void move(double speed) {
	vx=(1+speed/50)*Math.cos(Math.toRadians(theta))*t;
	vy=(1+speed/50)*Math.sin(Math.toRadians(theta))*t -0.5*g*t*t;
	
	x=x+vx*t;
	y=y+vy*t;
	
//    y=(y+0.5*Math.sin(Math.toRadians(theta)));
//    x=(x+0.5*Math.cos(Math.toRadians(theta)));
    t+=incTime;
    
//	super.move(speed);
}

}
