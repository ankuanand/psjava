package org.psjava.algo.sequence.rmq;

import java.util.Comparator;


import org.psjava.ds.array.Array;

public interface RangeMinimumQuery {
	<T> RangeMinimumQuerySession preprocess(Array<T> a, Comparator<T> comp);
}
