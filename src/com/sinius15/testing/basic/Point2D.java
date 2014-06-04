package com.sinius15.testing.basic;

import java.awt.Point;

public class Point2D {

	public double x, y;

	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point getIntPoint(){
		return new Point((int) x, (int) y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getDistance(Point2D p){
		double dx = Math.abs(p.x - x);
		double dy = Math.abs(p.y - y);
		return Math.sqrt(
				dx*dx + dy*dy
				);
	}

	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
