package org.psjava.ds.array;

import java.util.Iterator;

import org.psjava.util.IterableToString;

public class MutableArrayUsingIntArray {

	public static MutableArray<Integer> wrap(final int[] a) {
		return new MutableArray<Integer>() {
			@Override
			public Integer get(int i) {
				return a[i];
			}

			@Override
			public void set(int i, Integer v) {
				a[i] = v;
			}

			@Override
			public int size() {
				return a.length;
			}

			@Override
			public final boolean isEmpty() {
				return size() == 0;
			}

			@Override
			public final Iterator<Integer> iterator() {
				return ArrayIterator.create(this);
			}

			@Override
			public final String toString() {
				return IterableToString.toString(this);
			}
		};
	}

	private MutableArrayUsingIntArray() {
	}

}
