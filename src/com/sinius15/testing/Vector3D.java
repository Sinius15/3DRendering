package com.sinius15.testing;

public class Vector3D {

	double x=0, y=0, z=0, length;

	public Vector3D(double x, double y, double z) {
		
		length = Math.sqrt(x*x + y*y + z*z);
		if(length == 0)
			return;
		this.x = x/length;
		this.y = y/length;
		this.z = z/length;
	}
	
	public Vector3D crossProduct(Vector3D v){
		return new Vector3D(
			y * v.z - z * v.y,
			z * v.x - x * v.z,
			x * v.y - y * v.x);
	}
	
	
	
}
