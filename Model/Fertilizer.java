package Model;

/**
 * A tool that helps plants survive the farm heat. It also boosts 
 * the selling price of the plant.
 * @author Carlos & Johanna
 */
public class Fertilizer extends Tool implements Purchasable {

    private int count;

    public Fertilizer() {
        count = 5;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public double computeBuyingPrice() {
        return 10;
    }

    public String getDescription() {
        return "Fertilizes a specific tile. Cannot be placed on a tile with a plant.\n"
                + "You have " + count + " fertilizers remaining.";
    }
}
