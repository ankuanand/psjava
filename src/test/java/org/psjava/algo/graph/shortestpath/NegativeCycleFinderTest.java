package org.psjava.algo.graph.shortestpath;

import static org.junit.Assert.*;

import org.junit.Test;
import org.psjava.ds.graph.DirectedWeightedEdge;
import org.psjava.ds.graph.MutableDirectedWeightedGraph;
import org.psjava.ds.graph.TestGraphFactory;
import org.psjava.ds.numbersystrem.IntegerNumberSystem;
import org.psjava.util.IterableToString;

public class NegativeCycleFinderTest {

	@Test
	public void testNegativeCycle() {
		int[][] data = { { 1, 2, 1 }, { 2, 3, -4 }, { 3, 4, 1 }, { 4, 2, 1 } };
		MutableDirectedWeightedGraph<Integer, Integer> g = TestGraphFactory.createDirectedWeightedNew(data);
		NegativeCycleFinderResult<DirectedWeightedEdge<Integer, Integer>> r = NegativeCycleFinder.find(g, IntegerNumberSystem.getInstance());
		assertTrue(r.hasCycle());
		assertEquals("(3->4(1),4->2(1),2->3(-4))", IterableToString.toString(r.getPath()));
	}

	@Test
	public void testSizeOne() {
		int[][] data = { { 1, 1, -1 } };
		MutableDirectedWeightedGraph<Integer, Integer> g = TestGraphFactory.createDirectedWeightedNew(data);
		NegativeCycleFinderResult<DirectedWeightedEdge<Integer, Integer>> r = NegativeCycleFinder.find(g, IntegerNumberSystem.getInstance());
		assertTrue(r.hasCycle());
		assertEquals("(1->1(-1))", IterableToString.toString(r.getPath()));
	}

}
