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
public class Pickaxe extends Tool {
    public Pickaxe() { }
    
    public boolean use(Tile t) {
        if (t.getstate() == Tile.ROCKY)
            t.setState(Tile.UNPLOWED);
        else
            return false;
        return true;
    }
    
    public String getDescription() {
        return "Used for destroying rocks obstructing tiles.";
    }
    
}
