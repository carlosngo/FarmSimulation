package Model;

/**
 * A tool that helps plants survive the farm heat. It also boosts 
 * the selling price of the plant.
 * @author Carlos
 */
public class Fertilizer extends Tool implements Purchasable {

    private int count;

    public Fertilizer() {
        count = 5;
    }

    /**
     * 
     * @return the player's fertilizer count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count the integer to be set as the new count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Fertilizes the parameter's Tile object and decrements the count of the 
     * fertilizer.
     * @param t the tile to be fertilized
     * @return true, if the fertilization was successful; false otherwise 
     */
    public boolean use(Tile t) {
        if (getCount() > 0) {
            if (t.getstate() == Tile.PLOWED) {
                t.setFertilizer(t.getFertilizer() + 1);
                count--;
                return true;
            }
        }
        return false;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public double computeBuyingPrice() {
        return 10;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String getDescription() {
        return "Fertilizes a specific tile. Cannot be placed on a tile with a plant.\n"
                + "You have " + count + " fertilizers remaining.";
    }
}
