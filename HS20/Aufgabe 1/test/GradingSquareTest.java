import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradingSquareTest {
	
	List<int [][]> allTrue;
	List<int [][]> allFalse;
	
	@BeforeEach
	public void before() {
		allTrue = new ArrayList<int [][]>();
		allFalse = new ArrayList<int [][]>();
		
		{
			int[][] t3 = { 
				{20, 5, 0}, 
				{8, 6, 5}, 
				{20, 5, 0} 
			};
			allTrue.add(t3);
		}
		
		{
			int[][] t3 = { 
				{1, 2, 3}, 
				{0, 4, 5}, 
				{1, 2, 3} 
			};
			allTrue.add(t3);
		}
				
		{
			int[][] t5 = { 
				{0, 3, 5, 7, 2}, 
				{2, 7, 4, 3, 0}, 
				{4, 4, 6, 1, 4}, 
				{7, 7, 4, 3, 3}, 
				{0, 3, 5, 7, 2} 
			};
			allTrue.add(t5);
		}
		
		{
			int[][] t5 = { 
				{1, 2, 3, 4, 5}, 
				{9, 3, 8, 2, 8}, 
				{7, 2, 1, 9, 8}, 
				{6, 3, 8, 2, 0}, 
				{1, 2, 3, 4, 5} 
			};
			allTrue.add(t5);
		}
		
		{
			int[][] f3 = { 
				{3, 5, 14}, 
				{4, 19, 8}, 
				{0, 13, 3} 
			};
			allFalse.add(f3);
		}
		
		{
			int[][] f3 = { 
				{1, 2, 5}, 
				{0, 4, 5}, 
				{1, 2, 3} 
			};
			allFalse.add(f3);
		}
		
		{				 
			int[][] f5 = { 
				{7, 2, 3, 2, 5}, 
				{5, 0, 6, 5, 4}, 
				{0, 6, 4, 5, 6}, 
				{5, 5, 7, 1, 3}, 
				{3, 4, 6, 3, 2} 
			};
			allFalse.add(f5);
		}
		
		{				 
			int[][] f5 = { 
				{1, 2, 3, 4, 5}, 
				{9, 3, 8, 2, 8}, 
				{7, 2, 1, 9, 8}, 
				{6, 3, 8, 2, 0}, 
				{1, 2, 3, 4, 0} 
			};
			allFalse.add(f5);
		}
		
	}
	
	public boolean atLeastOneTrue() {
		for(int[][] square : allTrue) {
			try {
				if(Square.checkProperty(square)) {
					return true;
				}
			} catch(Throwable e) {
				// ignore exception
			}
		}
		return false;
	}
	
	public boolean atLeastOneFalse() {
		for(int[][] square : allFalse) {
			try {
				if(!Square.checkProperty(square)) {
					return true;
				}
			} catch(Throwable e) {
				// ignore exception
			}
		}
		return false;
	}
	
	@Test
	public void testSmallTrue() {
		for(int[][] square : allTrue) {
			assertTrue(Square.checkProperty(square));
		}
		// do not award points if the solution always returns true or false
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
	@Test
	public void testSmallFalse() {
		for(int[][] square : allFalse) {
			assertFalse(Square.checkProperty(square));
		}
		// do not award points if the solution always returns true or false
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
	@Test
	public void testCornersTrue() {

		{
			int[][] sq = { 
				{1, 1, 1, 1, 2}, 
				{1, 1, 1, 1, 1}, 
				{1, 1, 1, 1, 1}, 
				{1, 1, 1, 1, 1}, 
				{1, 1, 1, 1, 2}
			};
			assertTrue(Square.checkProperty(sq));	
		}
		
		{
			int[][] sq = { 
				{1, 1, 1, 1, 1}, 
				{1, 1, 1, 4, 1}, 
				{1, 1, 555, 1, 1}, 
				{1, 1, 1, 4, 1}, 
				{1, 1, 1, 1, 1}
			};
			assertTrue(Square.checkProperty(sq));	
		}
		
		{
			int[][] sq = { 
				{3, 3, 3, 3, 3}, 
				{3, 3, 3, 3, 3}, 
			   {17, 4, 7, 5, 23}, 
				{3, 3, 3, 3, 3}, 
				{3, 3, 3, 3, 3},
			};
			assertTrue(Square.checkProperty(sq));	
		}
		
		{
			int[][] sq = { 
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 17, 6, 7, 54, 9},
				{1, 2, 32, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{7, 5, 8, 3, 5, 6, 7, 8, 9},
				{7, 5, 8, 4, 5, 6, 7, 8, 9},
				{7, 5, 32, 4, 5, 6, 7, 8, 9},
				{7, 2, 3, 4, 17, 6, 7, 54, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			};
			assertTrue(Square.checkProperty(sq));	
		}
		
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
	@Test
	public void testCornersFalse() {

		{
			int[][] sq = { 
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 9, 6, 7, 8, 9},
				{7, 5, 8, 3, 5, 6, 7, 8, 9},
				{7, 5, 8, 4, 8, 6, 7, 8, 9},
				{7, 5, 3, 4, 5, 6, 7, 8, 9},
				{7, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			};
			assertFalse(Square.checkProperty(sq));	
		}
		
		{
			int[][] sq = { 
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 17, 6, 7, 8, 9},
				{1, 2, 42, 4, 5, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{7, 5, 8, -3, 5, 6, 7, 8, 9},
				{7, 5, 8, 4, 5, 6, 7, 8, 9},
				{7, 5, 23, 4, 5, 6, 7, 8, 9},
				{7, 2, 3, 4, 17, 6, 7, 8, 9},
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			};
			assertFalse(Square.checkProperty(sq));	
		}
		
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
	@Test
	public void testRotatedTriangleFalse() {
		
		{
			int[][] sq = { 
				{1, 4, 1}, 
				{2, 3, 2}, 
				{1, 5, 1},
			};
			assertFalse(Square.checkProperty(sq));	
		}
		
		{
			int[][] sq = { 
				{1, 8, 42, 13, 1}, 
				{5, 2, 9, 2, 5}, 
			    {7, 4, 50, 4, 7}, 
				{5, 2, 0, 2, 5}, 
				{1, 23, 17, 18, 1},
			};
			assertFalse(Square.checkProperty(sq));	
		}
		
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
	@Test
	public void testLargeTrue() {
		{
			int[][] sq = { 
				{ 1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17},
				{42,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 6 },
				{19, 42,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 5,  1 },
				{72,  2, 76,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 4, 16, 10 },
				{ 3, 14, 15,  2,  5,  6,  7,  8,  9, 10, 11, 12, 13, 3, 15, 16, 11 },
				{26, 35, 70,  4,  3,  6,  7,  8,  9, 10, 11, 12, 2, 14, 15, 16, 12 },
				{68, 41, 54,  4,  5,  4,  7,  8,  9, 10, 11, 1, 13, 14, 15, 16, 14 },
				{83, 85, 52,  4,  5,  6,  5,  8,  9, 10, 0, 12, 13, 14, 15, 16, 15 },
				{ 8, 47,  5,  2,  1,  0,  3,  6,  9, 7, 93, 48,  6, 11, 25, 36, 87 },
				{ 1, 29,  6,  4,  5,  6,  0,  8,  9, 10, 4, 12, 13, 14, 15, 16, 76 },
				{ 3,  6,  0,  7,  1,  2,  7,  8,  9, 10, 11, 5, 13, 14, 15, 16, 23 },
				{ 4,  1,  2,  0,  8,  6,  7,  8,  9, 10, 11, 12, 6, 14, 15, 16, 97 },
				{ 2,  5, 36,  9,  5,  6,  7,  8,  9, 10, 11, 12, 13, 7, 15, 16, 32 },
				{ 8,  6, 94,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 8, 16, 17 },
				{ 6,  3,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 9, 45 },
				{ 5,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16,63 },
				{ 1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16,17 },
			};
			assertTrue(Square.checkProperty(sq));	
		}
		// do not award points if the solution always returns true or false
		assertTrue(atLeastOneTrue());
		assertTrue(atLeastOneFalse());
	}
	
}
