import java.util.*;

public class DynamicSite extends MySite {
	
	private int flowLimit;
	
	public DynamicSite(Set<Integer> types, int limit, int flowLimit, CCompany company) {
		super(types, limit, company);
		this.flowLimit = flowLimit;
	}
	
	public Set<Resource> getOverFlow() {
		// stores overflow
		Set<Resource> overFlow = new HashSet<>();
		
		// iterator as may need to remove stuff
		Iterator<Resource> itr = resources.iterator();
		while (itr.hasNext()) {
			// get element
			Resource resource = itr.next();
			
			// too many -> remove
			if (stock.get(resource.type()) > flowLimit) {
				overFlow.add(resource);
				stock.put(resource.type(), stock.get(resource.type()) - 1);
				itr.remove();
			}
			
		}
		
		return overFlow;
	}

}
