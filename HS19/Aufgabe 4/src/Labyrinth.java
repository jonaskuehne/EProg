import java.util.*;

public class Labyrinth {

	public static void main(String[] args) {
		Room a1 = new Room(1);
		Room a2 = new Room(3);
		Room a3 = new Room(4);
		Room a4 = new Room(2);
		Room a5 = new Room(1);
		Room a6 = new Room(5);
		a1.doorsTo.add(a2);
		a1.doorsTo.add(a4);
		a2.doorsTo.add(a3);
		a3.doorsTo.add(a1);
		a4.doorsTo.add(a5);
		a4.doorsTo.add(a6);

		System.out.println(Labyrinth.colorNotSuccessively(a1));
	}

	public static boolean colorExactlyOnce(Room room) {
		
		if (room == null) {
			return false;
		}

		Set<Room> visited = new HashSet<>();
		Set<Integer> colors = new HashSet<>();

		return recColorExactlyOnce(room, visited, colors);
	}

	public static boolean recColorExactlyOnce(Room room, Set<Room> visited, Set<Integer> colors) {

		// base case
		// was already here or already saw color
		if (visited.contains(room) || colors.contains(room.getColor())) {
			return false;
		}
		
		visited.add(room);
		colors.add(room.getColor());

		// found exit and all colors!
		if (room.isExit() && colors.size() == 10) {
			return true;
		}

		for (Room r : room.doorsTo) {
			if (recColorExactlyOnce(r, visited, colors)) {
				return true;
			}
		}

		return false;

	}

	public static boolean colorNotSuccessively(Room room) {
		
		if (room == null) {
			return false;
		}
		
		Set<Room> visited = new HashSet<>();
		
		return recColorNotSuccessively(room, visited, -1);
	}

	public static boolean recColorNotSuccessively(Room room, Set<Room> visited, int lastColor) {

		// base case
		// was already here or already saw color
		if (visited.contains(room) || lastColor == room.getColor()) {
			return false;
		}
		
		visited.add(room);
		
		// found exit and all colors!
		if (room.isExit()) {
			return true;
		}

		for (Room r : room.doorsTo) {
			if (recColorNotSuccessively(r, visited, room.getColor())) {
				return true;
			}
		}

		return false;

	}

	public static void removeCycle(Room room) {
		if (room == null) {
			return;
		}
		
		Set<Room> visited = new HashSet<>();
		
		recRemoveCycle(visited, room);
	}
	
	// DFS praise the steurer
	public static Set<Room> recRemoveCycle(Set<Room> visited, Room room) {
		
		visited.add(room);
		
		Set<Room> reachable = new HashSet<>();
		
		for (Room r : room.doorsTo) {
			r.pre = room;
			reachable.add(r);
			
			if (!visited.contains(r)) {
				reachable.addAll(recRemoveCycle(visited, r));
			}
			
		}
		
		if (reachable.contains(room)) {
			itRemove(room);
		}
		
		return reachable;
		
	}
	
	public static void itRemove(Room room) {
		
		Room r = room;
		do {
			r.pre.doorsTo.remove(r);
			r = r.pre;
		} while (r != room);
		
		
	}

}
