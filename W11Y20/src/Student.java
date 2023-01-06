import java.util.Set;
import java.util.HashSet;

public class Student {
	
	String firstName;
	String lastName;
	Set<House> house;
	
	// Aendern Sie nicht die Signaturen der bestehenden Methoden und Konstruktoren.
    // Sie duerfen weitere Methoden, Felder, und Konstruktoren zu der Klasse hinzufuegen.
	
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.house = new HashSet<>();
	}
	
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	public void givePoints(int points) {
		for (House h : house) {
			h.points = Math.max(0, h.points + points);
		}
	}
}
