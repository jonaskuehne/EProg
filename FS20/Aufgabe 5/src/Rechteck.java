import java.util.*;

public class Rechteck {

    public static void main(String[] args) { 

        Segment[] linien = {
            new Segment(2,2,2,7),
            new Segment(1,3,9,3),
            new Segment(0,6,8,6),
            new Segment(5,0,5,8)
        };

        int anzahl = findeRechtecke(linien); 
        System.out.println(anzahl);
    }
    
    public static int findeRechtecke(Segment[] linien) {
    	
    	if (linien == null) {
    		return 0;
    	}
    	
        // reduce set to all that intersect exactly two
    	Map<Segment, List<Segment>> intersects = new HashMap<>();
    	
    	// track all that intersect with exactly two lines and with which
    	for (Segment s1 : linien) {
    		// list for potential intersection
    		List<Segment> cuts = new ArrayList<>();
    		for (Segment s2: linien) {
    			// intersects
    			if (s1.schneidetSich(s2)) {
        			cuts.add(s2);
        		}
    		}
    		
    		// right number of intersections?
    		if (cuts.size() == 2) {
    			intersects.put(s1, cuts);
    		}
    	}
    	
    	int numRectangles = 0;
    	
    	for (Segment s : intersects.keySet()) {
    		// get list of intersected segments
    		List<Segment> l = intersects.get(s);
    		// both are also in map -> also cut two segments
    		if (intersects.containsKey(l.get(0)) && intersects.containsKey(l.get(1))) {
    			// first one
    			List<Segment> l0 = intersects.get(l.get(0));
    			// second one
    			List<Segment> l1 = intersects.get(l.get(1));
    			
    			// second one contains all of first one -> same, has only 2 elements
    			if (l1.contains(l0.get(0)) && l1.contains(l0.get(0))) {
    				++numRectangles;
    			}
    		}
    	
    	}
    	
    	// solutions counted for each edge -> / 4
    	return numRectangles / 4;
    }
    
}
