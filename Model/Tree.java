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
public class Tree extends Seed {
        
    public Tree (String name, double harvestTime, int waterNeeded, int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, int minProducts, int maxProducts, double seedCost, double basePrice) {
        super (name, harvestTime, waterNeeded, waterMax, fertilizerNeeded, fertilizerMax, harvestCost, minProducts, maxProducts, seedCost, basePrice);
    }

    public Tree (Tree t) {
        super (t);
    }
    
    public double computeSellingPrice() {
        return getBasePrice() + getWater() * 0.25 * getBasePrice() + getFertilizer() * 0.5 * getBasePrice();
    }
}
