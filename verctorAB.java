
/**
 * @author murugan.kannan
 *
 */

//import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class verctorAB extends JPanel implements KeyListener, ActionListener,MouseMotionListener,MouseWheelListener,MouseListener {
	float angle = 0.0f;

	int speed = 5;
	double bulletTime=0.01;
	static Vector2D vecAB;
	int mouse_x,mouse_y;

	int currKey = 0;
	int key_up = 2;
	int key_down = 4;
	int key_left = 8;
	int key_right = 16;
	int key_space = 32;

	ArrayList<bullet> bullets = new ArrayList<bullet>();
	static int width = 1200, height = 800;
	private Timer refreshTimer;
	char[] bulletType = {'S','B','K','M','W'};// S,B,K,M
	int selBullet=2;
	
	private int animate=0,anim=1;
	
boolean autoGen=false,changeToMouse=false;
	public verctorAB() {
		refreshTimer = new Timer(5, this);
		refreshTimer.start();
		vecAB = new Vector2D(31, 540, "AB", -25.0, 5.0);
	}

	public static void main(String[] args) {

		JFrame f = new JFrame();
		verctorAB p = new verctorAB();
		f.addKeyListener(p);
		f.addMouseMotionListener(p);
		f.addMouseWheelListener(p);
		f.addMouseListener(p);
		f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		f.setBounds(200, 200, width, height);
		f.getContentPane().add(p);
		f.setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		
		if(changeToMouse){
		vecAB.setX(mouse_x-5);
		vecAB.setY(mouse_y-25);
		}
		updateKey();
		if(autoGen)
		if (bulletType[selBullet]=='S') {
			if (bullets.size() < 10) {
				for (int i = 0; i < 5; i++) {
					bullets.add(new bullet(vecAB.x, vecAB.y, (-2 + i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 20,bulletTime));
				}
			}
		} else if (bulletType[selBullet]=='M') {
			if (bullets.size() < 1) {
				bullets.add(new bullet(vecAB.x, vecAB.y, vecAB.theta,
						vecAB.mag, bulletType[selBullet], 10,bulletTime));
			}
		}else if (bulletType[selBullet]=='B') {
			
				bullets.add(new bullet(vecAB.x, vecAB.y, vecAB.theta,
						vecAB.mag, bulletType[selBullet], 5,bulletTime));
			
		} else if (bulletType[selBullet]=='K') {
			if (bullets.size() < 100) {
				for (int i = 0; i < 36; i++) {
					bullets.add(new bullet(vecAB.x, vecAB.y, (i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 5,bulletTime));
				}
			}
		}else if(bulletType[selBullet]=='W'){
			if (bullets.size() < 1000) {
				for (int i = 0; i < 5; i++) {
					bullets.add(new bullet(vecAB.x, vecAB.y, (-2 + i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 5,bulletTime));
				}
			}
		}
		
		if (vecAB.x > width)
			vecAB.x = 0;
		else if (vecAB.x < 0)
			vecAB.x = width;
		if (vecAB.y > height)
			vecAB.y = 0;
		else if (vecAB.y < 0)
			vecAB.y = height;
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		drawMenu(g);
		g.setColor(Color.black);
		vecAB.drawVector(g, vecAB);
g.drawOval(mouse_x-30, mouse_y-50, 50, 50);
bullet b=null;
		for (int i = 0; i < bullets.size(); i++) {
			b=bullets.get(i);
			if (b.x < 0 || b.x > width
					|| b.y < 0 || b.y > height || b.t>10) {
				bullets.remove(i);
			} else {
				b.paint(g,vecAB.mag);
			}
		}

/*		Graphics2D g2 = (Graphics2D) g;
		resetTrans(g2);
		double x, y;
		y = (vecAB.y - 50 * Math.sin(Math.toRadians(vecAB.theta)));
		x = (vecAB.x - 50 * Math.cos(Math.toRadians(vecAB.theta)));
		g2.translate(x, y);

		g2.rotate(Math.toRadians(vecAB.theta));
		g2.fillRect(0, -5, 100, 10);
		g2.fillRect(0, -15, 50, 30);*/
	}
	private void drawMenu(Graphics g) {
		g.setColor(Color.black);
			g.drawRect(-300+anim, 100, 300, 500);
			g.drawRect(anim, 100, 50, 100);
			int intial=150;
			
			g.drawString("esc-quit",-250+anim, intial);
			intial+=20;

			g.drawString("space- Triger bullet",-250+anim, intial);
			intial+=20;

			g.drawString("up-move farward",-250+anim, intial);
			intial+=20;
			
			g.drawString("down-move backward",-250+anim, intial);
			intial+=20;
			
			g.drawString("left-rotate antiClockwise",-250+anim, intial);
			intial+=20;
			
			g.drawString("right-rotate clockwise",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM8-move position up",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM2-move position down",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM4-move position left",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM6-move position right",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM7-Increase size",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM9-Decrease size",-250+anim, intial);
			intial+=20;
			
			g.drawString("F-Increase speed",-250+anim, intial);
			intial+=20;
			
			g.drawString("S-Decrease speed",-250+anim, intial);
			intial+=20;
			
			g.drawString("D-switch particle",-250+anim, intial);
			intial+=20;
			
			g.drawString("A-autoGenerate",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM1-Increase Time",-250+anim, intial);
			intial+=20;
			
			g.drawString("NUM3-Decrease Time",-250+anim, intial);
			intial+=20;
			
			g.drawString("C-change position to mouse control",-250+anim, intial);
			intial+=20;
			
			g.drawString("R-Remove all particle",-250+anim, intial);
			intial+=20;
			
			g.drawString("mouse scroll- angle",-250+anim, intial);
			intial+=20;
			g.drawString("mouse move- player position",-250+anim, intial);
			intial+=20;

			
			
			if(animate >0 ){
				if(anim<300){
					anim+=5;
				}else
					animate=0;
			}else if(animate <0 ){
				if(anim>0){
					anim-=5;
				}else
					animate=0;
			}
		
	}

	private void updateKey() {
		if(!changeToMouse)
		if ((currKey & key_up) == key_up) {
			vecAB.move(speed);
		}else
		if ((currKey & key_down) == key_down) {
			vecAB.move(-speed);
		}
		if ((currKey & key_left) == key_left) {
			vecAB.changeAngle(-speed);
		}else
		if ((currKey & key_right) == key_right) {
			vecAB.changeAngle(speed);
		}
		if ((currKey & key_space) == key_space) {
			vecAB.print();
			if(!autoGen)
			if (bulletType[selBullet]=='S') {
				if (bullets.size() < 10) {
					for (int i = 0; i < 5; i++) {
						bullets.add(new bullet(vecAB.x, vecAB.y, (-2 + i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 20,bulletTime));
					}
				}
			} else if (bulletType[selBullet]=='M') {
				if (bullets.size() < 1) {
					bullets.add(new bullet(vecAB.x, vecAB.y, vecAB.theta,
							vecAB.mag, bulletType[selBullet], 10,bulletTime));
				}
			}else if (bulletType[selBullet]=='B') {
				
					bullets.add(new bullet(vecAB.x, vecAB.y, vecAB.theta,
							vecAB.mag, bulletType[selBullet], 5,bulletTime));
				
			} else if (bulletType[selBullet]=='K') {
				if (bullets.size() < 100) {
					for (int i = 0; i < 36; i++) {
						bullets.add(new bullet(vecAB.x, vecAB.y, (i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 5,bulletTime));
					}
				}
			}else if(bulletType[selBullet]=='W'){
				if (bullets.size() < 1000) {
					for (int i = 0; i < 5; i++) {
						bullets.add(new bullet(vecAB.x, vecAB.y, (-2 + i) * 10 + vecAB.theta, vecAB.mag, bulletType[selBullet], 5,bulletTime));
					}
				}
			}
		}
	}



	public void resetTrans(Graphics2D g2) {
		AffineTransform at = new AffineTransform();
		at.setToIdentity();
		g2.setTransform(at);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_SPACE:
			currKey |= key_space;
			break;

		case KeyEvent.VK_UP:
			currKey |= key_up;
			break;
		case KeyEvent.VK_DOWN:
			currKey |= key_down;
			break;
		case KeyEvent.VK_LEFT:
			currKey |= key_left;
			break;
		case KeyEvent.VK_RIGHT:
			currKey |= key_right;
			break;

		case KeyEvent.VK_NUMPAD8:
			vecAB.changey(-speed);
			break;
		case KeyEvent.VK_NUMPAD2:
			vecAB.changey(speed);
			break;
		case KeyEvent.VK_NUMPAD4:
			vecAB.changex(-speed);
			break;
		case KeyEvent.VK_NUMPAD6:
			vecAB.changex(speed);
			break;
		case KeyEvent.VK_NUMPAD7:
			vecAB.changeMod(speed);
			break;
		case KeyEvent.VK_NUMPAD9:
			vecAB.changeMod(-speed);
			break;
		case KeyEvent.VK_F:
			speed++;
			break;
		case KeyEvent.VK_S:
			if(speed>0)
			speed--;
			break;
		case KeyEvent.VK_D:
			if(selBullet<4)
				selBullet++;
			else
				selBullet=0;
			break;
		case KeyEvent.VK_A:
			autoGen=!autoGen;
			break;
		case KeyEvent.VK_NUMPAD1:
			bulletTime+=0.01;
			break;
		case KeyEvent.VK_NUMPAD3:
			bulletTime-=0.01;
			break;
		case KeyEvent.VK_C:
			changeToMouse=!changeToMouse;
			break;
		case KeyEvent.VK_R:
			for (int i = 0; i < bullets.size(); i++) {
				bullets.remove(i);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			currKey &= ~key_up;
			break;
		case KeyEvent.VK_DOWN:
			currKey &= ~key_down;
			break;
		case KeyEvent.VK_LEFT:
			currKey &= ~key_left;
			break;
		case KeyEvent.VK_RIGHT:
			currKey &= ~key_right;
			break;
		case KeyEvent.VK_SPACE:
			currKey &= ~key_space;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x=e.getX();
		mouse_y=e.getY();
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int scroll=e.getScrollAmount();
		if(e.getWheelRotation()>0)
			vecAB.changeAngle(scroll*speed);
		else
			vecAB.changeAngle(-scroll*speed);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(anim<3 && e.getX()<50 && e.getY()<200 && e.getY()>50)
			animate=1;
		if(anim<303 && e.getX()<350 && e.getX()>300 && e.getY()<200 && e.getY()>100)
			animate=-1;
		System.out.println(anim +" "+ e.getX()+" "+ e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
