
import java.util.*;

// Implementieren Sie die noetigen Methoden.

public class RoutingNode implements Node {
	
	protected Map<Integer, Node> routingTable;
	protected Map<Integer, Integer> prioTable;
	
	// Sie koennen Felder und Methoden hinzufuegen,
	// aber aendern Sie nicht die gegebenen Signaturen!
	
	RoutingNode(Map<Integer, Node> routingTable) {
		this.routingTable = routingTable;
		prioTable = new HashMap<>();
		for (int i : routingTable.keySet()) {
			prioTable.put(i, 0);
		}
	}
	
	Map<Integer, Node> getRoutingTable() {
		return new HashMap<Integer, Node>(routingTable);
	}
	
	public Set<Integer> candidates(List<Set<Integer>> path) {
		Set<Integer> cand = new HashSet<>();
		Set<Integer> pathSet = path.get(0);
		
		for (int c : pathSet) {
			if (routingTable.containsKey(c)) {
				cand.add(c);
			}
		}
		
		return cand;
	}
	
	public void incrementCount(int id) {
		prioTable.put(id, 1 + prioTable.get(id));
	}
	
	public int selectConnection(Set<Integer> candidates) {
		// init
		int count = Integer.MAX_VALUE;
		int iD = 0;
		
		// for all possible connections
		for (int c : candidates) {
			// better
			if (prioTable.get(c) < count) {
				iD = c;
				count = prioTable.get(c);
			// same
			} else if (prioTable.get(c) == count) {
				iD = Math.min(iD, c);
			}
		}
		
		return iD;
	}
	
	@Override
	public void receive(Message msg) {
		List<Set<Integer>> path = new LinkedList<Set<Integer>>(msg.getPath());
		if (path.isEmpty()) {
			process(msg);
		} else {
			Set<Integer> candidates = candidates(path);
			if (!candidates.isEmpty()) {
				Integer target = selectConnection(candidates);
				
				List<Set<Integer>> newPath = path.subList(1, path.size());
				Message newMsg = msg.withPath(newPath);
				incrementCount(target);
				
				routingTable.get(target).receive(newMsg);
			}
		}
	}
	
	public void process(Message msg) {
		// is update
		if (msg instanceof UpdateMessage) {
			Integer newId = ((UpdateMessage) msg).newId;
			Node newNode = ((UpdateMessage) msg).newNode;
			
			routingTable.put(newId, newNode);
			prioTable.put(newId, 0);
		}
	}
	
}
