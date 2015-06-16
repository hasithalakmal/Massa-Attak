/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.awt.Graphics;

/**
 *
 * @author Mr.Mic
 */
public abstract class Entity {
    protected int x,y,w,h;
    protected boolean removed = false;
    
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    } 
    
    public void draw(Graphics g){
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getW(){return w;}
    public int getH(){return h;}
}
