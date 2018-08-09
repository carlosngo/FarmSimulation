package Model;

/**
 * This class contains an implementation of a method from the Seed Class;
 * it is specialized for a Vegetable object.
 * @author Carlos
 */
public class Vegetable extends Seed {
    
    public Vegetable(String name, double harvestTime, int waterNeeded, int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, int minProducts, int maxProducts, double seedCost, double basePrice) {
        super (name, harvestTime, waterNeeded, waterMax, fertilizerNeeded, fertilizerMax, harvestCost, minProducts, maxProducts, seedCost, basePrice);
    }

    public Vegetable (Vegetable v) {
        super (v);
    }
     
    /**
    * {@inheritDoc}
    */
    @Override
    public double computeSellingPrice() {
        return getBasePrice() + getWater() * 0.25 * getBasePrice() + getFertilizer() * 0.5 * getBasePrice() - getHarvestCost();
    }
}
