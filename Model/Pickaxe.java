package Model;

/**
 * A tool that can remove rocks from tiles.
 * @author Carlos & Johanna
 */
public class Pickaxe extends Tool {
    public Pickaxe() { }
    
    /**
     * Sets the state of the tile parameter from rocky to unplowed. 
     * @param t the tile to remove rocks from.
     * @return true, if the tile has rocks; false otherwise
     */
    public boolean use(Tile t) {
        if (t.getstate() == Tile.ROCKY)
            t.setState(Tile.UNPLOWED);
        else
            return false;
        return true;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getDescription() {
        return "Used for destroying rocks obstructing tiles.";
    }
    
}
