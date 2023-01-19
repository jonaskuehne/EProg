import java.util.*;

public class Frequencies {
	public double minDistance; // minDistance entspricht dem "d" in der Aufgabenstellung
	public ArrayList<Coordinate> stations;

	public Frequencies(ArrayList<Coordinate> stations, double minDistance) {
		this.stations = stations;
		this.minDistance = minDistance;
	}

	public static void main(String[] args) {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		stations.add(new Coordinate(0.0, 5.0));
		stations.add(new Coordinate(1.0, 3.0));
		stations.add(new Coordinate(6.0, 0.5));

		Frequencies ff = new Frequencies(stations, 7.0);
		boolean moeglich = ff.assignmentPossible(2);
		System.out.println(moeglich); // expected output: true
		int minFrequenzen = ff.minFrequencies();
		System.out.println(minFrequenzen); // expected output: 2
	}

	public boolean assignmentPossible(int N) {
		return minFrequencies() <= N;
	}

	public int minFrequencies() {
		// go through stations
		for (int i = 0; i < stations.size(); ++i) {
			// store element to safe some chars
			Coordinate c1 = stations.get(i);

			// set first frequency
			c1.freq = 1;

			// go through all stations
			for (int j = 0; j < stations.size(); ++j) {
				// again store element
				Coordinate c2 = stations.get(j);

				// inference!
				if (i != j && c1.distance(c2) < minDistance && c1.freq == c2.freq) {
					// need additional frequency
					++c1.freq;
					// try again
					j = 0;
				}
			}
		}

		// get max of frequencies
		int max = 0;
		for (Coordinate c : stations) {
			max = Math.max(max, c.freq);
		}

		return max;
	}

	public List<Coordinate> mostProblematicStations(int m) {
		List<Coordinate> cords = new ArrayList<>();
		
		// go through all stations
		for (Coordinate c1 : stations) {
			for (Coordinate c2 : stations) {
				// inference!
				if (c1 != c2 && c1.distance(c2) < minDistance) {
					++c1.numInferences;
				}
			}
		}
		
		// sort stations
		stations.sort(new Comparator<Coordinate>() {

			@Override
			public int compare(Coordinate c1, Coordinate c2) {
				// swap c1, c2 to get decreasing order
				return Integer.valueOf(c2.numInferences).compareTo(c1.numInferences);
			}
			
		});
		
		// put first m elements into list
		for (int i = 0; i < m; ++i) {
			cords.add(stations.get(i));
		}

		return cords;
	}

}
