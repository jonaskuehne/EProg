public class Segment {
    // Aendern Sie nicht die Signaturen der bestehenden Methoden und Konstruktoren.
    // Sie duerfen weitere Methoden und Felder der Klasse hinzufuegen.

    public final int start_x;
    public final int start_y;
    public final int end_x;
    public final int end_y;

    Segment(int sx, int sy, int ex, int ey) {
        start_x = sx;
        start_y = sy; 
        end_x = ex;
        end_y = ey;
    }
    
    public boolean isH() {
    	return start_y == end_y;
    }
    
    public boolean schneidetSich(Segment linie) {
    	
        // both horizontal
    	if (isH() && linie.isH()) {
    		return false;
    	}
    	// both vertical
    	if (!isH() && !linie.isH()) {
    		return false;
    	}
    	
    	if (isH()) {
    		return linie.end_y >= start_y && linie.start_y <= start_y && linie.start_x >= start_x && linie.start_x <= end_x;
    	} else {
    		return linie.start_x <= start_x && linie.end_x >= start_x && linie.end_y >= start_y && linie.start_y <= end_y;
    	}
    }
    
 }
