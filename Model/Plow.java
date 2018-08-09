/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * A tool that can prepare a tile for planting and remove withered plants from tiles.
 * @author Carlos
 */
public class Plow extends Tool {
    public Plow() { }
    
    /**
     * Sets the state of the tile parameter from unplowed to plowed. 
     * Returns false if the tile is already plowed or has no seed nor withered plant.
     * @param t the tile to be plowed or prepared for planting.
     * @return true, if the tile is unplowed or has a seed planted or withered plant; false otherwise
     */
    public boolean use(Tile t) {
        if (t.getstate() == Tile.WITHERED || (t.getstate() == Tile.PLANTED && t.getSeed() != null)) {
            ;
        } else if (t.getstate() == Tile.UNPLOWED)
            t.setState(Tile.PLOWED);
        else
            return false;
        return true;
    }
    
    /**
    * {@inheritDoc}
    */
    @Override
    public String getDescription() {
        return "Prepares a specific title for planting; Also removes plants (costs 10% of seed cost to remove)";
    }
}
