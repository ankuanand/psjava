package org.psjava.ds.geometry;

import org.psjava.ds.array.Array;
import org.psjava.ds.array.MutableArrayFromIterable;

public class Polygon2D<T> {

	public static <T> Polygon2D<T> create(Iterable<Point2D<T>> ccwOrderPoints) {
		return new Polygon2D<T>(ccwOrderPoints);
	}

	private final Array<Point2D<T>> ccwOrderPoints;

	private Polygon2D(Iterable<Point2D<T>> ccwOrderPoints) {
		this.ccwOrderPoints = MutableArrayFromIterable.create(ccwOrderPoints);
	}

	public Array<Point2D<T>> getCCWOrderPoints() {
		return ccwOrderPoints;
	}

	@Override
	public String toString() {
		return "Polygon2D(" + ccwOrderPoints + ")";
	}

}
