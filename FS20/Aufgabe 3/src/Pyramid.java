import java.util.*;

public class Pyramid {

	public static void main(String[] args) {
		Node pyramid = new Node(
			new Node(null, null),
			new Node(null, null));
        System.out.println("result: " + isPyramid(pyramid));
	}
	
	public static boolean isPyramid(Node node) {
		// coward move
		if (node == null) {
			return false;
		}
		
		// lists
		List<Set<Node>> nodeSet = new ArrayList<>();
		List<List<Node>> nodeList = new ArrayList<>();
		Set<Node> allNodes = new HashSet<>();
		
		// fill
		fill(node, 0, 0, nodeSet, nodeList);
		
		// check unique elements
		for (int level = 0; level < nodeSet.size(); ++level) {
			// not right number of nodes per level
			if (nodeSet.get(level).size() != level + 1) {
				return false;
			}
			
			// add to set with all nodes
			allNodes.addAll(nodeSet.get(level));
		}
		
		// check number of all nodes correct
		int k = nodeSet.size();
		int all = (k * (k + 1)) / 2;
		
		if (allNodes.size() != all) {
			return false;
		}
		
		// check children, not for last one
		for (int i = 0; i < nodeList.size() - 1; ++i) {
			for (int j = 0; j < nodeList.get(i).size(); ++j) {
				// check left
				if (nodeList.get(i).get(j).getLeft() != nodeList.get(i + 1).get(j)) {
					return false;
				}
				// check right
				if (nodeList.get(i).get(j).getRight() != nodeList.get(i + 1).get(j + 1)) {
					return false;
				}
			}
		}
		
		// check for last
		for (int i = 0; i < nodeList.get(k - 1).size(); ++i) {
			// check left
			if (nodeList.get(k - 1).get(i).getLeft() != null) {
				return false;
			}
			// check right
			if (nodeList.get(k - 1).get(i).getRight() != null) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public static void fill(Node n, int level, int num, List<Set<Node>> nodeSet, List<List<Node>> nodeList) {
		// base case
		if (n == null) {
			return;
		}
		
		// lists large enough?
		if (nodeList.size() <= level) {
			nodeList.add(new ArrayList<>());
			nodeSet.add(new HashSet<>());
		}
		
		// add to set
		nodeSet.get(level).add(n);
		
		// check if already in list
		if (nodeList.get(level).size() <= num) {
			nodeList.get(level).add(n);
		} else {
			nodeList.get(level).set(num, n);
		}
		
		// go left
		fill(n.getLeft(), level + 1, num, nodeSet, nodeList);
		// go right
		fill(n.getRight(), level + 1, num + 1, nodeSet, nodeList);
	}

}
