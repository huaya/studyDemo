package com.maxlong.applet;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年8月1日 下午2:59:07
 * 类说明
 */
public class BufferMovingCircle extends NoBufferMovingCircle {
    Graphics doubleBuffer = null;               //缓冲区

    public void init() {
        super.init();
        doubleBuffer = screenImage.getGraphics();
    }

    public void paint(Graphics g) { //使用缓冲区，优化原有的paint()方法
        doubleBuffer.setColor(Color.white); //先在内存中画图
        doubleBuffer.fillRect(0, 0, 200, 100);
        drawCircle(doubleBuffer);
        g.drawImage(screenImage, 0, 0, this);   //将buffer一次性显示出来
    }
}  