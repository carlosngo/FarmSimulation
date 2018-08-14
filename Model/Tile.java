package Model;

import Controller.*;

/**
 * The class that represents a tile in the game.
 * @author Carlos
 */
public class Tile implements Runnable, Selectable {
    public final static int ROCKY = 0;
    public final static int UNPLOWED = 1;
    public final static int PLOWED = 2;
    public final static int PLANTED = 3;
    public final static int READY_TO_HARVEST = 4;
    public final static int WITHERED = 5;
    private GameGUIController controller;
    private Thread thread;
    private Seed seed;
    private int fertilizer;
    private int state;
    private int row;
    private int col;

    public Tile(GameGUIController controller, int row, int col) {
        this.controller = controller;
        this.row = row;
        this.col = col;
        thread = new Thread(this);
        init();
    }

    /**
     * 
     * @return the row number of this tile
     */
    public int getRow() {
        return row;
    }

    /**
     * 
     * @return the column number of this tile
     */
    public int getCol() {
        return col;
    }

    
    /**
     * {@inheritDoc}
     */
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
//            controller.updateGameGUI();
            controller.updateTile(row, col);
            Thread.sleep(60000);
            if (state == READY_TO_HARVEST) {
                state = WITHERED;
                controller.updateLog("A plant has withered.");
            }
//            controller.updateGameGUI();
            controller.updateTile(row, col);
            if (seed != null)
                Thread.sleep(seed.getHarvestTime() * 2);
            if (state == WITHERED)
                controller.updateTile(this);
//            controller.updateGameGUI();
            controller.updateTile(row, col);
        } catch (InterruptedException e) { }
    }
    
    /**
     * Initializes the Tile. 10% chance for the tile to be rocky.
     */
    public void init() {
        if (thread.isAlive())
            thread.interrupt();
        thread = new Thread(this);
        if (Math.random() * 100 <= 10)
            setState(ROCKY);
        else
            setState(UNPLOWED);
        seed = null;
        fertilizer = 0;
    }
    
    /**
     * 
     * @return the Tile object's Seed object
     */
    public Seed getSeed() {
        return seed;
    }
    
    /**
     * Returns the state of the tile object.
     * @return 0 if the Tile object is rocky<br>
     *         1 if the Tile object is unplowed<br>
     *         2 if the Tile object is plowed<br>
     *         3 if the Tile object is planted<br>
     *         4 if the Tile object is ready to be harvested<br>
     *         5 if the Tile object is withered<br>
     */
    public int getstate() {
        return state;
    }
    
    /**
     * 
     * @return the number of fertilizers in the tile
     */
    public int getFertilizer() {
        return fertilizer;
    }
    
    /**
     * 
     * @return the Tile object's thread
     */
    public Thread getThread() {
        return thread;
    }
    
    /**
     * 
     * @param seed the Seed object to be set as the Tile object's Seed object
     * @return true is the state of the tile is plowed, otherwise false
     */
    public boolean setSeed(Seed seed) {
        if (state == PLOWED) {
            this.seed = seed;
            this.seed.setFertilizer(fertilizer);
            this.seed.setPlantTime(System.currentTimeMillis());
        } else
            return false;
        return true;
    }
    
    /**
     * 
     * @param state the integer to be set as the Tile object's state
     */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * 
     * @param fertilizer the integer to be set as the See object's fertilizer
     */
    public void setFertilizer(int fertilizer) {
        this.fertilizer = fertilizer;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
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
       
        if (state == PLANTED || state == WITHERED) {
            if (seed != null) {
                sb.append("Fertilizer: " + seed.getFertilizer());
                sb.append("\nFertilizer Needed: " + seed.getFertilizerNeeded());
                sb.append("\nSeed: " + seed.getName());
                sb.append("\nWater: " + seed.getWater());
                sb.append("\nWater Needed: " + seed.getWaterNeeded());
                sb.append("\nTime until harvest: " + ((seed.getHarvestTime() - seed.getTimeElapsed()) / 1000.0) + " seconds");
            } else {
                sb.append("\nSeed: none (part of a Tree)");
            }
        } else
            sb.append("Fertilizer: " + fertilizer);
        return sb.toString();
    }
}
