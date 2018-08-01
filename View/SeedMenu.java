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

public class SeedMenu implements ActionListener{
    private JButton[][] actionButtons;
    private JFrame f;
    private JButton back;
    private JTabbedPane tp;

    public SeedMenu() {
        actionButtons = new JButton[12][6];
        initSeedMenu();
    }
    
    public void initSeedMenu(){
        f = new JFrame();
        f.setLayout(new FlowLayout());
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        tp = new JTabbedPane(); 
        tp.setFont(new Font("Abril Fatface", Font.PLAIN, 14));
        
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
    
    public void addPanel(String category){
        TabPanel tab = new TabPanel(category);
        tp.add(category, tab.getMainPnl());
    }
    
    public JLabel makeLabel(String s){
        JLabel label = new JLabel(s);
        label.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);    
        label.setBorder(border);
        return label;
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==back){
            f.dispose();
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
        s.addPanel("flowers");
    }
}
