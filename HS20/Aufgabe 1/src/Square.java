public class Square {
	
    public static void main(String[] args) {
    	int[][] t = { 
    			{0, 3, 5, 7, 2}, 
    			{2, 7, 4, 3, 0}, 
    			{4, 4, 6, 1, 4}, 
    			{7, 7, 4, 3, 3}, 
    			{0, 3, 5, 7, 2} 
    		};

    	System.out.println(checkProperty(t));
    }

    static boolean checkProperty(int[][] x) {
    	
    	// PTSD from "perfect matrix"
    	if (x == null) {
    		return false;
    	}
    	// check size
    	int n = x.length;
    	// odd?
    	if (n % 2 == 0 || n <= 1) {
    		return false;
    	}
    	
    	// check for each row
    	for (int i = 0; i < x.length; ++i) {
    		if (x[i].length != n) {
    			return false;
    		}
    	}
    	
    	// go till mid (+ 1 since odd)
    	for (int i = 0; i < n + 1; ++i) {
    		// pyramid scheme
    		for (int j = i; j < n - i; ++j) {
    			// does not fit
    			if (x[i][j] != x[n - i - 1][j]) {
    				return false;
    			}
    		}
    	}
    	
    	// all good
    	return true;
    }

}
    
