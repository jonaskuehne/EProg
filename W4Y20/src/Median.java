
import java.util.Arrays;

public class Median {

	public static void main(String[] args) {
		
		int[] test1 = new int[] {1, 5, 4, 3, 0};
		double result1 = median(test1);
		double expectedResult1 = 3.0;
		if (result1 == expectedResult1) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		
		int[] test2 = new int[] {1000, -100, 0};
		double result2 = median(test2);
		double expectedResult2 = 0.0;
		if (result2 == expectedResult2) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		
		int[] test3 = new int[] {4, 17, 5, 1};
		double result3 = median(test3);
		double expectedResult3 = 4.5;
		if (result3 == expectedResult3) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
	}

	public static double median(int[] sequence) {
		int[] newSeq = sequence.clone();
		Arrays.sort(newSeq);
		if (newSeq.length % 2 == 0) {
			return (newSeq[newSeq.length / 2] + newSeq[newSeq.length / 2 - 1]) / 2.0; 
		} else {
			return newSeq[newSeq.length / 2];
		}
	}
}
