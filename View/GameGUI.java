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
    public static final int numOfTiles = 50;
    
    //Player p;
    JButton[][] tileButtons;
    JFrame f;
    JLabel level, type, money;
    JButton mainmenu, watercan, plow, pickaxe, fertilizer, seeds, exit;
    
    public GameGUI(/*Player p*/){
        //this.p = p;
        tileButtons = new JButton[row][col];
        initGameGUI();
    }
    
    public void initGameGUI(){
        f = new JFrame();
        f.setLayout(new FlowLayout());
        
        JPanel p = new JPanel(); 
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        
        JPanel p1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
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
        //Border border7 = BorderFactory.createLineBorder(Color.BLUE, 1);   plow.setBorder(border7);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(plow,c);
        
        pickaxe = new JButton("Pickaxe");
        pickaxe.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        pickaxe.setIcon(new ImageIcon(resizeImage("pickaxe.png",50,35)));
        pickaxe.setHorizontalAlignment(SwingConstants.LEFT);
        //Border border8 = BorderFactory.createLineBorder(Color.BLUE, 1);   pickaxe.setBorder(border8);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(pickaxe,c);
        
        fertilizer = new JButton("Fertilizer");
        fertilizer.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        fertilizer.setIcon(new ImageIcon(resizeImage("fertilizer.png",50,35)));
        fertilizer.setHorizontalAlignment(SwingConstants.LEFT);
        //Border border9 = BorderFactory.createLineBorder(Color.BLUE, 1);   fertilizer.setBorder(border9);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(fertilizer,c);
        
        seeds = new JButton("Seeds");
        seeds.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        seeds.setIcon(new ImageIcon(resizeImage("seeds.png",50,35)));
        seeds.setHorizontalAlignment(SwingConstants.LEFT);
        //Border border10 = BorderFactory.createLineBorder(Color.BLUE, 1);  seeds.setBorder(border10);
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        p1.add(seeds,c);
        
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
        p.add(p1);
        p.add(p2);
        f.add(p);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        if(e.getSource()==mainmenu){//e.getActionCommand().equals("Main Menu")
            HomeScreen h = new HomeScreen();
            f.dispose();
        }
        else if(e.getSource()==seeds){
            SeedMenu s = new SeedMenu(/*p.getInventory()*/);
        }
    }
    
    public static void main(String[] args){
        GameGUI g = new GameGUI();
    }
}
