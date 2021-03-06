package org.psjava.ds.graph;

import org.psjava.util.ConvertedDataIterable;
import org.psjava.util.DataConverter;
import org.psjava.util.MergedIterable;

public class AllEdgeInGraph {
	public static <V, E> Iterable<E> wrap(final Graph<V, E> g) {
		return MergedIterable.wrap(ConvertedDataIterable.create(g.getVertices(), new DataConverter<V, Iterable<? extends E>>() {
			@Override
			public Iterable<? extends E> convert(V v) {
				return g.getEdges(v);
			}
		}));
	}
	private AllEdgeInGraph() {}
}
