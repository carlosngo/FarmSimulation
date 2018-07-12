public class Inventory{
	ArrayList<Seed> seeds;
	int noOfFertilizers;
	
	Inventory(int noOfFertilizers){
		this.noOfFertilizers = noOfFertilizers;
	}
	
	Inventory(){
		
	}
	
	public ArrayList<Seed> getSeeds(){
		return seeds;
	}
	
	public int getFertilizers(){
		return noOfFertilizers;
	}
	
	public void addSeeds(Seed s, int num){
		for(int i=0;i<num;i++)
			seeds.add(s);
	}
	/*
	//for multiple kinds of seed
	public void addSeeds(ArrayList<Seed> seeds, int num){
		for(int i=0;i<seeds.size();i++){
			this.seeds.add(seeds.get(i));
		}
	}
	*/
	public Seed removeSeed(Seed s){
		//seeds.remove(s);
	}
	
	public void displayInventory(){
		System.out.println("Seeds: "):
		for(int i=0;i<seeds.size();i++){
			System.out.println("" + i+1 + "." + seeds.get(i).getName());
		}
		System.out.println("Fertilizer:" + noOfFertilizers);
	}
}