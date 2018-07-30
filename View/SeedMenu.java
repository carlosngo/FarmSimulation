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

/**
 *
 * @author User
 */
public class SeedMenu implements ActionListener{
    
    //Inventory inv;
    JButton[][] actionButtons;
    JFrame f;
    JButton back;
    
    public SeedMenu(/*Inventory inv*/) {
        //this.inv = inv;
        actionButtons = new JButton[12][6];
        initSeedMenu();
    }
    
    public void initSeedMenu(){
        f = new JFrame();
        f.setLayout(new FlowLayout());
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        JTabbedPane tp = new JTabbedPane(); 
        tp.setFont(new Font("Abril Fatface", Font.PLAIN, 14));
        
        GridBagConstraints c = new GridBagConstraints();
        //vegetables tab
        JPanel pVeggie = new JPanel(new GridBagLayout());
        JLabel veggie = makeLabel("Vegetables");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(veggie,c);
        JLabel veggieSeedAmt = makeLabel("Number of Seeds");
        c.gridx = 2; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(veggieSeedAmt,c);
        JLabel veggieActions = makeLabel("Actions");
        c.gridx = 4; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(veggieActions,c);
        JLabel turnip = makeLabel("Turnip");
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(turnip,c);
        JLabel turnipAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(turnipAmt,c);
        JPanel turnipActions = makeActionPanel(actionButtons[0][0], actionButtons[0][1]);
        c.gridx = 4; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(turnipActions,c);
        JLabel carrot = makeLabel("Carrot");
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(carrot,c);
        JLabel carrotAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(carrotAmt,c);
        JPanel carrotActions = makeActionPanel(actionButtons[1][0], actionButtons[1][1]);
        c.gridx = 4; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(carrotActions,c);
        JLabel tomato = makeLabel("Tomato");
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(tomato,c);
        JLabel tomatoAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(tomatoAmt,c);
        JPanel tomatoActions = makeActionPanel(actionButtons[2][0], actionButtons[2][1]);
        c.gridx = 4; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(tomatoActions,c);
        JLabel potato = makeLabel("Potato");
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(potato,c);
        JLabel potatoAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(potatoAmt,c);
        JPanel potatoActions = makeActionPanel(actionButtons[3][0], actionButtons[3][1]);
        c.gridx = 4; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pVeggie.add(potatoActions,c);
        tp.add("Vegetables",pVeggie);
        //flowers tab
        JPanel pFlower = new JPanel(new GridBagLayout());
        JLabel flower = makeLabel("Flowers");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(flower,c);
        JLabel flowerSeedAmt = makeLabel("Number of Seeds");
        c.gridx = 2; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(flowerSeedAmt,c);
        JLabel flowerActions = makeLabel("Actions");
        c.gridx = 4; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(flowerActions,c);
        JLabel rose = makeLabel("Rose");
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(rose,c);
        JLabel roseAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(roseAmt,c);
        JPanel roseActions = makeActionPanel(actionButtons[4][0], actionButtons[4][1]);
        c.gridx = 4; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(roseActions,c);
        JLabel tulip = makeLabel("Tulip");
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(tulip,c);
        JLabel tulipAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(tulipAmt,c);
        JPanel tulipActions = makeActionPanel(actionButtons[5][0], actionButtons[5][1]);
        c.gridx = 4; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(tulipActions,c);
        JLabel stargazer = makeLabel("Stargazer");
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(stargazer,c);
        JLabel stargazerAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(stargazerAmt,c);
        JPanel stargazerActions = makeActionPanel(actionButtons[6][0], actionButtons[6][1]);
        c.gridx = 4; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(stargazerActions,c);
        JLabel sunflower = makeLabel("Sunflower");
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(sunflower,c);
        JLabel sunflowerAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(sunflowerAmt,c);
        JPanel sunflowerActions = makeActionPanel(actionButtons[7][0], actionButtons[7][1]);
        c.gridx = 4; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pFlower.add(sunflowerActions,c);
        tp.add("Flowers",pFlower);
        //fruit trees tab
        JPanel pTree = new JPanel(new GridBagLayout());
        JLabel tree = makeLabel("Fruit Trees");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(tree,c);
        JLabel treeSeedAmt = makeLabel("Number of Seeds");
        c.gridx = 2; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(treeSeedAmt,c);
        JLabel treeActions = makeLabel("Actions");
        c.gridx = 4; c.gridy = 0; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(treeActions,c);
        JLabel mango = makeLabel("Mango");
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(mango,c);
        JLabel mangoAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(mangoAmt,c);
        JPanel mangoActions = makeActionPanel(actionButtons[8][0], actionButtons[8][1]);
        c.gridx = 4; c.gridy = 1; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(mangoActions,c);
        JLabel apple = makeLabel("Apple");
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(apple,c);
        JLabel appleAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(appleAmt,c);
        JPanel appleActions = makeActionPanel(actionButtons[9][0], actionButtons[9][1]);
        c.gridx = 4; c.gridy = 2; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(appleActions,c);
        JLabel banana = makeLabel("Banana");
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(banana,c);
        JLabel bananaAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(bananaAmt,c);
        JPanel bananaActions = makeActionPanel(actionButtons[10][0], actionButtons[10][1]);
        c.gridx = 4; c.gridy = 3; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(bananaActions,c);
        JLabel orange = makeLabel("Orange");
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(orange,c);
        JLabel orangeAmt = makeLabel("0");
        c.gridx = 2; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(orangeAmt,c);
        JPanel orangeActions = makeActionPanel(actionButtons[11][0], actionButtons[11][1]);
        c.gridx = 4; c.gridy = 4; c.gridwidth = 2; c.fill = GridBagConstraints.HORIZONTAL;
        pTree.add(orangeActions,c);
        tp.add("Fruit Trees",pTree);
        
        back = new JButton("Back");
        back.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(this);
        
        p.add(tp);
        p.add(back);
        f.add(p);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public JLabel makeLabel(String s){
            JLabel label = new JLabel(s);
            label.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);    
            label.setBorder(border);
            return label;
    }
    
    public JPanel makeActionPanel(JButton buy, JButton plant){
        JPanel action = new JPanel();
        action.setLayout(new BoxLayout(action, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1); 
        action.setBorder(border);
        buy = new JButton("Buy");
        buy.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        action.add(buy);
        plant = new JButton("Plant");
        plant.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        action.add(plant);
        return action;
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==back){
            f.dispose();
        }
        else if(e.getSource()==actionButtons[0][0]){
            //buy turnip
            //add 1 seed to turnip list
        }
        else if(e.getSource()==actionButtons[0][1]){
            //plant turnip
            //deduct 1 seed from turnip list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[1][0]){
            //buy carrot
            //add 1 seed to carrot list
        }
        else if(e.getSource()==actionButtons[1][1]){
            //plant carrot
            //deduct 1 seed from carrot list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[2][0]){
            //buy tomato
            //add 1 seed to tomato list
        }
        else if(e.getSource()==actionButtons[2][1]){
            //plant tomato
            //deduct 1 seed from tomato list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[3][0]){
            //buy potato
            //add 1 seed to potato list
        }
        else if(e.getSource()==actionButtons[3][1]){
            //plant potato
            //deduct 1 seed from potato list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[4][0]){
            //buy rose
            //add 1 seed to rose list
        }
        else if(e.getSource()==actionButtons[4][1]){
            //plant rose
            //deduct 1 seed from rose list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[5][0]){
            //buy tulip
            //add 1 seed to tulip list
        }
        else if(e.getSource()==actionButtons[5][1]){
            //plant tulip
            //deduct 1 seed from tulip list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[6][0]){
            //buy stargazer
            //add 1 seed to stargazer list
        }
        else if(e.getSource()==actionButtons[6][1]){
            //plant stargazer
            //deduct 1 seed from stargazer list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[7][0]){
            //buy sunflower
            //add 1 seed to sunflower list
        }
        else if(e.getSource()==actionButtons[7][1]){
            //plant sunflower
            //deduct 1 seed from sunflower list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[8][0]){
            //buy mango
            //add 1 seed to mango list
        }
        else if(e.getSource()==actionButtons[8][1]){
            //plant mango
            //deduct 1 seed from mango list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[9][0]){
            //buy apple
            //add 1 seed to apple list
        }
        else if(e.getSource()==actionButtons[9][1]){
            //plant apple
            //deduct 1 seed from apple list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[10][0]){
            //buy banana
            //add 1 seed to banana list
        }
        else if(e.getSource()==actionButtons[10][1]){
            //plant banana
            //deduct 1 seed from banana list
            //start seed growth process
            //set tiles's boolean properties
        }
        else if(e.getSource()==actionButtons[11][0]){
            //buy orange
            //add 1 seed to orange list
        }
        else if(e.getSource()==actionButtons[11][1]){
            //plant orange
            //deduct 1 seed from orange list
            //start seed growth process
            //set tiles's boolean properties
        }
    }
    
    
    public static void main(String[] args){
        SeedMenu s = new SeedMenu();
    }
}
