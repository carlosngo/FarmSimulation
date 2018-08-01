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

public class TabPanel {
    private JButton[][] actionButtons;
    String category;
    JPanel mainPnl;
    GridBagConstraints c;
    private static int row;
    private static int col;

    public TabPanel(String category) {
        row = 0;
        col = 0;
        actionButtons = new JButton[12][6];
        this.category = category;
        c = new GridBagConstraints();
        
        mainPnl = new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.Y_AXIS));
        
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
        
        mainPnl.add(firstPnl);
    }
    
    public String getCategory() {
        return category;
    }

    public JPanel getMainPnl() {
        return mainPnl;
    }
    
    public void addRow(String name, int number){
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
        mainPnl.add(pnl);
    }

    public JButton[][] getActionButtons() {
        return actionButtons;
    }
    
    public JLabel makeLabel(String s){
            JLabel label = new JLabel(s);
            label.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);    
            label.setBorder(border);
            return label;
    }
}
