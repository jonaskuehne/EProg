
public class MultiTicket extends SimpleTicket {
	
	// class variables
    String[] names;
    Product[] products;
    int current;
    
    // ctor
    public MultiTicket(String[] names, int priority) {
        super(null, priority);
        this.names = names;
        this.products = new Product[names.length];
    }

    @Override
    public boolean isReady() {
        for (Product product : products) {
            if (product == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Product getProduct() {
        if (!isReady()) {
        	throw new IllegalArgumentException();
        }
            
        // cycle
        if (current >= products.length) {
            current = 0;
        }

        return products[current++];
    }

    public void setProduct(Product product, int i) {
        this.products[i] = product;
    }

    @Override
    public boolean rightProduct(String name) {
        for (int i = 0; i < names.length; i++) {
        	// make sure not already set
            if (names[i].equals(name) && products[i] == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void takeProduct(Product product) {
        for (int i = 0; i < names.length; i++) {
        	// product taken
            if (names[i].equals(product.name()) && products[i] == null) {
                products[i] = product;
                return;
            }
        }
    }
}
