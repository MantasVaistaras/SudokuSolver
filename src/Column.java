
public class Column {
	
	private int ID;
	private Region[] regions;
	
	public Column(int ID, int size) {
		this.ID = ID;
		this.regions = new Region[size];
	}
	
	public void setRegion(Region region, int i) {
		this.regions[i] = region; 
	}
	
	public Region[] getRegions() {
		return this.regions;
	}
	
	public int getID() {
		return this.ID;
	}

}
