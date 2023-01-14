import java.util.*;

public class School {
	
	// use map to track already used names in same datastructure
	Map<String, House> houses;
	Set<Student> students;
	
	public School() {
		houses = new HashMap<>();
		students = new HashSet<>();
	}
	
	
	public House createHouse(String name) {
		// invalid name
		if (name == null || houses.containsKey(name)) {
			throw new IllegalArgumentException();
		}
		
		// create new object and add to map
		House h = new House(name, students);
		houses.put(name, h);
		
		return h;
	}
	
	public House winner() {
		if (houses.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		// get house with most points
		House maxH = new House("", null);
		maxH.points = -1;
		
		for (House h : houses.values()) {
			if (h.points > maxH.points) {
				maxH = h;
			}
		}
		
		return maxH;
	}
	
	public int points() {
		int points = 0;
		
		for (House h : houses.values()) {
			points += h.points();
		}
		
		return points;
	}
}
