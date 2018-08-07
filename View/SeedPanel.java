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

public class SeedPanel extends JPanel{
    
    private JPanel product;
    private JLabel qty;
    private JButton buy;
    //private String name;
    //private int amt;
    
    public JPanel getProduct() {
        return product;
    }
    

    public void setQty(int amt) {
        qty.setText("Qty.: " + amt);
        qty.setFont(new Font("Arial", Font.PLAIN, 14));
    }
    
    public SeedPanel(String name, int amt) {
        product = new JPanel();
        product.setLayout(new BorderLayout());
        //this.setOpaque(true);
        JLabel seedpic = new JLabel();
        switch(name){
            case "Turnip"   :     seedpic.setIcon(new ImageIcon(resizeImage("turnip.png",100,80))); break;
            case "Carrot"   :     seedpic.setIcon(new ImageIcon(resizeImage("carrot.png",100,80))); break;
            case "Tomato"   :     seedpic.setIcon(new ImageIcon(resizeImage("tomato.png",100,80))); break;
            case "Potato"   :     seedpic.setIcon(new ImageIcon(resizeImage("potato.png",100,80))); break;
            case "Rose"     :     seedpic.setIcon(new ImageIcon(resizeImage("rose.png",100,80))); break;
            case "Tulip"    :     seedpic.setIcon(new ImageIcon(resizeImage("tulip.png",100,80))); break;
            case "Stargazer":     seedpic.setIcon(new ImageIcon(resizeImage("stargazer.png",100,80))); break;
            case "Sunflower":     seedpic.setIcon(new ImageIcon(resizeImage("sunflower.png",100,80))); break;
            case "Mango"    :     seedpic.setIcon(new ImageIcon(resizeImage("mango.png",100,80))); break;
            case "Apple"    :     seedpic.setIcon(new ImageIcon(resizeImage("apple.png",100,80))); break;
            case "Banana"   :     seedpic.setIcon(new ImageIcon(resizeImage("banana.png",100,80))); break;
            case "Orange"   :     seedpic.setIcon(new ImageIcon(resizeImage("orange.png",100,80))); break;
        }
        product.add(seedpic, BorderLayout.CENTER);
        JPanel functional = new JPanel(new FlowLayout());
        qty = new JLabel();
        qty.setText("Qty.: " + amt);
        qty.setFont(new Font("Arial", Font.PLAIN, 24));
        functional.add(qty);
        buy = new JButton();
        buy.setIcon(new ImageIcon(resizeImage("add seed.png",50,35)));
        functional.add(buy);
        product.add(functional, BorderLayout.SOUTH);
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
}
