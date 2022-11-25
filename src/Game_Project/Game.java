/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Project;

import Game_Project.Display;
import Game_Project.Actor;
import Game_Project.WaveAmmo;
import Game_Project.WaveSnake;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


/**
 *
 * @author Admin
 */
public class Game extends JPanel implements KeyListener{
    boolean readyToFire, shot;
    long lastPress = 0;
    Rectangle bullet;
    int score = 0; 
    int ammoInGame = 10;
    Actor police = new Actor(50,390,100,100);
    WaveSnake[] snake = Snake(3);
    WaveAmmo[] ammo = Ammo(1);
    
    public Game(){
        this.setBounds(0, 0, 1000, 600);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
    }
    
    @Override
    public void paint(Graphics g){
        try {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawBackground(g2);
        g2.setColor(Color.red);
        g2.drawImage(police.getImage(),police.x, police.y, police.Size, police.Size, null);
        if(ammoInGame > 0){
            if(shot){
            g2.setColor(Color.black);
            g2.drawRect(bullet.x, bullet.y, bullet.width, bullet.height);
            }
        }
        g2.setColor(Color.blue);
        g2.setFont(new Font("arial", Font.BOLD, 20));
        g2.drawString(police.health+"%", 100, 100);
        for(WaveSnake snake : snake){
            g2.drawImage(snake.getImage(), snake.x, (snake.y - snake.height), 40, snake.height + 10, null);
            if(police.health<=0){
                    snake.speed=0;
                    police.jumpHight=0;
                    police.health=0;
                    shot=false;
                    g2.setColor(Color.ORANGE);
                    g2.setFont(new Font("arial", Font.BOLD, 50));
                    g2.drawString("Game Over", 550, 200);
                    
                    g2.setFont(new Font("arial", Font.BOLD, 20));
                    g2.drawString("R to RESTART", 620, 240);  
            }
            else{
                snake.speed=70;
            }
            if(WaveSnake.Hit(police, snake)){
                police.health -=10;
            }
        }
        shoot();
        g2.setColor(Color.black);
        for(WaveAmmo ammo : ammo){
            g2.drawImage(ammo.getImage(), ammo.x, ammo.y, ammo.width, ammo.height, null);
            if(police.health<=0){
                ammo.speed=0;
            }
            else{
                ammo.speed=100;
            }
          
            if(WaveAmmo.Hit(police, ammo)){
                ammoInGame+=1;
            }
        }
        g2.setFont(new Font("arial", Font.BOLD, 20));
        g2.drawString("Score -->: "+score, 500, 50);
        g2.setFont(new Font("arial", Font.BOLD, 20));
        g2.drawString("Ammo : "+ammoInGame, 1000, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void drawBackground(Graphics2D g2) throws IOException {
        g2.drawImage(ImageIO.read(new File("image\\mountain.png")), -80, 0, 1500, 500, null);
    }
    
    private WaveSnake[] Snake(int waveNumber) {
        WaveSnake[] snake = new WaveSnake[waveNumber];
        for(int i=0;i<waveNumber;i++){
            double waveLocation = 1300+(Math.random()*1000);
            snake[i] = new WaveSnake((int)waveLocation,480,70,65,100,this);
        }
        return snake;
    }
    
    private WaveAmmo[] Ammo(int waveNumber) {
        WaveAmmo[] ammo = new WaveAmmo[waveNumber];
        for(int i=0;i<waveNumber;i++){
            double waveLocation = 1000+Math.floor(Math.random()*500);
            ammo[i] = new WaveAmmo((int)waveLocation,280,70,65,100,this);
        }
        return ammo;
    }
    

    public void shoot(){
        if(shot)
            bullet.y-=70;
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(System.currentTimeMillis()- lastPress>330){
            if(e.getKeyCode() == KeyEvent.VK_W){
                police.jump(this);
                this.repaint();
            }
            lastPress = System.currentTimeMillis();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(bullet == null)
                readyToFire = true;
            if(readyToFire){
                bullet = new Rectangle(90,390,3,15);
                shot = true;
                if(ammoInGame > 0){
                    ammoInGame--;
                    score+=1;
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            score = 0;
            ammoInGame = 10;
            police.jumpHight=80;
            police.health=100;
            shot=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            readyToFire = false;
            if(bullet.y <= -10){
                bullet = new Rectangle(0,0,0,0);
                shot = false;
                readyToFire = true;
            }
        }      
    }
    
    public static void main(String[] args) {
        Display display = new Display();
    }
}