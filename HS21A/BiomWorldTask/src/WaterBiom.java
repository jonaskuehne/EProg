
public class WaterBiom implements Biom {
	
	int flora;
	
	public WaterBiom() {
		this(15);
	}
	
	// additional constructor for clone method
	public WaterBiom(int flora) {
		this.flora = flora;
	}

	@Override
	public String getBiomType() {
		return "W";
	}

	@Override
	public int getFlora() {
		return flora;
	}

	@Override
	public int getHeight() {
		return 0;
	}
	
	// boolean to check if now other type -> here always false
	@Override
	public boolean stepDryUp() {
		
		flora = Math.max(flora - 5, 0);
		
		return false;
	}
	
	// don't care about height
	@Override
	public void set(int flora, int height) {
		this.flora = flora;
		
	}
	
	@Override
	public Biom clone() {
		return new WaterBiom(flora);
	}
	
}
