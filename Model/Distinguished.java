package Model;

/**
 * This class contains implemented Player class methods personalized for a "Distinguished" player
 * @author Carlos
 */
public class Distinguished extends Player  {
    public static final int HARVEST_TIME_REDUCTION = 10;
    public static final int LEVEL_REQUIREMENT = 15;
    public static final int TRANSACTION_BENEFITS = 3;
    public static final int WATER_FERTILIZER_BONUS = 1;
    public static final int REGISTRATION_FEE = 250;
    
    public Distinguished(Player p) {
        super (p);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = (p.computeBuyingPrice() - TRANSACTION_BENEFITS) * quantity;
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
        if (getMoney() >= Honorable.REGISTRATION_FEE && getLevel() >= Honorable.LEVEL_REQUIREMENT) {
            setMoney(getMoney() - Honorable.REGISTRATION_FEE);
            return new Honorable(this);
        } 
        return null;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean plant(Tile t, Seed s) {
        Seed seedClone = getInventory().getClone(s);
        seedClone.setWaterMax(s.getWaterMax() + WATER_FERTILIZER_BONUS);
        seedClone.setFertilizerMax(s.getFertilizerMax() + WATER_FERTILIZER_BONUS);
        seedClone.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * HARVEST_TIME_REDUCTION / 100));
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
        setMoney(getMoney() + ((seed.computeSellingPrice() + TRANSACTION_BENEFITS) * seed.getProducts()));
        t.init();
    }
    
}
