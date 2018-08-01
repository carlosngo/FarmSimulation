package Model;

import java.util.*;

public class Inventory {

    private HashMap<Seed, Integer> seeds;
    private Fertilizer fertilizers;
    public static final Pickaxe PICKAXE = new Pickaxe();
    public static final Plow PLOW = new Plow();
    public static final WateringCan WATERING_CAN = new WateringCan();

    public Inventory() {
        seeds = new HashMap<>();
        // ADD ALL AVAILABLE SEEDS
        fertilizers.setCount(5);
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
        return fertilizers;
    }

    public int getQuantity(Seed s) {
        return seeds.get(s);
    }

    public Seed getSeed(String name) {
        for (Seed s : seeds.keySet()) {
            if (s.getName().equals(name)) {
                if (s instanceof Tree)
                    return new Tree((Tree)s);
                else if (s instanceof Flower)
                    return new Flower((Flower)s);
                else if (s instanceof Vegetable)
                    return new Vegetable((Vegetable)s);
            }
        }
        return null;
    }

    public void setQuantity(Seed s, int quantity) {
        seeds.put(s, quantity);
    }

    public void addFertilizer(int quantity) {
        fertilizers.setCount(fertilizers.getCount() + quantity);
    }

    public void addSeed(Seed s) {
        seeds.put(s, 0);
    }

    public boolean removeSeed(Seed s) {
        if (getQuantity(s) < 1) {
            return false;
        } else {
            setQuantity(s, getQuantity(s) + 1);
        }
        return true;
    }
}
