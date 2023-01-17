import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingBiomTest {

	/** a) **/
	@Test
	public void testGetBiomName() {
        String [][] input = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };
        
        String [][] expectedType = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };
        
        World w = new World(input);
        
        assertArrayEquals(expectedType, actualBiomTypes(w, input.length));
	}
	
	@Test
	public void testGetBiomFlora() {
        String [][] input = {
        		{"W","F","W","W"},
        		{"F","F","F","F"},
        		{"W","W","W","W"},
        		{"F","F","W","W"}
        };

        
        int [] [] expectedFlora = {
        		{15,12,15,15},
        		{12,12,12,12},
        		{15,15,15,15},
        		{12,12,15,15}
        };
        
        int [] [] expectedHeights = {
        		{0,3,0,0},
        		{3,3,3,3},
        		{0,0,0,0},
        		{3,3,0,0}
        };
        
        World w = new World(input);
        
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}
	
	@Test
	public void testBiomConstructionA_2x2_types() {
        String [][] input1 = {
        		{"F","F"},
        		{"F","F"}
        };     
        testConstructionHelper(input1, true);
        String [][] input2 = {
        		{"W","W"},
        		{"W","W"}
        };     
        testConstructionHelper(input2, true);
        String [][] input3 = {
        		{"F","W"},
        		{"W","F"}
        };     
        testConstructionHelper(input3, true);
        String [][] input4 = {
        		{"W","W"},
        		{"F","F"}
        };     
        testConstructionHelper(input4, true);
	}	
	
	@Test
	public void testBiomConstructionA_2x2_all() {
        String [][] input1 = {
        		{"F","F"},
        		{"F","F"}
        };     
        testConstructionHelper(input1, false);
        String [][] input2 = {
        		{"W","W"},
        		{"W","W"}
        };     
        testConstructionHelper(input2, false);
        String [][] input3 = {
        		{"F","W"},
        		{"W","F"}
        };     
        testConstructionHelper(input3, false);
        String [][] input4 = {
        		{"W","W"},
        		{"F","F"}
        };     
        testConstructionHelper(input4, false);
	}		
	@Test
	public void testBiomConstructionA_8x8_types() {
        String [][] inputF = createInputArray(8, "F");
        String [][] inputW = createInputArray(8, "W");
        testConstructionHelper(inputF, true);
        testConstructionHelper(inputW, true);
	}	
	@Test
	public void testBiomConstructionA_8x8_all() {
        String [][] inputF = createInputArray(8, "F", "W");
        String [][] inputW = createInputArray(8, "W", "W");
        testConstructionHelper(inputF, false);
        testConstructionHelper(inputW, false);
	}		
	@Test
	public void testBiomConstructionA_3_5_6_7_types() {
		int[] sizes = {3,5,6,7};
		for(int siz : sizes) {
	        String [][] input = createInputArray(siz, "F", "W", "F", "F");
	        testConstructionHelper(input, true);
		}
	}	
	@Test
	public void testBiomConstructionA_3_5_6_7_all() {
		int[] sizes = {3,5,6,7};
		for(int siz : sizes) {
	        String [][] input = createInputArray(siz, "F", "W", "F", "F");
	        testConstructionHelper(input, false);
		}
	}	
	
	/** b) **/
	@Test
	public void testDryUpWater() {
        String [][] input = {
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        		{"W","W","W","W"},
        };
        
        World w = new World(input);
        
        w.stepDryUp();
        
        int [][] expectedFlora1 = {
        		{10,10,10,10},
        		{10,10,10,10},
        		{10,10,10,10},
        		{10,10,10,10},        		
        };
                
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        
        w.stepDryUp();
        
        int [][] expectedFlora2 = {
        		{5,5,5,5},
        		{5,5,5,5},
        		{5,5,5,5},
        		{5,5,5,5},        		
        };

        assertArrayEquals(expectedFlora2, actualFlora(w, expectedFlora2.length));
        
        w.stepDryUp(); 
        w.stepDryUp();
        
        int [][] expectedFlora4 = {
        		{0,0,0,0},
        		{0,0,0,0},
        		{0,0,0,0},
        		{0,0,0,0},        		
        };
        
        assertArrayEquals(expectedFlora4, actualFlora(w, expectedFlora4.length));
	}
	
	@Test
	public void testDryUpGeneral() {
        String [][] input = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        World w = new World(input);
        w.stepDryUp(); 
        
        String [][] expectedType1 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora1 = {
        		{9,10,10},
        		{9,9,10},
        		{9,10,10},
        };
        
        int [][] expectedHeights1 = {
        		{2,0,0},
        		{2,2,0},
        		{2,0,0},
        };
        
        assertArrayEquals(expectedType1, actualBiomTypes(w, expectedType1.length));
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        assertArrayEquals(expectedHeights1, actualHeights(w, expectedHeights1.length));
        
        w.stepDryUp();
        w.stepDryUp();
        
        String [][] expectedType3 = {
        		{"W","W","W"},
        		{"W","W","W"},
        		{"W","W","W"},
        };
        
        int [][] expectedFlora3 = {
        		{3,0,0},
        		{3,3,0},
        		{3,0,0},
        };
        
        int [][] expectedHeights3 = {
        		{0,0,0},
        		{0,0,0},
        		{0,0,0},
        };

        assertArrayEquals(expectedType3, actualBiomTypes(w, expectedType3.length));
        assertArrayEquals(expectedFlora3, actualFlora(w, expectedFlora3.length));
        assertArrayEquals(expectedHeights3, actualHeights(w, expectedHeights3.length));
        
        w.stepDryUp();
        
        assertArrayEquals(expectedHeights3, actualHeights(w, expectedHeights3.length));        
	}

	@Test	
	public void testDryUp_tiny_Once() {
        String [][] input = {
        		{"F","F"},
        		{"F","W"}
        };
        World w = new World(input);
        w.stepDryUp();
        assertArrayEquals(input, actualBiomTypes(w, input.length));
        int [][] expectedFlora = {
				 {9,9},
				 {9,10}
        };
        int [][] expectedHeights = {
				 {2,2},
				 {2,0}
        }; 
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));       
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));       
	}
	
	@Test
	public void testDryUpPlains_types() {
		String [][] input = createInputArray(5, "F");
        World w = new World(input);
        w.stepDryUp(); 
        assertArrayEquals(input, actualBiomTypes(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(input, actualBiomTypes(w, input.length));
        w.stepDryUp();
        String [][] allwater = createInputArray(5, "W");
        assertArrayEquals(allwater, actualBiomTypes(w, allwater.length));
	}
	
	@Test
	public void testDryUpPlains_height() {
		String [][] input = createInputArray(5, "F");
        World w = new World(input);
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {2}), actualHeights(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {1}), actualHeights(w, input.length));
        w.stepDryUp();
        assertArrayEquals(createIntArray(5, new int[] {0}), actualHeights(w, input.length));
	}
	
	@Test
	public void testDryUpPlains_flora() {
		String [][] input = createInputArray(5, "F");
        World w = new World(input);
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {9}), actualFlora(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {6}), actualFlora(w, input.length));
        w.stepDryUp();
        assertArrayEquals(createIntArray(5, new int[] {3}), actualFlora(w, input.length));
        w.stepDryUp();
        assertArrayEquals(createIntArray(5, new int[] {0}), actualFlora(w, input.length));
        w.stepDryUp();
        assertArrayEquals(createIntArray(5, new int[] {0}), actualFlora(w, input.length));
	}

	@Test
	public void testDryUpCheckerboard_flora() {
		String [][] input = createInputArray(5, "F", "W");
        World w = new World(input);
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {9, 10}), actualFlora(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {6, 5}), actualFlora(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {3, 0}), actualFlora(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(5, new int[] {0, 0}), actualFlora(w, input.length));
	}

	@Test
	public void testDryUpCheckerboard_height() {
		String [][] input = createInputArray(4, "F", "W");
        World w = new World(input);
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(4, new int[] {2, 0}), actualHeights(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(4, new int[] {1, 0}), actualHeights(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(4, new int[] {0, 0}), actualHeights(w, input.length));
        w.stepDryUp(); 
        assertArrayEquals(createIntArray(4, new int[] {0, 0}), actualHeights(w, input.length));
	}
	
	/** c) **/
	@Test
	public void testDistributeAndDryUp() {
        String [][] input = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        World w = new World(input);
        w.stepDistribute(1);
        
        String [][] expectedType1 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora1 = {
        		{27,39,30},
        		{36,57,42},
        		{27,39,30},
        };
        
        int [][] expectedHeights1 = {
        		{4,0,0},
        		{6,4,0},
        		{4,0,0},
        };
        
        assertArrayEquals(expectedType1, actualBiomTypes(w, expectedType1.length));
        assertArrayEquals(expectedFlora1, actualFlora(w, expectedFlora1.length));
        assertArrayEquals(expectedHeights1, actualHeights(w, expectedHeights1.length));
        
        w.stepDryUp();
        
        String [][] expectedType2 = {
        		{"F","W","W"},
        		{"F","F","W"},
        		{"F","W","W"},
        };
        
        int [][] expectedFlora2 = {
        		{24,34,25},
        		{33,54,37},
        		{24,34,25},
        };
        
        int [][] expectedHeights2 = {
        		{3,0,0},
        		{5,3,0},
        		{3,0,0},
        };
        
        assertArrayEquals(expectedType2, actualBiomTypes(w, expectedType2.length));
        assertArrayEquals(expectedFlora2, actualFlora(w, expectedFlora2.length));
        assertArrayEquals(expectedHeights2, actualHeights(w, expectedHeights2.length));
	}

	@Test	
	public void testDistributeP1_island_heightOnce() {
        String [][] input = {
        		{"W","W","W"},
        		{"W","F","W"},
        		{"W","W","W"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedHeights = {
        		{0,0,0},
        		{0,3,0},
        		{0,0,0},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}
	@Test	
	public void testDistributeP1_island_heightMany() {
        String [][] input = {
        		{"W","W","W"},
        		{"W","F","W"},
        		{"W","W","W"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedHeights = {
        		{0,0,0},
        		{0,3,0},
        		{0,0,0},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}	
	
	@Test	
	public void testDistributeP1_lake_heightOnce() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedHeights = {
        		{5,5,5},
        		{5,0,5},
        		{5,5,5},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}
	@Test	
	public void testDistributeP1_lake_heightMany() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedHeights = {
        		{9,9,9},
        		{9,0,9},
        		{9,9,9},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}			
	@Test	
	public void testDistributeP1_island_floraOnce() {
        String [][] input = {
        		{"W","W","W"},
        		{"W","F","W"},
        		{"W","W","W"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedFlora = {
        		{30,42,30},
        		{42,60,42},
        		{30,42,30},
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}	
	
	@Test	
	public void testDistributeP1_island_floraMany() {
        String [][] input = {
        		{"W","W","W"},
        		{"W","F","W"},
        		{"W","W","W"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedFlora = {
				 {240,336,240},
				 {336,480,336},
				 {240,336,240}
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}		
	
	@Test	
	public void testDistributeP1_lake_floraOnce() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedFlora = {
        		{24,39,24},
        		{39,48,39},
        		{24,39,24},
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}	
	
	@Test	
	public void testDistributeP1_lake_floraMany() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedFlora = {
				 {192,312,192},
				 {312,384,312},
				 {192,312,192}
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}

	@Test	
	public void testDistributeP1_tiny_floraOnce() {
        String [][] input = {
        		{"F","F"},
        		{"F","W"}
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedFlora = {
				 {24,27},
				 {27,24}
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));       
	}
	
	@Test	
	public void testDistributeP1_tiny_floraTwice() {
        String [][] input = {
        		{"F","F"},
        		{"F","W"}
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedFlora = {
				 {54,48},
				 {48,54}
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}	

	@Test	
	public void testDistributeP1_tiny_HeightOnce() {
        String [][] input = {
        		{"F","F"},
        		{"F","W"}
        };
        World w = new World(input);
        w.stepDistribute(1);
        int [][] expectedHeights = {
				 {5,4},
				 {4,0}
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));       
	}
	
	@Test	
	public void testDistributeP1_tiny_HeightTwice() {
        String [][] input = {
        		{"F","F"},
        		{"F","W"}
        };
        World w = new World(input);
        w.stepDistribute(1);
        w.stepDistribute(1);
        int [][] expectedHeights = {
				 {7,5},
				 {5,0}
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}	
	
	@Test	
	public void testDistributeP2_lake_floraOnce() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(2);
        int [][] expectedFlora = {
        		{48,51,48},
        		{51,48,51},
        		{48,51,48},
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}	
	
	@Test	
	public void testDistributeP2_lake_floraTwice() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(2);
        w.stepDistribute(2);
        int [][] expectedFlora = {
        		{198,195,198},
        		{195,204,195},
        		{198,195,198},
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}

	@Test	
	public void testDistributeP2_lake_HeightOnce() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(2);
        int [][] expectedHeights = {
        		{7,6,7},
        		{6,0,6},
        		{7,6,7},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}	
	
	@Test	
	public void testDistributeP2_lake_HeightTwice() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(2);
        w.stepDistribute(2);
        int [][] expectedHeights = {
        		{11,9,11},
        		{9,0,9},
        		{11,9,11},
        };      
        assertArrayEquals(expectedHeights, actualHeights(w, expectedHeights.length));
	}

	
	@Test	
	public void testDistributeP2P1_lake_flora() {
        String [][] input = {
        		{"F","F","F"},
        		{"F","W","F"},
        		{"F","F","F"},
        };
        World w = new World(input);
        w.stepDistribute(2);
        w.stepDistribute(1);
        int [][] expectedFlora = {
        		{102,144,102}, 
        		{144,204,144},
        		{102,144,102},
        };      
        assertArrayEquals(expectedFlora, actualFlora(w, expectedFlora.length));
	}	
	
	
	/** Helper methods **/
	public static int[][] createIntArray(int size, int[] contents) {
		int[][] input = new int[size][size];
		int counter = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
            	input[row][col] = contents[counter % contents.length];
            	counter = counter +1;
            }
        }
        return input;
	}
	public static String[][] createInputArray(int size, String... bioms) {
		List<String> biomList = List.of(bioms);
		String[] biomArray = new String[biomList.size()];
		biomList.toArray(biomArray);
		String[][] input = new String[size][size];
		int counter = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
            	input[row][col] = biomArray[counter % biomArray.length];
            	counter = counter +1;
            }
        }
        return input;
	}
	
	public static void testConstructionHelper(String[][] input, boolean typesOnly) {
	    World w = new World(input);
	    int[][] flora = new int[input.length][input[0].length];
	    int[][] heights = new int[input.length][input[0].length];
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
            	if (input[row][col] == "W") {
	                flora[row][col] = 15;
	                heights[row][col] = 0;
            	} else {
	                flora[row][col] = 12;
	                heights[row][col] = 3;           		
            	}
            }
        }
	    assertArrayEquals(input, actualBiomTypes(w, input.length));
	    if(typesOnly) return;
	    assertArrayEquals(flora, actualFlora(w, flora.length));
	    assertArrayEquals(heights, actualHeights(w, heights.length));
	}
	
	public static String [][] actualBiomTypes(World w, int size) {
		String [][] types = new String[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		types[i][j] = w.getBiom(i, j).getBiomType();
        	}
        }
        
        return types;
	}

	public static int [][] actualFlora(World w, int size) {
		int [][] flora = new int[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		flora[i][j] = w.getBiom(i, j).getFlora();
        	}
        }
        
        return flora;
	}
	
	public static int [][] actualHeights(World w, int size) {
		int [][] height = new int[size][size];
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		assertNotNull(w.getBiom(i, j));
        		height[i][j] = w.getBiom(i, j).getHeight();
        	}
        }
        
        return height;
	}
}
