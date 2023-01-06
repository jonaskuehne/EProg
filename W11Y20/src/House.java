import java.util.Set;

public class House {
	String name;
	int points;
	Set<Student> students;
	
	public House(String name, Set<Student> students) {
		this.name = name;
		this.points = 0;
		this.students = students;
	}
	
	// Aendern Sie nicht die Signaturen der bestehenden Methoden.
	// Sie duerfen weitere Methoden, Felder, und Konstruktoren zu der Klasse hinzufuegen.
	
	public String name() {
		return name;
	}
	
	public int points() {
		return points;
	}
	
	public void assign(Student student) {
		// not valid or already in some house
		if (student == null || students.contains(student)) {
			throw new IllegalArgumentException();
		}
		
		// assign house(s)
		student.house.add(this);
		// add to students
		students.add(student);
	}
}
