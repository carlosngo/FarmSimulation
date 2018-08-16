/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.*;
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

public class SeedPanel extends JPanel implements ActionListener {
    
    private JPanel product;
    private JLabel qty;
    private JButton buy;
    private GameGUIController controller;
    private String name;
    //private int amt;
    
    public JPanel getProduct() {
        return product;
    }
    
    public int getQty() {
        return Integer.parseInt(qty.getText());
    }
    
    public String getPlantName() {
        return name;
    }
            
    public void setQty(int amt) {
        qty.setText("Qty.: " + amt);
        qty.setFont(new Font("Arial", Font.PLAIN, 14));
    }
    
    public SeedPanel(String name, int amt, GameGUIController controller) {
        this.name = name;
        this.controller = controller;
        product = new JPanel(new GridBagLayout());
        product.setOpaque(false);
        product.setBackground( new Color(208, 240, 192) );
        Border border = BorderFactory.createLineBorder(new Color(0,78,56), 1);  
        product.setBorder(border);
        GridBagConstraints c = new GridBagConstraints(); 
        //product.setLayout(new BoxLayout(product, BoxLayout.Y_AXIS));
        //this.setOpaque(true);
        JButton seedPic = new JButton();
        seedPic.setActionCommand("Plant Seed");
        seedPic.setToolTipText(name);
        seedPic.addActionListener(this);
        seedPic.setIcon(new ImageIcon(resizeImage("/" + name.toLowerCase() + ".png", 120, 100)));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        product.add(seedPic,c);
        JPanel functional = new JPanel(new FlowLayout());
        functional.setBackground(new Color(0,168,107));
        //functional.setOpaque(false);
        qty = new JLabel();
        qty.setText("Qty.: " + amt);
        qty.setFont(new Font("Arial", Font.PLAIN, 18));
        functional.add(qty);
        buy = new JButton();
        buy.setActionCommand("Buy Seed");
        buy.setToolTipText("Buy Seed");
        buy.addActionListener(this);
        buy.setIcon(new ImageIcon(resizeImage("/add seed.png",35,25)));
        functional.add(buy);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        product.add(functional,c);
    }

    public BufferedImage resizeImage(String address, int width, int height) {
        try{
             BufferedImage rawHolder = ImageIO.read(getClass().getResource(address));
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
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Plant Seed"))
            controller.selectSeed(name);
        else
            controller.buySeed(name);
    }
}
