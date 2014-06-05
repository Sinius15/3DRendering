package com.sinius15.testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinius15.testing.basic.Point3D;
import com.sinius15.testing.basic.Polygon2D;
import com.sinius15.testing.basic.Polygon3D;

public class RenderTest extends JPanel implements KeyListener, MouseMotionListener, MouseListener{

	public static final double SLEEP_TIME = 1000/30;
	
	public static Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	private static final long serialVersionUID = 1L;
	
	public List<Polygon2D> drawablePolys = new ArrayList<>();
	public List<Polygon3D> worldPolygons = new ArrayList<>();
	public Camera camera = new Camera(new Point3D(12, 0, 0), new Point3D(0, 0, 0));
	
	public RenderTest(){
		
		drawablePolys = (List<Polygon2D>) Collections.synchronizedList(drawablePolys);
		worldPolygons = (List<Polygon3D>) Collections.synchronizedList(worldPolygons);
		
		int m = 1;
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,0,0}, new double[]{0,0,m,m}, Color.red));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{m,m,m,m}, new double[]{0,0,m,m}, Color.black));
		worldPolygons.add(new Polygon3D(new double[]{m,m,m,m}, new double[]{0,0,m,m}, new double[]{0,m,m,0}, Color.blue));
		worldPolygons.add(new Polygon3D(new double[]{0,0,0,0}, new double[]{0,0,m,m}, new double[]{0,m,m,0}, Color.cyan));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,m,m}, new double[]{0,0,0,0}, Color.magenta));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,m,m}, new double[]{m,m,m,m}, Color.pink));
		
		for(int x = 0; x<10; x++){
			for(int y = 0; y<10; y++){
				worldPolygons.add(new Polygon3D(new double[]{x, x, x+1, x+1}, new double[]{y, y+1, y+1, y}, new double[]{0, 0, 0, 0}, Color.GRAY));
			}
		}
		
		worldPolygons.add(new Polygon3D(new double[]{0, 3, 0}, new double[]{0, 0, 3}, new double[]{0, 0, 0}, Color.red));
		
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
		camera.calculateViewTo();
		g.clearRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		g.setColor(Color.black);
		synchronized (drawablePolys) {
			Collections.sort(drawablePolys);
			for(Polygon2D p : drawablePolys){
				p.collapse();
				g.setColor(p.color);
				g.fillPolygon(p);
				g.setColor(Color.black);
				g.drawPolygon(p);
			}	
		}
		
	}
	
	public void gameLoop(){
		while(true){
			synchronized (drawablePolys) {
				drawablePolys.clear();
				for(Polygon3D p3 : worldPolygons){
					if(p3.isVisable(camera))
						drawablePolys.add(p3.createPolygon2D(camera));
				}
			}	
			
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	

	public static void main(String[] args) {
		final RenderTest t = new RenderTest();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setUndecorated(true);
		f.setSize(screenSize.getSize());
		f.add(t);
		f.setVisible(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				t.gameLoop();
			}
		}).start();;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			camera.from.y++;   
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			  camera.from.y--;
		if(e.getKeyCode() == KeyEvent.VK_UP)   
			camera.from.z++;   
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			camera.from.z--;
		
		
		if(e.getKeyCode() == KeyEvent.VK_Q) 
			camera.from.x++;   
		if(e.getKeyCode() == KeyEvent.VK_E)
			camera.from.x--;
		
		if(e.getKeyCode() == KeyEvent.VK_Z)
			camera.from = new Point3D(5,5,5);

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	int xOld = 0;
	int yOld = 0;
	boolean isIn = false;
	@Override
	public void mouseDragged(MouseEvent e) {
		if(isIn){
			if(e.getX() - xOld > 0){
				camera.hoekHoriz-=1;
				xOld = e.getX();
			}
			if(e.getX() - xOld < 0){
				camera.hoekHoriz+=1;
				xOld = e.getX();
			}
			if(e.getY() - yOld > 0){
				camera.hoekVertic-=1;
				yOld = e.getY();
			}
			if(e.getY() - yOld < 0){
				camera.hoekVertic+=1;
				yOld = e.getY();
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isIn = true;
		xOld = e.getX();
		yOld = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		xOld = -1;
		yOld = -1;
		isIn = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}
