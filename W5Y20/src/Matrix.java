
import java.util.LinkedList;

public class Matrix {

	static boolean checkMatrix(int[][] m) {
		
		// take all elements
		LinkedList<Integer> elements = new LinkedList<>();
		
		// check size of matrix
		int l = m.length;
		for (int i = 0; i < l; ++i) {
			if (m[i].length != l) {
				return false;
			}
		}
		
		// get first sums
		int rowSum = 0;
		int colSum = 0;
		for (int i = 0; i < l; ++i) {
			rowSum += m[0][i];
			colSum += m[i][0];
		}
		
		// compare sums
		for (int i = 0; i < l; ++i) {
			int tempRowSum = 0;
			int tempColSum = 0;
			for (int j = 0;j < l; ++j) {
				tempRowSum += m[i][j];
				tempColSum += m[j][i];
				// store
				elements.add(m[i][j]);
			}
			if (rowSum != tempRowSum || colSum != tempColSum) {
				return false;
			}
		}
		
		// go through elements
		while (!elements.isEmpty()) {
			int current = elements.remove();
			// size
			if (current <= 0 || current > (l * l)) {
				return false;
			}
			// unique?
			if (elements.contains(current)) {
				return false;
			}
			
		}
		
		// all good
		return true;
	}
}
