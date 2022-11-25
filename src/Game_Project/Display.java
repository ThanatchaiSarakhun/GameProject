/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Project;

import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class Display extends JFrame{
    
    public Display(){
        super("GameProject");
        this.setSize(1300,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(new Game());
    }
}
