import java.util.*;

public class Graph {
	
	public static void removeEdges(Node origin, String label, int maxWeight) {
		// coward move
		if (origin == null) {
			return;
		}
		
		// use iterator to delete edges in same loop
		Iterator<Edge> itr = origin.getNeighbours().iterator();
		
		while (itr.hasNext()) {
			
			// get edge
			Edge e = itr.next();
			
			// conditions to be removed
			if (e.getLabel().equals(label) && e.getWeight() <= maxWeight) {
				itr.remove();
			}
			
			// recursive case
			removeEdges(e.getTarget(), label, maxWeight);
		}
		
	}
	
	// doesn't pass testFindNodesS03 and testFindNodesS08, if you spot the bug you can tell me :) 
	public static List<Node> findNodes(Node origin, List<String> path) {
		// coward move
		if (origin == null || path == null) {
			return null;
		}
		
		// use set to automatically only store unique nodes
		Set<Node> nodeSet = new HashSet<>();
		// store minimal weight in path
		Map<Node, Integer> weightMap = new HashMap<>();
		
		// go through nodes
		getNodes(origin, path, 0, Integer.MAX_VALUE, nodeSet, weightMap);
		
		// convert to list
		List<Node> nodeList = new LinkedList<>(nodeSet);
		
		// sort
		nodeList.sort(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				
				// condition 1
				if (weightMap.get(n1) < weightMap.get(n2)) {
					return -1;
				}
				if (weightMap.get(n1) > weightMap.get(n2)) {
					return 1;
				}
				
				// condition 2
				if (n1.getNeighbours().size() < n2.getNeighbours().size()) {
					return -1;
				}
				if (n1.getNeighbours().size() > n2.getNeighbours().size()) {
					return 1;
				}
				
				// same
				return 0;
			}
		});
		
		return nodeList;
	}
	
	public static void getNodes(Node n, List<String> path, int depth, int weight, Set<Node> nodeSet, Map<Node, Integer> weightMap) {
		// made it to end of path, base case
		if (depth == path.size()) {
			nodeSet.add(n);
			
			// already processed that one
			if (weightMap.containsKey(n)) {
				// take smaller
				weight = Math.min(weight, weightMap.get(n));
			}
			
			// put
			weightMap.put(n, weight);
			return;
		}
		
		// all adjacent edges
		for (Edge e : n.getNeighbours()) {
			// meets condition
			if (e.getLabel().equals(path.get(depth))) {
				// next node, depth + 1, minimal weight
				getNodes(e.getTarget(), path, depth + 1, Math.min(e.getWeight(), weight) , nodeSet, weightMap);
			}
			
		}
		
	}
}
