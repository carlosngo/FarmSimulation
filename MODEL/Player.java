public class Player{
	private String name;
	private int type;
	private int level;
	private int exp;
	private Lot lot;
	private double money;
	private Inventory inv;
	private Object selected;
	
	public Player(String name){
		this.name = name;
	}
	
	/*
	public Player(String name, double money){
		this.name = name;
		this.money = money;
	}
	*/
	public String getName(){
		return name;
	}
	
	public int getType(){
		return type;
	}
	
	public int getLvl(){
		return level;
	}
	
	public int getExp(){
		return exp;
	}
	
	public Lot getLot(){
		return Lot;
	}
	
	public double getMoney(){
		return money;
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public Object getSelected(){
		return selected;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setLvl(int level){
		this.level = level;
	}
	
	public void setExp(int exp){
		this.exp = exp;
	}
	
	public void setMoney(double money){
		this.money = money;
	}
	/*
	public void displayStats(){
		//print out the player's name, level, exp, and money?
	}
	*/
	public void select(Object obj){
		
	}
	
	public boolean buy(Object obj, int quantity){
		
	}
	
	public boolean register(int type){
		
	}
	
	public boolean expandLot(){
		
	}
	
	public boolean plow(Tile t){
		
	}
	
	public boolean water(Tile t){
		
	}
	
	public void plant(Tile t, Seed s){
		
	}
	
	public boolean harvest(Tile t){
		//calculate t's sp and etc
		// add money to wallet
	}
	
	public boolean removeWithered(Tile t){
		
	}
	
	public boolean putFertilizer(Tile t){
		// add to t's fertilizer
	}
	
	public void removeRocks(Tile t){
		// set t's hasRocks to false
	}
	/*
	public void use(Object obj){
		//uses object
		//activates object's actions
	}
	
	public void openShop(){
		//display shop items
	}
	
	public void openMenu(){
		//display menu
	}
	*/
	
}
