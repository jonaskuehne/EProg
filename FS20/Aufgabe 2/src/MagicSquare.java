import java.util.*;

class MagicSquare {
    public static void main(String[] args) {
        int[][] in = { {1, 5, 9}, {8, 3, 4}, {6, 7, 2} };
        System.out.println("result: " + checkSquare(in));
    }

    static boolean checkSquare(int[][] in) {
    	
    	// dimensions
    	int n = in.length;
    	for (int[] a : in) {
    		if (a.length != n) {
    			return false;
    		}
    	}
    	
    	// set to store elements
    	Set<Integer> numbers = new HashSet<>();
    	
    	int rowSum = 0;
    	int colSum = 0;
    	// get initial row and column sum
    	for (int i = 0; i < n; ++i) {
    		rowSum += in[0][i];
    		colSum += in[i][0];
    	}
    	
    	// check
    	for (int i = 0; i < n; ++i) {
    		int tempRowSum = 0;
    		int tempColSum = 0;
    		for (int j = 0; j < n; ++j) {
    			// add to set for further tests
    			numbers.add(in[i][j]);
    			// sums
    			tempRowSum += in[i][j];
    			tempColSum += in[j][i];
    		}
    		
    		// don't match
    		if (tempRowSum != rowSum || tempColSum != colSum) {
    			return false;
    		}
    	}
    	
    	int nSq = n * n;
    	// check if elements unique
    	if (numbers.size() != nSq) {
    		return false;
    	}
    	
    	// check range of elements
    	for (int num : numbers) {
    		if (!(0 < num && num <= nSq)) {
    			return false;
    		}
    	}
    	
		// all good 	
    	return true;
    }
}

