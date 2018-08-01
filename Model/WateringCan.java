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
public class WateringCan extends Tool {
    public WateringCan() { }
    
    public boolean use(Tile t) {
        if (t.getState() == Tile.PLANTED && t.getSeed().canWater())
            t.getSeed().setWater(t.getSeed().getWater() + 1);
        else
            return false;
        return true;
    }
    
    public String getDescription() {
        return "Waters a specific crop; Can dispense an infinite amount of water.";
    }
    
}
