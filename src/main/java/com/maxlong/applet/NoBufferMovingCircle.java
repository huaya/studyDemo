package com.maxlong.applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JApplet;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年8月1日 下午2:57:21
 * 类说明
 */
public class NoBufferMovingCircle extends JApplet implements Runnable {
    Image screenImage = null;
    Thread thread;
    int x = 5;
    int move = 1;

    public void init() {
        screenImage = createImage(230, 160);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        try {
            while (true)
            {
                x += move;
                if ((x > 105) || (x < 5))
                    move *= -1;
                repaint();
                Thread.sleep(10);
            }
        } catch (Exception e) {
        }
    }

    public void drawCircle(Graphics gc) {
        Graphics2D g = (Graphics2D) gc;
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 200, 100);
        g.setColor(Color.red);
        g.fillOval(x, 5, 90, 90);
    }

    public void paint(Graphics g) {             //画一个圆
        g.setColor(Color.white);                    //这里没有缓冲
        g.fillRect(0, 0, 200, 100);
        drawCircle(g);
    }
}  