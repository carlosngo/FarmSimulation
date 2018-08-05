package Model;

import Controller.*;

public class Tile implements Runnable, Selectable {
    public final static int ROCKY = 0;
    public final static int UNPLOWED = 1;
    public final static int PLOWED = 2;
    public final static int PLANTED = 3;
    public final static int READY_TO_HARVEST = 4;
    public final static int WITHERED = 5;
    private GameGUIController controller;
    private Seed seed;
    private int fertilizer;
    private int state;

    public Tile(GameGUIController controller) {
        this.controller = controller;
        init();
    }

    public void run() {
        try {
            state = PLANTED;
            Thread.sleep(seed.getHarvestTime());
            if (!seed.isWithered()) {
                state = READY_TO_HARVEST;
                controller.updateLog("A plant is ready to harvest.");
            } else {
                state = WITHERED;
                controller.updateLog("A plant has withered.");
            }
            controller.updateGameGUI();
            Thread.sleep(60000);
            if (state == READY_TO_HARVEST) {
                state = WITHERED;
                controller.updateLog("A plant has withered.");
            }
            controller.updateGameGUI();
            if (seed != null)
                Thread.sleep(seed.getHarvestTime() * 2);
            if (state == WITHERED)
                controller.updateTile(this);
            controller.updateGameGUI();
        } catch (InterruptedException e) {
            
        }
    }
    
    public void init() {
        if (Math.random() * 100 <= 10)
            setState(ROCKY);
        else
            setState(UNPLOWED);
        seed = null;
        fertilizer = 0;
    }
    
    public Seed getSeed() {
        return seed;
    }
    
    public int getstate() {
        return state;
    }
    
    public int getFertilizer() {
        return fertilizer;
    }
    
    public boolean setSeed(Seed seed) {
        if (state == PLOWED) {
            this.seed = seed;
            this.seed.setFertilizer(fertilizer);
        } else
            return false;
        return true;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public void setFertilizer(int fertilizer) {
        this.fertilizer = fertilizer;
    }
    
    public String getDescription() {
        StringBuilder sb = new StringBuilder("State: ");
        switch (state) {
            case ROCKY:
                sb.append("Rocky\n");
                break;
            case UNPLOWED:
                sb.append("Unplowed\n");
                break;
            case PLOWED:
                sb.append("Plowed\n");
                break;
            case WITHERED:
                sb.append("Withered\n");
                break;     
            default:
                sb.append("Planted\n");
        }
        sb.append("Fertilizer: " + fertilizer);
        if (state == PLANTED) {
            if (seed != null) {
                sb.append("\nSeed: " + seed.getName() + "\n");
                sb.append("Water: " + seed.getWater());
            } else {
                sb.append("\nSeed: none (part of a Tree)");
            }
        }
        return sb.toString();
    }
}
