package org.psjava.ds.graph;

public class TestGraphFactory {

	public static MutableDirectedWeightedGraph<Integer, Integer> createDirectedWeightedNew(int[][] edata) {
		MutableDirectedWeightedGraph<Integer, Integer> g = new MutableDirectedWeightedGraph<Integer, Integer>();
		for (int[] e : edata) {
			g.insertVertex(e[0]);
			g.insertVertex(e[1]);
			g.addEdge(e[0], e[1], e[2]);
		}
		return g;
	}

	public static Graph<String, DirectedEdge<String>> createDirectedNew(String[][] edata) {
		MutableDirectedUnweightedGraph<String> g = MutableDirectedUnweightedGraph.create();
		for (String[] e : edata) {
			g.insertVertex(e[0]);
			g.insertVertex(e[1]);
			g.addEdge(e[0], e[1]);
		}
		return g;
	}

	public static Graph<String, UndirectedEdge<String>> createUndirectedNew(String[][] edata) {
		MutableUndirectedUnweightedGraph<String> g = MutableUndirectedUnweightedGraph.create();
		for (String[] e : edata) {
			g.insertVertex(e[0]);
			g.insertVertex(e[1]);
			g.addEdge(e[0], e[1]);
		}
		return g;
	}

	public static Graph<String, CapacityEdge<String, Integer>> createCapacityGraphNew(Object[][] edata) {
		MutableCapacityGraph<String, Integer> g = MutableCapacityGraph.create();
		for (Object[] e : edata) {
			g.insertVertex((String) e[0]);
			g.insertVertex((String) e[1]);
			g.addEdge((String) e[0], (String) e[1], (Integer) e[2]);
		}
		return g;
	}

	public static MutableDirectedWeightedGraph<String, Integer> createDirectedWeightedNew(Object[][] data) {
		MutableDirectedWeightedGraph<String, Integer> g = MutableDirectedWeightedGraph.create();
		for (Object[] e1 : data) {
			g.insertVertex((String) e1[0]);
			g.insertVertex((String) e1[1]);
			g.addEdge((String) e1[0], (String) e1[1], (int) (Integer) e1[2]);
		}
		return g;
	}

	public static MutableUndirectedWeightedGraph<String, Integer> createUndirectedWeightedNew(Object[][] data) {
		MutableUndirectedWeightedGraph<String, Integer> g = MutableUndirectedWeightedGraph.create();
		for (Object[] e1 : data) {
			g.insertVertex((String) e1[0]);
			g.insertVertex((String) e1[1]);
			g.addEdge((String) e1[0], (String) e1[1], (Integer) e1[2]);
		}
		return g;
	}

}
