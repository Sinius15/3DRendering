package com.sinius15.testing.basic;

import java.awt.Color;

import com.sinius15.testing.Camera;
import com.sinius15.testing.RenderTest;

public class Polygon3D {

	double[] x, y, z;
	Color c;
	public Polygon3D(double[] x, double[] y, double[] z, Color c) {
		if(x.length != y.length || x.length != z.length)
			throw new IndexOutOfBoundsException("lengt x[], y[], z[] must be the same");
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
	}
	
	public Polygon2D createPolygon2D(Camera cam){
		Polygon2D out = new Polygon2D();
		out.color = c;
		for(int i = 0; i<x.length; i++){
			Point2D point = cam.dddtodd( x[i], y[i], z[i]);
			if(point == null)
				continue;
			point.x =  50*point.x - RenderTest.screenSize.width/2;
			point.y = 50*point.y - RenderTest.screenSize.height/2;
			out.addPoint(point.getIntPoint());
			
		}
		out.avgDist = getAverageDistance(cam.from);
		return out;
	}
	
	public double getAverageDistance(Point3D point){
		double total = 0;
		for(int i = 0; i<x.length; i++)
			total += new Point3D(x[i], y[i], z[i]).getDistance(point);
		return total / x.length;
	}
	
	public boolean isVisable(Camera cam){
		for(int i = 0; i<x.length; i++){
			Vector3D vec = new Vector3D(cam.from, new Point3D(x[i], y[i], z[i]));
//			System.out.println("cam.hoekHorizontal: " + cam.hoekHoriz + "  vec.hoekHorizontal: " + vec.hoekHoriz);
//			System.out.println("cam.hoekVertical: " + cam.hoekVertic + "  vec.hoekVertical: " + vec.hoekVertic);
			if(Math.abs(cam.hoekHoriz - vec.hoekHoriz) > 50)
				return false;
			if(Math.abs(cam.hoekVertic - vec.hoekVertic) > 200)
				return false;
		}
		
		return true;
	}
	
	
}
