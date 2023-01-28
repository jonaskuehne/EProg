
public class AdminPager extends NormalPager {

	public AdminPager(Hospital h) {
		super(h);
	}
	
	@Override
	public void command(String destination, Message msg) {
		// mah boy be sleepin'
		if (h.paused.getOrDefault(name, false)) {
			return;
		}
		
		// text
		if (msg instanceof TextMessage) {
			processTextMsg(destination, msg);
		// pause
		} else if (msg instanceof PauseMessage) {
			processPauseMsg(destination);
		// query
		} else if (msg instanceof QueryMessage) {
			processQueryMsg(destination);
		}
	}
	
	@Override
	public void processTextMsg(String destination, Message msg) {
		// add to all
		for (Pager p : h.pagers.values()) {
			p.inbox().add(msg);
			// update count
			sentTo.put(p, 1 + sentTo.getOrDefault(p, 0));
		}
	}
	
	public void processPauseMsg(String destination) {
		// pause, ! of value if already there
		h.paused.put(destination, !h.paused.getOrDefault(destination, false));
	}

}
