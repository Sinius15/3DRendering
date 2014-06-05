package com.sinius15.mathTest;

import com.sinius15.testing.basic.Point3D;
import com.sinius15.testing.basic.Vector3D;

public class MathTest {

	public static void main(String[] args) {
		Vector3D vec1 = new Vector3D(10, 10, 10, 20, 0, 170);
		
		Point3D pTo = vec1.getToPoint();
		Point3D pFrom = vec1.getFromPoint();
		
		
		System.out.println(pFrom);
		System.out.println(pTo);
		
		Vector3D vec2 = new Vector3D(pFrom, pTo);
		
		System.out.println(vec1.toString());
		System.out.println(vec2.toString());
	
		System.out.println();
	}
	
}
