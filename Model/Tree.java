package Model;

/**
 * This class contains an implementation of a method from the Seed Class;
 * it is specialized for a Tree object.
 * @author Carlos
 */
public class Tree extends Seed {
        
    public Tree (String name, double harvestTime, int waterNeeded, int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, int minProducts, int maxProducts, double seedCost, double basePrice) {
        super (name, harvestTime, waterNeeded, waterMax, fertilizerNeeded, fertilizerMax, harvestCost, minProducts, maxProducts, seedCost, basePrice);
    }

    public Tree (Tree t) {
        super (t);
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public double computeSellingPrice() {
        return getBasePrice() + getWater() * 0.25 * getBasePrice() + getFertilizer() * 0.5 * getBasePrice() - getHarvestCost();
    }
}
