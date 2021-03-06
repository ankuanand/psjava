package org.psjava.algo.graph.shortestpath;

import org.psjava.ds.Collection;
import org.psjava.ds.deque.DoubleLinkedList;
import org.psjava.ds.graph.AllEdgeInGraph;
import org.psjava.ds.graph.DirectedWeightedEdge;
import org.psjava.ds.graph.Graph;
import org.psjava.ds.graph.MutableDirectedGraph;
import org.psjava.ds.numbersystrem.AddableNumberSystem;
import org.psjava.ds.numbersystrem.InfinitableAddableNumberSystem;
import org.psjava.ds.set.MutableSet;
import org.psjava.goods.GoodMutableSetFactory;
import org.psjava.util.AssertStatus;

/** Finds any one of possible negative cycles in a graph. */
public class NegativeCycleFinder {

	private static class AugmentedEdge<V, W, E extends DirectedWeightedEdge<V, W>> implements DirectedWeightedEdge<Object, W> {
		private final Object from;
		private final Object to;
		private final W weight;
		private final E originalOrNull; // to track original graph's path

		AugmentedEdge(Object from, Object to, W w, E originalOrNull) {
			this.from = from;
			this.to = to;
			this.weight = w;
			this.originalOrNull = originalOrNull;
		}

		@Override
		public Object from() {
			return from;
		}

		@Override
		public Object to() {
			return to;
		}

		@Override
		public W weight() {
			return weight;
		}

		public E getOriginal() {
			AssertStatus.assertTrue(originalOrNull != null);
			return originalOrNull;
		}
	}

	private static final Object VIRTUAL_START = new Object();

	public static <V, W, E extends DirectedWeightedEdge<V, W>> NegativeCycleFinderResult<E> find(Graph<V, E> graph, AddableNumberSystem<W> weightSystem) {
		Graph<Object, AugmentedEdge<V, W, E>> augmented = augment(graph, weightSystem);
		InfinitableAddableNumberSystem<W> ns = InfinitableAddableNumberSystem.wrap(weightSystem);
		SingleSourceShortestPathCalcStatus<Object, W, AugmentedEdge<V, W, E>> bellmanFordStatus = BellmanFordAlgorithm.createInitialStatus(augmented, VIRTUAL_START, ns);
		BellmanFordAlgorithm.relaxEnough(augmented, bellmanFordStatus, ns);
		AugmentedEdge<V, W, E> relaxed = relaxAnyEdgeIfPossible(augmented, ns, bellmanFordStatus);
		return createResult(bellmanFordStatus, relaxed);
	}

	private static <V, W, E extends DirectedWeightedEdge<V, W>> Graph<Object, AugmentedEdge<V, W, E>> augment(Graph<V, E> original, AddableNumberSystem<W> ns) {
		MutableDirectedGraph<Object, AugmentedEdge<V, W, E>> r = MutableDirectedGraph.create();
		for (V v : original.getVertices())
			r.insertVertex(v);
		for (E e : AllEdgeInGraph.wrap(original))
			r.addEdge(new AugmentedEdge<V, W, E>(e.from(), e.to(), e.weight(), e));
		r.insertVertex(VIRTUAL_START);
		for (V v : original.getVertices())
			r.addEdge(new AugmentedEdge<V, W, E>(VIRTUAL_START, v, ns.getZero(), null));
		return r;
	}

	private static <V, W, E extends DirectedWeightedEdge<V, W>> AugmentedEdge<V, W, E> relaxAnyEdgeIfPossible(Graph<Object, AugmentedEdge<V, W, E>> graph, InfinitableAddableNumberSystem<W> ns,
			SingleSourceShortestPathCalcStatus<Object, W, AugmentedEdge<V, W, E>> status) {
		for (AugmentedEdge<V, W, E> e : AllEdgeInGraph.wrap(graph))
			if (Relax.relax(status.distance, status.previous, e, ns))
				return e;
		return null;
	}

	private static <V, W, E extends DirectedWeightedEdge<V, W>> NegativeCycleFinderResult<E> createResult(final SingleSourceShortestPathCalcStatus<Object, W, AugmentedEdge<V, W, E>> status,
			final AugmentedEdge<V, W, E> lastRelaxedEdgeOrNull) {
		return new NegativeCycleFinderResult<E>() {
			@Override
			public boolean hasCycle() {
				return lastRelaxedEdgeOrNull != null;
			}

			@Override
			public Collection<E> getPath() {
				AssertStatus.assertTrue(hasCycle(), "no cycle");
				MutableSet<Object> visited = GoodMutableSetFactory.getInstance().create();
				DoubleLinkedList<E> path = DoubleLinkedList.create();
				AugmentedEdge<V, W, E> curEdge = lastRelaxedEdgeOrNull;
				while (true) {
					path.addToFirst(curEdge.getOriginal());
					visited.add(curEdge.to());
					if (visited.contains(curEdge.from()))
						break;
					curEdge = status.previous.get(curEdge.from());
				}
				while (!path.getLast().to().equals(curEdge.from()))
					path.removeLast();
				return path;
			}
		};
	}

	private NegativeCycleFinder() {
	}

}
