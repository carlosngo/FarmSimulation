package Model;

import java.util.*;

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

    public HashMap<Seed, Integer> getSeeds() {
        return seeds;
    }
    
    public TreeMap<Tree, Integer> getTrees() {
        TreeMap<Tree, Integer> trees = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Tree) {
                trees.put((Tree) s, getQuantity(s));
            }
        }
        return trees;
    }

    public TreeMap<Flower, Integer> getFlowers() {
        TreeMap<Flower, Integer> flowers = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Flower) {
                flowers.put((Flower) s, getQuantity(s));
            }
        }
        return flowers;
    }

    public TreeMap<Vegetable, Integer> getVegetables() {
        TreeMap<Vegetable, Integer> vegetables = new TreeMap<>();
        for (Seed s : seeds.keySet()) {
            if (s instanceof Vegetable) {
                vegetables.put((Vegetable) s, getQuantity(s));
            }
        }
        return vegetables;
    }

    public Fertilizer getFertilizers() {
        return FERTILIZERS;
    }

    public int getQuantity(Seed s) {
        return seeds.get(s);
    }

    public Seed getSeed(String name) {
        for (Seed s : seeds.keySet()) 
            if (s.getName().equals(name)) 
                return s;
        return null;
    }
    
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

    public void setQuantity(Seed s, int quantity) {
        seeds.put(s, quantity);
    }

    public void addFertilizer(int quantity) {
        FERTILIZERS.setCount(FERTILIZERS.getCount() + quantity);
    }

    public void addSeed(Seed s) {
        seeds.put(s, 0);
    }

    public boolean removeSeed(Seed s) {
        if (getQuantity(s) < 1) {
            return false;
        } else {
            setQuantity(s, getQuantity(s) - 1);
        }
        return true;
    }
}
