import java.util.*;

public class DynamicSite extends MySite {
	
	private int flowLimit;
	
	public DynamicSite(Set<Integer> types, int limit, int flowLimit, CCompany company) {
		super(types, limit, company);
		this.flowLimit = flowLimit;
	}
	
	public Set<Resource> getOverFlow() {
		Set<Resource> overFlow = new HashSet<>();
		
		Iterator<Resource> itr = resources.iterator();
		
		while (itr.hasNext()) {
			
			Resource resource = itr.next();
			
			if (stock.get(resource.type()) > flowLimit) {
				overFlow.add(resource);
				stock.put(resource.type(), stock.get(resource.type()) - 1);
				itr.remove();
			}
			
		}
		
		return overFlow;
	}

}
