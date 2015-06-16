/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_game1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Mr.Mic
 */
public class Timer {

    private long x;
    private String dateFormat;
    private int pause_time = 0, pause_init;

    public boolean isWait(int z) {

        int y = 0;
        x = (int) (System.currentTimeMillis() / 1000);

        while (y < z) {
            y = (int) ((System.currentTimeMillis() / 1000) - x);
            System.out.println(y);
        }
        return true;

    }

    public long initTime() {
        x = (int) (System.currentTimeMillis() / 1000);
        return x;
    }

    public void setPause_init() {
        pause_init = (int) (System.currentTimeMillis() / 1000);

    }

    public String timer(long init, int length) {
        int y = length + pause_time - (int) ((System.currentTimeMillis() / 1000) - init);
        String time = Integer.toString(y);
        return time;
    }

    public void setPause_time() {
        pause_time =(int) ((System.currentTimeMillis() / 1000) - pause_init);
    }
    
    
    

    /*   public static void main(String[] args) {
     Timer t = new Timer();
     long p = t.initTime();
     String x = t.timer(p, 5);
     System.out.println(x);
     }*/
}
