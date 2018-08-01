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
        double cost = p.computeBuyingPrice() * quantity - 3;
        if (cost > getMoney())
            return false;
        setMoney(getMoney() - cost);
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
        s.setWaterMax(s.getWaterMax() + 1);
        s.setFertilizerMax(s.getFertilizerMax() + 1);
        s.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * 0.1));
        if (getLot().plantSeed(t, s))
            return true;
        return false;
    }

    @Override
    public void harvest(Tile t) {
        Seed seed = t.getSeed();
        if (seed instanceof Tree)
            for (Tile tile : getLot().getAdjacentTiles(t))
                tile = new Tile();
        setMoney(getMoney() + ((seed.computeSellingPrice() + 3) * seed.getProducts()));
        t = new Tile();
    }
    
}
