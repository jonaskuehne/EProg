import java.util.*;

public class SlowPager extends NormalPager {

	List<Work> work;
	
	public SlowPager(Hospital h) {
		super(h);
		work = new LinkedList<>();
	}
	
	@Override
	public void command(String destination, Message msg) {
		work.add(new Work(destination, msg));
		
		if (work.size() >= 3 && !h.paused.getOrDefault(name, false)) {
			
			for (Work w : work) {
				if (w.msg instanceof TextMessage) {
					processTextMsg(w.destination, w.msg);
				} else if (w.msg instanceof QueryMessage) {
					processQueryMsg(w.destination);
				}
			}
			
			work.clear();
			
		}
		
	}

}
