import java.io.*;
import java.util.*;

public class SeqAnalyzer {
	
	// stores numbers of sequence
	List<Integer> numbers;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputSequence = new Scanner("1 3 2 9 2 45");
		SeqAnalyzer analyzer = new SeqAnalyzer(inputSequence);

		List<SeqNumber> seqNumbers = analyzer.getNumbers();

		for (SeqNumber seqNumber : seqNumbers) {
			System.out.println(seqNumber);
		}
	}

	public SeqAnalyzer(Scanner scanner) {
		// get numbers
		numbers = new ArrayList<>();
		while (scanner.hasNextInt()) {
			numbers.add(scanner.nextInt());
		}
	}

	public List<SeqNumber> getNumbers() {
		// store objects
		List<SeqNumber> list = new ArrayList<>();
		// keep track of already added numbers
		Set<Integer> done = new HashSet<>();
		
		// for all
		for (int i = 0; i < numbers.size(); ++i) {
			int num = numbers.get(i);
			// already done?
			if (!done.contains(num)) {
				Set<Integer> pos = new HashSet<>();
				pos.add(i);
				// get other possible positions
				for (int j = i + 1; j < numbers.size(); ++j) {
					if (numbers.get(j) == num) {
						pos.add(j);
					}
				}
				// add object
				list.add(new SeqNumber(num, pos));
			}
			// mark
			done.add(num);
		}

		return list;
	}

	public List<SeqNumber> getRanking(int number) {
		// get list of all numbers
		List<SeqNumber> numList = getNumbers();
		
		// not enough elements
		if (number > numList.size()) {
			throw new IllegalArgumentException();
		}
		
		// set distance attribute for all
		for (SeqNumber seq : numList) {
			seq.d = getSize(seq.getNumber());
		}
		
		// sort in increasing order
		numList.sort(new Comparator<SeqNumber>() {
			@Override
			public int compare(SeqNumber s1, SeqNumber s2) {
				// swap s1 and s2 for increasing order
				return Integer.valueOf(s2.d).compareTo(s1.d);
			}
		});
		
		List<SeqNumber> sortList = new LinkedList<>();
		
		// get largest elements
		for (int i = 0; i < number; ++i) {
			sortList.add(numList.get(i));
		}
		
		return sortList;
	}
	
	public int getSize(int num) {
		
		int d = Integer.MAX_VALUE;
		
		// get first index
		int start = numbers.indexOf(num);
		
		// get other instances
		for (int i = start + 1; i < numbers.size(); ++i) {
			// found
			if (numbers.get(i) == num) {
				// better than last?
				d = Math.min(d, i - start);
				// take new position
				start = i;
			}
		}
		
		// still same?
		if (d == Integer.MAX_VALUE) {
			// quite the plot twist
			d = Integer.MIN_VALUE;
		}
		
		return d;
	}
}
