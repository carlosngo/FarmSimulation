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

public class SeedMenu extends JFrame implements ActionListener {
    private JButton back;
    private JPanel informationPanel, seedPanel;
    private GameGUIController controller;
    private JLabel pic, name, ht, wn, fn, sc, hc, pp, bp;

    public void setPic(BufferedImage img) {
        pic.setIcon(new ImageIcon(img));
    }
    
    public void setName(String s) {
        this.name.setText("Name: " + s);
    }

    public void setHt(String s) {
        ht.setText("Harvest Time: " + s);
    }

    public void setWn(String s) {
        wn.setText("Water Needed (max): " + s);
    }

    public void setFn(String s) {
        fn.setText("Fertilizer Needed (max): " + s);
    }

    public void setSc(String s) {
        sc.setText("Seed Cost: " + s);
    }

    public void setHc(String s) {
        hc.setText("Harvest Cost: " + s);
    }

    public void setPp(String s) {
        pp.setText("Products Produced: " + s);
    }

    public void setBp(String s) {
        bp.setText("Base Price: " + s);
    }
    
    public SeedMenu(GameGUIController controller) {
        this.controller = controller;
        initSeedMenu();
    }

    public void initSeedMenu() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);        
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pic = new JLabel();
        pic.setIcon(new ImageIcon("seed.png"));
        pic.setPreferredSize(new Dimension(120, 0));
        informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
        JPanel seedInfo = new JPanel();
        seedInfo.setLayout(new GridLayout(4, 2));
        
        name = new JLabel("Name: ");
        
        seedInfo.add(name);
        
        ht = new JLabel("Harvest Time: ");        
        
        seedInfo.add(ht);
        
        wn = new JLabel("Water Needed (max): ");        
        
        seedInfo.add(wn);
        
        fn = new JLabel("Fertilizer Needed (max): ");        
        
        seedInfo.add(fn);
        
        hc = new JLabel("Harvest Cost: ");        
        
        seedInfo.add(hc);
        
        pp = new JLabel("Products Produced: ");        
        
        seedInfo.add(pp);
        
        sc = new JLabel("Seed Cost: ");        
        
        seedInfo.add(sc);
        
        bp = new JLabel("Base Price: ");        
        
        seedInfo.add(bp);
        
        informationPanel.add(pic);
        informationPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        informationPanel.add(seedInfo);
        
        seedPanel = new JPanel();
        JScrollPane sp = new JScrollPane(seedPanel);
        sp.setPreferredSize(new Dimension(65, 100));
//        sp.setBorder(BorderFactory.createEmptyBorder());
        JLabel title = new JLabel("Seed Information");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        back = new JButton("Back");
        back.addActionListener(this);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(informationPanel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(sp);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(back);
        setContentPane(content);
//        setSize(1100, 420);
        pack();
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
    }

    public void addSeed(String name) {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(controller.getPlantImage(name)));
        btn.setActionCommand(name);
        btn.addActionListener(this);
        seedPanel.add(btn);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
        } else {
            controller.updateSeedInfo(e.getActionCommand());
        }
    }

//    public static void main(String[] args){
//        
//        
//    }
}
