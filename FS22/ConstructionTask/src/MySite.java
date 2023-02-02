import java.util.*;

public class MySite implements CSite {
	// limit for types
	protected int limit;
	// track number of resources of types
	protected Map<Integer, Integer> stock;
	// all resources
	protected Set<Resource> resources;
	// only needed for "close" method
	protected CCompany company;
	
	// ctor
	public MySite(Set<Integer> types, int limit, CCompany company) {
		// initialize stuff
		stock = new HashMap<>();
		resources = new HashSet<>();
		
		// stock empty
		for (int type : types) {
			stock.put(type, 0);
		}
		
		this.limit = limit;
		this.company = company;
	}
	
	@Override
	public Set<Resource> resources() {
		return resources;
	}

	@Override
	public boolean canAdd(Resource resource) {
		// conditions to add resource
		return stock.containsKey(resource.type()) && stock.get(resource.type()) < limit;
	}

	@Override
	public void add(Resource resource) {
		// illegal
		if (!canAdd(resource)) {
			throw new IllegalArgumentException();
		}
		
		// add, adjust stock
		resources.add(resource);
		stock.put(resource.type(), stock.get(resource.type()) + 1);
	}

	@Override
	public void use(Resource resource) {
		// illegal
		if (!resources.contains(resource)) {
			throw new IllegalArgumentException();
		}
		
		// remove, adjust stock
		resources.remove(resource);
		stock.put(resource.type(), stock.get(resource.type()) - 1);
		
	}

	@Override
	public void close() {
		company.removeSite(this);
		
	}

}
