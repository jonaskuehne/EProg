import java.util.*;

public class GraphExecution {
	
	public static List<ProgramState> allResults(Node n, ProgramState initState) {
		List<ProgramState> states = new LinkedList<>();
		
		states.add(initState);
		
		getStates(n, states);
		
		return states;
	}
	
	public static void getStates(Node n, List<ProgramState> states) {
		
		// add
		if (n.getType().equals("ADD")) {
			
			for (ProgramState state : states) {
				state.incCounter();
				state.incSum(n.getValue());
			}
		
		// seq
		} else if (n.getType().equals("SEQ")) {
			
			for (Node next : n.getSubnodes()) {
				getStates(next, states);
			}
		
		// choice
		} else {
			
			List<ProgramState> storeChoice = new LinkedList<>();
			for (Node next : n.getSubnodes()) {
				List<ProgramState> copy = cloneList(states);
				getStates(next, copy);
				storeChoice.addAll(copy);
			}
			
			states.clear();
			states.addAll(storeChoice);
			
		}
		
	}
	
	public static List<ProgramState> cloneList(List<ProgramState> source) {
		List<ProgramState> clone = new LinkedList<>();
		
		for (ProgramState state : source) {
			clone.add(state.clone());
		}
		
		return clone;
		
	}
	
	public static void sort(List<List<ProgramState>> input) {
		
		input.sort(new Comparator <List<ProgramState>> () {

			@Override
			public int compare(List<ProgramState> l1, List<ProgramState> l2) {
				
				ProgramState maxStateL1 = maxState(l1);
				ProgramState maxStateL2 = maxState(l2);
				
				// condition 1
				if (maxStateL1.getSum() > maxStateL2.getSum()) {
					return -1;
				}
				if (maxStateL1.getSum() < maxStateL2.getSum()) {
					return 1;
				}
				
				// condition 2
				if (maxStateL1.getCounter() > maxStateL2.getCounter()) {
					return -1;
				}
				if (maxStateL1.getCounter() < maxStateL2.getCounter()) {
					return 1;
				}
				
				
				return 0;
			}
			
		});
		
	}
	
	public static ProgramState maxState(List<ProgramState> states) {
		
		ProgramState maxState = states.get(0);
		
		for (ProgramState state : states) {
			
			if (state.getSum() > maxState.getSum() || 
					(state.getSum() == maxState.getSum() && state.getCounter() >= maxState.getCounter())) {
				maxState = state;
			}
			
		}
		
		return maxState;
	}
	
	public static boolean isSubProgram(Node n1, Node n2) {
		
		if (n2.getType().equals("ADD")) {
			return isSubProgramAdd(n1, n2.getValue());
		} else {
			
			return isSubProgramSeq(n1, n2.getSubnodes());
		}
	}
	
	public static boolean isSubProgramAdd(Node n, int value) {
		
		// base case
		if (n.getType().equals("ADD")) {
			return n.getValue() == value;
		}
		
		// recursive case
		for (Node next : n.getSubnodes()) {
			if (isSubProgramAdd(next, value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isSubProgramSeq(Node n, List<Node> subs) {
		
		// base cases
		if (n.getType().equals("ADD")) {
			return false;
		}
		
		if (isPermutation(n.getSubnodes(), subs)) {
			return true;
		}
		
		// recursive case
		for (Node next : n.getSubnodes()) {
			if (isSubProgramSeq(next, subs)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isPermutation(List<Node> l1, List<Node> l2) {
		
		if (l1.size() != l2.size()) {
			return false;
		}
		
		for (Node n1 : l1) {
			boolean hasSame = false;
			
			for (Node n2 : l2) {
				
				if (isSubProgram(n1, n2)) {
					hasSame = true;
					break;
				}
				
			}
			
			if (!hasSame) {
				return false;
			}
			
		}
		
		return true;
	}

}
