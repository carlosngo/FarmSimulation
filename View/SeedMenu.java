/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class SeedMenu extends JFrame implements ActionListener{
    private JButton[][] actionButtons;
    private JButton back;
    private JTabbedPane tp;
    private TabPanel pVeggie, pFlower, pTree;
    private GridBagConstraints c = new GridBagConstraints();
    private static int row;
    private static int col;
    
    public TabPanel getpVeggie() {
        return pVeggie;
    }

    public TabPanel getpFlower() {
        return pFlower;
    }

    public TabPanel getpTree() {
        return pTree;
    }

    
    public JFrame getFrame() {
        return this;
    }

    public SeedMenu() {
        row = 0;
        col = 0;
        actionButtons = new JButton[12][6];
        initSeedMenu();
    }
    
    public void initSeedMenu(){
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        tp = new JTabbedPane(); 
        tp.setSize(500, 500);
        tp.setFont(new Font("Abril Fatface", Font.PLAIN, 14));
        
        pVeggie = new TabPanel("Vegetables");
        tp.add("Vegetables", pVeggie);
        
        pFlower = new TabPanel("Flowers");
        tp.add("Flowers", pFlower);
        
        pTree = new TabPanel("Trees");
        tp.add("Fruit Tree", pTree);
        
        back = new JButton("Back");
        back.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(this);
        
        p.add(tp);
        p.add(back);
        add(p);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /*
    public void addTab(String category){
        TabPanel tab = new TabPanel(category);
        tp.add(category, tab.getMainPnl());
    }
    */
    /*
    public JPanel addFirstRow(String category){
        JPanel firstPnl = new JPanel(new GridBagLayout());
        JLabel nameLabel = makeLabel(category);
        c.gridx = 0; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        firstPnl.add(nameLabel,c);
        JLabel amount = makeLabel("Amount");
        c.gridx = 2; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        firstPnl.add(amount,c);
        JLabel action = makeLabel("Actions");
        c.gridx = 4; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        firstPnl.add(action,c);
        return firstPnl;
    }
    
    public JPanel addRow(String name, int number){
        JPanel pnl = new JPanel(new GridBagLayout());
        JLabel nameLabel = makeLabel(name);
        c.gridx = 0; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        pnl.add(nameLabel,c);
        JLabel amount = makeLabel("" + number);
        c.gridx = 2; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        pnl.add(amount,c);
        JPanel actionButtonPnl = new JPanel();
        actionButtonPnl.setLayout(new BoxLayout(actionButtonPnl, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);  
        actionButtonPnl.setBorder(border);
        actionButtons[row][col] = new JButton("Buy");
        actionButtons[row][col].setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        actionButtonPnl.add(actionButtons[row][col]);
        col++;
        actionButtons[row][col] = new JButton("Plant");
        actionButtons[row][col].setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        actionButtonPnl.add(actionButtons[row][col]);
        c.gridx = 4; 
        c.gridy = 0; 
        c.gridwidth = 2; 
        c.fill = GridBagConstraints.HORIZONTAL;
        pnl.add(actionButtonPnl,c);
        row++;
        return pnl;
    }
    
    public JLabel makeLabel(String s){
        JLabel label = new JLabel(s);
        label.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);    
        label.setBorder(border);
        return label;
    }
    */
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==back){
            dispose();
        }
        else{
            for(int i=0;i<12;i++){
                for(int j=0;j<2;j++){
                    if(e.getSource()==actionButtons[i][j]){
                        // do actions from actions panel (buy or plant)
                    }
                }
            }
        }
    }
    
    public static void main(String[] args){
        SeedMenu s = new SeedMenu();
        s.getpVeggie().addRow("carrot",5);
        s.getpFlower().addRow("Sunflower", 3);
        s.getpTree().addRow("Mango Tree", 10);
    }
}



