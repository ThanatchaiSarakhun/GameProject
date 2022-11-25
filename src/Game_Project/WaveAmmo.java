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
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class WaveAmmo {
    public int x,y,width,height,speed,xStart;
    
    public WaveAmmo(int x, int y, int w, int h, int speed, JPanel game) {
        this.x = x;
        this.xStart = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.speed = speed;
        move(game);
    }
    
    public void move(JPanel game) {
        Thread gameThread = new Thread() {
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(100);
                    x -= speed;
                    game.repaint();
                    if(x<0){
                    x = xStart;
                    }
                } catch (InterruptedException ex) {
                  
                }
            }
        }
    };
        gameThread.start();
    }
    
    public static boolean Hit(Actor police, WaveAmmo ammo){
        if(police.x + police.Size > ammo.x && police.x < ammo.x){
            if(police.y - police.Size < ammo.y - ammo.height){
                return true;
            }
        }
        return false;
    }
    
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("image\\Ammo.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
