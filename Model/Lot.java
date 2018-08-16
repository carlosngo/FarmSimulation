package Model;

import java.util.*;
import Controller.*;

/**
 * The class that contains the tiles.
 * @author Carlos & Johanna
 */
public class Lot {
    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 5;
    private int currentRows;
    private ArrayList<Tile[]> tiles;
    private GameGUIController controller;

    public Lot(GameGUIController controller) {
        this.controller = controller;
        tiles = new ArrayList<>();
        currentRows = 5;
        for (int i = 0; i < currentRows; i++) {
            tiles.add(new Tile[MAX_COL]);
            for (int j = 0; j < MAX_COL; j++) {
                tiles.get(i)[j] = new Tile(this.controller, i, j);
            }
        }
    }

    /**
     * 
     * @return the 2D array of Tile objects
     */
    public ArrayList<Tile[]> getLot() {
        return tiles;
    }

    /**
     * 
     * @param row the row number of the tile
     * @param col the column number of the tile
     * @return the Tile object in the specified coordinates
     */
    public Tile getTile(int row, int col) {
        return tiles.get(row)[col];
    }

    /**
     * Re-initialize the specified tile. If the tile contains a tree, 
     * re-initialize the tiles around it as well.
     * @param t the Tile to be re-initialized
     */
    public void resetTile(Tile t) {
        if (t.getSeed() instanceof Tree) {
            for (Tile tile : getAdjacentTiles(t)) {
                if (tile.getstate() == Tile.PLANTED && tile.getSeed() == null) {
                    boolean hasTreeAdjacent = false;
                    for (Tile adjacent : getAdjacentTiles(tile)) {
                        if (!(adjacent.equals(t)) && adjacent.getSeed() instanceof Tree) {
                            hasTreeAdjacent = true;
                        }
                    }
                    if (!hasTreeAdjacent)
                        tile.init();
                } else {
                    tile.init();
                }
            }
        }
        t.init();
    }

    /**
     * Sets the parameter's Seed as the seed of the parameter's Tile. 
     * If the seed is a tree, the adjacent tiles of the tile parameter will have leaves.
     * @param t the Tile object to take in the Seed object
     * @param s the Seed object to be planted in the tile object 
     * @return true if planting is successful, otherwise false
     */
    public boolean setSeed(Tile t, Seed s) {
        if (t.getstate() != Tile.PLOWED) {
            return false;
        }
        if (s instanceof Tree) {
            boolean canPlant = true;
            for (Tile tile : getAdjacentTiles(t)) {
                boolean isValidTile = true;
                if (tile.getstate() == Tile.PLANTED && tile.getSeed() != null) 
                    isValidTile = false;
                if (!isValidTile)
                    canPlant = false;
            }
            if (canPlant) {
                for (Tile tile : getAdjacentTiles(t)) {
                    tile.setState(Tile.PLANTED);
                }
            } else {
                return false;
            }
        }
        t.setSeed(s);
        t.getThread().start();
        return true;
    }

    /**
     * 
     * @param t the Tile object to be checked
     * @return the ArrayList of the adjacent tiles around the parameter's Tile object.
     */
    public ArrayList<Tile> getAdjacentTiles(Tile t) {
        int row = t.getRow();
        int col = t.getCol();
        ArrayList<Tile> list = new ArrayList<>();
        if (row - 1 >= 0) {
            list.add(tiles.get(row - 1)[col]);
        }
        if (row + 1 < currentRows) {
            list.add(tiles.get(row + 1)[col]);
        }
        if (col - 1 >= 0) {
            list.add(tiles.get(row)[col - 1]);
        }
        if (col + 1 < MAX_COL) {
            list.add(tiles.get(row)[col + 1]);
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            list.add(tiles.get(row - 1)[col - 1]);
        }
        if (row - 1 >= 0 && col + 1 < MAX_COL) {
            list.add(tiles.get(row - 1)[col + 1]);
        }
        if (row + 1 < currentRows && col - 1 >= 0) {
            list.add(tiles.get(row + 1)[col - 1]);
        }
        if (row + 1 < currentRows && col + 1 < MAX_COL) {
            list.add(tiles.get(row + 1)[col + 1]);
        }
        return list;
    }
    
    /**
     * Expands the lot with the specified rows.
     * @param rows the number of rows to be added 
     */
    public void expand(int rows) {
        int max = currentRows + rows;
        for (int i = currentRows; i < max; i++) {
            tiles.add(new Tile[MAX_COL]);
            currentRows++;
            for (int j = 0; j < MAX_COL; j++) {
                Tile t = new Tile(this.controller, i, j);
                for (Tile tile: getAdjacentTiles(t)) {
                    if (tile != null && tile.getSeed() != null && tile.getSeed() instanceof Tree) {
                        t.setState(Tile.PLANTED);
                    }
                }    
                tiles.get(i)[j] = t;
                
            }
        }
    }
}
