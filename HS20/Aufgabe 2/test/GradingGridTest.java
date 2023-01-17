import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GradingGridTest {
	
	@Test
	public void test01AdjustToOrigin() {
		final Node [][] grid = createGrid(3,2);
		
		Grid.adjustGrid(grid[0][0], 1, 1);
        
        assertNull(grid[0][0].getRight());
        assertNull(grid[0][0].getBottom());
	}
	
	@Test
	public void test02OnlyAddNodes() {
		final Node [][] grid = createGrid(2,3);			
		
		Grid.adjustGrid(grid[0][0], 4, 3);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 3);
		checkRowsEqualUpTo(grid[0], row0, 3);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 3);
		checkRowsEqualUpTo(grid[1], row1, 3);
		checkRowsConnected(row0, row1);
		
		final Node [] row2 = checkAndGetRow(row1[0].getBottom(), 3);
		checkRowsConnected(row1, row2);
		
		final Node [] row3 = checkAndGetRow(row2[0].getBottom(), 3);
		checkRowsConnected(row2, row3);
		checkIsLastRow(row3);
	}
	
	@Test
	public void test03OnlyAddNodes() {
		final Node [][] grid = createGrid(2,2);
		
		Grid.adjustGrid(grid[0][0], 4, 2);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 2);
		checkRowsEqualUpTo(grid[0], row0, 2);
				
		final Node [] row1 = checkAndGetRow(grid[1][0], 2);
		checkRowsEqualUpTo(grid[1], row1, 2);
		checkRowsConnected(row0, row1);
		
		final Node [] row2 = checkAndGetRow(row1[0].getBottom(), 2);
		checkRowsConnected(row1, row2);

		final Node [] row3 = checkAndGetRow(row2[0].getBottom(), 2);
		checkRowsConnected(row2, row3);
		checkIsLastRow(row3);
	}
	
	@Test
	public void test04OnlyAddNodes() {
		final Node [][] grid = createGrid(3,3);
		
		Grid.adjustGrid(grid[0][0], 5, 4);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		assertEquals(grid[2][0], grid[1][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 4);
		checkRowsEqualUpTo(grid[0], row0, 3);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 4);
		checkRowsEqualUpTo(grid[1], row1, 3);
		checkRowsConnected(row0, row1);

		final Node [] row2 = checkAndGetRow(grid[2][0], 4);
		checkRowsEqualUpTo(grid[2], row2, 3);
		checkRowsConnected(row1, row2);
		
		assertNotNull(row2[0].getBottom());
		final Node [] row3 = checkAndGetRow(row2[0].getBottom(), 4);		
		checkRowsConnected(row2, row3);
		
		assertNotNull(row3[0].getBottom());
		final Node [] row4 = checkAndGetRow(row3[0].getBottom(), 4);
		checkRowsConnected(row3, row4);
		checkIsLastRow(row4);
	}
	
	@Test
	public void test05OnlyRemoveNodes() {
		final Node [][] grid = createGrid(2,3);
		
		Grid.adjustGrid(grid[0][0], 2, 1);
		
		assertNull(grid[0][0].getRight());

		assertEquals(grid[1][0], grid[0][0].getBottom());

		assertNull(grid[1][0].getRight());
		assertNull(grid[1][0].getBottom());
	}
	
	@Test
	public void test06OnlyRemoveNodes() {
		final Node [][] grid = createGrid(3,3);
		
		Grid.adjustGrid(grid[0][0], 2, 3);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 3);
		checkRowsEqualUpTo(grid[0], row0, 3);
		
		final Node[] row1 = checkAndGetRow(grid[1][0], 3);
		checkRowsEqualUpTo(grid[1], row1, 3);
		checkRowsConnected(row0, row1);
		checkIsLastRow(row1);
	}

	@Test
	public void test07OnlyRemoveNodes() {
		final Node [][] grid = createGrid(5,4);
		
		Grid.adjustGrid(grid[0][0], 3, 2);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		assertEquals(grid[2][0], grid[1][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 2);
		checkRowsEqualUpTo(grid[0], row0, 2);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 2);
		checkRowsEqualUpTo(grid[1], row1, 2);
		checkRowsConnected(row0, row1);

		final Node [] row2 = checkAndGetRow(grid[2][0], 2);
		checkRowsEqualUpTo(grid[2], row2, 2);
		checkRowsConnected(row1, row2);
		checkIsLastRow(row2);
	}
	
	@Test
	public void test08Grid() {
		final Node [][] grid = createGrid(4,4);
		
		Grid.adjustGrid(grid[0][0], 5, 3);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		assertEquals(grid[2][0], grid[1][0].getBottom());
		assertEquals(grid[3][0], grid[2][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 3);
		checkRowsEqualUpTo(grid[0], row0, 3);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 3);
		checkRowsEqualUpTo(grid[1], row1, 3);
		checkRowsConnected(row0, row1);
		
		final Node [] row2 = checkAndGetRow(grid[2][0], 3);
		checkRowsEqualUpTo(grid[2], row2, 3);
		checkRowsConnected(row1, row2);
		
		final Node [] row3 = checkAndGetRow(grid[3][0], 3);
		checkRowsEqualUpTo(grid[3], row3, 3);
		checkRowsConnected(row2, row3);		
		
		final Node [] row4 = checkAndGetRow(row3[0].getBottom(), 3);
		checkRowsConnected(row3, row4);
		checkIsLastRow(row4);
	}
	
	@Test
	public void test09Grid() {
		final Node [][] grid = createGrid(4,2);
		
		Grid.adjustGrid(grid[0][0], 2, 4);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
		
		final Node [] row0 = checkAndGetRow(grid[0][0], 4);
		checkRowsEqualUpTo(grid[0], row0, 2);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 4);
		checkRowsEqualUpTo(grid[1], row1, 2);
		checkRowsConnected(row0, row1);
		checkIsLastRow(row1);
	}
	
	@Test
	public void test10Grid() {
		final Node [][] grid = createGrid(2,3);
		
		Grid.adjustGrid(grid[0][0], 4, 1);
		
		assertEquals(grid[1][0], grid[0][0].getBottom());
				
		final Node [] row0 = checkAndGetRow(grid[0][0], 1);
		checkRowsEqualUpTo(grid[0], row0, 1);
		
		final Node [] row1 = checkAndGetRow(grid[1][0], 1);
		checkRowsConnected(row0, row1);
		
		final Node [] row2 = checkAndGetRow(row1[0].getBottom(), 1);
		checkRowsConnected(row1, row2);

		final Node [] row3 = checkAndGetRow(row2[0].getBottom(), 1);
		checkRowsConnected(row2, row3);	
		checkIsLastRow(row3);
	}
	
	/** helper functions **/
		
	//get row starting from leftMost and check that it has exactly nCols columns
	public static Node [] checkAndGetRow(Node leftMost, int nCols) {
		Node [] result = new Node[nCols];
		Node cur = leftMost;
		
		for(int i = 0; i < nCols; i++) {
			assertNotNull(cur);
			result[i] = cur;
			cur = cur.getRight();
		}
		
		//cur is the right node of the final node in the row
		assertNull(cur);
		return result;
	}
	
	
	public static void checkIsLastRow(Node [] row) {
		for(int i = 0; i < row.length; i++) {
			assertNull(row[i].getBottom());
		}
	}
	
	public static void checkRowsConnected(Node [] top, Node [] bottom) {
		for(int i = 0; i < top.length; i++) {
			assertEquals(bottom[i], top[i].getBottom());
		}
	}
	
	//checks the two input rows are the same for the first k elements
	public static void checkRowsEqualUpTo(Node [] first, Node [] second, int k) {
		for(int i = 0; i < k; i++) {
			assertEquals(first[i], second[i]);
		}
	}
	
	public static Node [][] createGrid(int nrows, int ncols) {		
		Node [][] gridNodes = new Node[nrows][ncols];
		
		for(int i = 0; i < nrows; i++) {
			for(int j = 0; j < ncols; j++) {
				Node curNode = new Node(null, null);
				gridNodes[i][j] = curNode;
				
				if(i != 0) {
					gridNodes[i-1][j].setBottom(curNode);
				}
				if(j != 0) {
					gridNodes[i][j-1].setRight(curNode);
				}
			}
		}
		
		return gridNodes;
	}


}
