package Model;

import Controller.*;

/**
 * Contains all the game-related information of the 
 * user. An instance of the Player class can select and deselect a Selectable 
 * object. A Player object can also buy a Purchasable instance, plant a seed
 * on a tile, harvest a tile, and register to enjoy benefits.
 * 
 * @author Carlos & Johanna
 */
public abstract class Player {
    public static final int MAX_LEVEL = 50;
    private String name;
    private int level;
    private int exp;
    private Lot lot;
    private double money;
    private Inventory inv;
    private Selectable selected;
    private GameGUIController controller;
    
    public Player(String name, GameGUIController controller) {
        this.controller = controller;
        this.name = name;
        lot = new Lot(this.controller);
        money = 10000;
        level = 9;
        exp = 2250;
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
        this.controller = p.getController();
    }
    /**
     * 
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return a String that corresponds to the player's type
     */
    public String getType() {
        if (this instanceof Normal)
            return "Farmer";
        else if (this instanceof Distinguished)
            return "Distinguished";
        else if (this instanceof Honorable)
            return "Honorable";
        else 
            return "Registered";
        
    }
    
    /**
     * 
     * @return the player's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * 
     * @return the player's accumulated experience
     */
    public int getExp() {
        return exp;
    }

    /**
     * 
     * @return the player's lot
     */
    public Lot getLot() {
        return lot;
    }

    /**
     * 
     * @return how much Object Coins the player has
     */
    public double getMoney() {
        return money;
    }

    /**
     * 
     * @return the player's inventory
     */
    public Inventory getInventory() {
        return inv;
    }

    /**
     * 
     * @return the most recent Selectable object that the player selected
     */
    public Selectable getSelected() {
        return selected;
    }
    
    /**
     * 
     * @return the controller of the player
     */
    public GameGUIController getController() {
        return controller;
    }

    /**
     * 
     * @param level level to update the current level of the player
     */
    public void setLvl(int level) {
        this.level = level;
    }

    /**
     * 
     * @param exp experience to update the current experience of the player
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * 
     * @param money amount of Object Coins to update the current money of the 
     * player
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Selects a Selectable object and assigns it to the current selected field.
     * If the farmer has not selected anything, this method assigns the
     * parameter as the current selected object. Otherwise, the current selected
     * will be used to the Selectable parameter. If a Tool is used, the player 
     * gains 10 EXP per action. If a Seed is used, the player gains 50 EXP.
     * @param s the Selectable object to be selected
     * @return true, if the selection was successful; false otherwise
     */
    public boolean select(Selectable s) {
        Tool tool;
        Tile tile;
        Seed seed;
        if (selected == null) {
            if (s instanceof Tile) {
                tile = (Tile) s;
                if (tile.getSeed() != null && tile.getstate() == Tile.READY_TO_HARVEST) {
                    harvest(tile);
                    addExp(100);
                } else {
                    selected = s;
                }
            } else
                selected = s;
        } else {
            if (selected instanceof Tool) {
                tool = (Tool)selected;
                if (s instanceof Tile) {
                    tile = (Tile) s;
                    if (tool.use(tile)) {
                        if (tool instanceof Plow && (tile.getstate() == Tile.WITHERED
                                || (tile.getstate() == Tile.PLANTED && tile.getSeed() != null))) {
                            if (money - tile.getSeed().getSeedCost() * 0.1 < 0)
                                return false;
                            money -= tile.getSeed().getSeedCost() * 0.1;
                            lot.resetTile(tile);
                        }
                        addExp(10);
                    } else
                        return false;
                } else {
                    selected = s;
                    return true;
                }
            } else if (selected instanceof Seed) {
                seed = (Seed) selected;
                if (s instanceof Tile) {
                    tile = (Tile) s;
                    if (!plant(tile, seed))
                        return false;
                    else
                        addExp(50);
                } else {
                    selected = s;
                }
            } else if (selected instanceof Tile) {
                if (s instanceof Tile) {
                    tile = (Tile) s;
                    if (tile.getSeed() != null && tile.getstate() == Tile.READY_TO_HARVEST) {
                        harvest(tile);
                        addExp(100);
                    } else
                        selected = s;
                } else
                    selected = s;
            } else {
                selected = s;
            }
        }
        return true;
    }
    
    /**
     * Deselects the current selected item of the player.
     */
    public void deselect() {
        selected = null;
    }
    
    /**
     * Adds the value of the parameter to the current EXP and sets the sum
     * as the new value. The method will also update the level of the player.
     * However, it will not do anything if the level of the player exceeds the
     * max value if the parameter was to be added.
     * @param exp the amount of experience to be added
     */
    public void addExp(int exp) {
        if (getExp() + exp <= MAX_LEVEL * 250) {
            setExp(getExp() + exp);
            setLvl(getExp() / 250);
        } else {
            setExp(MAX_LEVEL * 250);
            setLvl(50);
        }
    }
    
    /**
     * Adds the purchased items to the inventory and reduces the
     * amount of Object Coins by the cost of the transaction. The player gains
     * 25 EXP every purchase.
     * @param p item to be purchased
     * @param quantity the number of items to be purchased
     * @return true, if the user has enough Object Coins to buy the object; false
     * otherwise
     */
    public abstract boolean buy(Purchasable p, int quantity);
     
    /**
     * Creates a new Player with the information of this player and returns it.
     * The new player will be one rank or type higher than the old one, except if
     * the old player is of the highest rank.
     * @return the created player
     */
    public abstract Player register();

    /**
     * Calls the plantSeed() method of the Inventory class. If successful, 
     * the quantity of the planted seed in the inventory will be decremented, 
     * and the player will gain 50 EXP. 
     * @param t the tile to be planted on
     * @param s the seed to be planted
     * @return true, if the tile is valid for planting; false otherwise
     */
    public abstract boolean plant(Tile t, Seed s);

    /**
     * Resets the tile and adds the selling price of its planted seed
     * to the player's money.
     * @param t the tile to be harvested
     */
    public abstract void harvest(Tile t);
}
