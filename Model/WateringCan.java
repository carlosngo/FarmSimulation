package Model;

/**
 * A tool that can water a plant.
 * @author Carlos & Johanna
 */
public class WateringCan extends Tool {
    public WateringCan() { }
    
    /**
     * Increments the water level of a plant.
     * @param t the tile to be watered.
     * @return true, if the tile is has a seed planted and that the seed can 
     * still be watered; false otherwise
     */
    public boolean use(Tile t) {
        if (t.getstate() == Tile.PLANTED && t.getSeed() != null && t.getSeed().canWater())
            t.getSeed().setWater(t.getSeed().getWater() + 1);
        else
            return false;
        return true;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getDescription() {
        return "Waters a specific crop; Can dispense an infinite amount of water.";
    }
    
}
