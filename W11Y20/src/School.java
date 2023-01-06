import java.util.Set;
import java.util.HashSet;

public class School {
	
	Set<House> houses;
	Set<String> houseNames;
	Set<Student> students;
	
	// ctor
	public School() {
		houses = new HashSet<>();
		houseNames = new HashSet<>();
		students = new HashSet<>();
	}
	
	// Aendern Sie nicht die Signaturen der bestehenden Methoden.
    // Sie duerfen weitere Methoden, Felder, und Konstruktoren zu der Klasse hinzufuegen.
	
	public House createHouse(String name) {
		// illegal name
		if (name == null || houseNames.contains(name)) {
			throw new IllegalArgumentException();
		}
		
		// add name to names
		houseNames.add(name);
		// house object
		House house = new House(name, students);
		// store in set
		houses.add(house);
		
		return house;
	}
	
	public House winner() {
		
		if (houses.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		House t = new House("", null);
		t.points = -1;
		
		for (House h : houses) {
			if (h.points > t.points) {
				t = h;
			}
		}

		return t;
	}
	
	public int points() {
		
		if (houses.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		int sum = 0;
		
		for (House h : houses) {
			sum += h.points;
		}
		
		return sum;
	}
}
