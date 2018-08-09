package Model;

import java.util.*;

/**
 * This class contains methods for tools' actions to be executed.
 * @author Carlos
 */
public class Inventory {

    private HashMap<Seed, Integer> seeds;
    private final Fertilizer FERTILIZERS = new Fertilizer();
    public static final Pickaxe PICKAXE = new Pickaxe();
    public static final Plow PLOW = new Plow();
    public static final WateringCan WATERING_CAN = new WateringCan();

    public Inventory() {
        seeds = new HashMap<>();
        for (Seed s : getAvailableSeeds()) {
            seeds.put(s, 0);
        }
    }

    /**
     * This list is used to initialize the HashMap seeds list
     * @return ArrayList of Seed objects
     */
    private ArrayList<Seed> getAvailableSeeds() {
        ArrayList<Seed> seeds = new ArrayList<>();
        Seed turnip = new Vegetable("Turnip", 1, 1, 2, 0, 1, 1, 1, 1, 5, 6);
        Seed carrot = new Vegetable("Carrot", 1.5, 1, 2, 0, 1, 1, 1, 2, 10, 9);
        Seed tomato = new Vegetable("Tomato", 2.5, 3, 4, 1, 2, 1, 1, 3, 20, 15);
        Seed potato = new Vegetable("Potato", 5, 4, 5, 2, 3, 1, 1, 6, 25, 13);
        Seed rose = new Flower("Rose", 1, 1, 2, 0, 1, 2, 1, 1, 5, 5);
        Seed tulip = new Flower("Tulip", 1.5, 2, 3, 0, 1, 2, 1, 1, 7, 7);
        Seed stargazer = new Flower("Stargazer", 2.5, 2, 3, 0, 1, 2, 1, 1, 10, 9);
        Seed sunflower = new Flower("Sunflower", 3.5, 2, 3, 1, 2, 2, 1, 1, 20, 19);
        Seed mango = new Tree("Mango", 7, 7, 7, 4, 4, 3, 5, 10, 50, 4);
        Seed apple = new Tree("Apple", 7, 7, 7, 5, 5, 3, 7, 10, 55, 3.5);
        Seed banana = new Tree("Banana", 8, 8, 8, 5, 5, 3, 10, 15, 60, 3.5);
        Seed orange = new Tree("Orange", 8, 8, 8, 6, 6, 3, 13, 15, 65, 4.5);
        seeds.add(turnip);
        seeds.add(carrot);
        seeds.add(tomato);
        seeds.add(potato);
        seeds.add(rose);
        seeds.add(tulip);
        seeds.add(stargazer);
        seeds.add(sunflower);
        seeds.add(mango);
        seeds.add(apple);
        seeds.add(banana);
        seeds.add(orange);
        return seeds;
    }

    /**
     * 
     * @return the HashMap list of Seed objects
     */
    public HashMap<Seed, Integer> getSeeds() {
        return seeds;
    }
    
    /**
     * 
     * @return the subHashMap seeds list or the Tree objects HashMap list
     */
    public TreeMap<Tree, Integer> getTrees() {
        TreeMap<Tree, Integer> trees = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Tree) {
                trees.put((Tree) s, getQuantity(s));
            }
        }
        return trees;
    }

    /**
     * 
     * @return the subHashMap seeds list or the Flower objects HashMap list
     */
    public TreeMap<Flower, Integer> getFlowers() {
        TreeMap<Flower, Integer> flowers = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Flower) {
                flowers.put((Flower) s, getQuantity(s));
            }
        }
        return flowers;
    }

    /**
     * 
     * @return the subHashMap seeds list or the Vegetable objects HashMap list
     */
    public TreeMap<Vegetable, Integer> getVegetables() {
        TreeMap<Vegetable, Integer> vegetables = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Vegetable) {
                vegetables.put((Vegetable) s, getQuantity(s));
            }
        }
        return vegetables;
    }

    /**
     * 
     * @return the initial Fertilizer object
     */
    public Fertilizer getFertilizers() {
        return FERTILIZERS;
    }

    /**
     * 
     * @param s the Seed object of the requested ID
     * @return the HashMap ID of the parameter's Seed object
     */
    public int getQuantity(Seed s) {
        return seeds.get(s);
    }

    /**
     * Returns the Seed object with the matching name with the parameter's name.
     * If no matches are found, null is returned.
     * @param name the String name of the chosen Seed object
     * @return Seed object if a match is found, otherwise null
     */
    public Seed getSeed(String name) {
        for (Seed s : seeds.keySet()) 
            if (s.getName().equals(name)) 
                return s;
        return null;
    }
    
    /**
     * Returns a clone object of the Seed with the matching name with the parameter's name.
     * @param name the String name of the chosen Seed object to be cloned
     * @return Seed object if a match is found, otherwise null
     */
    public Seed getSeedClone(String name) {
        for (Seed s : seeds.keySet()) {
            if (s.getName().equals(name)) {
                if (s instanceof Tree) {
                    return new Tree((Tree) s);
                } else if (s instanceof Flower) {
                    return new Flower((Flower) s);
                } else if (s instanceof Vegetable) {
                    return new Vegetable((Vegetable) s);
                }
            }
        }
        return null;
    }

    /**
     * Sets the HashMap ID of the parameter's Seed object to the parameter's quantity.
     * @param s the Seed object whose ID is to be changed
     * @param quantity the new ID to be used
     */
    public void setQuantity(Seed s, int quantity) {
        seeds.put(s, quantity);
    }

    /**
     * Adds the parameter's quantity to the count of fertilizers.
     * @param quantity the integer to be added to the current number of fertilizers
     */
    public void addFertilizer(int quantity) {
        FERTILIZERS.setCount(FERTILIZERS.getCount() + quantity);
    }

    /**
     * Adds the parameter's Seed object to the list of seeds.
     * @param s the Seed object to be added to the seeds ArrayList
     */
    public void addSeed(Seed s) {
        seeds.put(s, 0);
    }

    /**
     * Decreases the number of Seed objects in the HashMap seeds list and returns true.
     * @param s the Seed object to be removed
     * @return true if removal is successful, otherwise null
     */
    public boolean removeSeed(Seed s) {
        if (getQuantity(s) < 1) {
            return false;
        } else {
            setQuantity(s, getQuantity(s) - 1);
        }
        return true;
    }
}
