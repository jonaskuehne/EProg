import java.util.*;

public class Median {
	public static double median(Scanner scanner) {
		
		// everything into list
		List<Integer> list = new ArrayList<>();
		
		while (scanner.hasNextInt()) {
			list.add(scanner.nextInt());
		}
		
		// sort
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
			
		});
		
		// get elements in middle
		if (list.size() % 2 == 1) {
			return list.get(list.size() / 2);
		} else {
			return (double)(list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
		}
		
	}
}
