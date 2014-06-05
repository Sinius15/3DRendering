package com.sinius15.testing.basic;

public class Vector3D {

	public double x, y, z, length, hoekHoriz, hoekVertic;   //hoekVerticaal 0 betekent recht omhoog, 180 betekent recht naar beneden

	public Vector3D(double x, double y, double z, double length, double hoekHoriz, double hoekVertic) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.length = length;
		this.hoekHoriz = hoekHoriz;
		this.hoekVertic = hoekVertic;
	}
	
	public Vector3D(Point3D p, double length, double hoekHoriz, double hoekVertic){
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		this.length = length;
		this.hoekHoriz = hoekHoriz;
		this.hoekVertic = hoekVertic;
	}
	
	public Vector3D(Point3D from, Point3D to){
		this.x = from.x;
		this.y = from.y;
		this.z = from.z;
		
		double dx = to.x - from.x;
		double dy = to.y - from.y;
		double dz = to.z - from.z;
		
		this.length = Math.sqrt(dx*dx+dy*dy+dz*dz);
		
		double s = Math.sqrt(dx*dx+dy*dy);
		
		this.hoekHoriz = Math.abs(Math.toDegrees(Math.asin(dx / s)));
		this.hoekVertic = Math.abs(Math.toDegrees(Math.asin(dz / this.length)));
		
	}

	public Point3D getToPoint(){
		Point3D to = new Point3D(0, 0, 0);
		
		double v = Math.toRadians(hoekVertic);    //verticale hoek in radian
		double h = Math.toRadians(hoekHoriz);     //horizontale hoek in radian
		
		//to.z = z + length * ( (to.z-z < 0) ? -1*Math.cos(v) : Math.cos(v) );
		//to.z = z + ( (to.z-z < 0) ? -1*(length*Math.cos(v)) : length*Math.cos(v) );
		
		to.z = z + length * Math.cos(v);

		double r2 = length * Math.sin(v);
		
		to.y = y + r2*Math.cos(h);
		to.x = x + r2*Math.sin(h);
		
		return to;

	}

	public Point3D getFromPoint() {
		return new Point3D(x, y, z);
	}
	
	@Override
	public String toString() {
		return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + ", length="
				+ length + ", hoekHoriz=" + hoekHoriz + ", hoekVertic="
				+ hoekVertic + "]";
	}	
}
