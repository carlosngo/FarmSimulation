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
    private ArrayList<ArrayList<JButton>> actionButtons;
    private ArrayList<String> names;
    String category;
    JPanel mainPnl;
    private static int row;
    private static int col;

    public TabPanel(String category) {
        row = 0;
        col = 0;
        actionButtons = new ArrayList<>();
        names = new ArrayList<>();
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
        amount.setMinimumSize(new Dimension(85, 36));
        amount.setMaximumSize(new Dimension(85, 36));
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(amount);
        
        JLabel harvestTimeLabel = makeLabel("HT");        
        harvestTimeLabel.setMinimumSize(new Dimension(85, 36));
        harvestTimeLabel.setMaximumSize(new Dimension(85, 36));
        harvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(harvestTimeLabel);
        
        JLabel waterNeededLabel = makeLabel("WN (bl)");        
        waterNeededLabel.setMinimumSize(new Dimension(85, 36));
        waterNeededLabel.setMaximumSize(new Dimension(85, 36));
        waterNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(waterNeededLabel);
        
        JLabel fertilizerNeededLabel = makeLabel("FN (bl)");        
        fertilizerNeededLabel.setMinimumSize(new Dimension(85, 36));
        fertilizerNeededLabel.setMaximumSize(new Dimension(85, 36));
        fertilizerNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(fertilizerNeededLabel);
        
        JLabel harvestCostLabel = makeLabel("HC");        
        harvestCostLabel.setMinimumSize(new Dimension(85, 36));
        harvestCostLabel.setMaximumSize(new Dimension(85, 36));
        harvestCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(harvestCostLabel);
        
        JLabel productsProducedLabel = makeLabel("PP");        
        productsProducedLabel.setMinimumSize(new Dimension(100, 36));
        productsProducedLabel.setMaximumSize(new Dimension(100, 36));
        productsProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(productsProducedLabel);
        
        JLabel seedCostLabel = makeLabel("SC");        
        seedCostLabel.setMinimumSize(new Dimension(85, 36));
        seedCostLabel.setMaximumSize(new Dimension(85, 36));
        seedCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(seedCostLabel);
        
        JLabel basePriceLabel = makeLabel("BP");        
        basePriceLabel.setMinimumSize(new Dimension(85, 36));
        basePriceLabel.setMaximumSize(new Dimension(85, 36));
        basePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPnl.add(basePriceLabel);
        
        JLabel actionLabel = makeLabel("Actions");        
        actionLabel.setMinimumSize(new Dimension(140, 36));
        actionLabel.setMaximumSize(new Dimension(140, 36));
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
    
    public ArrayList<String> getNames() {
        return names;
    }
    
    public void addRow(String name, int quantity, double harvestTime, int waterNeeded,
            int waterMax, int fertilizerNeeded, int fertilizerMax, double harvestCost, 
            int minProducts, int maxProducts, double seedCost, double basePrice) {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        
        JLabel nameLabel = makeLabel(name);
        nameLabel.setMinimumSize(new Dimension(150, 36));
        nameLabel.setMaximumSize(new Dimension(150, 36));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(nameLabel);
        names.add(name);
        
        JLabel amount = makeLabel("" + quantity);
        amount.setMinimumSize(new Dimension(85, 36));
        amount.setMaximumSize(new Dimension(85, 36));
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(amount);
        
        JLabel harvestTimeLabel = makeLabel("" + harvestTime);        
        harvestTimeLabel.setMinimumSize(new Dimension(85, 36));
        harvestTimeLabel.setMaximumSize(new Dimension(85, 36));
        harvestTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(harvestTimeLabel);
        
        JLabel waterNeededLabel = makeLabel(waterNeeded + " (" + waterMax + ")");        
        waterNeededLabel.setMinimumSize(new Dimension(85, 36));
        waterNeededLabel.setMaximumSize(new Dimension(85, 36));
        waterNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(waterNeededLabel);
        
        JLabel fertilizerNeededLabel = makeLabel(fertilizerNeeded + " (" + fertilizerMax + ")");        
        fertilizerNeededLabel.setMinimumSize(new Dimension(85, 36));
        fertilizerNeededLabel.setMaximumSize(new Dimension(85, 36));
        fertilizerNeededLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(fertilizerNeededLabel);
        
        JLabel harvestCostLabel = makeLabel("" + harvestCost);        
        harvestCostLabel.setMinimumSize(new Dimension(85, 36));
        harvestCostLabel.setMaximumSize(new Dimension(85, 36));
        harvestCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(harvestCostLabel);
        
        JLabel productsProducedLabel = makeLabel(minProducts + "-" + maxProducts);        
        productsProducedLabel.setMinimumSize(new Dimension(100, 36));
        productsProducedLabel.setMaximumSize(new Dimension(100, 36));
        productsProducedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(productsProducedLabel);
        
        JLabel seedCostLabel = makeLabel("" + seedCost);        
        seedCostLabel.setMinimumSize(new Dimension(85, 36));
        seedCostLabel.setMaximumSize(new Dimension(85, 36));
        seedCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(seedCostLabel);
        
        JLabel basePriceLabel = makeLabel("" + basePrice);        
        basePriceLabel.setMinimumSize(new Dimension(85, 36));
        basePriceLabel.setMaximumSize(new Dimension(85, 36));
        basePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pnl.add(basePriceLabel);
        
        JPanel actionButtonPnl = new JPanel();
        actionButtonPnl.setLayout(new BoxLayout(actionButtonPnl, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);  
        actionButtonPnl.setBorder(border);
        
        ArrayList<JButton> row = new ArrayList<>();
        
        JButton buy = new JButton("Buy");
        buy.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        row.add(buy);
        actionButtonPnl.add(buy);
       
        JButton plant = new JButton("Plant");
        plant.setFont(new Font("Abril Fatface", Font.PLAIN, 18));
        row.add(plant);
        actionButtonPnl.add(plant);
        
        actionButtons.add(row);
        pnl.add(actionButtonPnl);
        add(pnl);
    }

    public ArrayList<ArrayList<JButton>> getActionButtons() {
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
