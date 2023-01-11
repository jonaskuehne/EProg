import java.util.*;

public class Ticket {
	
	int code;
	boolean drawn;
	Set<Integer> nums;
	Set<Integer> correctNums;
	
	// ctor
	public Ticket(int[] numbers, int code) {
		drawn = false;
		this.code = code;
		nums = new HashSet<>();
		
		for (int i : numbers) {
			nums.add(i);
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
		
		// maybe more cash w/ the other version who knows
		Set<Integer> onlyNums = new HashSet<>();
		
		for (int i : correctNums) {
			if (nums.contains(i)) {
				onlyNums.add(i);
			}
		}
		
		return onlyNums;
	}

	public int getPrize() {
		
		if (!drawn) {
			throw new IllegalStateException();
		}
		
		int numSame = 0;
		
		for (int i : nums) {
			if (correctNums.contains(i)) {
				++numSame;
			}
		}
		
		if (numSame == 0) {
			return 0;
		}
		
		return (int) (Math.pow(20, numSame - 1) * 5);
	}
}
