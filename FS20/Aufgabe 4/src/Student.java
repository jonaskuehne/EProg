import java.util.*;

public class Student {
	
	String firstName;
	String lastName;
	Set<House> houses;
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		houses = new HashSet<>();
	}
	
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	public void givePoints(int points) {
		// could be in multiple houses
		for (House h : houses) {
			h.points = Math.max(0, h.points + points);
		}
	}
}
