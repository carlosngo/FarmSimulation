/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class GameGUIController {

    private Player player;
    private MainMenu mainMenu;
    private SeedMenu seedMenu;
    private GameGUI game;
    private Clip music;
    private Clip levelUp;
    private Clip plowing;
    private Clip watering;
    private Clip removingRocks;
    private Clip plantingOrHarvesting;
    private Clip buying;
    private AudioInputStream audioSource;
    private FloatControl control;

    public void startGame() {
        mainMenu = new MainMenu(this);
    }

    public void playMusic() {
        try {
            audioSource = AudioSystem.getAudioInputStream(new File("Pineapple Overture.wav"));
            music = AudioSystem.getClip();
            music.open(audioSource);
            control = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(0.25f);
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            System.out.println("error");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("error");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void playSoundEffects(Clip clip, String title) {
        try {
            audioSource = AudioSystem.getAudioInputStream(new File(title));
            clip = AudioSystem.getClip();
            clip.open(audioSource);
            //FloatControl ctrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            //ctrl.setValue(1.0f);
            clip.start();
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("UnsupportedAudioFileException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
/*
    public void playPlowSound() {
        try {
            audioSource = AudioSystem.getAudioInputStream(new File("plowing.wav"));
            Clip plowing = AudioSystem.getClip();
            plowing.open(audioSource);
            //plowing.setMicrosecondPosition(3000000);
            //FloatControl ctrl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            //ctrl.setValue(1.0f);
            plowing.start();
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("UnsupportedAudioFileException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
*/
    public BufferedImage getPlantImage(String name) {
        return game.getPlantImages().get(name);
    }

    public SeedMenu getSeedMenu() {
        return seedMenu;
    }

    public void updateSeedInfo(String name) {
        Seed s = player.getInventory().getSeed(name);
        seedMenu.setPic(game.getPlantImages().get(s.getName()));
        seedMenu.setName(s.getName());
        seedMenu.setHc(s.getHarvestCost() + " OC");
        seedMenu.setWn(s.getWaterNeeded() + " (" + s.getWaterMax() + ")");
        seedMenu.setFn(s.getFertilizerNeeded() + " (" + s.getFertilizerMax() + ")");
        seedMenu.setBp(s.getBasePrice() + " OC");
        seedMenu.setSc(s.getSeedCost() + " OC");
        seedMenu.setPp(s.getMinProducts() + " - " + s.getMaxProducts());
        seedMenu.setHt(s.getHarvestTime() / 60000.0 + " mins.");
    }

    public void initializePlayer(String name) {
        player = new Normal(name, this);
    }

    public void initializeGame() {
        game = new GameGUI(this);
        seedMenu = new SeedMenu(this);
        playMusic();
        for (Seed s : player.getInventory().getSeeds().keySet()) {
            game.addPlantImage(s.getName());
        }
        JButton[][] buttons = game.getTileButtons();
        for (int i = 0; i < game.getCurrentRows(); i++) {
            for (int j = 0; j < GameGUI.MAX_COL; j++) {
                updateTile(i, j);
            }
        }
        initInventory();
        updateGameGUI();
        int choice = JOptionPane.showConfirmDialog(null, "Welcome to Farm Simulator!\n"
                + "Would you like to read the tutorial?", "Tutorial", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            game.showTutorial();
        }
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
            game.addVegetablePanel(s.getName(), this);
            seedMenu.addSeed(s.getName());
        }
        for (Seed s : inv.getFlowers().keySet()) {
            game.addFlowerPanel(s.getName(), this);
            seedMenu.addSeed(s.getName());
        }
        for (Seed s : inv.getTrees().keySet()) {
            game.addTreePanel(s.getName(), this);
            seedMenu.addSeed(s.getName());
        }
    }

    public void updateGameGUI() {
        game.setNameLabel(player.getName());
        game.setLevel(player.getLevel());
        game.setMoney(player.getMoney());
        game.setExp(player.getExp());
        game.setType(player.getType());
        if (player.getSelected() != null) {
            game.setDescription(player.getSelected().getDescription() + "\n"
                    + "Click on the background to deselect this object.");

        }
        game.invalidate();
        game.validate();
        game.repaint();
    }

    public void updateTile(int row, int col) {
        Tile t = player.getLot().getTile(row, col);
        if (t.getSeed() != null) {
            game.setTileImage(t.getstate(), t.getSeed().getName(), game.getTileButtons()[row][col]);
        } else {
            game.setTileImage(t.getstate(), "", game.getTileButtons()[row][col]);
        }
        game.invalidate();
        game.validate();
        game.repaint();
    }

    public void updateCursor(String name) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (name != null) {
            
            Image img = toolkit.getImage(name + ".png");
            Cursor c = toolkit.createCustomCursor(img, new Point(game.getX(),
                    game.getY()), "img");
            game.setCursor(c);
        } else {
            game.setCursor(null);
        }
    }

    public void updateSelected(JButton btn) {
        double moneyTemp = player.getMoney();
        int levelTemp = player.getLevel();
        String cmd = btn.getActionCommand();
        if (cmd.equals("Watering Can")) {
            player.select(Inventory.WATERING_CAN);
            game.setSelected("Watering Can");
            updateCursor("watering can");
        } else if (cmd.equals("Plow")) {
            player.select(Inventory.PLOW);
            game.setSelected("Plow");
            updateCursor("plow");
        } else if (cmd.equals("Pickaxe")) {
            game.setSelected("Pickaxe");
            player.select(Inventory.PICKAXE);
            updateCursor("pickaxe");
        } else if (cmd.equals("Fertilizer")) {
            game.setSelected("Fertilizer");
            player.select(player.getInventory().getFertilizers());
            updateCursor("fertilizer");
        } else if (cmd.equals("EXIT GAME")) {
            music.close();
            game.dispose();
        } else if (cmd.equals("View Seeds")) {
            if (game.getSeedMenu().isVisible()) {
                game.getSeedMenu().setVisible(false);
            } else {
                game.getSeedMenu().setVisible(true);
            }
        } else if (cmd.equals("Register")) {
            deselect();
            int choice = -1;
            switch (player.getType()) {
                case "Farmer":
                    choice = JOptionPane.showConfirmDialog(null, "REGISTRATION BENEFITS\n+"
                            + Registered.HARVEST_TIME_REDUCTION + "% harvest time reduction\n+"
                            + Registered.WATER_FERTILIZER_BONUS + " max fertilizer per tile\n+"
                            + Registered.WATER_FERTILIZER_BONUS + " max water per plant\n+"
                            + Registered.TRANSACTION_BENEFIT + " OC selling price per individual produce\n-"
                            + Registered.TRANSACTION_BENEFIT + " OC buying price per individual item\n"
                            + "+10 additional tiles\n"
                            + "REGISTRATION REQUIREMENTS\n"
                            + "Registration Fee: " + Registered.REGISTRATION_FEE + " OC\n"
                            + "Level Requirement: " + Registered.LEVEL_REQUIREMENT,
                            "Sign up as Registered", JOptionPane.YES_NO_OPTION);
                    break;
                case "Registered":
                    choice = JOptionPane.showConfirmDialog(null, "REGISTRATION BENEFITS\n+"
                            + Distinguished.HARVEST_TIME_REDUCTION + "% harvest time reduction\n+"
                            + Distinguished.WATER_FERTILIZER_BONUS + " max fertilizer per tile\n+"
                            + Distinguished.WATER_FERTILIZER_BONUS + " max water per plant\n+"
                            + Distinguished.TRANSACTION_BENEFITS + " OC selling price per individual produce\n-"
                            + Distinguished.TRANSACTION_BENEFITS + " OC buying price per individual item\n"
                            + "+10 additional tiles\n"
                            + "REGISTRATION REQUIREMENTS\n"
                            + "Registration Fee: " + Distinguished.REGISTRATION_FEE + " OC\n"
                            + "Level Requirement: " + Distinguished.LEVEL_REQUIREMENT,
                            "Sign up as Distinguished", JOptionPane.YES_NO_OPTION);
                    break;
                case "Distinguished":
                    choice = JOptionPane.showConfirmDialog(null, "REGISTRATION BENEFITS\n+"
                            + Honorable.HARVEST_TIME_REDUCTION + "% harvest time reduction\n+"
                            + Honorable.WATER_FERTILIZER_BONUS + " max fertilizer per tile\n+"
                            + Honorable.WATER_FERTILIZER_BONUS + " max water per plant\n+"
                            + Honorable.TRANSACTION_BENEFIT + " OC selling price per individual produce\n-"
                            + Honorable.TRANSACTION_BENEFIT + " OC buying price per individual item\n"
                            + "+5 additional tiles\n"
                            + "REGISTRATION REQUIREMENTS\n"
                            + "Registration Fee: " + Honorable.REGISTRATION_FEE + " OC\n"
                            + "Level Requirement: " + Honorable.LEVEL_REQUIREMENT,
                            "Sign up as Honorable", JOptionPane.YES_NO_OPTION);
                    break;
            }

            if (choice == JOptionPane.YES_OPTION) {
                Player p = player.register();
                if (p != null) {
                    player = p;
                    player.addExp(25);
                    switch (player.getType()) {
                        case "Registered":
                            player.getLot().expand(2);
                            game.addRow(2);
                            break;
                        case "Distinguished":
                            player.getLot().expand(2);
                            game.addRow(2);
                            break;
                        default:
                            player.getLot().expand(1);
                            game.addRow(1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You do not meet the requirements to register.");
                }
            }
        } else if (cmd.equals("Buy Fertilizer")) {
            deselect();
            int qty = askQuantity();
            if (qty != 0) {
                double cost = calculateCost(10, qty);
                int choice = JOptionPane.showConfirmDialog(null, "That would be " + cost + " OC. Proceed?", "Purchase Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (!player.buy(player.getInventory().getFertilizers(), qty)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                    } else {
                        playSoundEffects(buying, "cash register.wav");
                        game.appendLog("Bought " + qty + " fertilizers.");
                        updateGameGUI();
                    }
                }

            }
        } else if (cmd.equals("Volume Down")) {
            deselect();
            float volume = (float) Math.pow(10f, control.getValue() / 20f);
            if (volume - 0.1f >= 0f) {
                control.setValue(20f * (float) Math.log10((float) Math.pow(10f, control.getValue() / 20f) - 0.1f));
            }
        } else if (cmd.equals("Volume Up")) {
            deselect();
            float volume = (float) Math.pow(10f, control.getValue() / 20f);
            if (volume + 0.1f <= 1f) {
                control.setValue(20f * (float) Math.log10((float) Math.pow(10f, control.getValue() / 20f) + 0.1f));
            }
        } else if (cmd.equals("Show Tutorial")) {
            deselect();
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to read the tutorial?", "Select an Option", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                game.showTutorial();
            }
        } else if (cmd.equals("Exit Game")) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Tutorial", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                seedMenu.dispose();
                game.dispose();
                music.stop();
            }
        } else {
            TileButton tileBtn = (TileButton) btn;
            int i = tileBtn.getRow();
            int j = tileBtn.getCol();
            Tile t = player.getLot().getTile(i, j);
            if (player.getSelected() instanceof WateringCan) {
                playSoundEffects(watering, "watering.wav");
                game.setLogAction(1, player.select(t));
            } else if (player.getSelected() instanceof Plow) {
                if (t.getSeed() != null) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to remove this plant?") == JOptionPane.YES_OPTION) {
                        if (player.select(t)) {
                            playSoundEffects(plowing, "plowing.wav");
                            game.setLogAction(2, true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                            game.setLogAction(2, false);
                        }
                    }
                } else {
                    playSoundEffects(plowing, "plowing.wav");
                    //plowing.close();
                    game.setLogAction(2, player.select(t));
                }
            } else if (player.getSelected() instanceof Pickaxe) {
                playSoundEffects(removingRocks, "remove rocks.wav");
                //removingRocks.close();
                game.setLogAction(3, player.select(t));
            } else if (player.getSelected() instanceof Fertilizer) {
                playSoundEffects(plantingOrHarvesting, "plant or pick.wav");
                game.setLogAction(4, player.select(t));
            } else if (player.getSelected() instanceof Seed) {
                playSoundEffects(plantingOrHarvesting, "plant or pick.wav");
                //plantingOrHarvesting.close();
                game.setLogAction(5, player.select(t));
                if (player.getInventory().getQuantity((Seed) player.getSelected()) == 0) {
                    //playSoundEffects(plantingOrHarvesting, "planting or harvesting.wav");
                    deselect();
                }
            } else {
                if (t.getstate() == Tile.READY_TO_HARVEST) {
                    playSoundEffects(plantingOrHarvesting, "plant or pick.wav");
                    //plantingOrHarvesting.close();
                    game.appendLog("Harvested " + t.getSeed().getProducts() + " " + t.getSeed().getName() + "(s).");
                }
                player.select(player.getLot().getTile(i, j));
            }
            updateInventory();
            updateTile(i, j);
            for (Tile tile : player.getLot().getAdjacentTiles(t)) {
                updateTile(tile.getRow(), tile.getCol());
            }

        }
        if (moneyTemp != player.getMoney()) {
            if (moneyTemp > player.getMoney()) {
                playSoundEffects(buying, "cash register.wav");
                game.setLogPurchase(moneyTemp - player.getMoney());
            } else {
                playSoundEffects(buying, "cash register.wav");
                game.setLogHarvested(player.getMoney() - moneyTemp);
            }
        }
        if (levelTemp != player.getLevel()) {
            playSoundEffects(levelUp, "level up.wav");
            //levelUp.close();
            game.appendLog(player.getName() + " has leveled up!");
        }
        updateGameGUI();

    }

    public void buySeed(String name) {
        deselect();
        int qty = askQuantity();
        if (qty != 0) {
            if (!name.isEmpty()) {
                double cost = calculateCost(player.getInventory().getSeed(name).computeBuyingPrice(), qty);
                int choice = JOptionPane.showConfirmDialog(null, "That would be " + cost + " OC. Proceed?", "Purchase Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (!player.buy(player.getInventory().getSeed(name), qty)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Object Coins");
                    } else {
                        updateInventory();
                        updateGameGUI();
                        playSoundEffects(buying, "cash register.wav");
                        game.appendLog("Bought " + qty + " " + name + "(s).");
                        game.setLogPurchase(cost);
                    }
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
            updateCursor(name.toLowerCase() + " cursor");
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
        updateCursor(null);
    }

    public void showHelp() {
        seedMenu.setVisible(true);
    }

    public int askQuantity() {
        while (true) {
            try {
                String qty = JOptionPane.showInputDialog(null, "How many do you want to buy?");
                if (qty == null) {
                    break;
                }
                int quantity = Integer.parseInt(qty);
                if (quantity > 0) {
                    return quantity;
                } else {
                    JOptionPane.showMessageDialog(null, "Please input a whole number");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input a whole number");
            }
        }
        return 0;
    }

    public double calculateCost(double basePrice, int qty) {
        double cost;
        switch (player.getType()) {
            case "Farmer":
                cost = basePrice * qty;
                break;
            case "Registered":
                cost = (basePrice - Registered.TRANSACTION_BENEFIT) * qty;
                break;
            case "Distinguished":
                cost = (basePrice - Distinguished.TRANSACTION_BENEFITS) * qty;
                break;
            default:
                cost = (basePrice - Honorable.TRANSACTION_BENEFIT) * qty;
        }
        return cost;
    }
}
