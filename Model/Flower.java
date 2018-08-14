package Model;

/**
 * This class contains an implementation of a method from the Seed Class;
 * it is specialized for a Flower object.
 * @author Carlos & Johanna
 */
public class Flower extends Seed {
        
    public Flower(String name, double harvestTime, int waterNeeded, int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, int minProducts, int maxProducts, double seedCost, double basePrice) {
        super (name, harvestTime, waterNeeded, waterMax, fertilizerNeeded, fertilizerMax, harvestCost, minProducts, maxProducts, seedCost, basePrice);
    }

    public Flower (Flower f) {
        super (f);
    }
    
    
    /**
    * {@inheritDoc}
    */
    @Override
    public double computeSellingPrice() {
        double sum = getBasePrice() + getWater() * 0.25 * getBasePrice() + getFertilizer() * 0.5 * getBasePrice();
        return sum + sum * 0.05 - getHarvestCost();
    }
}
