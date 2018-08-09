package Model;

import java.util.*;
import Controller.*;

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

    public Tile[][] getLot() {
        return tiles;
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public void resetTile(Tile t) {
        if (t.getSeed() instanceof Tree) {
            for (Tile tile : getAdjacentTiles(t)) {
                tile.init();
            }
        }
        t.init();
    }

    /**
     * Sets the seed parameter as the seed of the tile parameter. If the seed 
     * is a tree, the adjacent 
     * tiles of the tile parameter will have roots.
     * @param t
     * @param s
     * @return 
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
