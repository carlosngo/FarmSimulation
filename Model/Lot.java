package Model;

import java.util.*;
import Controller.*;

/**
 * The lot that contains the tiles.
 * @author Carlos
 */
public class Lot {

    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 5;
    private Tile[][] tiles;
    private GameGUIController controller;

    public Lot(GameGUIController controller) {
        this.controller = controller;
        tiles = new Tile[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                tiles[i][j] = new Tile(this.controller);
            }
        }
    }

    /**
     * 
     * @return the 2D array of Tile objects
     */
    public Tile[][] getLot() {
        return tiles;
    }

    /**
     * 
     * @param row the number of rows
     * @param col the number of columns
     * @return the Tile object associated with the parameter's row and col
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Resets the tiles around a Tile planted with a Tree Seed.
     * @param t the Tile to be initialized
     */
    public void resetTile(Tile t) {
        if (t.getSeed() instanceof Tree) {
            for (Tile tile : getAdjacentTiles(t)) {
                tile.init();
            }
        }
        t.init();
    }

    /**
     * Sets (plants) the parameter's Seed as the seed of the parameter's Tile 
     * and returns true. 
     * If the seed is a tree, the adjacent tiles of the tile parameter will have leaves.
     * @param t the Tile object to take in the Seed object
     * @param s the Seed object to be planted in the tile object 
     * @return true if planting is successful, otherwise false
     */
    public boolean plantSeed(Tile t, Seed s) {
        if (t.getstate() != Tile.PLOWED) {
            return false;
        }
        if (s instanceof Tree) {
            boolean canPlant = true;
            for (Tile tile : getAdjacentTiles(t)) {
                boolean isValidTile = false;
                if (tile.getstate() == Tile.PLOWED ||
                        tile.getstate() == Tile.PLANTED && tile.getSeed() == null) 
                    isValidTile = true;
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
     * Returns the list of the adjacent tiles around the parameter's Tile object.
     * @param t the Tile object that is the center of the adjacent Tile objects
     * @return ArrayList of Tile objects
     */
    public ArrayList<Tile> getAdjacentTiles(Tile t) {
        int row = -1;
        int col = -1;
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (tiles[i][j].equals(t)) {
                    row = i;
                    col = j;
                }
            }
        }
        ArrayList<Tile> list = new ArrayList<>();
        if (row - 1 >= 0) {
            list.add(tiles[row - 1][col]);
        }
        if (row + 1 < MAX_ROW) {
            list.add(tiles[row + 1][col]);
        }
        if (col - 1 >= 0) {
            list.add(tiles[row][col - 1]);
        }
        if (col + 1 < MAX_COL) {
            list.add(tiles[row][col + 1]);
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            list.add(tiles[row - 1][col - 1]);
        }
        if (row - 1 >= 0 && col + 1 < MAX_COL) {
            list.add(tiles[row - 1][col + 1]);
        }
        if (row + 1 < MAX_ROW && col - 1 >= 0) {
            list.add(tiles[row + 1][col - 1]);
        }
        if (row + 1 < MAX_ROW && col + 1 < MAX_COL) {
            list.add(tiles[row + 1][col + 1]);
        }
        return list;
    }
}
