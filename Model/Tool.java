package Model;

/**
 * Represents a selectable tool that can be used on tiles.
 * @author Carlos & Johanna
 */
public abstract class Tool implements Selectable {

    public Tool() { }
    
    /**
     * Performs a specific task on the tile.
     * @param t the tile that the tool will work on
     * @return true, if the tool can be used on the tile; false otherwise
     */
    public abstract boolean use(Tile t);
    
    /**
    * {@inheritDoc}
    */
    @Override
    public abstract String getDescription();
}
