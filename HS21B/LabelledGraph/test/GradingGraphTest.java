import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GradingGraphTest {
	
	/** a) **/
	
	@Test
	public void testRemoveEdges01() {
		Node bottom = new Node();
		Node top = new Node(NewArrayList(new Edge(bottom, "E", 10)));
		
		Graph.removeEdges(top, "E", 20);
		
		assertEquals(0, top.getNeighbours().size());
	}
	
	@Test
	public void testRemoveEdges02() {
		Node [][] actualGraph = graph0();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "C", 100);
		
		expectedEdgesInGraph.get(0).get(0).remove(2);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}

	@Test
	public void testRemoveEdges03() {
		Node [][] actualGraph = graph0();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "B", 100);
		
		expectedEdgesInGraph.get(0).get(0).remove(4);
		expectedEdgesInGraph.get(0).get(0).remove(3);
		expectedEdgesInGraph.get(0).get(0).remove(1);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}
	
	@Test
	public void testRemoveEdges04() {
		Node [][] actualGraph = graph0();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "B", 6);
		
		expectedEdgesInGraph.get(0).get(0).remove(4);
		expectedEdgesInGraph.get(0).get(0).remove(3);		
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}

	@Test
	public void testRemoveEdges05() {
		Node [][] actualGraph = graph1();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "S", 2);
		
		expectedEdgesInGraph.get(2).get(0).remove(1);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}
	
	@Test
	public void testRemoveEdges06() {
		Node [][] actualGraph = graph1();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "Q", 301);
		
		expectedEdgesInGraph.get(1).get(0).remove(1);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}
	
	@Test
	public void testRemoveEdges07() {
		Node [][] actualGraph = graph2();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "M", 6);
		
		expectedEdgesInGraph.get(1).get(1).remove(3);
		expectedEdgesInGraph.get(1).get(1).remove(2);
		expectedEdgesInGraph.get(1).get(1).remove(1);
	
		expectedEdgesInGraph.get(1).get(2).remove(1);
		expectedEdgesInGraph.get(1).get(2).remove(0);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}
	
	@Test
	public void testRemoveEdges08() {
		Node [][] actualGraph = graph2();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "A", 20);
		
		expectedEdgesInGraph.get(3).get(2).remove(0);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}
	
	@Test
	public void testRemoveEdges09() {
		Node [][] actualGraph = graph5();
		List<List<ArrayList<Edge>>> expectedEdgesInGraph = createCopyOfEdges(actualGraph);
		
		Graph.removeEdges(actualGraph[0][0], "R", 12);
		
		expectedEdgesInGraph.get(1).get(1).remove(0);
		expectedEdgesInGraph.get(2).get(1).remove(0);
		
		assertRemoveEdges(expectedEdgesInGraph, actualGraph);
	}

		
	/** b) **/
	
	/** order is irrelevant **/
	@Test
	public void testFindNodesA01() {		
		Node [][] graph = graph0();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("R"));
		
		assertFindNodes(List.of(), actualResult, false);
	}
	
	@Test
	public void testFindNodesA02() {		
		Node [][] graph = graph0();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C"));
		assertFindNodes(List.of(graph[1][2]), actualResult, false);
	}
	
	@Test
	public void testFindNodesA03() {
		Node [][] graph = graph1();
		{
			List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","Q"));
			
			assertFindNodes(List.of(graph[2][1]), actualResult, false);
		}
		
		{
			List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","Q","A"));
			
			assertFindNodes(List.of(graph[3][2]), actualResult, false);
		}
	}

	@Test
	public void testFindNodesA04() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C","M","S"));
		
		assertFindNodes(List.of(graph[3][1]), actualResult, false);
	}
	
	/** order is relevant **/

	@Test
	public void testFindNodesC01() {		
		Node [][] graph = graph0();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("B"));
		
		assertFindNodes(List.of(graph[1][3], graph[1][4], graph[1][1]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS01() {		
		Node [][] graph = graph0();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("B"));
		
		assertFindNodes(List.of(graph[1][3], graph[1][4], graph[1][1]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC02() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][0], graph[2][3]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS02() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][0], graph[2][3]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC03() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B","A"));
		
		assertFindNodes(List.of(graph[3][5], graph[3][0], graph[3][7], graph[3][6]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS03() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B","A"));
		
		assertFindNodes(List.of(graph[3][5], graph[3][0], graph[3][7], graph[3][6]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC04() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B","S"));
		
		assertFindNodes(List.of(graph[3][1], graph[3][4]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS04() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","B","S"));
		
		assertFindNodes(List.of(graph[3][1], graph[3][4]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC05() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A"));
		
		assertFindNodes(List.of(graph[1][0], graph[1][1]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS05() {
		Node [][] graph = graph1();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A"));
		
		assertFindNodes(List.of(graph[1][0], graph[1][1]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC06() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C"));
		
		assertFindNodes(List.of(graph[1][0], graph[1][2], graph[1][1]), actualResult, false);
	}
		
	@Test
	public void testFindNodesS06() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C"));
		
		assertFindNodes(List.of(graph[1][0], graph[1][2], graph[1][1]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC07() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C","R"));
		
		assertFindNodes(List.of(graph[2][0], graph[2][3]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS07() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C","R"));
		
		assertFindNodes(List.of(graph[2][0], graph[2][3]), actualResult, true);
	}
	
	
	@Test
	public void testFindNodesC08() {
		Node [][] graph = graph4();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("Q","R"));
		
		assertFindNodes(List.of(graph[2][1], graph[2][2], graph[2][0]), actualResult, false);		
	}
	
	@Test
	public void testFindNodesS08() {
		Node [][] graph = graph4();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("Q","R"));
		
		assertFindNodes(List.of(graph[2][1], graph[2][2], graph[2][0]), actualResult, true);		
	}
	
	@Test
	public void testFindNodesC09() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C","R","M","A"));
		
		assertFindNodes(List.of(graph[4][1], graph[4][0]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS09() {
		Node [][] graph = graph2();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("C","R","M","A"));
		
		assertFindNodes(List.of(graph[4][1], graph[4][0]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC10() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N"));
		
		assertFindNodes(List.of(graph[1][2], graph[1][1], graph[1][0]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS10() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N"));
		
		assertFindNodes(List.of(graph[1][2], graph[1][1], graph[1][0]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC11() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N","B"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][1], graph[2][0]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS11() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N","B"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][1], graph[2][0]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC12() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N","B","A"));
		
		assertFindNodes(List.of(graph[3][0], graph[3][2], graph[3][1], graph[3][3]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS12() {
		Node [][] graph = graph3();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("N","B","A"));
		
		assertFindNodes(List.of(graph[3][0], graph[3][2], graph[3][1], graph[3][3]), actualResult, true);
	}
	
	@Test
	public void testFindNodesC13() {
		Node [][] graph = graph5();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","T"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][1]), actualResult, false);
	}
	
	@Test
	public void testFindNodesS13() {
		Node [][] graph = graph5();
		
		List<Node> actualResult = Graph.findNodes(graph[0][0], List.of("A","T"));
		
		assertFindNodes(List.of(graph[2][2], graph[2][1]), actualResult, true);
	}

	/** Graphs **/
	
	public static Node [][] graph0() {
		Node [] nodes1 = {
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
		};

		
		Node [] nodes0 =
			{new Node(
				NewArrayList(
				        new Edge(nodes1[0], "A", 8),
						new Edge(nodes1[1], "B", 7),
						new Edge(nodes1[2], "C", 10),
						new Edge(nodes1[3], "B", 3),
						new Edge(nodes1[4], "B", 5), 
						new Edge(nodes1[5], "F", 20)
						))
			};
		
		Node [][] result = { nodes0, nodes1 };
		return result;
	}
	
	public static Node [][] graph1() {				
		Node [] nodes3 = {
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
				new Node(),
		};
		
		Node [] nodes2 =
			{
						new Node(NewArrayList(new Edge(nodes3[0], "A", 2),   
								         new Edge(nodes3[1], "S", 1))),
						
						new Node(NewArrayList(new Edge(nodes3[2], "A", 101), 
								         new Edge(nodes3[3], "R", 102))),
						
						new Node(NewArrayList(new Edge(nodes3[4], "S", 20),  
								         new Edge(nodes3[5], "A", 1))),
						
						new Node(NewArrayList(new Edge(nodes3[6], "A", 2),   
								         new Edge(nodes3[7], "A", 1)))
			};
		
		Node [] nodes1 =
			{
					new Node(NewArrayList(new Edge(nodes2[0], "B", 10), 
							         new Edge(nodes2[1], "Q", 300))),
					
					new Node(NewArrayList(new Edge(nodes2[2], "B", 2), 
							         new Edge(nodes2[3],  "B", 11))),
			};
		
		Node [] nodes0 = 
			{
					new Node(NewArrayList(new Edge(nodes1[0], "A", 4), 
							         new Edge(nodes1[1], "A", 5))),
			};
		
		
		Node [][] result = { nodes0, nodes1, nodes2, nodes3 };
		
		return result;
	}
	
	public static Node [][] graph2() {				
		Node [] nodes4 = 
			{
					new Node(),
					new Node()
			};
		
		Node [] nodes3 =
			{
					new Node(NewArrayList(new Edge(nodes4[0], "A", 5000))),
					
					new Node(),
					
					new Node(NewArrayList(new Edge(nodes4[1], "A", 17)))
			};
		
		Node [] nodes2 = 
			{
					new Node(NewArrayList(new Edge(nodes3[0], "M", 8000))),
					
					new Node(NewArrayList(new Edge(nodes3[1], "S", 501))),
					
					new Node(NewArrayList(new Edge(nodes3[1], "S", 502))),
					
					new Node(NewArrayList(new Edge(nodes3[2], "M", 9)))
			};
		
		Node [] nodes1 = 
			{
					new Node(  NewArrayList(new Edge(nodes2[0], "R", 1))),
					
					new Node(  NewArrayList(new Edge(nodes2[0], "R", 10), 
							           new Edge(nodes3[0], "M", 3), 
							           new Edge(nodes2[1], "M", 2),
							           new Edge(nodes2[2], "M", 4))),
					
					new Node(  NewArrayList(new Edge(nodes3[1], "M", 5),
							           new Edge(nodes2[2], "M", 1),
							           new Edge(nodes2[3], "R", 5)))
			};
		
		Node [] nodes0 = 
			{
					new Node(NewArrayList(new Edge(nodes1[0], "C", 1),
							         new Edge(nodes1[1], "C", 1),
							         new Edge(nodes1[2], "C", 1)))
			};
		
		Node [][] result = {nodes0, nodes1, nodes2, nodes3, nodes4};
		
		return result;
	}
	
	public static Node [][] graph3() {
		Node [] nodes4 = {
				new Node(),
				new Node(),
				new Node(),
				new Node()
		};
		
		Node [] nodes3 = {
				new Node(NewArrayList(new Edge(nodes4[0], "W", 101))),
				
				new Node(NewArrayList(new Edge(nodes4[0], "W", 101),
						         new Edge(nodes4[1], "W", 101),
						         new Edge(nodes4[2], "W", 101))),
				
				new Node(NewArrayList(new Edge(nodes4[0], "W", 101),
				                 new Edge(nodes4[1], "W", 1))),
				
				new Node(NewArrayList(new Edge(nodes4[0], "W", 101),
							     new Edge(nodes4[1], "W", 101),
							     new Edge(nodes4[2], "W", 101),
							     new Edge(nodes4[3], "W", 101))),
		};
		
		Node [] nodes2 = {
				new Node(NewArrayList(new Edge(nodes3[0], "A", 1),
						         new Edge(nodes3[1], "A", 1))),
				
				new Node(NewArrayList(new Edge(nodes3[2], "A", 1))),
				
				new Node(NewArrayList(new Edge(nodes3[3], "A", 2)))
		};
		
		Node [] nodes1 = {
				new Node(NewArrayList(new Edge(nodes2[0], "B", 1))),
				
				new Node(NewArrayList(new Edge(nodes2[1], "B", 2),
		                         new Edge(nodes2[2], "B", 2))),

				new Node(NewArrayList(new Edge(nodes2[2], "B", 1))),
				
				new Node()
		};
		
		Node [] nodes0 = {
				new Node(NewArrayList(new Edge(nodes1[0], "N", 2),
								 new Edge(nodes1[1], "N", 1),
								 new Edge(nodes1[2], "N", 1),
								 new Edge(nodes1[3], "D", 1)))
		};
		
		Node [][] result = {
				nodes0, nodes1, nodes2, nodes3, nodes4
		};
		
		return result;
	}
	
	public static Node [][] graph4() {
		Node [] nodes2 = {
				new Node(),
				new Node(),
				new Node()
		};
		
		Node [] nodes1 = {
				new Node(NewArrayList(new Edge(nodes2[0], "R", 3))),
				new Node(NewArrayList(new Edge(nodes2[0], "R", 5),
									  new Edge(nodes2[1], "R", 1),
						              new Edge(nodes2[2], "R", 10))),
				new Node(NewArrayList(new Edge(nodes2[2], "R", 3))),				
		};
		
		Node [] nodes0 = {
				new Node(NewArrayList(new Edge(nodes1[0], "Q", 2),
						              new Edge(nodes1[1], "Q", 1),
						              new Edge(nodes1[2], "Q", 1)))
		};
		
		Node [][] result = {
				nodes0, nodes1, nodes2
		};
		
		return result;
	}
	
	public Node [][] graph5() {
		Node [] nodes3 = { new Node() };
		
		Node [] nodes2 = {
				new Node(),
				new Node(NewArrayList(new Edge(nodes3[0], "R", 12))),
				new Node()
		};
		
		Node [] nodes1 = {
				new Node(NewArrayList(
						new Edge(nodes2[0], "R", 15),
						new Edge(nodes2[1], "T", 4)
						)),
				new Node(NewArrayList(
						new Edge(nodes2[1], "R", 4),
						new Edge(nodes2[2], "T", 5)
						)),
				new Node(NewArrayList(
						new Edge(nodes2[2], "T",1)
						))				
		};
		
		Node [] nodes0 = {
				new Node(NewArrayList(
						new Edge(nodes1[0], "A", 2),
						new Edge(nodes1[1], "A", 4),
						new Edge(nodes1[2], "A", 2)
						))
		};

		
		Node [] [] result = { nodes0, nodes1, nodes2 };
		
		return result;		
	}
	
	
	/** Hilfsfunktionen **/
	
	/**
	 * @param <E>
	 * @param elems
	 * @return
	 */
	@SafeVarargs
	public static <E> ArrayList<E> NewArrayList(E ... elems) {
		ArrayList<E> result = new ArrayList<E>();
		for(E e : elems) {
			result.add(e);
		}
		return result;
	}
	
	/**
	 * @param graph
	 * @return Kopie der Kanten von {@code graph}. Fuer die resultierende Liste {@code result} gilt, dass 
	 * {@code result.get(i).get(j)} der Kopie der Kanten von {@code graph[i][j]} entspricht. 
	 */
	public List<List<ArrayList<Edge>>> createCopyOfEdges(Node [][] graph) {
		List<List<ArrayList<Edge>>> result = new ArrayList<List<ArrayList<Edge>>>();
		
		for(int i = 0; i < graph.length; i++) {
			ArrayList<ArrayList<Edge>> edgesAtDistance = new ArrayList<ArrayList<Edge>>();			
			for(int j = 0; j < graph[i].length; j++) {				
				ArrayList<Edge> edges = new ArrayList<Edge>();
				
				for(Edge e : graph[i][j].getNeighbours()) {
					edges.add(new Edge(e.getTarget(), e.getLabel(), e.getWeight()));
				}
				
				edgesAtDistance.add(edges);
			}
			result.add(edgesAtDistance);
		}
		
		return result;
	}
	
	/**
	 * Prueft, ob die Kanten im Graphen repraesentiert durch @{code actualGraph} den Kanten in @{code expectedEdgesInGraph} entsprechen.
	 * Diese Methode wird nur aufgerufen, wenn die Dimensionen von @{code expectedEdgesInGraph} und @{code actualGraph} uebereinstimmen.
	 * @param expectedEdgesInGraph
	 * @param actualGraph
	 */
	public static void assertRemoveEdges(List<List<ArrayList<Edge>>> expectedEdgesInGraph, Node [] [] actualGraph) {
		if(expectedEdgesInGraph.size() != actualGraph.length) {
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < expectedEdgesInGraph.size(); i++) {
			if(expectedEdgesInGraph.get(i).size() != actualGraph[i].length) {
				throw new IllegalArgumentException();
			}
			
			for(int j = 0; j < expectedEdgesInGraph.get(i).size(); j++) {
				//Check j-th Node with distance i from origin
				
				List<Edge> expectedEdges = expectedEdgesInGraph.get(i).get(j);
				List<Edge> actualEdges = actualGraph[i][j].getNeighbours();
				
				assertNotNull(actualEdges);
				
				assertEquals(expectedEdges.size(), actualEdges.size(),
						"Node " + j + " with distance " + i + " from the origin has an unexpected number of edges");
				
				for(Edge actualEdge : actualEdges) {
					assertTrue(containsEdge(expectedEdges, actualEdge),
							"Node " + j + " with distance " + i + " from the origin has an unexpected edge " + edgeString(actualEdge));
				}
			}
		}
	}
	
	public static String edgeString(Edge e) {
		return "("+e.getLabel()+","+e.getWeight()+")";
	}
	
	/**
	 * Prueft, ob das Resultat {@code actualNodes} von @{Graph.findNodes} dem erwarteten Resultat {@code expectedNodes}
	 * entspricht. {@checkSorted} gibt an, ob die Reihenfolge der Nodes einbezogen werden soll oder nicht.  
	 * 
	 * @param expectedNodes
	 * @param actualNodes
	 * @param checkSorted
	 */
	public static void assertFindNodes(List<Node> expectedNodes, List<Node> actualNodes, boolean checkSorted) {
		assertNotNull(actualNodes);
		assertEquals(expectedNodes.size(), actualNodes.size());
		if(checkSorted) {
			for(int i = 0; i < actualNodes.size(); i++) {
				assertSame(expectedNodes.get(i), actualNodes.get(i), "Sorting error: Nodes for index " + i + " do not match");				
			}
		} else {
			for(Node actualNode : actualNodes) {
				assertTrue(containsNode(expectedNodes, actualNode), "Unexpected Node in result list");
			}
		}
	}
	
	public static boolean containsEdge(List<Edge> edges, Edge edge) {
		return containsEdge(edges, edge.getTarget(), edge.getLabel(), edge.getWeight());
	}
	
	public static boolean containsEdge(List<Edge> edges, Node target, String label, int w) {
		for(Edge e : edges) {
			if(e.getTarget() == target && e.getLabel().equals(label) && e.getWeight() == w) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsNode(List<Node> nodes, Node node) {
		for(Node n : nodes) {
			if(n == node) {
				return true;
			}
		}
		
		return false;
	}
}
