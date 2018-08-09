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
    
    public static final int HARVEST_TIME_REDUCTION = 5;
    public static final int LEVEL_REQUIREMENT = 10;
    public static final int TRANSACTION_BENEFIT = 2;
    public static final int WATER_FERTILIZER_BONUS = 0;
    public static final int REGISTRATION_FEE = 200;
    
    public Registered(Player p) {
        super (p);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = (p.computeBuyingPrice() - TRANSACTION_BENEFIT)* quantity;
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
        if (getMoney() >= Distinguished.REGISTRATION_FEE && getLevel() >= Distinguished.LEVEL_REQUIREMENT) {
            setMoney(getMoney() - Distinguished.REGISTRATION_FEE);
            return new Distinguished(this);
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
        setMoney(getMoney() + ((seed.computeSellingPrice() + TRANSACTION_BENEFIT) * seed.getProducts()));
        t.init();
    }
    
}
