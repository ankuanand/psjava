package org.psjava.example.algo;

import org.junit.Assert;
import org.junit.Test;
import org.psjava.algo.graph.mst.KruscalAlgorithm;
import org.psjava.algo.graph.mst.MinimumSpanningTreeAlgorithm;
import org.psjava.algo.sequence.sort.RandomizedQuicksort;
import org.psjava.ds.Collection;
import org.psjava.ds.graph.MutableUndirectedWeightedGraph;
import org.psjava.ds.graph.UndirectedWeightedEdge;
import org.psjava.ds.numbersystrem.IntegerNumberSystem;

/**
 * @implementation {@link KruscalAlgorithm}
 * 
 * @see {@link MinimumSpanningTreeAlgorithmExample}
 */
public class KruscalAlgorithmExample {

	@Test
	public void example() {
		MutableUndirectedWeightedGraph<String, Integer> graph = MutableUndirectedWeightedGraph.create();
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.addEdge("A", "B", 100);
		graph.addEdge("A", "C", 10);
		graph.addEdge("B", "C", 20);

		MinimumSpanningTreeAlgorithm algorithm = KruscalAlgorithm.getInstance(RandomizedQuicksort.getInstance());

		Collection<UndirectedWeightedEdge<String, Integer>> tree = algorithm.calc(graph, IntegerNumberSystem.getInstance());

		int sum = 0;
		for (UndirectedWeightedEdge<String, Integer> e : tree)
			sum += e.weight();

		// sum must be 10+20=30

		Assert.assertEquals(30, sum);
	}
}
