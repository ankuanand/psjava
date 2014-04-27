package org.psjava.ds.array;

import org.psjava.util.ZeroTo;

public class MutableArrayFromValues {
	public static <T> MutableArray<T> create(T... values) {
		@SuppressWarnings("unchecked")
		T[] a = (T[]) new Object[values.length];
		for (int i : ZeroTo.get(a.length))
			a[i] = values[i];
		return MutableArrayUsingJavaArray.create(a);
	}

	private MutableArrayFromValues() {
	}
}
