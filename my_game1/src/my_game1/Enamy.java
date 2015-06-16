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
public class Enamy extends Entity{
    
    private Rectangle hitbox;
    private boolean dead=false;
    private int ix,iy;
    private My_game1 instance;
    private BufferedImage image;

    public Enamy(My_game1 instance, int x, int y) {
        super(x, y);
        this.instance = instance;
        
        //rectangles as enamy
        hitbox = new Rectangle(x,y,20,32);
        
         try {
            image = ImageIO.read(new File("img\\fly2n.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        
        ix=0;
        iy=1;
    }
    
    private void move(){
        if(instance.getStage().isCollided(hitbox) /*&& (instance.getPlayer().getLife()==0)*/){
            iy=0;
            dead = true;
        }
        hitbox.x+=ix;
        hitbox.y+=iy;
    }
    
    public boolean isDead(){return dead;}
    public Rectangle getHitBox(){return hitbox;}
    
    @Override
    public void draw(Graphics g){
        move();
        
      /*  //rectangles as enamy
        g.setColor(Color.CYAN);
        g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height); */
        
        g.drawImage(image, hitbox.x, hitbox.y, null);
    }
    
}
