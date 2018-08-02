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
import javax.swing.border.Border;

public class TabPanel extends JPanel{
    private JButton[][] actionButtons;
    String category;
    JPanel mainPnl;
    private static int row;
    private static int col;

    public TabPanel(String category) {
        row = 0;
        col = 0;
        actionButtons = new JButton[12][6];
        this.category = category;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel firstPnl = new JPanel();
        firstPnl.setLayout(new BoxLayout(firstPnl, BoxLayout.X_AXIS));
        JLabel nameLabel = makeLabel("Name");
        nameLabel.setMinimumSize(new Dimension(150, 36));
        nameLabel.setMaximumSize(new Dimension(150, 36));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(nameLabel);
        JLabel amount = makeLabel("Qty.");
        amount.setMinimumSize(new Dimension(75, 36));
        amount.setMaximumSize(new Dimension(75, 36));
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(amount);
        JLabel action = makeLabel("Actions");        
        action.setMinimumSize(new Dimension(170, 36));
        action.setMaximumSize(new Dimension(170, 36));
        action.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(action);
        add(firstPnl);
    }
    
    public String getCategory() {
        return category;
    }

    public JPanel getMainPnl() {
        return mainPnl;
    }
    
    public void addRow(String name, int number){
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        JLabel nameLabel = makeLabel(name);
        nameLabel.setMinimumSize(new Dimension(150, 36));
        nameLabel.setMaximumSize(new Dimension(150, 36));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(nameLabel);
        JLabel amount = makeLabel("" + number);
        amount.setMinimumSize(new Dimension(75, 36));
        amount.setMaximumSize(new Dimension(75, 36));
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(amount);
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
        row++;
        pnl.add(actionButtonPnl);
        add(pnl);
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
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Test");
        TabPanel tp = new TabPanel("Vegetables");
        tp.addRow("Carrot", 5);
        f.setContentPane(tp);
        f.setSize(500, 500);
        f.pack();
        f.setVisible(true);
    }
}
