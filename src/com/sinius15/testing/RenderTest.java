package com.sinius15.testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sinius15.testing.basic.Point3D;
import com.sinius15.testing.basic.Polygon2D;
import com.sinius15.testing.basic.Polygon3D;

public class RenderTest extends JPanel implements KeyListener{

	public static final double SLEEP_TIME = 1000/30;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final long serialVersionUID = 1L;
	
	public List<Polygon2D> drawablePolys = new ArrayList<>();
	public List<Polygon3D> worldPolygons = new ArrayList<>();
	public Camera camera = new Camera(new Point3D(10, 0, 0), new Point3D(0, 0, 0));
	
	public RenderTest(){
		
		drawablePolys = (List<Polygon2D>) Collections.synchronizedList(drawablePolys);
		worldPolygons = (List<Polygon3D>) Collections.synchronizedList(worldPolygons);
		
		//worldPolygons.add(new Polygon3D(new double[]{0, 0, 0}, new double[]{0, 5, 0}, new double[]{0, 0, 5}, Color.gray));
		//worldPolygons.add(new Polygon3D(new double[]{2, 2, 2}, new double[]{0, 5, 0}, new double[]{0, 0, 5}, Color.red));
		
		int m = 5;
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,0,0}, new double[]{0,0,m,m}, Color.red));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{m,m,m,m}, new double[]{0,0,m,m}, Color.black));
		worldPolygons.add(new Polygon3D(new double[]{m,m,m,m}, new double[]{0,0,m,m}, new double[]{0,m,m,0}, Color.blue));
		worldPolygons.add(new Polygon3D(new double[]{0,0,0,0}, new double[]{0,0,m,m}, new double[]{0,m,m,0}, Color.cyan));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,m,m}, new double[]{0,0,0,0}, Color.magenta));
		worldPolygons.add(new Polygon3D(new double[]{0,m,m,0}, new double[]{0,0,m,m}, new double[]{m,m,m,m}, Color.pink));
		
		//worldPolygons.add(new Polygon3D(new double[]{-100, -100, 100, 100}, new double[]{0, 0, 0, 0}, new double[]{-2, 2, 2, -2}, Color.red));   //x lineeee
		addKeyListener(this);
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
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
				for(Polygon3D p : worldPolygons)
					drawablePolys.add(p.createPolygon2D(camera));
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
		f.setSize(screenSize);
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)   //wij gaan naar links en rechts en blijven kijken naar het object.
				camera.viewFrom.y++;   
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			  camera.viewFrom.y--;
		if(e.getKeyCode() == KeyEvent.VK_A)    		//wij gaan naar vooren en naar achter
			camera.viewFrom.x++;   
		if(e.getKeyCode() == KeyEvent.VK_Q)
			camera.viewFrom.x--;
		
		if(e.getKeyCode() == KeyEvent.VK_UP)    		//wij gaan naar boven en beneden
			camera.viewFrom.z++;   
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			camera.viewFrom.z--;
		
//		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
//			camera.viewTo.y++;   //camera naar rechts
//		if(e.getKeyCode() == KeyEvent.VK_LEFT)
//		  camera.viewTo.y--;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
