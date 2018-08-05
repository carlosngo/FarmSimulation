package Model;

import java.util.*;
import Controller.*;

public class Lot {

    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 10;
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
    
    public boolean plantSeed(Tile t, Seed s) {
        if (t.getstate() != Tile.PLOWED) {
            return false;
        }
        if (s instanceof Tree) {
            if (getAdjacentTiles(t) != null) {
                for (Tile tile : getAdjacentTiles(t)) {
                    tile.setState(Tile.PLANTED);
                }
            } else {
                return false;
            }
        }
        t.setSeed(s);
        new Thread(t).start();
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
        boolean canPlantTree = true;
        boolean canRemoveTree = true;
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
        if (list.size() < 8) {
            canPlantTree = false;
        } else {
            for (Tile tile : list) {
                if (tile.getstate() != Tile.PLOWED) {
                    canPlantTree = false;
                } else if (!(tile.getstate() == Tile.PLANTED && tile.getSeed() == null))
                    canRemoveTree = false;
            }
        }
        if (canPlantTree || canRemoveTree) {
            return list;
        } else {
            return null;
        }
    }
}
