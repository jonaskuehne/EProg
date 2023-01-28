import java.util.*;

public class Graph {
	
	public static void removeEdges(Node origin, String label, int maxWeight) {

		if (origin == null) {
			return;
		}
		
		Set<Edge> remove = new HashSet<>();
		
		for (Edge e : origin.getNeighbours()) {
			
			if (e.getLabel().equals(label) && e.getWeight() <= maxWeight) {
				remove.add(e);
			}
			
			removeEdges(e.getTarget(), label, maxWeight);
			
		}
		
		for (Edge e : remove) {
			origin.getNeighbours().remove(e);
		}
		
	}
	
	public static List<Node> findNodes(Node origin, List<String> path) {
		List<Node> nodeList = new LinkedList<>();
		
		if (origin == null || path == null) {
			return null;
		}
		
		Set<Node> nodeSet = new HashSet<>();
		Map<Node, Integer> weightMap = new HashMap<>();
		
		getNodes(origin, path, 0, Integer.MAX_VALUE, nodeSet, weightMap);
		
		for (Node n : nodeSet) {
			nodeList.add(n);
		}
		
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
		
		if (depth == path.size()) {
			nodeSet.add(n);
			
			if (weightMap.containsKey(n)) {
				weight = Math.min(weight, weightMap.get(n));
			}
			
			weightMap.put(n, weight);
			return;
		}
		
		for (Edge e : n.getNeighbours()) {
			
			if (e.getLabel().equals(path.get(depth))) {
				getNodes(e.getTarget(), path, depth + 1, Math.min(e.getWeight(), weight) , nodeSet, weightMap);
			}
			
		}
		
	}
}
