public class Shop{
	ArrayList<Seed> availableSeeds;
	
	public Shop(ArrayList<Seed> seeds){
		availableSeeds = seeds;
	}
	
	public Shop(){
		
	}
	
	public Seed buySeed(int type){
		
	}
	
	public void displaySeeds(){
		for(int i=0;i<availableSeeds.size();i++){
			System.out.print(availableSeeds.get(i).getName());
		}
	}
}