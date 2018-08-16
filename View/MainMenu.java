/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author User
 */
public class MainMenu extends JFrame implements ActionListener, DocumentListener {
    GameGUIController controller;
    JTextField nameInput;
    JButton playgame;
    JButton exit;
    String name;

    public MainMenu(GameGUIController controller) {
        this.controller = controller;
        initHomeScreen();
    }
    
    public void initHomeScreen() {
        JPanel motherPnl = new JPanel();
        motherPnl.setLayout(new OverlayLayout(motherPnl));
        
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        
        JLabel title = new JLabel("MyFarm");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Abril Fatface", Font.BOLD, 60));
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        JPanel subP = new JPanel();
        subP.setOpaque(false);
        subP.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        subP.add(nameLabel);
        
        nameInput = new JTextField("" , 20);
        nameInput.addActionListener(this);
        nameInput.getDocument().addDocumentListener(this);
        nameInput.setFont(new Font("Arial", Font.BOLD, 24));
        subP.add(nameInput);
        subP.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(subP);
        //p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        playgame = new JButton("Play Game");
        playgame.setAlignmentX(Component.CENTER_ALIGNMENT);
        playgame.addActionListener(this);
        playgame.setFont(new Font("Arial", Font.BOLD, 24));
        playgame.setForeground(Color.white);
        playgame.setBackground(new Color(0,78,56));
        playgame.setOpaque(true);
        playgame.setBorderPainted(false);
        playgame.setEnabled(false);
        p.add(playgame);
        p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(this);
        exit.setFont(new Font("Arial", Font.PLAIN, 24));
        exit.setForeground(Color.white);
        exit.setBackground(new Color(0,78,56));
        exit.setOpaque(true);
        exit.setBorderPainted(false);
        p.add(exit);
        p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        p.setAlignmentX(0.5f);
        p.setAlignmentY(0.5f);
        motherPnl.add(p);

        try{
             BufferedImage rawHolder = ImageIO.read(getClass().getResource("/farm.png"));
             Image raw = rawHolder.getScaledInstance(550, 300, Image.SCALE_SMOOTH);
             BufferedImage resized = new BufferedImage(550, 300, BufferedImage.TYPE_INT_ARGB);
             Graphics2D g2d = resized.createGraphics();
             g2d.drawImage(raw, 0, 0, null);
             g2d.dispose();
             JLabel farmImg = new JLabel(new ImageIcon(resized));                   
             farmImg.setAlignmentX(0.5f);
             farmImg.setAlignmentY(0.5f);
             motherPnl.add(farmImg);
             add(motherPnl);
        }
        catch(IOException e){
             System.out.println("Picture not found.");
        }

        
        
        add(motherPnl);
        pack();
        setVisible(true);
        setResizable(false);
        //setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed (ActionEvent e){
       
        if(e.getActionCommand().equals("Play Game")){
            controller.initializePlayer(nameInput.getText().trim().split("\\s+")[0]);
            controller.initializeGame();
            dispose();
        }
        else if(e.getActionCommand().equals("Exit")){
            dispose();
        }
    }
    
    public void insertUpdate(DocumentEvent e) { 
        if (nameInput.getText().isEmpty())
            playgame.setEnabled(false);
        else
            playgame.setEnabled(true);
    }
    
    public void removeUpdate(DocumentEvent e) { 
        if (nameInput.getText().isEmpty())
            playgame.setEnabled(false);
        else
            playgame.setEnabled(true);
    }
    
    public void changedUpdate(DocumentEvent e) {
        if (nameInput.getText().isEmpty())
            playgame.setEnabled(false);
        else
            playgame.setEnabled(true);
    }
    
   /* 
   public static void main(String[] args){
        MainMenu m = new MainMenu();
   }
   */
}
