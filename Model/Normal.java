package Model;

/**
 * This class contains implemented Player class methods personalized for an "Unregistered" player
 * @author Carlos
 */
import Controller.*;

public class Normal extends Player  {
    
    public Normal(String name, GameGUIController controller) {
        super (name, controller);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = p.computeBuyingPrice() * quantity;
        if (cost > getMoney())
            return false;
        setMoney(getMoney() - cost);
        if (p instanceof Fertilizer) {
            getInventory().addFertilizer(quantity);
        } else if (p instanceof Seed) {
            getInventory().setQuantity((Seed) p, getInventory().getQuantity((Seed) p) + quantity);
        } 
        
        addExp(25);
        return true;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public Player register() {
        if (getMoney() >= 200 && getLevel() >= 10) {
            setMoney(getMoney() - 200);
            return new Registered(this);
        } 
        return null;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean plant(Tile t, Seed s) {
        Seed seedClone = getInventory().getSeedClone(s.getName());
        if (getInventory().getQuantity(s) > 0 && getLot().plantSeed(t, seedClone)) {
            getInventory().removeSeed(s);
            return true;
        }
        return false;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void harvest(Tile t) {
        Seed seed = t.getSeed();
        if (seed instanceof Tree)
            for (Tile tile : getLot().getAdjacentTiles(t))
                tile.init();
        setMoney(getMoney() + (seed.computeSellingPrice() * seed.getProducts()));
        t.init();
    }
    
}
