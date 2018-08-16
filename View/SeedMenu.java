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

    public JButton getBackButton() {
        return back;
    }
    
    public void setPic(BufferedImage img) {
        pic.setIcon(new ImageIcon(resizeImage(img, 90, 90)));
    }
    
    public void setName(String s) {
        this.name.setText(" Name: " + s);
    }

    public void setHt(String s) {
        ht.setText(" Harvest Time: " + s);
    }

    public void setWn(String s) {
        wn.setText(" Water Needed (max): " + s);
    }

    public void setFn(String s) {
        fn.setText(" Fertilizer Needed (max): " + s);
    }

    public void setSc(String s) {
        sc.setText(" Seed Cost: " + s);
    }

    public void setHc(String s) {
        hc.setText(" Harvest Cost: " + s);
    }

    public void setPp(String s) {
        pp.setText(" Products Produced: " + s);
    }

    public void setBp(String s) {
        bp.setText(" Base Price: " + s);
    }
    
    public SeedMenu(GameGUIController controller) {
        this.controller = controller;
        initSeedMenu();
    }

    public void initSeedMenu() {
        JPanel base = new JPanel();
        base.setLayout(new OverlayLayout(base));
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);        
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        content.setOpaque(false);
        //content.setBackground(new Color(152, 251, 152));
        pic = new JLabel();
        pic.setIcon(new ImageIcon(resizeImage("/seed.png",90,90)));
        Border border = BorderFactory.createLineBorder(new Color(68, 76, 56), 10, true);    
        pic.setBorder(border);
        pic.setPreferredSize(new Dimension(110, 0));
        informationPanel = new JPanel();
        informationPanel.setOpaque(false);
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.X_AXIS));
        JPanel seedInfo = new JPanel();
        seedInfo.setBackground( new Color(152, 251, 152) ); 
        seedInfo.setLayout(new GridLayout(4, 2));
        Border infoBorder = BorderFactory.createLineBorder(new Color(68, 76, 56), 5, true);    
        seedInfo.setBorder(infoBorder);
        
        name = new JLabel(" Name: ");
        name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(name);
        
        ht = new JLabel(" Harvest Time: ");        
        ht.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(ht);
        
        wn = new JLabel(" Water Needed (max): ");        
        wn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(wn);
        
        fn = new JLabel(" Fertilizer Needed (max): ");        
        fn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(fn);
        
        hc = new JLabel(" Harvest Cost: ");        
        hc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(hc);
        
        pp = new JLabel(" Products Produced: ");        
        pp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(pp);
        
        sc = new JLabel(" Seed Cost: ");        
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(sc);
        
        bp = new JLabel(" Base Price: ");        
        bp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        seedInfo.add(bp);
        
        informationPanel.add(pic);
        informationPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        informationPanel.add(seedInfo);
        
        seedPanel = new JPanel();
        //seedPanel.setOpaque(false);
        seedPanel.setBackground( new Color(68, 76, 56) );
        JScrollPane sp = new JScrollPane(seedPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                                                     ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(65, 100));
        sp.setBackground( new Color(68, 76, 56) );
//        sp.setBorder(BorderFactory.createEmptyBorder());
        JPanel titlePnl = new JPanel();
        titlePnl.setMinimumSize(new Dimension(550, 10));
        titlePnl.setMaximumSize(new Dimension(550, 10));
        titlePnl.setBackground( new Color(68, 76, 56) );
        JLabel title = new JLabel("Seed Information");
        //Border border = BorderFactory.createLineBorder(Color.BLACK, 1);    
        //title.setBorder(border);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePnl.add(title);
        
        back = new JButton("Back");
        back.addActionListener(this);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setFont(new Font("Arial", Font.BOLD, 24));
        back.setForeground(Color.white);
        back.setBackground(new Color(0,78,56));
        back.setOpaque(true);
        back.setBorderPainted(false);
        
        content.add(titlePnl);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(informationPanel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(sp);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(back);
        
        content.setAlignmentX(0.5f);
        content.setAlignmentY(0.5f);
        base.add(content);
        
        JLabel seedmenu = new JLabel(new ImageIcon(resizeImage("/seedmenu background.png",600,380)));
        seedmenu.setAlignmentX(0.5f);
        seedmenu.setAlignmentY(0.5f);
        base.add(seedmenu);
        
        setContentPane(base);
//        setSize(1100, 420);
        pack();
        setResizable(false);
        //setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
    }

    public void addSeed(String name) {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(resizeImage(controller.getPlantImage(name), 90, 90)));
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

    public static BufferedImage resizeImage(BufferedImage img, int width, int height) {
        
             BufferedImage rawHolder = img;
             Image raw = rawHolder.getScaledInstance(width, height, Image.SCALE_SMOOTH);
             BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2d = resized.createGraphics();
             g2d.drawImage(raw, 0, 0, null);
             g2d.dispose();
             return resized;
        
    }
    
    public BufferedImage resizeImage(String name, int width, int height) {
        try{
             BufferedImage rawHolder = ImageIO.read(getClass().getResource(name));
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
//    public static void main(String[] args){
//        
//        
//    }
}
