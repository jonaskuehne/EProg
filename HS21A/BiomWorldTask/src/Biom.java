interface Biom {

	public String getBiomType();
	public int getFlora();
	public int getHeight();
	
	// added those
	public boolean stepDryUp();
	public void set(int flora, int height);
	public Biom clone();
}