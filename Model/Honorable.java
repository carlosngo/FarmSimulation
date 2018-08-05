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
public class Honorable extends Player  {

    public Honorable(Player p) {
        super (p);
    }
    
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = (p.computeBuyingPrice() - 5) * quantity;
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
        return this;
    }

    @Override
    public boolean plant(Tile t, Seed s) {
        Seed seedClone = getInventory().getSeedClone(s.getName());
        seedClone.setWaterMax(s.getWaterMax() + 2);
        seedClone.setFertilizerMax(s.getFertilizerMax() + 2);
        seedClone.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * 0.15));
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
        setMoney(getMoney() + ((seed.computeSellingPrice() + 5) * seed.getProducts()));
        t.init();
    }
    
}
