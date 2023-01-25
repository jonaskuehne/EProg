public class World {

    private int size;
    // store objects
    Biom[][] world;
    
	@Override 
	public String toString() {
    	String str = "";
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            	str += this.getBiom(x, y).getBiomType();
            }
            str += System.lineSeparator();
        }
        return str;
	}
	
    public World(String [] [] biomGrid) {
        size = biomGrid.length;

    	if(biomGrid.length < 2) {
    		throw new IllegalArgumentException();
    	}
    	
    	world = new Biom[size][size];
    	
        for (int i = 0; i < biomGrid.length; i++) {
        	if(biomGrid[i].length != biomGrid.length) {
        		throw new IllegalArgumentException();        		
        	}
        			
        	for(int j = 0; j < biomGrid[i].length; j++) {
        		String biomRepr = biomGrid[i][j];
        		
        		// new water
        		if(biomRepr.equals("W")) {
                    world[i][j] = new WaterBiom();
        		
                // new land
        		} else if(biomRepr.equals("F")) {
                    world[i][j] = new LandBiom();
        			
        		} else {
        			throw new IllegalArgumentException();
        		}
        	}
        }
    }

    public Biom getBiom(int x, int y) {
        return world[x][y];
    }

    public void stepDryUp() {
        for (int i = 0; i < size; ++i) {
        	for (int j = 0; j < size; ++j) {
        		// land -> water
        		if (world[i][j].stepDryUp()) {
        			world[i][j] = new WaterBiom(world[i][j].getFlora());
        		}
        		
        	}
        }
    }

    public void stepDistribute(int p) {
    	
    	// store old objects
    	Biom[][] oldWorld = new Biom[size][size];
    	
    	for (int i = 0; i < size; ++i) {
    		for (int j = 0; j < size; ++j) {
    			// clone
    			oldWorld[i][j] = world[i][j].clone();
    		}
    	}
    	
    	
    	for (int i = 0; i < size; ++i) {
    		for (int j = 0; j < size; ++j) {
    			
    			// initial values
    			int floraSum = 0;
    			int heightSum = oldWorld[i][j].getHeight();
    			
    			// process neighbors
    			for (int k = 1; k <= p; ++k) {
    				// for all valid neighbors
    				if (i - k >= 0) {
    					// add flora
    					floraSum += oldWorld[i - k][j].getFlora();
    					// add 0 if water, 1 if land (with math.min)
    					heightSum += Math.min(oldWorld[i - k][j].getHeight(), 1);
    				}
    				
    				if (i + k < size) {
    					floraSum += oldWorld[i + k][j].getFlora();
    					heightSum += Math.min(oldWorld[i + k][j].getHeight(), 1);
    				}
    				
    				if (j - k >= 0) {
    					floraSum += oldWorld[i][j - k].getFlora();
    					heightSum += Math.min(oldWorld[i][j - k].getHeight(), 1);
    				}
    				
    				if (j + k < size) {
    					floraSum += oldWorld[i][j + k].getFlora();
    					heightSum += Math.min(oldWorld[i][j + k].getHeight(), 1);
    				}
    				
    			}
    			
    			// set new values in new world
    			world[i][j].set(floraSum, heightSum);
    			
    		}
    	}
    	
    	
    }
    
}

