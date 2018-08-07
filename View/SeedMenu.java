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
        JPanel motherPnl = new JPanel();
        motherPnl.setLayout(new OverlayLayout(motherPnl));
        
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tp = new JTabbedPane();
        tp.setOpaque(false);
        tp.setFont(new Font("Marker Felt", Font.PLAIN, 14));
        tp.setBackground( new Color(152, 251, 152) );

        pVeggie = new TabPanel("Vegetables");
        pVeggie.setOpaque(false);
        tp.add("Vegetables", pVeggie);

        pFlower = new TabPanel("Flowers");
        pFlower.setOpaque(false);
        tp.add("Flowers", pFlower);

        pTree = new TabPanel("Trees");
        pTree.setOpaque(false);
        tp.add("Fruit Tree", pTree);
        
        back = new JButton("Back");
        JButton button = new JButton();
        back.setBackground(new Color(1,121,111));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setFont(new Font("Marker Felt", Font.PLAIN, 18));
        back.setForeground(new Color(208,240,192));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(this);

        JTextArea legend = new JTextArea(3, 50);
        legend.setBackground( new Color(208, 240, 192) );
        legend.setText("Legend:\nSN – seed name; CT – crop type; HT – time to harvest in minutes; WN (bl) – water needed (bonus limit); FN (bl) – "
                + "Fertilizer needed (bonus limit);\nHC – harvest cost; PP – number of products produced; SC – seed cost; BP – base selling"
                + " cost per piece produced");
        legend.setFont(new Font("Marker Felt", Font.BOLD, 14));
        legend.setAlignmentX(Component.CENTER_ALIGNMENT);
        legend.setLineWrap(true);
        legend.setWrapStyleWord(true);
        //legend.setOpaque(false);
        legend.setEditable(false);
        JScrollPane sp = new JScrollPane(legend);
        sp.setBorder(BorderFactory.createEmptyBorder());
        JPanel titlePnl = new JPanel();
        Border border = BorderFactory.createLineBorder(new Color(0,78,56), 2);    
        titlePnl.setBorder(border);
        titlePnl.setBackground( new Color(0, 76, 56) );
        JLabel title = new JLabel("Seed List");
        //title.setForeground(Color.black);
        title.setForeground(Color.white); //new Color(199,234,70)
        title.setFont(new Font("Marker Felt", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePnl.add(title);
        p.add(titlePnl);
        p.add(tp);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(sp);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(back);
        p.setAlignmentX(0.5f);
        p.setAlignmentY(0.5f);
        motherPnl.add(p);
        
        try{
             BufferedImage rawHolder = ImageIO.read(new File("seedmenu background.png"));
             Image raw = rawHolder.getScaledInstance(1100, 420, Image.SCALE_SMOOTH);
             BufferedImage resized = new BufferedImage(1100, 420, BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2d = resized.createGraphics();
             g2d.drawImage(raw, 0, 0, null);
             g2d.dispose();
             JLabel seedMenuBG = new JLabel(new ImageIcon(resized));                   
             seedMenuBG.setAlignmentX(0.5f);
             seedMenuBG.setAlignmentY(0.5f);
             motherPnl.add(seedMenuBG);
             //add(motherPnl);
        }
        catch(IOException e){
             System.out.println("Picture not found.");
        }

        setContentPane(motherPnl);
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
