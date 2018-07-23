/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GameGUI implements ActionListener{
    JFrame f;
    JLabel level, type, money;
    JButton watercan, plow, pickaxe, fertilizer, seeds, exit, 
            t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, 
            t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, 
            t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, 
            t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, 
            t41, t42, t43, t44, t45, t46, t47, t48, t49, t50;
    
    public GameGUI(){
        initGameGUI();
    }
    
    public void initGameGUI(){
        f = new JFrame();
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        
        //buttons go here
        //use GridBagLayout
        
        f.add(p);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed (ActionEvent e){
        
    }
}
