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
public class Normal extends Player  {
    
    public Normal(String name) {
        super (name);
    }
    
    @Override
    public boolean buy(Purchasable p, int quantity) {
        double cost = p.computeBuyingPrice() * quantity;
        if (cost > getMoney())
            return false;
        setMoney(getMoney() - cost);
        addExp(25);
        return true;
    }

    @Override
    public Player register() {
        if (getMoney() >= 200 && getLevel() >= 10) {
            setMoney(getMoney() - 200);
            return new Registered(this);
        } 
        return null;
    }

    @Override
    public boolean plant(Tile t, Seed s) {
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
        setMoney(getMoney() + (seed.computeSellingPrice() * seed.getProducts()));
        t = new Tile();
    }
    
}
