package org.psjava.algo.sequence.search;

import java.util.Comparator;

import org.psjava.ds.math.Function;
import org.psjava.ds.numbersystrem.AddInvert;
import org.psjava.ds.numbersystrem.IntegerDivisableNumberSystem;
import org.psjava.formula.Decrease;
import org.psjava.formula.Increase;
import org.psjava.formula.ReflectionOnYAxis;
import org.psjava.util.ReversedComparator;

public class BinarySearchLast {

	public static <I, O> I search(IntegerDivisableNumberSystem<I> inputNumberSystem, Function<I, O> f, Comparator<O> sortedOrder, I begin, I end, O target, I def) {
		IntegerDivisableNumberSystem<I> ns = inputNumberSystem;
		I newBegin = AddInvert.calc(ns, Decrease.calc(ns, end));
		I newEnd = Increase.calc(ns, AddInvert.calc(ns, begin));
		I subr = BinarySearchFirst.search(ns, ReflectionOnYAxis.create(ns, f), ReversedComparator.wrap(sortedOrder), newBegin, newEnd, target, null);
		if (subr != null)
			return AddInvert.calc(ns, subr);
		else
			return def;
	}

	private BinarySearchLast() {
	}

}
