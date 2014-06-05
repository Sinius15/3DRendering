package com.sinius15.testing.basic;

public class Vector3D {

	double x, y, z, length, hoekHoriz, hoekVertic;

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
		
		this.hoekHoriz = Math.toDegrees(Math.asin(dx / s));
		this.hoekVertic = Math.toDegrees(Math.acos(dz/length));
	}

	@Override
	public String toString() {
		return "Vector3D [x=" + x + ", y=" + y + ", z=" + z + ", length="
				+ length + ", hoekHoriz=" + hoekHoriz + ", hoekVertic="
				+ hoekVertic + "]";
	}
	
	
	
}
