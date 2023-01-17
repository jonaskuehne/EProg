import java.util.*;

public class Labyrinth {

	public static void main(String[] args) {
		Room a0 = new Room(0);
	    Room a1 = new Room(1);
	    Room a2 = new Room(2);
	    Room a3 = new Room(3);
	    Room a4 = new Room(4);
	    Room a5 = new Room(5);

	    a0.doorsTo.add(a1);
	    a0.doorsTo.add(a3);

	    a1.doorsTo.add(a2);

	    a2.doorsTo.add(a1);

	    a3.doorsTo.add(a4);

	    a4.doorsTo.add(a5);

	    a5.doorsTo.add(a3);

	    //cycles (a3, a4, a5) and (a1,a2)
	    Labyrinth.removeCycle(a0);
	    
	    System.out.println(0);
	    for (Room r : a0.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	    
	    System.out.println(1);
	    for (Room r : a1.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	    
	    System.out.println(2);
	    for (Room r : a2.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	    
	    System.out.println(3);
	    for (Room r : a3.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	    
	    System.out.println(4);
	    for (Room r : a4.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	    
	    System.out.println(5);
	    for (Room r : a5.doorsTo) {
	    	System.out.print(r.getColor() + ", ");
	    }
	    System.out.println();
	}

	public static boolean colorExactlyOnce(Room room) {
		// coward
		if (room == null) {
			return false;
		}
		
		// keep track of visited colors
		Set<Integer> colors = new HashSet<>();

		return recColorExactlyOnce(room, colors);
	}

	public static boolean recColorExactlyOnce(Room room, Set<Integer> colors) {

		// base case
		// was already here or already saw color
		if (colors.contains(room.getColor())) {
			return false;
		}
		
		// keep track
		colors.add(room.getColor());

		// found exit and all colors!
		if (room.isExit() && colors.size() == 10) {
			return true;
		}
		
		// try next ones
		for (Room r : room.doorsTo) {
			// get result from recursion stack
			if (recColorExactlyOnce(r, colors)) {
				return true;
			}
		}
		
		// remove if no success
		colors.remove(room.getColor());
		
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
		
		// remove if no success
		visited.remove(room);
		
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
				// check if already one room removed
				if (recRemoveCycle(visited, r) == null) {
					return null;
				}
				
				// else continue
				reachable.addAll(recRemoveCycle(visited, r));
			}
			
		}
		
		// can reach room from here -> cycle!
		if (reachable.contains(room)) {
			itRemove(room);
			// removed one cycle, seems like it's wrong if we remove all
			return null;
		}
		
		// remove if no success
		visited.remove(room);
		
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
