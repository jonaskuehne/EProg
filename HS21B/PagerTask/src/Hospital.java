import java.util.*;

public class Hospital {
	
	// stores pagers
	Map<String, Pager> pagers;
	// stores paused pagers
	Map<String, Boolean> paused;
	
	public Hospital() {
		// initialize
		pagers = new HashMap<>();
		paused = new HashMap<>();
	}

	public Pager createPager(String role) {
		// assigns pagers
		if (role.equals("normal")) {
			return new NormalPager(this);
		} else if (role.equals("slow")) {
			return new SlowPager(this);
		} else if (role.equals("admin")) {
			return new AdminPager(this);
		}
		// compiler happy :)
		return null;
	}
	
}



