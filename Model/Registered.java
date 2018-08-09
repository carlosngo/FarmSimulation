/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.*;

/**
 * This class contains implemented Player class methods personalized for a "Registered" player
 * @author Carlos 
 */
public class Registered extends Player {
    
    public Registered(Player p) {
        super (p);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = (p.computeBuyingPrice() - 2)* quantity;
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
        if (getMoney() >= 250 && getLevel() >= 15) {
            setMoney(getMoney() - 250);
            return new Distinguished(this);
        } 
        return null;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public boolean plant(Tile t, Seed s) {
        Seed seedClone = getInventory().getSeedClone(s.getName());
        seedClone.setHarvestTime(s.getHarvestTime() - (long)(s.getHarvestTime() * 0.05));
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
        setMoney(getMoney() + ((seed.computeSellingPrice() + 2) * seed.getProducts()));
        t.init();
    }
    
}
