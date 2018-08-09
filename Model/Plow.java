/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public class Plow extends Tool {
    public Plow() { }
    
    public boolean use(Tile t) {
        if (t.getstate() == Tile.WITHERED || (t.getstate() == Tile.PLANTED && t.getSeed() != null)) {
            ;
        } else if (t.getstate() == Tile.UNPLOWED)
            t.setState(Tile.PLOWED);
        else
            return false;
        return true;
    }
    
    public String getDescription() {
        return "Prepares a specific title for planting; Also removes plants (costs 10% of seed cost to remove)";
    }
}
