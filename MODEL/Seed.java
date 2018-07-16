public class Seed(){
	int type;
	String name;
	double hTime;
	int water;
	int waterMax;
	int fertilizer;
	int fertilizerMax;
	double hCost;
	int products;
	double sCost;
	double bp;
	double sp;
	double timeElapsed;
	
	public Seed(int type){
		this.type = type;
	}
	
	public Seed(){}
	
	public int getType(){
		return type;
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
		return products;
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
	/*
	public boolean equals(Object obj){
		Seed s = (Seed) obj;
		return type == s.type && name.equals(s.name) && hTime==s.hTime && water==s.water && waterMax==s.waterMax &&
			   fertilizer==s.fertilizer && fertilizerMax==s.fertilizerMax && hCost==s.hCost && products==s.products &&
			   sCost==s.sCost && bp==s.bp && sp==s.sp && timeElapsed==s.timeElapsed;
	}
	*/
	public boolean equals(Object obj){
		Seed s = (Seed) obj;
		return type == s.type && name.equals(s.name);
	}
}
