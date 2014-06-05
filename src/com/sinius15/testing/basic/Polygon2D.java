package com.sinius15.testing.basic;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import com.sinius15.testing.RenderTest;

public class Polygon2D extends Polygon implements Comparable<Polygon2D>{

	public double avgDist = 0;
	
	private static final long serialVersionUID = 1L;
	public Color color;

	public Polygon2D(int[] xpoints, int[] ypoints, Color c) {
		super(xpoints, ypoints, xpoints.length);
		this.color = c;
	}
	
	public Polygon2D(){}
	
	public Polygon2D(double[] xpoints, double[] ypoints, Color c){	
		if(xpoints.length != ypoints.length)
			throw new IndexOutOfBoundsException("lengt x[], y[] must be the same");
		this.npoints = xpoints.length;
		this.color = c;
		for(int i = 0; i<xpoints.length; i++)
			this.addPoint((int)xpoints[i], (int)ypoints[i]);
		
	}

	public void addPoint(Point p) {
		addPoint(p.x, p.y);
	}

	@Override
	public int compareTo(Polygon2D o) {
		return new Double(o.avgDist).compareTo(avgDist);
	}
	
	public void collapse(){
		for(int i = 0; i<xpoints.length; i++){
			xpoints[i] = xpoints[i]*-1;
		    ypoints[i] = ypoints[i]*-1;
		    
		    ypoints[i] = RenderTest.screenSize.height - ypoints[i];
		}
	}

	public boolean isInside(Rectangle rec) {
		for(int i = 0; i < npoints; i++){
			if(!rec.contains(new Point(xpoints[i], ypoints[i]))){
				return false;
			}
		}
		return true;
	}
	
}
