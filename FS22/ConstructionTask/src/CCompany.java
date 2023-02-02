import java.util.*;

public class CCompany {
	
	private Set<CSite> sites;
	private Set<Resource> resources;
		
	public CCompany() {
		sites = new LinkedHashSet<>();
		resources = new LinkedHashSet<>();
	}
	
	public Set<Resource> resources() {
		return resources;
	}
	
	public void add(Resource resource) {
		resources.add(resource);
	}
	
	public void nextDay() {
		
		for (CSite site : sites) {
			if (site instanceof DynamicSite) {
				resources.addAll(((DynamicSite)site).getOverFlow());
			}
		}
		
		Iterator<Resource> itr = resources.iterator();
		
		while (itr.hasNext()) {
			Resource resource = itr.next();
			
			for (CSite site : sites) {
				
				if (site.canAdd(resource)) {
					site.add(resource);
					itr.remove();
					break;
				}
				
			}
			
		}
		
	}
	
	public void removeSite(CSite site) {
		sites.remove(site);
	}
	
	public CSite createCSite(int type) {
		// Aendern Sie diese Methode, falls Sie Task (a) nicht geloest haben.
		return createCSite(Set.of(type), 2);
	}

	public CSite createCSite(Set<Integer> types, int limit) {
		CSite site = new MySite(types, limit, this);
		sites.add(site);
		return site;
	}
	
	public CSite createCSite(Set<Integer> types, int limit, int flowLimit) {
		CSite site = new DynamicSite(types, limit, flowLimit, this);
		sites.add(site);
		return site;
	}
}



