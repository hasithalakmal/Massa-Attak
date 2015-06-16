/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Mr.Mic
 */
public class Stage {

    private Rectangle pf = new Rectangle(0, 540, 800, 20);

    public Stage() {

    }

    public boolean isCollided(Rectangle entity) {
        return pf.intersects(entity);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(pf.x, pf.y, pf.width, pf.height);
    }

}
