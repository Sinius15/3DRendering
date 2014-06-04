package com.sinius15.math;

import com.sinius15.testing.basic.Point2D;

public class MathTest {

	static Point2D from = new Point2D(0, 0), to = new Point2D(0, 10);
	
	public static void main(String[] args) {
		double hoekHorizontal = 0;
		
		double dist = from.getDistance(to);
		
		double a = Math.toRadians(hoekHorizontal);
		
		to.y = dist*Math.cos(a);
		to.x = dist*Math.sin(a);

		if(hoekHorizontal >= 360)
			hoekHorizontal = 0;
		if(hoekHorizontal < 0)
			hoekHorizontal = 360;
	}
	
}
