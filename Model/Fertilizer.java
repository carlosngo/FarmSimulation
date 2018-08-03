package Model;

public class Fertilizer extends Tool implements Purchasable {

    private int count;

    public Fertilizer() {
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
        return "Fertilizes a specific tile. Cannot be placed on a tile with a plant";
    }
}
