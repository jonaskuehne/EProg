import java.util.*;

public class House {
	
	String name;
	int points;
	Set<Student> students;
	
	public House(String name, Set<Student> students) {
		this.name = name;
		this.students = students;
	}
	
	public String name() {
		return name;
	}
	
	public int points() {
		return points;
	}
	
	public void assign(Student student) {
		// invalid student
		if (student == null || students.contains(student)) {
			throw new IllegalArgumentException();
		}
		
		// add to students
		students.add(student);
		// add house to student
		student.houses.add(this);
	}
}
