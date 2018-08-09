package Model;

/**
 * The tool that waters a plant.
 * @author Carlos
 */
public class WateringCan extends Tool {
    public WateringCan() { }
    
    /**
     * Adds 1 to the tile's seed's number of water
     * Returns false if the tile has already reached its maximum amount of water.
     * @param t the tile to be watered.
     * @return true, if the tile is has a seed planted and that the seed can still be watered; false otherwise
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
