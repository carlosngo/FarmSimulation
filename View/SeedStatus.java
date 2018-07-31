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
public class SeedStatus implements ActionListener, DocumentListener{
    //Tile t;
    JFrame f;
    JButton harvest, done;
    
    public SeedStatus(/*Tile t*/) {
        //this.t = t;
        initSeedStatus();
    }
    public void initSeedStatus(){
        f = new JFrame();
        f.setLayout(new FlowLayout());
        
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
        JLabel title = new JLabel("Seed Status");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(title,c);
        
      
        JLabel name = new JLabel();
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        //if(t.getSeed().getName()==null)
        //  name.setText("Name: N/A (tree ground)");
        //else
            name.setText("Name: "/* + t.getSeed().getName()*/);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(name,c);
        
        JLabel time = new JLabel();
        time.setHorizontalAlignment(SwingConstants.LEFT);
        time.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        time.setText("Time Elapsed: "/* + time left*/);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(time,c);
        
        
        harvest = new JButton("Harvest");
        harvest.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        harvest.addActionListener(this);
        harvest.setEnabled(false);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(harvest,c);
        
        done = new JButton("Done");
        done.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        done.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        p.add(done,c);
        
        f.add(p);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource()==harvest){
            //plant is harvested
            //tile becomes unoccupied
            //player's money increases
        }
        else if(e.getSource()==done){
            f.dispose();
        }
    }
    
    public void insertUpdate(DocumentEvent e) { 
        
    }
    
    public void removeUpdate(DocumentEvent e) { 
        
    }
    
    public void changedUpdate(DocumentEvent e) {
       /*if (t.isWithered())
            harvest.setEnabled(false);
        else
            harvest.setEnabled(true);
       */
    }
    
    public static void main(String[] args){
        SeedStatus s = new SeedStatus();
    }
}

