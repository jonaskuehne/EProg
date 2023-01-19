
public class SimpleTicket implements Ticket {
	
	// class variables
    String productName;
    Product product;
    int prio;
    
    // ctor
    public SimpleTicket(String productName, int prio) {
        this.productName = productName;
        this.prio = prio;
    }

    @Override
    public boolean isReady() {
        return product != null;
    }

    @Override
    public Product getProduct() {
        if (!isReady()) {
        	throw new IllegalArgumentException();
        }

        return product;
    }
    
    // also make sure not already set
    public boolean rightProduct(String name) {
        return  this.product == null && this.productName.equals(name);
    }

    public void takeProduct(Product product) {
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
        
    }
}
