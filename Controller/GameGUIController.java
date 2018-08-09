/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class GameGUIController {

    private Player player;
    private MainMenu mainMenu;
    private GameGUI game;
    private Clip music;
    private AudioInputStream audioSource;

    public void startGame() {
        mainMenu = new MainMenu(this);
    }

    public void playMusic() {
        try {
            /*
            audioSource = AudioSystem.getAudioInputStream(new File("Pineapple Overture.wav"));
 
            AudioFormat format = audioSource.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            music = (Clip) AudioSystem.getLine(info);
 
            music.addLineListener(info);
 
            music.open(audioSource);
             
            music.start();
            
            music.loop(Clip.LOOP_CONTINUOUSLY);
             */
            audioSource = AudioSystem.getAudioInputStream(new File("Pineapple Overture.wav"));
            music = AudioSystem.getClip();
            music.open(audioSource);
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            System.out.println("error");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("error");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void initializePlayer(String name) {
        player = new Normal(name, this);
    }

    public void initializeGame() {
        game = new GameGUI(this);
        playMusic();
        for (Seed s : player.getInventory().getSeeds().keySet()) {
            game.addPlantImage(s.getName());
        }
        initInventory();
        updateGameGUI();
    }

    public void updateInventory() {
        Inventory inv = player.getInventory();
        for (SeedPanel sp : game.getSeedPanels()) {
            sp.setQty(inv.getQuantity(inv.getSeed(sp.getPlantName())));
            
        }
    }
    
    public void initInventory() {
        Inventory inv = player.getInventory();
        for (Seed s : inv.getVegetables().keySet()) {
            System.out.println("Added" + s.getName());
            game.addVegetablePanel(s.getName(), this);
//            seedMenu.getpVeggie().addRow(s.getName(), inv.getQuantity(s),
//                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
//                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
//                    s.getSeedCost(), s.getBasePrice());
        }
        for (Seed s : inv.getFlowers().keySet()) {
            game.addFlowerPanel(s.getName(), this);
//            seedMenu.getpFlower().addRow(s.getName(), inv.getQuantity(s),
//                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
//                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
//                    s.getSeedCost(), s.getBasePrice());
        }
        for (Seed s : inv.getTrees().keySet()) {
            game.addTreePanel(s.getName(), this);
//            seedMenu.getpTree().addRow(s.getName(), inv.getQuantity(s),
//                    s.getHarvestTime() / 60000.0, s.getWaterNeeded(), s.getWaterMax(),
//                    s.getFertilizerNeeded(), s.getFertilizerMax(), s.getHarvestCost(), s.getMinProducts(), s.getMaxProducts(),
//                    s.getSeedCost(), s.getBasePrice());

        }
//        seedMenu.addActionListeners();
//        seedMenu.setFocusable(true);
//        seedMenu.requestFocusInWindow();
    }

    public void updateGameGUI() {
        game.setNameLabel(player.getName());
        game.setLevel(player.getLevel());
        game.setMoney(player.getMoney());
        game.setExp(player.getExp());
        game.setType(player.getType());
        if (player.getSelected() != null) 
            game.setDescription(player.getSelected().getDescription() + "\nClick the background to deselect this tool.");
        
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

    public void updateSelected (JButton btn) {
        double moneyTemp = player.getMoney();
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
            music.close();
//            audioSource.close();
            game.dispose();
        } else if (cmd.equals("View Seeds")) {
            if (game.getSeedMenu().isVisible())
                game.getSeedMenu().setVisible(false);
            else
                game.getSeedMenu().setVisible(true);
        } else if (cmd.equals("Register")) {
            Player p = player.register();
            if (p != null) {
                player = p;
                player.addExp(25);
            } else {
                JOptionPane.showMessageDialog(null, "You do not meet the requirements to register.");
            }
        } else if (cmd.equals("Buy Fertilizer")) {
            int qty = askQuantity();
            if (qty != 0) {
                if (!player.buy(player.getInventory().getFertilizers(), qty)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                } else {
                    game.appendLog("Bought " + qty + " fertilizers.");
                    updateGameGUI();
                }
            }
        } else {
            boolean found = false;
            for (int i = 0; i < GameGUI.MAX_ROW && !found; i++) {
                for (int j = 0; j < GameGUI.MAX_COL && !found; j++) {
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
                        found = true;
                        updateInventory();
                    }
                }
            }
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

    public void buySeed(String name) {
        int qty = askQuantity();
        if (qty != 0) {
            if (!name.isEmpty()) {
                if (!player.buy(player.getInventory().getSeed(name), qty)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                } else {
                    updateInventory();
                    updateGameGUI();
                    game.appendLog("Bought " + qty + " " + name + "s.");
                    game.setLogPurchase(player.getInventory().getSeed(name).computeBuyingPrice() * qty);
                }
            }
        }
    }

    public void selectSeed(String name) {

        Seed s = player.getInventory().getSeed(name);
        if (player.getInventory().getQuantity(s) > 0) {
            player.select(s);
            game.setSelected(s.getName());
            updateGameGUI();
        } else {
            JOptionPane.showMessageDialog(null, "You have insufficient seeds.");
        }
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
        game.setDescription("Click on a tool to select it, or click on a tile to view its details.");
    }

    public int askQuantity() {
        while (true) {
            try {
                String qty = JOptionPane.showInputDialog(null, "How many do you want to buy?");
                if (qty == null) {
                    break;
                } 
                int quantity = Integer.parseInt(qty);
                if (quantity > 0)
                    return quantity;
                else
                    JOptionPane.showMessageDialog(null, "Please input a positive integer");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input an integer");
            }
        }
        return 0;
    }
}
