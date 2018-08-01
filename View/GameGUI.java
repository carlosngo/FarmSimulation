package View;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class GameGUI implements ActionListener{
    public static final int row = 10;
    public static final int col = 5;
    private boolean canWater, canPlow, canPickaxe, canFertilize;
    JButton[][] tileButtons;
    JFrame f;
    JPanel p1;
    JLabel level, type, money, description;
    JButton mainmenu, watercan, plow, pickaxe, fertilizer, seeds;

    public GameGUI() {
        canWater = false;
        canPlow = false;
        canPickaxe = false;
        canFertilize = false;
        tileButtons = new JButton[row][col];
        initGameGUI();
    }
    
    public void initGameGUI(){
        f = new JFrame();
        f.setLayout(new FlowLayout());
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); 
        
        JPanel p1 = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("MyFarm");
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 30));
        //Border border = BorderFactory.createLineBorder(Color.BLUE, 5);    title.setBorder(border);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(title,c);
        
        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border1 = BorderFactory.createLineBorder(Color.BLUE, 1);   name.setBorder(border1);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(name,c);
        
        level = new JLabel("Level: ");
        level.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border2 = BorderFactory.createLineBorder(Color.BLUE, 1);   level.setBorder(border2);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(level,c);
        
        type = new JLabel("Type: ");
        type.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border3 = BorderFactory.createLineBorder(Color.BLUE, 1);   type.setBorder(border3);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(type,c);
        
        money = new JLabel("Money: ");
        money.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border4 = BorderFactory.createLineBorder(Color.BLUE, 1);   money.setBorder(border4);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(money,c);
        
        mainmenu = new JButton("Main Menu");
        mainmenu.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        mainmenu.addActionListener(this);
        //Border border5 = BorderFactory.createLineBorder(Color.BLUE, 1);   mainmenu.setBorder(border5);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(mainmenu,c);
        
        JLabel inventory = new JLabel("Inventory: ");
        inventory.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border6 = BorderFactory.createLineBorder(Color.BLUE, 1);   inventory.setBorder(border6);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(inventory,c);
        
        watercan = new JButton("Watering can");
        watercan.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        watercan.setIcon(new ImageIcon(resizeImage("watering can.png",50,35)));
        watercan.setHorizontalAlignment(SwingConstants.LEFT);
        watercan.addActionListener(this);
        //Border border6 = BorderFactory.createLineBorder(Color.BLUE, 1);   watercan.setBorder(border6);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(watercan,c);
        
        plow = new JButton("Plow");
        plow.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        plow.setIcon(new ImageIcon(resizeImage("plow.png",50,35)));
        plow.setHorizontalAlignment(SwingConstants.LEFT);
        plow.addActionListener(this);
        //Border border7 = BorderFactory.createLineBorder(Color.ORANGE, 1);   plow.setBorder(border7);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(plow,c);
        
        pickaxe = new JButton("Pickaxe");
        pickaxe.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        pickaxe.setIcon(new ImageIcon(resizeImage("pickaxe.png",50,35)));
        pickaxe.setHorizontalAlignment(SwingConstants.LEFT);
        pickaxe.addActionListener(this);
        //Border border8 = BorderFactory.createLineBorder(Color.GRAY, 1);   pickaxe.setBorder(border8);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(pickaxe,c);
        
        fertilizer = new JButton("Fertilizer");
        fertilizer.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        fertilizer.setIcon(new ImageIcon(resizeImage("fertilizer.png",50,35)));
        fertilizer.setHorizontalAlignment(SwingConstants.LEFT);
        fertilizer.addActionListener(this);
        //Border border9 = BorderFactory.createLineBorder(Color.GREEN, 1);   fertilizer.setBorder(border9);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(fertilizer,c);
        
        seeds = new JButton("Seeds");
        seeds.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        seeds.setIcon(new ImageIcon(resizeImage("seeds.png",50,35)));
        seeds.setHorizontalAlignment(SwingConstants.LEFT);
        seeds.addActionListener(this);
        //Border border10 = BorderFactory.createLineBorder(Color.BLUE, 1);  seeds.setBorder(border10);
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(seeds,c);
        
        JLabel specs = new JLabel("Description:");
        specs.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border11 = BorderFactory.createLineBorder(Color.BLUE, 1);  specs.setBorder(border11);
        //specs.setHorizontalAlignment(SwingConstants.LEFT);
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(specs,c);
        
        description = new JLabel();
        description.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //Border border = BorderFactory.createLineBorder(Color.BLACK, 1); description.setBorder(border);
        c.gridx = 0;
        c.gridy = 13;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(description,c);
        
        
        GridBagConstraints c2 = new GridBagConstraints();
        JPanel p2 = new JPanel(new GridBagLayout());
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                tileButtons[i][j] = new JButton();
                tileButtons[i][j].setIcon(new ImageIcon(resizeImage("plowed soil.png",60,60)));
                c2.gridx = j;
                c2.gridy = i;
                p2.add(tileButtons[i][j], c2);
            }
        }
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(p1,c);
        c.gridx = 11;
        c.gridy = 0;
        c.gridwidth = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(p2,c);
        f.add(p);
        //f.setSize(900,900);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /*
    public static BufferedImage setTileImage(int state, String plant){
        switch(state){
            case 0 : return resizeImage("rocky soil.png",60,60);
            case 1 : return resizeImage("unplowed soil.png",60,60);
            case 2 : return resizeImage("plowed soil.png",60,60);
            case 3 : switch(plant){
                        case "Turnip"   :     return resizeImage("turnip.png",60,60);
                        case "Carrot"   :     return resizeImage("carrot.png",60,60);
                        case "Tomato"   :     return resizeImage("tomato.png",60,60);
                        case "Potato"   :     return resizeImage("potato.png",60,60);
                        case "Rose"     :     return resizeImage("rose.png",60,60);
                        case "Tulip"    :     return resizeImage("tulip.png",60,60);
                        case "Stargazer":     return resizeImage("stargazer.png",60,60);
                        case "Sunflower":     return resizeImage("sunflower.png",60,60);
                        case "Mango"    :     return resizeImage("mango.png",60,60);
                        case "Apple"    :     return resizeImage("apple.png",60,60);
                        case "Banana"   :     return resizeImage("banana.png",60,60);
                        case "Orange"   :     return resizeImage("orange.png",60,60);
                     }
            case 4: return resizeImage("withered soil.png",60,60);
            default : return null;
        }
    }
    */
    
    public void setTileImage(int state, String plant, JButton tileButton){
        switch(state){
            case 0 : tileButton.setIcon(new ImageIcon(resizeImage("rocky soil.png",60,60)));
            case 1 : tileButton.setIcon(new ImageIcon(resizeImage("unplowed soil.png",60,60)));
            case 2 : tileButton.setIcon(new ImageIcon(resizeImage("plowed soil.png",60,60)));
            case 3 : switch(plant){
                        case "Turnip"   :     tileButton.setIcon(new ImageIcon(resizeImage("turnip.png",60,60)));
                        case "Carrot"   :     tileButton.setIcon(new ImageIcon(resizeImage("carrot.png",60,60)));
                        case "Tomato"   :     tileButton.setIcon(new ImageIcon(resizeImage("tomato.png",60,60)));
                        case "Potato"   :     tileButton.setIcon(new ImageIcon(resizeImage("potato.png",60,60)));
                        case "Rose"     :     tileButton.setIcon(new ImageIcon(resizeImage("rose.png",60,60)));
                        case "Tulip"    :     tileButton.setIcon(new ImageIcon(resizeImage("tulip.png",60,60)));
                        case "Stargazer":     tileButton.setIcon(new ImageIcon(resizeImage("stargazer.png",60,60)));
                        case "Sunflower":     tileButton.setIcon(new ImageIcon(resizeImage("sunflower.png",60,60)));
                        case "Mango"    :     tileButton.setIcon(new ImageIcon(resizeImage("mango.png",60,60)));
                        case "Apple"    :     tileButton.setIcon(new ImageIcon(resizeImage("apple.png",60,60)));
                        case "Banana"   :     tileButton.setIcon(new ImageIcon(resizeImage("banana.png",60,60)));
                        case "Orange"   :     tileButton.setIcon(new ImageIcon(resizeImage("orange.png",60,60)));
                     }
            case 4: tileButton.setIcon(new ImageIcon(resizeImage("withered soil.png",60,60)));
            default : System.out.println("Unable to set picture.");;
        }
    }
    
    public static BufferedImage resizeImage(String address, int width, int height) {
        try{
             BufferedImage rawHolder = ImageIO.read(new File(address));
             Image raw = rawHolder.getScaledInstance(width, height, Image.SCALE_SMOOTH);
             BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2d = resized.createGraphics();
             g2d.drawImage(raw, 0, 0, null);
             g2d.dispose();
             return resized;
        }
        catch(IOException e){
             System.out.println("File not found.");
             return null;
        }
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==mainmenu){
             MainMenu m = new MainMenu();
            f.dispose();
        }
        else if(e.getSource()==watercan){
            description.setText("Click on a plant to water it.");
            canWater = true;
            f.pack();
        }
        else if(e.getSource()==plow){
            description.setText("Click on a tile to plow it.");
            canPlow = true;
            f.pack();
        }
        else if(e.getSource()==pickaxe){
            description.setText("Click on a tile to clear the \nrocks in it.");
            canPickaxe = true;
            f.pack();
        }
        else if(e.getSource()==fertilizer){
            description.setText("Click on a plant to fertilize it.");
            canFertilize = true;
            f.pack();
        }
        else if(e.getSource()==seeds){
            SeedMenu s = new SeedMenu();
        }
        else{
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(e.getSource()==tileButtons[i][j]){
                        //do action when click on a tile
                    }
                }
            }
        }
        
    }
    
    public int water(){
        return 1;
    }
    
    public boolean plow(){
        return true;
    }
    
    public boolean clearRocks(){
        return true;
    }
    
    public int fertilize(){
        return 1;
    }

    public boolean isCanWater() {
        return canWater;
    }

    public boolean isCanPlow() {
        return canPlow;
    }

    public boolean isCanPickaxe() {
        return canPickaxe;
    }

    public boolean isCanFertilize() {
        return canFertilize;
    }
    
    
    public void openSeedStatus(String name, long time, int status){
        SeedStatus seedstatGUI = new SeedStatus(name,time,status);
    }
    
    public static void main(String[] args){
        GameGUI g = new GameGUI();
    }
}
