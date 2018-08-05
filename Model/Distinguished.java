/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public class Distinguished extends Player  {

    public Distinguished(Player p) {
        super (p);
    }
    
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = (p.computeBuyingPrice() - 3) * quantity;
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

    @Override
    public Player register() {
        if (getMoney() >= 350 && getLevel() >= 20) {
            setMoney(getMoney() - 350);
            return new Honorable(this);
        } 
        return null;
    }

    @Override
    public boolean plant(Tile t, Seed s) {
        Seed seedClone = getInventory().getSeedClone(s.getName());
        seedClone.setWaterMax(s.getWaterMax() + 1);
        seedClone.setFertilizerMax(s.getFertilizerMax() + 1);
        seedClone.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * 0.1));
        if (getInventory().getQuantity(s) > 0 && getLot().plantSeed(t, seedClone)) {
            getInventory().removeSeed(s);
            return true;
        }
        return false;
    }

    @Override
    public void harvest(Tile t) {
        Seed seed = t.getSeed();
        if (seed instanceof Tree)
            for (Tile tile : getLot().getAdjacentTiles(t))
                tile.init();
        setMoney(getMoney() + ((seed.computeSellingPrice() + 3) * seed.getProducts()));
        t.init();
    }
    
}
