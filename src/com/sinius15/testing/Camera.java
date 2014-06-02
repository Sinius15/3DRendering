package com.sinius15.testing;

import com.sinius15.testing.basic.Point3D;

public class Camera {

	public Point3D viewFrom;
	public Point3D viewTo;
	
	public static final int X = 0, Y = 1, Z = 1;
	
	public Camera(Point3D viewFrom, Point3D viewTo) {
		this.viewFrom = viewFrom;
		this.viewTo = viewTo;
	}
	
	public Vector3D getVector(){
		return new Vector3D(viewTo.x - viewFrom.x, viewTo.y - viewFrom.y, viewTo.z - viewFrom.z);
	}
	
	public Vector3D getRotationVector(){
		double dx = Math.abs(viewFrom.x-viewTo.x);
		double dy = Math.abs(viewFrom.x-viewTo.x);
		double xRot = dy/(dx+dy);
		double yRot = dx/(dx+dy);
		if(viewFrom.y > viewTo.y)
			xRot = -xRot;
		if(viewFrom.x < viewTo.x)
			yRot = -yRot;
		return new Vector3D(xRot, yRot, 0);
	}
	
}
