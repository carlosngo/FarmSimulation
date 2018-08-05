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
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("MyFarm");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Abril Fatface", Font.PLAIN, 36));
        p.add(title);
        p.add(Box.createRigidArea(new Dimension(0,25))); // add space
        
        JPanel subP = new JPanel();
        subP.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        subP.add(nameLabel);
        
        nameInput = new JTextField("" , 20);
        nameInput.addActionListener(this);
        nameInput.getDocument().addDocumentListener(this);
        nameInput.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        subP.add(nameInput);
        subP.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(subP);
        p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        playgame = new JButton("Play Game");
        playgame.setAlignmentX(Component.CENTER_ALIGNMENT);
        playgame.addActionListener(this);
        playgame.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        playgame.setEnabled(false);
        p.add(playgame);
        p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        exit = new JButton("Exit");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(this);
        exit.setFont(new Font("Abril Fatface", Font.PLAIN, 24));
        p.add(exit);
        p.add(Box.createRigidArea(new Dimension(0,7))); // add space
        
        add(p);
        pack();
        setVisible(true);
        setResizable(false);
//        setUndecorated(true);
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
    
//    public static void main(String[] args){
//        MainMenu m = new MainMenu();
//    }
}
