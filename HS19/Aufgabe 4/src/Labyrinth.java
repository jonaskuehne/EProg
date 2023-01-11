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
		// coward
		if (room == null) {
			return false;
		}
		
		// keep track of visited rooms and colors
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
		
		// keep track
		visited.add(room);
		colors.add(room.getColor());

		// found exit and all colors!
		if (room.isExit() && colors.size() == 10) {
			return true;
		}
		
		// try next ones
		for (Room r : room.doorsTo) {
			// get result from recursion stack
			if (recColorExactlyOnce(r, visited, colors)) {
				return true;
			}
		}
		
		// nothing found
		return false;

	}

	public static boolean colorNotSuccessively(Room room) {
		// coward
		if (room == null) {
			return false;
		}
		
		// keep track of visited rooms
		Set<Room> visited = new HashSet<>();
		
		// color of first one does not matter -> -1
		return recColorNotSuccessively(room, visited, -1);
	}

	public static boolean recColorNotSuccessively(Room room, Set<Room> visited, int lastColor) {

		// base case
		// was already here or same color in last room
		if (visited.contains(room) || lastColor == room.getColor()) {
			return false;
		}
		
		// keep track of rooms
		visited.add(room);
		
		// found exit and all colors!
		if (room.isExit()) {
			return true;
		}
		
		// try next ones
		for (Room r : room.doorsTo) {
			// get result from recursion stack
			if (recColorNotSuccessively(r, visited, room.getColor())) {
				return true;
			}
		}
		
		// nothing found
		return false;

	}

	public static void removeCycle(Room room) {
		// coward
		if (room == null) {
			return;
		}
		
		// keep track of visited rooms
		Set<Room> visited = new HashSet<>();
		
		recRemoveCycle(visited, room);
	}
	
	// DFS praise the steurer
	public static Set<Room> recRemoveCycle(Set<Room> visited, Room room) {
		
		visited.add(room);
		
		// keep track of reachable rooms from here
		Set<Room> reachable = new HashSet<>();
		
		for (Room r : room.doorsTo) {
			// set predecessor -> this room
			r.pre = room;
			// add
			reachable.add(r);
			
			// go to next if not already visited
			if (!visited.contains(r)) {
				reachable.addAll(recRemoveCycle(visited, r));
			}
			
		}
		
		// can reach room from here -> cycle!
		if (reachable.contains(room)) {
			itRemove(room);
		}
		
		return reachable;
		
	}
	
	public static void itRemove(Room room) {
		
		// remove door from last room to here until back here
		Room r = room;
		do {
			r.pre.doorsTo.remove(r);
			r = r.pre;
		} while (r != room);
		
	}

}
