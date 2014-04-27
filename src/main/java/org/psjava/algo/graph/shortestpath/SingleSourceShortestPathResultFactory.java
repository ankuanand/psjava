package org.psjava.algo.graph.shortestpath;

import java.util.LinkedList;

import org.psjava.ds.graph.DirectedEdge;
import org.psjava.ds.map.Map;
import org.psjava.util.AssertStatus;

public class SingleSourceShortestPathResultFactory {

	public static <V, E extends DirectedEdge<V>, W> SingleSourceShortestPathResult<V, W, E> create(final V start, final Map<V, W> distance, final Map<V, E> previous) {
		return new SingleSourceShortestPathResult<V, W, E>() {
			@Override
			public W getDistance(V to) {
				assertReachable(to);
				return distance.get(to);
			}

			@Override
			public Iterable<E> getPath(V to) {
				assertReachable(to);
				LinkedList<E> r = new LinkedList<E>();
				for (V v = to; !v.equals(start); v = previous.get(v).from())
					r.addFirst(previous.get(v));
				return r;
			}

			@Override
			public boolean isReachable(V to) {
				return distance.get(to) != null;
			}

			private void assertReachable(V to) {
				AssertStatus.assertTrue(isReachable(to), "Not reachable");
			}
		};
	}

	private SingleSourceShortestPathResultFactory() {
	}

}
