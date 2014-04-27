package org.psjava.formula.geometry;

import org.psjava.ds.geometry.Float64PointOrigin;
import org.psjava.ds.geometry.Point2D;
import org.psjava.ds.numbersystrem.Float64;

public class DistanceBetweenPointOrigin {

	public static double calc(Point2D<Float64> p) {
		return DistanceBetweenPoints.calc(p, Float64PointOrigin.ORIGIN);
	}

	private DistanceBetweenPointOrigin() {
	}

}
