import java.util.*;

public class Labyrinth {
	
	public static List<Room> exits(Room room, List<Room> prefix) {
		// coward move
		if (prefix == null) {
			prefix = new LinkedList<>();
		}
		
		// use set to automatically store only unique rooms
		Set<Room> exits = new HashSet<>();
		
		// get exits
		getExits(room, 1, prefix, exits);
		
		// convert to list
		List<Room> exitList = new LinkedList<>();
		exitList.addAll(exits);

		return exitList;
	}
	
	public static void getExits(Room room, int num, List<Room> prefix, Set<Room> exits) {
		
		// check if no prefix -> only if still in range of prefix
		if (num <= prefix.size() && room != prefix.get(num - 1)) {
			return;
		}
		
		// room is exit
		if (room.isExit()) {
			// path long enough?
			if (num >= prefix.size()) {
				exits.add(room);
			}
			return;
		}
		
		// for all, depth + 1
		for (Room next : room.doorsTo) {
			getExits(next, num + 1, prefix, exits);
		}
		
		
	}
	
	public static void sortRooms(List<Room> rooms) {
		
		// get list of exits for all elements of rooms
		for (Room room : rooms) {
			
			// get exits, prefix empty list -> all
			List<Room> roomList = exits(room, new LinkedList<>());
			
			// set number of exits
			room.numExits = roomList.size();
			
			// get length of longest string
			for (Room exit : roomList) {
				room.maxName = Math.max(room.maxName, exit.getName().length());
			}
			
		}
		
		// sort
		rooms.sort(new Comparator<Room>() {

			@Override
			public int compare(Room r1, Room r2) {
				// condition 1
				if (r1.numExits < r2.numExits) {
					return -1;
				}
				
				if (r1.numExits > r2.numExits) {
					return 1;
				}
				
				// condition 2
				if (r1.maxName > r2.maxName) {
					return -1;
				}
				
				if (r1.maxName < r2.maxName) {
					return 1;
				}
				
				// same
				return 0;
				
			}
			
		});
		
	}
	
	public static boolean pathsWithSameNames(Room room, int n) {
		// set of all paths
		Set<List<Room>> pathSet = new HashSet<>();
		getPaths(room, new ArrayList<>(), pathSet);
		
		// only keep unique paths -> use iterator to delete
		Iterator<List<Room>> itr = pathSet.iterator();
		
		while (itr.hasNext()) {
			// current path
			List<Room> path = itr.next();
			
			// check if has equal path
			boolean hasSame = false;
			for (List<Room> otherPath : pathSet) {
				
				// is itself -> skip
				if (path == otherPath) {
					continue;
				}
				
				// different by size -> skip
				if (path.size() != otherPath.size()) {
					continue;
				}
				
				// check if at least one different element
				boolean hasDifferent = false;
				for (int i = 0; i < path.size(); ++i) {
					// different
					if (path.get(i) != otherPath.get(i)) {
						hasDifferent = true;
						break;
					}
				}
				// all element same
				if (!hasDifferent) {
					hasSame = true;
					break;
				}
				
			}
			
			// has same path -> remove
			if (hasSame) {
				itr.remove();
			}
			
		}
		
		// get number of paths with same name
		int maxNumSame = 0;
		for (List<Room> path : pathSet) {
			
			int numSame = 0;
			
			for (List<Room> otherPath : pathSet) {
				// apparently we the path itself counts here
				if (sameNames(path, otherPath)) {
					++numSame;
				}
			}
			// get max
			maxNumSame = Math.max(numSame, maxNumSame);
		}
		
		// enough?
		return maxNumSame >= n;
	}
	
	public static void getPaths(Room room, List<Room> path, Set<List<Room>> pathSet) {
		// add to list
		path.add(room);
		
		// exit -> end of path
		if (room.isExit()) {
			pathSet.add(path);
			return;
		}
		
		// adjacent rooms
		for (Room next : room.doorsTo) {
			// copy list -> else same object edited
			List<Room> copy = new ArrayList<>();
			copy.addAll(path);
			
			// recursive case
			getPaths(next, copy, pathSet);
		}
		
	}
	
	public static boolean sameNames(List<Room> a, List<Room> b) {
		Map<String, Integer> ma = new HashMap<>();
		for (Room x : a) {
			String n = x.getName();
			ma.put(n, ma.getOrDefault(n, 0)+1);
		}
		
		Map<String, Integer> mb = new HashMap<>();
		for (Room x : b) {
			String n = x.getName();
			mb.put(n, mb.getOrDefault(n, 0)+1);
		}
		
		if (!ma.keySet().equals(mb.keySet())) {
			return false;
		}
		
		for (String n : ma.keySet()) {
			if (!ma.get(n).equals(mb.get(n))) {
				return false;
			}
		}
		
		return true;
	}
	
}
