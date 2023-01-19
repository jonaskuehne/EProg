import java.util.*;

public class BookingSystem {
	
	// store products and tickets
    List<ProductImpl> products = new LinkedList<>();
    List<SimpleTicket> reservations = new LinkedList<>();

    public void createProduct(String productName, String productKind) {
        if (!productKind.equals("fifo") && !productKind.equals("priority")) {
        	// illegal name
        	throw new IllegalArgumentException();
        }
        
        // create new product
        ProductImpl product = new ProductImpl(this, productName, productKind);
        products.add(product);
        
        // update
        update(product);
    }

    public Ticket reserve(String productName, int priority) {
    	// create new ticket
        SimpleTicket ticket = new SimpleTicket(productName, priority);
        reservations.add(ticket);
        
        // find product
        ProductImpl product = getProduct(productName);
        // viable?
        if (product != null) {
        	// take
            ticket.setProduct(product);
            product.taken = true;
        }

        return ticket;
    }

    public Ticket multiReserve(String[] productNames, int priority) {
    	// illegal array
        if (productNames == null || productNames.length == 0) {
        	throw new IllegalArgumentException();
        }
        
        // create ticket
        MultiTicket ticket = new MultiTicket(productNames, priority);
        reservations.add(ticket);
        
        // for all tickets
        for (int i = 0; i < productNames.length; ++i) {
        	// find product
            ProductImpl product = getProduct(productNames[i]);
            // viable?
            if (product != null) {
            	// take
                ticket.setProduct(product, i);
                product.taken = true;
            }
        }

        return ticket;
    }
    
    // update state in stock
    public void update(ProductImpl product) {
        List<SimpleTicket> availableTickets = new LinkedList<>();
        
        // for all tickets
        for (SimpleTicket ticket : reservations) {
            if (ticket.rightProduct(product.name())) {
            	availableTickets.add(ticket);
            }
        }
        
        // nothing to update
        if (availableTickets.isEmpty()) {
        	return;
        }
        
        // priority -> sort
        if (product.kind().equals("priority")) {
            availableTickets.sort(new Comparator<SimpleTicket>() {
                @Override
                public int compare(SimpleTicket t1, SimpleTicket t2) {
                	// swap for decreasing order
                	return Integer.valueOf(t2.prio).compareTo(t1.prio);
                }
            });
        }
        
        // take first one (for fifo directly)
        availableTickets.get(0).takeProduct(product);
        product.taken = true;
    }
    
    // find fitting product
    private ProductImpl getProduct(String productName) {
    	// go through products
        for (ProductImpl product : products) {
        	// found something
            if (product.name().equals(productName) && !product.taken) {
            	return product;
            } 
        }
        // nothing found
        return null;
    }
}
