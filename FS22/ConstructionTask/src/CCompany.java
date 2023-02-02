import java.util.*;

public class CCompany {
	// store sites and resources
	private Set<CSite> sites;
	private Set<Resource> resources;
		
	public CCompany() {
		// linked since order matters
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
		// go through sites, if dynamic site: get overflow
		for (CSite site : sites) {
			if (site instanceof DynamicSite) {
				resources.addAll(((DynamicSite)site).getOverFlow());
			}
		}
		
		// go through resources, may need to remove them -> iterator
		Iterator<Resource> itr = resources.iterator();
		while (itr.hasNext()) {
			// get element
			Resource resource = itr.next();
			
			// has valid site?
			for (CSite site : sites) {
				// yes
				if (site.canAdd(resource)) {
					site.add(resource);
					itr.remove();
					// leave loop
					break;
				}
				
			}
			
		}
		
	}
	
	// remove site from list -> no longer considered in "nextDay"
	public void removeSite(CSite site) {
		sites.remove(site);
	}
	
	public CSite createCSite(int type) {
		// Aendern Sie diese Methode, falls Sie Task (a) nicht geloest haben.
		return createCSite(Set.of(type), 2);
	}

	public CSite createCSite(Set<Integer> types, int limit) {
		// add site to set
		CSite site = new MySite(types, limit, this);
		sites.add(site);
		return site;
	}
	
	public CSite createCSite(Set<Integer> types, int limit, int flowLimit) {
		// add site to set
		CSite site = new DynamicSite(types, limit, flowLimit, this);
		sites.add(site);
		return site;
	}
}



