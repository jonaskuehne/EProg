public class Node {
	
	private final String type;
	
	private final int value;
	
	// changed to array to fit grading tests
	private Node[] subnodes;
	
	public String getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public Node[] getSubnodes() {
		return this.subnodes;
	}

	public Node(int value, String type, Node[] subnodes) {
		this.value = value;
		this.type = type;
		this.subnodes = subnodes;
	}
	
	public static Node newChoiceNode(Node[] subnodes) {
		return new Node(0, "CHOICE", subnodes);
	}
	
	public static Node newSeqNode(Node[] subnodes) {
		return new Node(0, "SEQ", subnodes);
	}
	
	public static Node newAddNode(int value) {
		return new Node(value, "ADD", new Node[] {});
	}

}
