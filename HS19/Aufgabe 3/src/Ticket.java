import java.util.*;

public class Ticket {
	// ID
	int code;
	// already drawn?
	boolean drawn;
	// sets
	Set<Integer> nums;
	Set<Integer> correctNums;
	
	// ctor
	public Ticket(int[] numbers, int code) {
		this.code = code;
		// add numbers to set
		nums = new HashSet<>();
		
		for (int i : numbers) {
			nums.add(i);
		}
		
	}
	
	// get correct numbers, keep those which are equal
	public void setCorrectNums(Set<Integer> winSet) {
		correctNums = new HashSet<>();
		
		for (int i : winSet) {
			if (nums.contains(i)) {
				correctNums.add(i);
			}
		}
	}
	
	public int getNumber() {
		return code;
	}

	public Set<Integer> getNumbers() {
		return nums;
	}

	public Set<Integer> getCorrectNumbers() {
		
		if (!drawn) {
			throw new IllegalStateException();
		}
		
		return correctNums;
	}

	public int getPrize() {
		
		if (!drawn) {
			throw new IllegalStateException();
		}
		
		int numSame = correctNums.size();
		
		if (numSame == 0) {
			return 0;
		}
		
		return (int) (Math.pow(20, numSame - 1) * 5);
	}
}
