/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class GameGUIController {

    private Player player;
    private MainMenu mainMenu;
    private GameGUI game;
    private SeedMenu seedMenu;

    public void startGame() {
        mainMenu = new MainMenu(this);

    }

    public void initializePlayer(String name) {
        player = new Normal(name, this);
    }

    public void initializeGame() {
        game = new GameGUI(this);
        updateGameGUI();
    }

    public void updateInventory() {
        seedMenu = new SeedMenu(this);
        Inventory inv = player.getInventory();
        for (Seed s : inv.getVegetables().keySet()) {
            seedMenu.getpVeggie().addRow(s.getName(), inv.getQuantity(s),
                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
                    s.getSeedCost(), s.getBasePrice());
        }
        for (Seed s : inv.getFlowers().keySet()) {
            seedMenu.getpFlower().addRow(s.getName(), inv.getQuantity(s),
                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
                    s.getSeedCost(), s.getBasePrice());
        }
        for (Seed s : inv.getTrees().keySet()) {
            seedMenu.getpTree().addRow(s.getName(), inv.getQuantity(s),
                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
                    s.getSeedCost(), s.getBasePrice());
        }
        seedMenu.addActionListeners();
//        seedMenu.setFocusable(true);
//        seedMenu.requestFocusInWindow();
    }

    public void updateGameGUI() {
        game.setNameLabel(player.getName());
        game.setLevel(player.getLevel());
        game.setMoney(player.getMoney());
        game.setExp(player.getExp());
        game.setType(player.getType());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                Tile tile = player.getLot().getTile(i, j);
                if (tile.getSeed() != null) {
                    game.setTileImage(tile.getstate(), tile.getSeed().getName(), game.getTileButtons()[i][j]);
                } else {
                    game.setTileImage(tile.getstate(), "", game.getTileButtons()[i][j]);
                }
            }
        }
        game.invalidate();
        game.validate();
        game.repaint();
    }

    public void updateSelected(JButton btn, String name, int quantity) {
        boolean isTile = false;
        double moneyTemp = player.getMoney();
        for (int i = 0; i < GameGUI.MAX_ROW && !isTile; i++) {
            for (int j = 0; j < GameGUI.MAX_COL && !isTile; j++) {
                if (btn.equals(game.getTileButtons()[i][j])) {
                    if (player.getSelected() instanceof WateringCan) {
                        game.setLogAction(1, player.select(player.getLot().getTile(i, j)));
                    } else if (player.getSelected() instanceof Plow) {
                        game.setLogAction(2, player.select(player.getLot().getTile(i, j)));
                    } else if (player.getSelected() instanceof Pickaxe) {
                        game.setLogAction(3, player.select(player.getLot().getTile(i, j)));
                    } else if (player.getSelected() instanceof Fertilizer) {
                        game.setLogAction(4, player.select(player.getLot().getTile(i, j)));
                    } else if (player.getSelected() instanceof Seed) {
                        game.setLogAction(5, player.select(player.getLot().getTile(i, j)));
                    } else {
                        player.select(player.getLot().getTile(i, j));
                    }
                    isTile = true;
                }
            }
        }
        if (!isTile) {
            String cmd = btn.getActionCommand();
            if (cmd.equals("Watering Can")) {
                player.select(Inventory.WATERING_CAN);
                game.setSelected("Watering Can");
            } else if (cmd.equals("Plow")) {
                player.select(Inventory.PLOW);
                game.setSelected("Plow");
            } else if (cmd.equals("Pickaxe")) {
                game.setSelected("Pickaxe");
                player.select(Inventory.PICKAXE);
            } else if (cmd.equals("Fertilizer")) {
                game.setSelected("Fertilizer");
                player.select(player.getInventory().getFertilizers());
            } else if (cmd.equals("EXIT GAME")) {
                game.dispose();
            } else if (cmd.equals("Seeds")) {
                updateInventory();
            } else if (cmd.equals("Plant")) {
                Seed s = player.getInventory().getSeed(name);
                if (player.getInventory().getQuantity(s) > 0) {
                    player.select(s);
                    game.setSelected(s.getName());
                    seedMenu.dispose();                    
                } else {
                    JOptionPane.showMessageDialog(null, "You have insufficient seeds.");
                }
            } else if (cmd.equals("Register")) {
                Player p = player.register();
                if (p != null) {
                    player = p;
                    player.addExp(25);
                } else {
                    JOptionPane.showMessageDialog(null, "You do not meet the requirements to register.");
                }
            } else if (cmd.equals("Buy")) {
                int qty = askQuantity();
                if (qty != 0) {
                    if (!name.isEmpty()) {
                        if (!player.buy(player.getInventory().getSeed(name), qty)) {
                            JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                        } else {
                            seedMenu.dispose();
                            updateInventory();
                        }
                    } else {
                        if (!player.buy(player.getInventory().getFertilizers(), qty)) {
                            JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                        } else {
                            updateGameGUI();
                        }
                    }
                }
            }
        }
        if (player.getSelected() != null) {
            game.setDescription(player.getSelected().getDescription());
        }
        if (moneyTemp != player.getMoney()) {
            if (moneyTemp > player.getMoney()) {
                game.setLogPurchase(moneyTemp - player.getMoney());
            } else {
                game.setLogHarvested(player.getMoney() - moneyTemp);
            }
        }
        updateGameGUI();

    }

    public void updateTile(Tile t) {
        player.getLot().resetTile(t);
    }

    public void updateLog(String log) {
        game.appendLog(log);
    }

    public void deselect() {
        player.deselect();
        game.setSelected("none");
        game.setDescription("");
    }

    public int askQuantity() {
        while (true) {
            try {
                String qty = JOptionPane.showInputDialog(null, "How many do you want to buy?");
                if (qty == null) {
                    break;
                }
                return Integer.parseInt(qty);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input an integer");
            }
        }
        return 0;
    }
}
