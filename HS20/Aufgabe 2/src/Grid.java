
public class Grid {
	
	public static void main(String[] args) {
		Node n00 = new Node(null, null);
		Node n01 = new Node(null, null);
		n00.setRight(n01);
		Node n10 = new Node(null, null);
		n00.setBottom(n10);
		Node n11 = new Node(null, null);
		
		n01.setBottom(n11);
		n10.setRight(n11);
		
		Grid.adjustGrid(n00, 3, 2);
		System.out.println("Executed main for Grid");
	}
	
	// I think we can assume that our grid before the function call is valid
	public static void adjustGrid(Node origin, int A, int B) {
		// get existing grid
		Node[][] g = new Node[A][B];
		
		// get grid
		g[0][0] = origin;
		
		// fill first row (or what we have)
		int y = 1;
		while (y < B && g[0][y - 1].getRight() != null) {
			g[0][y] = g[0][y - 1].getRight();
			++y;
		}
		
		// fill whole array (or what we have)
		int x = 1;
		while (x < A && g[x - 1][0].getBottom() != null) {
			for (int i = 0; i < y; ++i) {
				g[x][i] = g[x - 1][i].getBottom();
			}
			++x;
		}
		
		// not done with columns
		for (;y < B; ++y) {
			for (int i = 0; i < x; ++i) {
				// new object
				g[i][y] = new Node(null, null);
				// link from right
				g[i][y - 1].setRight(g[i][y]);
				// link from above if necessary
				if (i > 0) {
					g[i - 1][y].setBottom(g[i][y]);
				}
				
			}
		}
		
		// not done with rows
		for (;x < A; ++x) {
			// can assume that all columns are filled
			for (int i = 0; i < B; ++i) {
				// new node
				g[x][i] = new Node(null, null);
				// link from above
				g[x - 1][i].setBottom(g[x][i]);
				// link from right if necessary
				if (i > 0) {
					g[x][i - 1].setRight(g[x][i]);
				}
			}
		}
		
		// cut edges
		for (int i = 0; i < A; ++i) {
			// nodes on right to null
			g[i][B - 1].setRight(null);
		}
		
		for (int i = 0; i < B; ++i) {
			// nodes on bottom to null
			g[A - 1][i].setBottom(null);
		}

	}

}
