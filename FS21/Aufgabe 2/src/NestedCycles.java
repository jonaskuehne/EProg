import java.util.*;

public class NestedCycles {

	public static void main(String[] args) {
		Node n1 = new Node(null, null);
		Node n2 = new Node(null, null);
		n1.setOuter(n2);
		n2.setOuter(n1);

		System.out.println(isNestedCycle(n1)); // expected: true
		Map<Node, Integer> dist = cycleDistance(n1);
		System.out.println("Distance size: " + dist.size()); // expected: 2
		System.out.println("Distance from n1 to n1: " + dist.get(n1)); // expected: 0
		System.out.println("Distance from n1 to n2: " + dist.get(n2)); // expected: 1
	}

	public static Map<Node, Integer> cycleDistance(Node n0) {
		Map<Node, Integer> map = new HashMap<>();

		map.put(n0, 0);

		Node n = n0.getOuter();
		int count = 1;

		while (n != n0 && n != null) {
			map.put(n, count);

			n = n.getOuter();
			++count;
		}

		return map;
	}

	public static Set<Node> getCircleSet(Node n0) {
		Set<Node> circle = new HashSet<>();

		circle.add(n0);
		Node n = n0.getOuter();

		while (n != n0) {

			circle.add(n);
			n = n.getOuter();

		}

		return circle;
	}

	public static boolean isNestedCycle(Node n0) {

		// coward move
		if (n0 == null) {
			return false;
		}

		// list with all circles
		List<Set<Node>> graph = new ArrayList<>();

		Node n = n0;

		Set<Node> visited = new HashSet<>();

		// go inwards
		while (n != null) {
			// store elements
			Set<Node> circleSet = new HashSet<>();
			circleSet.add(n);

			// go along circle
			Node n1 = n.getOuter();
			while (n1 != n && n1 != null) {

				// saw that one already
				if (visited.contains(n1)) {
					return false;
				}

				// saw that one
				visited.add(n1);
				// add
				circleSet.add(n1);
				// go to next
				n1 = n1.getOuter();
			}

			// not same -> circle not full
			if (n1 != n) {
				return false;
			}

			// add set to graph
			graph.add(circleSet);

			n = n.getInner();
		}

		// check if set disjunct
		Set<Node> all = new HashSet<>();
		int numNodes = 0;
		for (Set<Node> circle : graph) {
			numNodes += circle.size();
			all.addAll(circle);
		}

		if (numNodes != all.size()) {
			return false;
		}
		
		// check inner references
		for (int i = 0; i < graph.size() - 1; ++i) {
			for (Node n1 : graph.get(i)) {
				if (!graph.get(i + 1).contains(n1.getInner())) {
					return false;
				}
			}
		}
		
		// inner circle
		for (Node n1 : graph.get(graph.size() - 1)) {
			if (n1.getInner() != null) {
				return false;
			}
		}

		// TODO
		return true;
	}
}
