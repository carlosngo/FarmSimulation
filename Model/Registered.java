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
public class Registered extends Player {

    public Registered(Player p) {
        super (p);
    }
    
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = p.computeBuyingPrice() * quantity - 2;
        if (cost > getMoney())
            return false;
        setMoney(getMoney() - cost);
        addExp(25);
        return true;
    }

    @Override
    public Player register() {
        if (getMoney() >= 250 && getLevel() >= 15) {
            setMoney(getMoney() - 250);
            return new Distinguished(this);
        } 
        return null;
    }

    @Override
    public boolean plant(Tile t, Seed s) {
        s.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * 0.05));
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
        setMoney(getMoney() + ((seed.computeSellingPrice() + 2) * seed.getProducts()));
        t = new Tile();
    }
    
}
