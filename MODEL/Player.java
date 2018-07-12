public class Player{
	private String name;
	private int type;
	private int level;
	private int exp;
	private Lot lot;
	private double money;
	private Inventory inv;
	private Object selected;
	
	public Player(String name, double money){
		this.name = name;
		this.money = money;
	}
	
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
	
	public void displayStats(){
		//print out the player's name, level, exp, and money?
	}
	
	public void select(Object obj){
		//selects object
	}
	
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
	
	public void plow(Tile t){
		// set t's isPlowed to true
	}
	
	public void water(Tile t){
		// add to t's water
	}
	
	public void harvest(Tile t){
		//calculate t's sp and etc
		// add money to wallet
	}
	
	public void putFertilizer(Tile t){
		// add to t's fertilizer
	}
	
	public void buy(Seed s){
		// add to Inventory's ArrayList<Seed>
	}
	
	public void destroyRocks(){
		// set t's hasRocks to false
	}
	
	public void register(int type){
		// set player's type
	}
}