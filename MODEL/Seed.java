public class Seed(){
	private int kind;
	private String name;
	private double hTime;
	private int water;
	private int waterMax;
	private int fertilizer;
	private int fertilizerMax;
	private double hCost;
	private int minProducts;
	private int maxProducts;
	//private int products;
	private double sCost;
	private double bp;
	private double sp;
	private double timeElapsed;
	
	public Seed(int kind){
		this.kind = kind;
	}
	
	//public Seed(){}
	
	public int getKind(){
		return kind;
	}
	
	public String getName(){
		return name;
	}
	
	public double getHarvestTime(){
		return hTime;
	}
	
	public int getWater(){
		return water;
	}
	
	public int getWaterMax(){
		return waterMax;
	}
	
	public int getFertilizer(){
		return fertilizer;
	}
	
	public int getFertilizerMax(){
		return fertilizerMax;
	}
	
	public double getHarvestCost(){
		return hCost;
	}
	
	public int getNumOfProducts(){
		
	}
	
	public double getSeedCost(){
		return sCost;
	}
	
	public double getBP(){
		return bp;
	}
	
	public double getSP(){
		return sp;
	}
	
	public boolean canHarvest(){
		
	}
	
	public boolean isWithered(){
		
	}
	
	public String toString(){
		
	}
	
	public boolean equals(Object obj){
		Seed s = (Seed) obj;
		return kind == s.kind && name.equals(s.name);
	}
	
	public int compareTo(Seed s){
		
	}
	
}
