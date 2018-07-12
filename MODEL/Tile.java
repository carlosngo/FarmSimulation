public class Tile{
	private Seed seed;
	private int water;
	private int fertilizer;
	private boolean isWithered;
	private boolean hasRocks;
	private boolean isPlowed;
	private boolean isTree;
	private boolean isOccupied;
	
	public Tile(Seed seed, int water, int fertilizer){
		this.seed = seed;
		this.water = water;
		this.fertilizer = fertilizer;
	}
	
	public Tile(){
		
	}
	
	public void setSeed(Seed seed){
		this.seed = seed;
	}
	
	public void (int water){
		this.water = water;
	}
	
	public void setFertilizer(int fertilizer){
		this.fertilizer = fertilizer;
	}
	
	public void putRocks(){
		//set rocks
	}
	
	public Seed getSeed(){
		return seed;
	}
	
	public int getWater(){
		return water;
	}
	
	public int getFertilizer(){
		return fertilizer;
	}
	
	public boolean isWithered(){
		return isWithered;
	}
	
	public boolean isOccupied(){
		return isOccupied = b;
	}
	
	public boolean hasRocks(){
		return hasRocks;
	}
	
	public boolean isPlowanle(){
		return isPlowed;
	} 
	
	public boolean isTree(){
		return isTree;
	}
}






















