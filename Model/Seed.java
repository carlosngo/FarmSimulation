package Model;

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

    public int getProducts() {
        return products;
    }
    
    public String getName() {
        return name;
    }
    
    public long getHarvestTime() {
        return harvestTime;
    }

    public int getWater() {
        return water;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getWaterMax() {
        return waterMax;
    }

    public int getFertilizer() {
        return fertilizer;
    }

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    public int getFertilizerMax() {
        return fertilizerMax;
    }

    public double getHarvestCost() {
        return harvestCost;
    }

    public int getMinProducts() {
        return minProducts;
    }

    public int getMaxProducts() {
        return maxProducts;
    }

    public double getSeedCost() {
        return seedCost;
    }

    public double getBasePrice() {
        return basePrice;
    }
    
    public long getTimeElapsed() {
        long timeElapsed = System.currentTimeMillis() - plantTime;
        if (timeElapsed <= harvestTime)
            return timeElapsed;
        else
            return harvestTime;
    }
    
    public void setWater (int water) {
        this.water = water;
    }
    
    public void setWaterMax(int waterMax) {
        this.waterMax = waterMax;
    }

    public void setFertilizer (int fertilizer) {
        if (fertilizer > fertilizerMax)
            fertilizer = fertilizerMax;
        this.fertilizer = fertilizer;
    }
    
    public void setFertilizerMax(int fertilizerMax) {
        this.fertilizerMax = fertilizerMax;
    }
   
    public void setPlantTime(long plantTime) {
        this.plantTime = plantTime;
    }
    
    public void setHarvestTime(long harvestTime) {
        this.harvestTime = harvestTime;
    }
    
    public boolean canFertilize() {
        return fertilizer < fertilizerMax;
    }
    
    public boolean canWater() {
        return water < waterMax;
    }
    
    public boolean isWithered() {
        return water < waterNeeded || fertilizer < fertilizerNeeded;
    }
    
    public double computeBuyingPrice() {
        return seedCost;
    }
    
    public String getDescription() {
        return "Name: " + getName() + "\nHarvest Time: " + (harvestTime / 60000.0) + " minutes\nWater Needed: "
                + waterNeeded + "\nFertilizer Needed: " + fertilizerNeeded;
    }
    
    public abstract double computeSellingPrice();
    
    public boolean equals (Object obj) {
        Seed s = (Seed)obj;
        return name.equals(s.getName());
    }
    
    public int compareTo(Seed s) {
        return name.compareTo(s.getName());
    }
}
