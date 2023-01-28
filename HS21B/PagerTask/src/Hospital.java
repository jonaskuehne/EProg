import java.util.*;

public class Hospital {
		
	Map<String, Pager> pagers;
	Map<String, Boolean> paused;
	
	public Hospital() {
		pagers = new HashMap<>();
		paused = new HashMap<>();
	}

	public Pager createPager(String role) {
		if (role.equals("normal")) {
			return new NormalPager(this);
		} else if (role.equals("slow")) {
			return new SlowPager(this);
		} else if (role.equals("admin")) {
			return new AdminPager(this);
		}
		return null;
	}
	
}



