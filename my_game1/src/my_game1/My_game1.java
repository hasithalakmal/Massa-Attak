/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Mr.Mic
 */
//doge == My_game1
public class My_game1 extends JPanel implements KeyListener {

    private int x = 290, y = 470;

    private Player player;
    private Stage stage;
    // private Enamy enamy;
     EnamyManager manager;
    private boolean isGameOver = false;
    private boolean pose_flag = false, restart_flag = false, restart_flag2 = false, lose=false;
    private Timer timer;
    private long init;
    private int time;
    private MakeSound ms;

    //put image to background
    private BufferedImage image;
   // private String score;

    //private String sc= "wada";
    public My_game1() {
        setSize(new Dimension(600, 500));
        setPreferredSize(new Dimension(600, 500));
        //setBackground(Color.green);
        setFocusable(true);
        addKeyListener(this);

        stage = new Stage();
        player = new Player(this, x, y);
        timer = new Timer();
        init = timer.initTime();
        ms = new MakeSound();
        //level manage
        manager = new EnamyManager(this, 7);
        // enamy = new  Enamy(this, 20, 120);

        try {
            image = ImageIO.read(new File("img\\bg4n.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public EnamyManager getManager() {
        return manager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameOver(boolean flag) {
        isGameOver = flag;
    }

    @Override
    public void paint(Graphics g) {
        // setIsWin();
        //put baground image
        super.paintComponent(g);
        g.drawImage(image, 0, 100, null);

        g.setColor(Color.black);
        // g.setFont(CENTER_ALIGNMENT);
        g.setFont(new Font("Aachen BT", Font.BOLD, 24));
        g.drawString("Welcome to Massa Attack", 150, 20);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Aachen BT", Font.ITALIC, 16));
        g.drawString("Score : " + player.getScore(), 30, 60);
        g.setColor(Color.red);
        g.drawString("Lifes : " + player.getLife(), 200, 60);

        if (restart_flag && !restart_flag2) {
            g.setFont(new Font("Aachen BT", Font.BOLD, 16));
            g.drawString("Clik Enter to Restart or Click ESC to Continue", 120, 300);

        } else if (restart_flag && restart_flag2) {
            restart_flag = false;
            restart_flag2 = false;
            player.draw(g);
            manager.draw(g);
        } else if (!isGameOver && !pose_flag) {
            time = Integer.parseInt(timer.timer(init, 60));
            g.setColor(Color.red);
            g.drawString("Timer : " + time, 350, 60);
            player.draw(g);
            manager.draw(g);
        } else if (!isGameOver && pose_flag) {
            g.setFont(new Font("Aachen BT", Font.BOLD, 24));
            g.drawString("pause!!!", 250, 300);
            g.setFont(new Font("Aachen BT", Font.BOLD, 16));
            g.drawString("Again Click ESC to Continue", 200, 340);
        } else if (isGameOver) {
            time = Integer.parseInt(timer.timer(init, 60));

            if (time > 0 && !lose) {
                g.setFont(new Font("Aachen BT", Font.BOLD, 24));
                g.drawString("You are lose!!!", 250, 300);
                lose = true;
                ms.playSound("sounds\\lose.wav");
            } else if(lose){
                g.setFont(new Font("Aachen BT", Font.BOLD, 24));
                g.drawString("You are lose!!!", 250, 300);
            }else if(time < 0 &&  !lose){
                 ms.playSound("sounds\\win.wav");
                g.setFont(new Font("Aachen BT", Font.BOLD, 24));
                g.drawString("You are win!!!", 250, 300);
            }
            
            

        }

        //  enamy.draw(g);
        g.dispose();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        My_game1 game = new My_game1();
        //create frame
        JFrame frame = new JFrame();
        frame.setTitle("Massa Attak");
        // frame.setSize(new Dimension(500,400));
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //key lestner interface abstact methodes
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 

    }

    public Stage getStage() {
        return stage;
    }

    public MakeSound getSound() {
        return ms;
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); 
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_UP) {
            if (player.getY() >= 120) {
                player.setYD(-1);
            } else {
                player.setYD(0);
            }
        }
        if (c == KeyEvent.VK_DOWN) {
            if (player.getY() < 480) {
                player.setYD(1);
            } else {
                player.setYD(0);
            }
        }
        if (c == KeyEvent.VK_RIGHT) {
            if (player.getX() < 600) {
                player.setXD(1);
            } else {
                player.setXD(0);
            }
        }
        if (c == KeyEvent.VK_LEFT) {
            if (player.getX() > 0) {
                player.setXD(-1);
            } else {
                player.setXD(0);
            }
        }

        if (c == KeyEvent.VK_ESCAPE) {
            if (restart_flag) {
                restart_flag = false;

            } else {
                if (pose_flag) {
                    pose_flag = false;
                    timer.setPause_time();

                } else {
                    pose_flag = true;
                    timer.setPause_init();
                }
            }
        }
        if (c == KeyEvent.VK_R) {
            restart_flag = true;
            //   player.setLife(5);
            //   player.setscore("");
        /*    if(isGameOver){
             restart_flag2 = true;
             player.setLife(5);
             player.setscore("");
             init = timer.initTime();
             }
             */
        }
        if (c == KeyEvent.VK_ENTER) {
            if (restart_flag) {
                restart_flag2 = true;
                player.setLife(5);
                player.setscore("");
                init = timer.initTime();
            }
        }
        if (c == KeyEvent.VK_N) {
            if (isGameOver) {
                player.setLife(5);
                player.setscore("");
                init = timer.initTime();
                isGameOver=false;
                pose_flag =false;
                lose =false;
                restart_flag =false;
                manager = new EnamyManager(this, 7);
                 
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
        player.setXD(0);
        player.setYD(0);
    }

}
