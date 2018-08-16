package Model;

/**
 * An object in the game that can be purchased by the user.
 * @author Carlos & Johanna
 */
public interface Purchasable {

    /**
     * Computes and returns the price of the object.
     * @return the price of the object
     */
    public abstract double computeBuyingPrice();
}
