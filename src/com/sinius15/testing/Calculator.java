package com.sinius15.testing;

import com.sinius15.testing.basic.Point2D;

public class Calculator {
	
	public static Point2D calculatePosition(Camera cam, double x, double y, double z) {
		Vector3D view, viewToPoint, rotation, weird1, weird2;
		
		view = cam.getVector();
		
		rotation = cam.getRotationVector();
		weird1 = view.crossProduct(rotation);
		weird2 = view.crossProduct(weird1);
		
		
		viewToPoint = new Vector3D(x - cam.viewFrom.x, y - cam.viewFrom.y, z - cam.viewFrom.z);
		
		double t =
				 (view.x * cam.viewTo.x + view.y * cam.viewTo.y + view.z * cam.viewTo.z)
				-(view.x * cam.viewFrom.x + view.y * cam.viewFrom.y + view.z * cam.viewFrom.z)
				/(view.x * viewToPoint.x           + view.y * viewToPoint.y + view.z * viewToPoint.z);
		
		x = cam.viewFrom.x + viewToPoint.x * t;
		y = cam.viewFrom.y + viewToPoint.y * t;
		z = cam.viewFrom.z + viewToPoint.z * t;
		
		if(t >= 0){
			return new Point2D(weird2.x * x + weird2.y * y + weird2.z * z, 
							   weird1.x * x + weird1.y * y + weird1.z * z);
		}
				
		return null;
	}

}
