package Model;

/**
 * The class that represents a seed in the game.
 * @author Carlos & Johanna
 */
public abstract class Seed implements Selectable, Purchasable, Comparable<Seed> {
    
    private String name;
    private long plantTime;
    private long harvestTime;
    private int water;
    private int waterNeeded;
    private int waterMax;
    private int fertilizer;
    private int fertilizerNeeded;
    private int fertilizerMax;
    private double harvestCost;
    private int products;
    private int minProducts;
    private int maxProducts;
    private double seedCost;
    private double basePrice;

    public Seed(String name, double harvestTime, int waterNeeded, int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, int minProducts, int maxProducts, double seedCost, double basePrice) {
        this.name = name;
        this.harvestTime = (long)(harvestTime * 60000);
        this.waterNeeded = waterNeeded;
        this.waterMax = waterMax;
        this.fertilizerNeeded = fertilizerNeeded;
        this.fertilizerMax = fertilizerMax;
        this.harvestCost = harvestCost;
        this.minProducts = minProducts;
        this.maxProducts = maxProducts;
        this.seedCost = seedCost;
        this.basePrice = basePrice;
    }
    
    public Seed(Seed s) {
        this.name = s.getName();
        this.harvestTime = s.getHarvestTime();
        this.waterNeeded = s.getWaterNeeded();
        this.waterMax = s.getWaterMax();
        this.fertilizerNeeded = s.getFertilizerNeeded();
        this.fertilizerMax = s.getFertilizerMax();
        this.harvestCost = s.getHarvestCost();
        this.minProducts = s.getMinProducts();
        this.maxProducts = s.getMaxProducts();
        this.seedCost = s.getSeedCost();
        this.basePrice = s.getBasePrice();
        this.products = minProducts + (int)(Math.random() * (maxProducts - minProducts + 1));
    }

    /**
     * 
     * @return the number of products produced by the seed
     */
    public int getProducts() {
        return products;
    }
    
    /**
     * 
     * @return the Seed object's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return the harvest time of the Seed object
     */
    public long getHarvestTime() {
        return harvestTime;
    }

    /**
     * 
     * @return the number of times the Seed object has been watered
     */
    public int getWater() {
        return water;
    }

    /**
     * 
     * @return the Seed object's required water
     */
    public int getWaterNeeded() {
        return waterNeeded;
    }

    /**
     * 
     * @return the Seed object's maximum number of water
     */
    public int getWaterMax() {
        return waterMax;
    }

    /**
     * 
     * @return the Seed object's number of fertilizers 
     */
    public int getFertilizer() {
        return fertilizer;
    }

    /**
     * 
     * @return the Seed object's required number of fertilizers
     */
    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    /**
     * 
     * @return the Seed object's maximum number of fertilizers
     */
    public int getFertilizerMax() {
        return fertilizerMax;
    }

    /**
     * 
     * @return the harvest cost of the Seed object
     */
    public double getHarvestCost() {
        return harvestCost;
    }

    /**
     * 
     * @return the minimum number of products the Seed object produces
     */
    public int getMinProducts() {
        return minProducts;
    }

    /**
     * 
     * @return the maximum number of products the Seed object produces
     */
    public int getMaxProducts() {
        return maxProducts;
    }

    /**
     * 
     * @return the cost of the Seed object
     */
    public double getSeedCost() {
        return seedCost;
    }

    /**
     * 
     * @return the base price of the Seed object
     */
    public double getBasePrice() {
        return basePrice;
    }
    
    /**
     * 
     * @return the elapsed time since the Seed object has been planted
     */
    public long getTimeElapsed() {
        long timeElapsed = System.currentTimeMillis() - plantTime;
        if (timeElapsed <= harvestTime)
            return timeElapsed;
        else
            return harvestTime;
    }
    
    /**
     * 
     * @param water the integer to be set as the new water integer 
     */
    public void setWater (int water) {
        this.water = water;
    }
    
    /**
     * 
     * @param waterMax the integer to be set as the Seed object's maximum number of water
     */
    public void setWaterMax(int waterMax) {
        this.waterMax = waterMax;
    }

    /**
     * 
     * @param fertilizer the integer value to be set as the Seed object's number of fertilizers
     */
    public void setFertilizer (int fertilizer) {
        if (fertilizer > fertilizerMax)
            fertilizer = fertilizerMax;
        this.fertilizer = fertilizer;
    }
    
    /**
     * 
     * @param fertilizerMax the integer to be set as the Seed object's maximum number of fertilizers
     */
    public void setFertilizerMax(int fertilizerMax) {
        this.fertilizerMax = fertilizerMax;
    }
   
    /**
     * 
     * @param plantTime the long value to be set as the Seed object's time planted
     */
    public void setPlantTime(long plantTime) {
        this.plantTime = plantTime;
    }
    
    /**
     * 
     * @param harvestTime the long value to be set as the Seed object's harvest time
     */
    public void setHarvestTime(long harvestTime) {
        this.harvestTime = harvestTime;
    }
    
    /**
     * 
     * @return true if the amount of fertilizer of the seed is below its max capacity; 
     * otherwise false
     */
    public boolean canFertilize() {
        return fertilizer < fertilizerMax;
    }
    
    /**
     * 
     * @return true if the water level of the seed is below its max capacity; 
     * otherwise false
     */
    public boolean canWater() {
        return water < waterMax;
    }
    
    /**
     * 
     * @return true if the plant is withered, otherwise false
     */
    public boolean isWithered() {
        return water < waterNeeded || fertilizer < fertilizerNeeded;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public double computeBuyingPrice() {
        return seedCost;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getDescription() {
        return "Name: " + getName() + "\nHarvest Time: " + (harvestTime / 60000.0) + " minutes\nWater Needed: "
                + waterNeeded + "\nFertilizer Needed: " + fertilizerNeeded;
    }
    
    /**
     * Computes and returns the selling price of an individual produce of the seed.
     * @return the selling price of the Seed object's produce
     */
    public abstract double computeSellingPrice();
    
    /**
     * {@inheritDoc}
     */
    public boolean equals (Object obj) {
        Seed s = (Seed)obj;
        return name.equals(s.getName());
    }
    
    /**
     * {@inheritDoc}
     */
    public int compareTo(Seed s) {
        return name.compareTo(s.getName());
    }
}
