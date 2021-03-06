package org.psjava.example.algo;

import org.junit.Assert;
import org.junit.Test;
import org.psjava.algo.graph.shortestpath.NegativeCycleFinder;
import org.psjava.ds.Collection;
import org.psjava.ds.graph.DirectedWeightedEdge;
import org.psjava.ds.graph.MutableDirectedWeightedGraph;
import org.psjava.ds.numbersystrem.IntegerNumberSystem;

/**
 * @implementation {@link NegativeCycleFinder}
 */
public class FindingNegativeCycleExample {

	@Test
	public void test() {

		IntegerNumberSystem NS = IntegerNumberSystem.getInstance();

		// consturct a simple graph.
		MutableDirectedWeightedGraph<String, Integer> g = MutableDirectedWeightedGraph.create();
		g.insertVertex("A");
		g.insertVertex("B");
		g.insertVertex("C");
		g.addEdge("A", "B", 100);
		g.addEdge("B", "C", 200);
		g.addEdge("C", "A", -100);

		// there is no negative cycles yet. so cycled is false
		boolean cycled1 = NegativeCycleFinder.find(g, NS).hasCycle();

		// now, insert another edge to create a negative cycle.

		g.addEdge("C", "A", -400);

		// then, there is a negative cycle.

		boolean cycled2 = NegativeCycleFinder.find(g, NS).hasCycle();
		Collection<DirectedWeightedEdge<String, Integer>> path = NegativeCycleFinder.find(g, NS).getPath();

		Assert.assertFalse(cycled1);
		Assert.assertTrue(cycled2);
		Assert.assertEquals(3, path.size());
	}
}
