
public class LandBiom implements Biom {
	
	int flora;
	int height;
	
	public LandBiom() {
		this(12, 3);
	}
	
	// additional constructor for clone method
	public LandBiom(int flora, int height) {
		this.flora = flora;
		this.height = height;
	}

	@Override
	public String getBiomType() {
		return "F";
	}

	@Override
	public int getFlora() {
		return flora;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	// boolean to check if now other type
	@Override
	public boolean stepDryUp() {
		
		flora = Math.max(flora - 3, 0);
		--height;
		
		return height <= 0;
	}
	
	@Override
	public void set(int flora, int height) {
		this.flora = flora;
		this.height = height;
		
	}
	
	@Override
	public Biom clone() {
		return new LandBiom(flora, height);
	}
	
}
