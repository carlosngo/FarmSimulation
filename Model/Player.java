package Model;

public abstract class Player {
    public static final int MAX_LEVEL = 50;
    private String name;
    private int level;
    private int exp;
    private Lot lot;
    private double money;
    private Inventory inv;
    private Selectable selected;

    public Player(String name) {
        this.name = name;
        lot = new Lot();
        money = 100;
        inv = new Inventory();
    }
    
    public Player(Player p) {
        this.name = p.getName();
        this.level = p.getLevel();
        this.exp = p.getExp();
        this.lot = p.getLot();
        this.money = p.getMoney();
        this.inv = p.getInventory();
        this.selected = p.getSelected();
    }
    public String getName() {
        return name;
    }
    
    public String getType() {
        if (this instanceof Normal)
            return "Farmer";
        else if (this instanceof Distinguished)
            return "Distinguished Farmer";
        else if (this instanceof Honorable)
            return "Honorable Farmer";
        else 
            return "Registered Farmer";
        
    }
    
    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public Lot getLot() {
        return lot;
    }

    public double getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inv;
    }

    public Selectable getSelected() {
        return selected;
    }

    public void setLvl(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void select(Selectable s) {
        Tool tool;
        Tile tile;
        Seed seed;
        if (selected == null) {
            if (s instanceof Tile) {
                tile = (Tile) s;
                if (tile.getSeed() != null && tile.getstate() == Tile.READY_TO_HARVEST) {
                    harvest(tile);
                    addExp(100);
                }
            } else
                selected = s;
        } else {
            if (selected instanceof Tool) {
                tool = (Tool)selected;
                if (s instanceof Tile) {
                    tile = (Tile) s;
                    if (tool.use(tile)) {
                        addExp(10);
                        if (tool instanceof Plow && tile.getstate() == Tile.WITHERED) {
                            money -= tile.getSeed().getSeedCost() * 0.1;
                            lot.resetTile(tile);
                        }
                    }
                    selected = null;
                }
            } else if (selected instanceof Seed) {
                seed = (Seed) selected;
                if (s instanceof Tile) {
                    tile = (Tile) s;
                    plant(tile, seed);
                    selected = null;
                }
            }
           
        }
    }
    
    public void addExp(int exp) {
        this.exp += exp;
        level = this.exp / 500;
    }
    
    public abstract boolean buy(Purchasable p, int quantity);

    public abstract Player register();

    public abstract boolean plant(Tile t, Seed s);

    public abstract void harvest(Tile t);
}
