package com.sinius15.testing.basic;

public class Point3D {

	public double x, y, z;

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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Point3D p){
		return Math.sqrt(
				Math.abs((x-p.x)*(x-p.x))+
				Math.abs((y-p.y)*(y-p.y))+
				Math.abs((z-p.z)*(z-p.z))
				);
	}

	@Override
	public String toString() {
		return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
}
