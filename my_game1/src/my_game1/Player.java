/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mr.Mic
 */
public class Player extends Entity {

    private int xd, yd;
    private My_game1 instance;
    private int life = 5;
    private Rectangle hitbox;
    private BufferedImage image;
    private String star ;

    public Player(My_game1 instance, int x, int y) {
        super(x, y);
        this.instance = instance;
        w = 15;
        h = 20;
        star ="*";
        //test
        hitbox = new Rectangle(x, y, w, h);

        //put player to image
        try {
            image = ImageIO.read(new File("img\\ply3n.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        /*  if(!instance.getStage().isCollided(hitbox)){
         yd =1;
         }else{
         yd=0;
         }*/

        /*   //players ball
         g.setColor(Color.orange);
         g.fillOval(hitbox.x, hitbox.y, hitbox.width, hitbox.height); */
        //player image
        //   super.paintComponent(g);
        g.drawImage(image, hitbox.x, hitbox.y, null);

        //g.fillOval(x, y, w, h);
        /*  g.setColor(Color.black);
         g.drawString("life : " + life, 250, 60); */
        star = star +"*";
    }

    public String getScore(){
        
        int x = star.length()*10*(life+1);
        String score = Integer.toString(x);
        return score;
    }
    public void move() {
        /*   x += xd;
         y += yd;

         if (x < 0) {
         x = 0;
         }
         if (x > 590) {
         x = 590;
         }
         if (y < 80) {
         y = 80;
         }
         if (y > 490) {
         y = 490;
         }
         */

        hitbox.x += xd;
        hitbox.y += yd;

        if (hitbox.x < 0) {
            hitbox.x = 0;
        }
        if (hitbox.x > 590) {
            hitbox.x = 590;
        }
        if (hitbox.y < 80) {
            hitbox.y = 80;
        }
        if (hitbox.y > 490) {
            hitbox.y = 490;
        }

        if (instance.getManager().isColliding(hitbox)) {
            if (life > 0) {
                life -= 1;
                instance.getSound().playSound("sounds\\fail.wav");
                 hitbox.x = 800/2 - w/2;
                 //hitbox.x = 290;
                 y=390;
               // instance.setGameOver(false);
              //  instance.getManager().setC(false);
              //  System.out.println("touch = "+life);
                 instance.manager= new EnamyManager(instance, 7);

            } else {
                instance.setGameOver(true);
            }
        }

        //  hitbox.x += xd;
        //   hitbox.y += yd;
    }

    public void setXD(int val) {
        xd = val;
    }

    public void setYD(int val) {
        //  yd = val;
        yd = 0;
    }
    
    public String getLife(){
        String lf = Integer.toString(life);
        return lf;
    }
    
    public void setLife(int x){
       this.life = x;
        
    }
    public void setscore(String x){
       this.star =x;
    }

    /*   public String myx() {
     String q = Integer.toString(x);
     return q;

     }
     */
}
