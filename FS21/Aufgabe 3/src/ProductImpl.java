
public class ProductImpl implements Product {
	
	// class variables
	boolean taken;
    String name;
    String kind;
    BookingSystem bs;
    
    // ctor
    public ProductImpl(BookingSystem bs, String name, String kind) {
        this.bs = bs;
        this.name = name;
        this.kind = kind;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return kind;
    }

    @Override
    public void giveBack() {
    	
    	// illegal to give something back that's not taken
    	if (!taken) {
    		throw new IllegalArgumentException();
    	}
    	
    	// reset
    	taken = false;
        bs.update(this);
    }
}
