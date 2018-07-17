public class Inventory{
	private ArrayList<Seed> seeds;
	private int noOfFertilizers;
	private ArrayList<Tool> tools;
	
	Inventory(int noOfFertilizers){
		this.noOfFertilizers = noOfFertilizers;
	}
	
	Inventory(){}
	
	public ArrayList<Seed> getSeeds(){
		return seeds;
	}

	public ArrayList<Tool> getTools(){
		return tools;
	}

	public int getQuantity(Seed s){
		seedCtr = 0;
		for(int i=0;i<seeds.size();i++){
			if(seeds.get(i).equals(s))
				seedCtr++;
		}
		return seedCtr;
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

	public void addFertilizer(int quantity){
		noOfFertilizers+=quantity;
	}

	public Seed removeSeed(Seed s){
		int index = seeds.indexOf(s);
		Seed selected = seeds.get(index);
		seeds.remove(s);
		return selected;
	}

	public void removeFertilizer(){}

	public String toString(){}
	/*
	public void displayInventory(){
		System.out.println("Seeds: "):
		for(int i=0;i<seeds.size();i++){
			System.out.println("" + i+1 + "." + seeds.get(i).getName());
		}
		System.out.println("Fertilizer:" + noOfFertilizers);
	}
	*/
}
