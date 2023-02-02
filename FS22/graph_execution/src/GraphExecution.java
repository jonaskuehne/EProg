import java.util.*;

// tweaked the classes to fit the grading tests, mix up between list / arrays -> now all arrays

public class GraphExecution {
	
	// task a)
	public static List<ProgramState> allResults(Node n, ProgramState initState) {
		// list for states
		List<ProgramState> states = new LinkedList<>();
		// add first one
		states.add(initState);
		
		// fill
		getStates(n, states);
		
		return states;
	}
	
	public static void getStates(Node n, List<ProgramState> states) {
		
		// type: add
		if (n.getType().equals("ADD")) {
			// just change values for all states
			for (ProgramState state : states) {
				state.incCounter();
				state.incSum(n.getValue());
			}
		
		// type: sequential
		} else if (n.getType().equals("SEQ")) {
			// execute for all successors
			for (Node next : n.getSubnodes()) {
				getStates(next, states);
			}
		
		// type: choice
		} else {
			// list for all potential states
			List<ProgramState> storeChoice = new LinkedList<>();
			// get for all successors
			for (Node next : n.getSubnodes()) {
				// clone list
				List<ProgramState> copy = cloneList(states);
				// get execute on copy
				getStates(next, copy);
				// add everything
				storeChoice.addAll(copy);
			}
			
			// replace
			states.clear();
			states.addAll(storeChoice);
		}
		
	}
	
	// clones list
	public static List<ProgramState> cloneList(List<ProgramState> source) {
		List<ProgramState> clone = new LinkedList<>();
		
		for (ProgramState state : source) {
			// added this to ProgramState
			clone.add(state.clone());
		}
		
		return clone;
		
	}
	
	// task b)
	public static void sort(List<List<ProgramState>> input) {
		
		// sort with custom comparator
		input.sort(new Comparator <List<ProgramState>> () {

			@Override
			public int compare(List<ProgramState> l1, List<ProgramState> l2) {
				// get max states
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
				
				// same
				return 0;
			}
			
		});
		
	}
	
	public static ProgramState maxState(List<ProgramState> states) {
		// initial state
		ProgramState maxState = states.get(0);
		
		// go through all
		for (ProgramState state : states) {
			// condition to be maximal
			if (state.getSum() > maxState.getSum() || 
					(state.getSum() == maxState.getSum() && state.getCounter() >= maxState.getCounter())) {
				maxState = state;
			}
			
		}
		
		return maxState;
	}
	
	// task c)
	public static boolean isSubProgram(Node n1, Node n2) {
		
		// distinction between node types of n2
		if (n2.getType().equals("ADD")) {
			return isSubProgramAdd(n1, n2.getValue());
		} else {
			return isSubProgramSeq(n1, n2.getSubnodes());
		}
	}
	
	// n2 add node
	public static boolean isSubProgramAdd(Node n, int value) {
		
		// base case
		if (n.getType().equals("ADD")) {
			// same?
			return n.getValue() == value;
		}
		
		// recursive case
		for (Node next : n.getSubnodes()) {
			// if one of them returns true
			if (isSubProgramAdd(next, value)) {
				return true;
			}
		}
		
		// nothing reachable with same value
		return false;
	}
	
	// n2 sequential node
	public static boolean isSubProgramSeq(Node n, Node[] subs) {
		
		// base cases
		// add -> cannot be same
		if (n.getType().equals("ADD")) {
			return false;
		}
		// check if permutation
		if (isPermutation(n.getSubnodes(), subs)) {
			return true;
		}
		
		// recursive case
		for (Node next : n.getSubnodes()) {
			// one of them true
			if (isSubProgramSeq(next, subs)) {
				return true;
			}
		}
		
		// nothing reachable with permutation list
		return false;
	}
	
	public static boolean isPermutation(Node[] l1, Node[] l2) {
		// not same length -> no permutation
		if (l1.length != l2.length) {
			return false;
		}
		
		// check for each node if there is same in other array
		for (Node n1 : l1) {
			boolean hasSame = false;
			for (Node n2 : l2) {
				
				// has same
				if (isSubProgram(n1, n2)) {
					hasSame = true;
					break;
				}
			}
			
			// at least one node has no corresponding instance in other list
			if (!hasSame) {
				return false;
			}
			
		}
		
		// all good
		return true;
	}

}
