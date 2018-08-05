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

    private JButton[][] actionButtons;
    private JButton back;
    private JTabbedPane tp;
    private TabPanel pVeggie, pFlower, pTree;
    private GridBagConstraints c = new GridBagConstraints();
    private static int row;
    private static int col;
    private GameGUIController controller;

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

    public SeedMenu(GameGUIController controller) {
        this.controller = controller;
        row = 0;
        col = 0;
        actionButtons = new JButton[12][6];
        initSeedMenu();
    }

    public void initSeedMenu() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tp = new JTabbedPane();
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

        JTextArea legend = new JTextArea(3, 50);
        legend.setText("Legend:\nSN – seed name; CT – crop type; HT – time to harvest in minutes; WN (bl) – water needed (bonus limit); FN (bl) – "
                + "Fertilizer needed (bonus limit);\nHC – harvest cost; PP – number of products produced; SC – seed cost; BP – base selling"
                + " cost per piece produced");
        legend.setFont(new Font("Times New Roman", Font.BOLD, 14));
        legend.setAlignmentX(Component.CENTER_ALIGNMENT);
        legend.setLineWrap(true);
        legend.setWrapStyleWord(true);
        legend.setOpaque(false);
        legend.setEditable(false);
        JScrollPane sp = new JScrollPane(legend);
        sp.setBorder(BorderFactory.createEmptyBorder());
        JLabel title = new JLabel("Seed List");
        title.setFont(new Font("Abril Fatface", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(tp);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(sp);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(back);
        setContentPane(p);
        setSize(1100, 420);
        setResizable(false);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addActionListeners() {
        for (ArrayList<JButton> list : pVeggie.getActionButtons()) {
            for (JButton b : list)
                b.addActionListener(this);
        }
        
        for (ArrayList<JButton> list : pFlower.getActionButtons()) {
            for (JButton b : list)
                b.addActionListener(this);
        }
        
        for (ArrayList<JButton> list : pTree.getActionButtons()) {
            for (JButton b : list)
                b.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            dispose();
        } else {
            TabPanel pnl = (TabPanel) tp.getSelectedComponent();
            for (int i = 0; i < pnl.getActionButtons().size(); i++) {
                for (int j = 0; j < 2; j++) {
                    if (e.getSource().equals(pnl.getActionButtons().get(i).get(j))) {
                          controller.updateSelected((JButton) e.getSource(), pnl.getNames().get(i), 0);
                        
                    }
                }
            }
        }
    }

//    public static void main(String[] args){
//        
//        
//    }
}
