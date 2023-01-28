import java.util.*;

public class NormalPager implements Pager {
	// class variables
	String name;
	Hospital h;
	List<Message> inbox;
	Map<Pager, Integer> sentTo;
	
	public NormalPager(Hospital h) {
		// assign / initialize
		this.h = h;
		inbox = new LinkedList<>();
		sentTo = new HashMap<>();
	}

	@Override
	public void register(String newName) {
		
		// nothing to do
		if (newName.equals(name)) {
			return;
		}
		
		// already there
		if (h.pagers.containsKey(newName)) {
			throw new IllegalArgumentException();
		}
		
		// not there then
		// remove old one if there
		if (name != null) {
			h.pagers.remove(name);
		}
		
		// update
		name = newName;
		
		// add new one
		h.pagers.put(name, this);
		
		// clear own history
		sentTo.clear();
		
		// clear history of other ones
		for (Pager p : h.pagers.values()) {
			p.getSentTo().remove(this);
		}
		
	}

	@Override
	public List<Message> inbox() {
		return inbox;
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
		// query
		} else if (msg instanceof QueryMessage) {
			processQueryMsg(destination);
		}
	}
	
	public void processTextMsg(String destination, Message msg) {
		// check if pager exists in hospital
		if (h.pagers.containsKey(destination)) {
			// add message and update count
			h.pagers.get(destination).inbox().add(msg);
			sentTo.put(h.pagers.get(destination), 1 + sentTo.getOrDefault(h.pagers.get(destination), 0));
		// not found
		} else {
			inbox.add(new PagerNotRegisteredMessage(destination));
		}
	}
	
	public void processQueryMsg(String destination) {
		// check if pager exists in hospital
		if (h.pagers.containsKey(destination)) {
			// add required message
			inbox.add(new QueryAnswerMessage(destination, h.pagers.get(destination).getSentTo().getOrDefault(this, 0)));
		// not found
		} else {
			inbox.add(new PagerNotRegisteredMessage(destination));
		}
	}

	@Override
	public Map<Pager, Integer> getSentTo() {
		return sentTo;
	}

}
