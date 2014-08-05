package org.psjava.algo.graph.shortestpath;

import java.util.Comparator;

import org.psjava.ds.graph.Graph;
import org.psjava.ds.graph.NewGraphFromGraph;
import org.psjava.ds.graph.DirectedWeightedEdge;
import org.psjava.ds.graph.OldGraph;
import org.psjava.ds.heap.Heap;
import org.psjava.ds.heap.HeapFactory;
import org.psjava.ds.heap.HeapNode;
import org.psjava.ds.map.MutableMap;
import org.psjava.ds.map.MutableMapFactory;
import org.psjava.ds.numbersystrem.AddableNumberSystem;
import org.psjava.goods.GoodMutableMapFactory;

public class DijkstraAlgorithm implements SingleSourceShortestPathAlgorithm {

	// TODO assert negative weight.

	public static DijkstraAlgorithm getInstance(final HeapFactory heapFactory) {
		return new DijkstraAlgorithm(heapFactory);
	}

	private static final MutableMapFactory MF = GoodMutableMapFactory.getInstance(); // TODO get from calc method or constructor..
	private HeapFactory factory;

	private DijkstraAlgorithm(HeapFactory heapFactory) {
		this.factory = heapFactory;
	}

	@Override
	public <V, W, E extends DirectedWeightedEdge<V, W>> SingleSourceShortestPathResult<V, W, E> calc(OldGraph<V, E> oldGraph, V start, final AddableNumberSystem<W> ns) {
		Graph<V, E> adj = NewGraphFromGraph.createFromDirected(oldGraph);
		final MutableMap<V, W> distance = MF.create();
		MutableMap<V, E> previous = MF.create();

		for (V v : oldGraph.getVertices())
			distance.add(v, null); // null means infinity
		distance.replace(start, ns.getZero());

		Heap<V> heap = factory.create(new Comparator<V>() {
			@Override
			public int compare(V o1, V o2) {
				return NullableDistanceCompare.compare(ns, distance.get(o1), distance.get(o2));
			}
		});

		MutableMap<V, HeapNode<V>> node = MF.create();
		for (V v : oldGraph.getVertices())
			node.add(v, heap.insert(v));

		while (!heap.isEmpty()) {
			V current = heap.extractMinimum();
			for (E edge : adj.getEdges(current)) {
				boolean relaxed = Relax.relax(distance, previous, edge, ns);
				if (relaxed)
					node.get(edge.to()).decreaseKey(edge.to());
			}
		}

		return SingleSourceShortestPathResultFactory.create(start, distance, previous);
	}

}