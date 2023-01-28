import java.util.*;

public class SlowPager extends NormalPager {
	// stores work
	List<Work> work;
	
	public SlowPager(Hospital h) {
		super(h);
		work = new LinkedList<>();
	}
	
	@Override
	public void command(String destination, Message msg) {
		// add to list
		work.add(new Work(destination, msg));
		
		// not paused and at least 3 messages to process
		if (work.size() >= 3 && !h.paused.getOrDefault(name, false)) {
			
			// process
			for (Work w : work) {
				// text
				if (w.msg instanceof TextMessage) {
					processTextMsg(w.destination, w.msg);
				// query
				} else if (w.msg instanceof QueryMessage) {
					processQueryMsg(w.destination);
				}
			}
			
			// work done
			work.clear();
		}
		
	}

}
