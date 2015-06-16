/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.util.*;

/**
 *
 * @author Mr.Mic
 */
public class EnamyManager {

    private int amount;
    private ArrayList<Enamy> enamies = new ArrayList<Enamy>();
    private My_game1 instance;
    
 //   private boolean c = false;

    public EnamyManager(My_game1 instance, int a) {
        this.amount = a;
        this.instance = instance;
        spawn();
    }

    public void spawn() {
        Random random = new Random();
        int ss = enamies.size();
        if (ss < amount) {
            for (int i = 0; i < amount - ss; i++) {
                enamies.add(new Enamy(instance, random.nextInt(778), random.nextInt(100) + 120));
            }
        } else if (ss > amount) {
            for (int i = 0; i < ss - amount; i++) {
                //enamies.get(0);
                enamies.remove(i);
            }
        }

    }

    public void draw(Graphics g) {
        update();
        for (Enamy e : enamies) {
            e.draw(g);
        }
    }

    private void update() {
        boolean re = false;
        for (int i = 0; i < enamies.size(); i++) {
            if (enamies.get(i).isDead()) {
                enamies.remove(i);
                re = true;
            }
        }
        if (re) {
            spawn();
        }
    }

    public boolean isColliding(Rectangle hitbox) {
       boolean c = false;
        for (int i = 0; i < enamies.size(); i++) {
            if (hitbox.intersects(enamies.get(i).getHitBox()) /* && (instance.getPlayer().getLife()==0)*/ ) {
               // System.out.println("bbbbbbb");
                c = true;
            }
        }
        //System.out.println(c);
        return c;
    }
    
 /*   public void setC(boolean b){
        c = b;
    }*/

}
