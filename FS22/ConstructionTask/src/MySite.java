import java.util.*;

public class MySite implements CSite {
	
	protected int limit;
	protected Map<Integer, Integer> stock;
	protected Set<Resource> resources;
	protected CCompany company;
	
	public MySite(Set<Integer> types, int limit, CCompany company) {
		stock = new HashMap<>();
		resources = new HashSet<>();
		
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
		return stock.containsKey(resource.type()) && stock.get(resource.type()) < limit;
	}

	@Override
	public void add(Resource resource) {
		if (!canAdd(resource)) {
			throw new IllegalArgumentException();
		}
		
		resources.add(resource);
		stock.put(resource.type(), stock.get(resource.type()) + 1);
		
	}

	@Override
	public void use(Resource resource) {
		if (!resources.contains(resource)) {
			throw new IllegalArgumentException();
		}
		
		resources.remove(resource);
		stock.put(resource.type(), stock.get(resource.type()) - 1);
		
	}

	@Override
	public void close() {
		company.removeSite(this);
		
	}

}
