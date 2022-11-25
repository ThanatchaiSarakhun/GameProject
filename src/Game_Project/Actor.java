/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Actor {
    public int x,y,Size,health;
    public int jumpHight = 80;

    public Actor(int x, int y, int Size, int health) {
        this.x = x;
        this.y = y;
        this.Size = Size;
        this.health = health;
    }
    
    public void jump(JPanel game){
        this.y -= jumpHight;
        game.repaint();
        Timer timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y += jumpHight;
                game.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public BufferedImage getImage(){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("image\\Police2.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }  
}
