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
        if (t.getstate() == Tile.WITHERED) 
            ;
        else if (t.getstate() == Tile.UNPLOWED)
            t.setState(Tile.PLOWED);
        else
            return false;
        return true;
    }
    
    public String getDescription() {
        return "Prepares a specific title for planting; Also removes withered plants (costs 2 OC to remove)";
    }
}
