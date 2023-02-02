
public class ProgramState {
	
	private int sum;
	private int counter;
	
	public ProgramState(int sum, int counter) {		
		this.sum = sum;
		this.counter = counter;
	}

	public int getSum() {
		return sum;
	}

	public int getCounter() {
		return counter;
	}
	
	public void incCounter() {
		++counter;
	}
	
	public void incSum(int sum) {
		this.sum += sum;
	}
	
	public ProgramState clone() {
		return new ProgramState(sum, counter);
	}

}