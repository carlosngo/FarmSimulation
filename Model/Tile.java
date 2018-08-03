package Model;

public class Tile implements Runnable, Selectable {
    public final static int ROCKY = 0;
    public final static int UNPLOWED = 1;
    public final static int PLOWED = 2;
    public final static int PLANTED = 3;
    public final static int READY_TO_HARVEST = 4;
    public final static int WITHERED = 5;
    private Seed seed;
    private int fertilizer;
    private int state;

    public Tile() {
        if (Math.random() * 100 <= 10)
            setState(ROCKY);
        else
            setState(UNPLOWED);
    }

    public void run() {
        try {
            state = PLANTED;
            Thread.sleep(seed.getHarvestTime());
            state = READY_TO_HARVEST;
            Thread.sleep(60000);
            state = WITHERED;
        } catch (InterruptedException e) {
            
        }
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
        return "";
    }
}
