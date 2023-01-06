import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Pyramid {
	
	public static boolean isPyramid(Node node) {
		
		// check if not null
		if (node == null) {
			throw new IllegalArgumentException();
		}
		
		// collections for storage
		ArrayList<Set<Node>> nodes = new ArrayList<>();
		ArrayList<ArrayList<Node>> nodeList = new ArrayList<>();
		
		// fill collections
		fillList(0, nodes, node, 0, nodeList);

		// store all nodes -> set as same objects only once stored
		Set<Node> all = new HashSet<>();
		
		// go through levels
		for (int i = 0; i < nodes.size(); ++i) {
			
			// check number per level
			if (nodes.get(i).size() != i + 1) {
				return false;
			}
			
			// add to global
			all.addAll(nodes.get(i));
		}
		
		// check global number
		int k = nodes.size();
		
		if (all.size() != (k * (k + 1)) / 2) {
			return false;
		}
		
		// check successors
		for (int i = 0; i < nodeList.size() - 1; ++i) {
			for (int j = 0; j < nodeList.get(i).size(); ++j) {
				// left
				if (nodeList.get(i).get(j).getLeft() == null ||  nodeList.get(i).get(j).getLeft() != nodeList.get(i + 1).get(j)) {
					return false;
				}
				// right
				if (nodeList.get(i).get(j).getRight() == null ||  nodeList.get(i).get(j).getRight() != nodeList.get(i + 1).get(j + 1)) {
					return false;
				}
			}
		}
		
		// check if all at end null not necessary as else list would have additional level
		
		// all good
		return true;
	}
	
	public static void fillList(int level, ArrayList<Set<Node>> nodes, Node n, int i, ArrayList<ArrayList<Node>> nodeList) {
		
		// increase list if necessary
		if (nodes.size() <= level) {
			nodes.add(new HashSet<>());
			nodeList.add(new ArrayList<>());
		}
		
		// add to set
		nodes.get(level).add(n);
		
		// increase sublist if necessary
		// add to list
		if (nodeList.get(level).size() <= i) {
			nodeList.get(level).add(n);
		} else {
			nodeList.get(level).set(i, n);
		}
		
		// recursive cases
		if (n.getLeft() != null) {
			fillList(level + 1, nodes, n.getLeft(), i, nodeList);
		}
		
		if (n.getRight() != null) {
			fillList(level + 1, nodes, n.getRight(), i + 1, nodeList);
		}
		
	}

	public static void main(String[] args) {
		Node pyramid = new Node(
			new Node(null, null),
			new Node(null, null));
        System.out.println("result: " + isPyramid(pyramid));
	}

}
