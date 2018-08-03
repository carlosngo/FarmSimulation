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
        
        JLabel harvestTimeLabel = makeLabel("Growth Time");        
        harvestTimeLabel.setMinimumSize(new Dimension(170, 36));
        harvestTimeLabel.setMaximumSize(new Dimension(170, 36));
        harvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(harvestTimeLabel);
        
        JLabel waterNeededLabel = makeLabel("Water Needed");        
        waterNeededLabel.setMinimumSize(new Dimension(170, 36));
        waterNeededLabel.setMaximumSize(new Dimension(170, 36));
        waterNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(waterNeededLabel);
        
        JLabel fertilizerNeededLabel = makeLabel("Fertilizer Needed");        
        fertilizerNeededLabel.setMinimumSize(new Dimension(170, 36));
        fertilizerNeededLabel.setMaximumSize(new Dimension(170, 36));
        fertilizerNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(fertilizerNeededLabel);
        
        JLabel harvestCostLabel = makeLabel("Harvest Cost");        
        harvestCostLabel.setMinimumSize(new Dimension(170, 36));
        harvestCostLabel.setMaximumSize(new Dimension(170, 36));
        harvestCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(harvestCostLabel);
        
        JLabel productsProducedLabel = makeLabel("Products Produced");        
        productsProducedLabel.setMinimumSize(new Dimension(170, 36));
        productsProducedLabel.setMaximumSize(new Dimension(170, 36));
        productsProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(productsProducedLabel);
        
        JLabel seedCostLabel = makeLabel("Seed Cost");        
        seedCostLabel.setMinimumSize(new Dimension(170, 36));
        seedCostLabel.setMaximumSize(new Dimension(170, 36));
        seedCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(seedCostLabel);
        
        JLabel basePriceLabel = makeLabel("Base Price");        
        basePriceLabel.setMinimumSize(new Dimension(170, 36));
        basePriceLabel.setMaximumSize(new Dimension(170, 36));
        basePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(basePriceLabel);
        
        JLabel actionLabel = makeLabel("Actions");        
        actionLabel.setMinimumSize(new Dimension(170, 36));
        actionLabel.setMaximumSize(new Dimension(170, 36));
        actionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(actionLabel);
        
        add(firstPnl);
    }
    
    public String getCategory() {
        return category;
    }

    public JPanel getMainPnl() {
        return mainPnl;
    }
    
    public void addRow(String name, int number, double harvestTime, String waterNeeded, String fertilizerNeeded, int harvestCost, String productsProduced, int seedCost, double basePrice){
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
        
        JLabel harvestTimeLabel = makeLabel("" + harvestTime);        
        harvestTimeLabel.setMinimumSize(new Dimension(170, 36));
        harvestTimeLabel.setMaximumSize(new Dimension(170, 36));
        harvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(harvestTimeLabel);
        
        JLabel waterNeededLabel = makeLabel(waterNeeded);        
        waterNeededLabel.setMinimumSize(new Dimension(170, 36));
        waterNeededLabel.setMaximumSize(new Dimension(170, 36));
        waterNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(waterNeededLabel);
        
        JLabel fertilizerNeededLabel = makeLabel(fertilizerNeeded);        
        fertilizerNeededLabel.setMinimumSize(new Dimension(170, 36));
        fertilizerNeededLabel.setMaximumSize(new Dimension(170, 36));
        fertilizerNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(fertilizerNeededLabel);
        
        JLabel harvestCostLabel = makeLabel("" + harvestCost);        
        harvestCostLabel.setMinimumSize(new Dimension(170, 36));
        harvestCostLabel.setMaximumSize(new Dimension(170, 36));
        harvestCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(harvestCostLabel);
        
        JLabel productsProducedLabel = makeLabel(productsProduced);        
        productsProducedLabel.setMinimumSize(new Dimension(170, 36));
        productsProducedLabel.setMaximumSize(new Dimension(170, 36));
        productsProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(productsProducedLabel);
        
        JLabel seedCostLabel = makeLabel("" + seedCost);        
        seedCostLabel.setMinimumSize(new Dimension(170, 36));
        seedCostLabel.setMaximumSize(new Dimension(170, 36));
        seedCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(seedCostLabel);
        
        JLabel basePriceLabel = makeLabel("" + basePrice);        
        basePriceLabel.setMinimumSize(new Dimension(170, 36));
        basePriceLabel.setMaximumSize(new Dimension(170, 36));
        basePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(basePriceLabel);
        
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
        tp.addRow("Carrot", 7, 1.5, "1(2)", "0(1)", 1, "1-2", 10, 9);
        f.setContentPane(tp);
        f.setSize(500, 500);
        f.pack();
        f.setVisible(true);
    }
}
