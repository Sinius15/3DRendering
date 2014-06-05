package com.sinius15.testing;

import com.sinius15.testing.basic.Point2D;
import com.sinius15.testing.basic.Point3D;
import com.sinius15.testing.basic.Vector3D;

public class Camera {

	public Point3D from;
	public Point3D to;
	public double hoekHoriz = 0, hoekVertic = 0;
	public double distance = 10;
	
	public Camera(Point3D viewFrom, Point3D viewTo) {
		this.from = viewFrom;
		this.to = viewTo;
	}
	
	public Vector3DX getVector(){
		return new Vector3DX(to.x - from.x, to.y - from.y, to.z - from.z);
	}
	
	public Vector3DX getRotationVector(){
		double dx = Math.abs(from.x-to.x);
		double dy = Math.abs(from.x-to.x);
		double xRot = dy/(dx+dy);
		double yRot = dx/(dx+dy);
		if(from.y > to.y)
			xRot = -xRot;
		if(from.x < to.x)
			yRot = -yRot;
		return new Vector3DX(xRot, yRot, 0);
	}

	public double getDistance(){
		return from.getDistance(to);
	}
	
	public void calculateViewTo(){
		
		Vector3D vec = new Vector3D(from, distance, hoekHoriz, hoekVertic);
		to = vec.getToPoint();
		
		
		if(hoekHoriz >= 360)
			hoekHoriz = 0;
		if(hoekHoriz < 0)
			hoekHoriz = 359;
		if(hoekVertic > 180)
			hoekVertic = 180;
		if(hoekVertic < 0)
			hoekVertic = 0;

	}
	
	public Point2D dddtodd(double x, double y, double z){
		Vector3DX view, viewToPoint, rotation, weird1, weird2;
		
		view = getVector();
		
		rotation = getRotationVector();
		weird1 = view.crossProduct(rotation);
		weird2 = view.crossProduct(weird1);
		
		
		viewToPoint = new Vector3DX(x - from.x, y - from.y, z - from.z);
		
		double t =
				 (view.x * to.x + view.y * to.y + view.z * to.z)
				-(view.x * from.x + view.y * from.y + view.z * from.z)
				/(view.x * viewToPoint.x           + view.y * viewToPoint.y + view.z * viewToPoint.z);
		
		x = from.x + viewToPoint.x * t;
		y = from.y + viewToPoint.y * t;
		z = from.z + viewToPoint.z * t;
		
		if(t < 0)
			return null;
		return new Point2D(weird2.x * x + weird2.y * y + weird2.z * z, weird1.x * x + weird1.y * y + weird1.z * z);		

	}

	@Override
	public String toString() {
		return "Camera [from=" + from + ", to=" + to + ", hoekHoriz="
				+ hoekHoriz + ", hoekVertic=" + hoekVertic + "]";
	}
	
	
	
}
