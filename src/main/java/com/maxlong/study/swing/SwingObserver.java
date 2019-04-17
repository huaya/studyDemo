package com.maxlong.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2019/4/16 13:53
 * 类说明:
 */
public class SwingObserver {
    JFrame jFrame;

    public static void main(String[] args) {
        SwingObserver observer = new SwingObserver();
        observer.go();
    }

    private void go() {
        jFrame = new JFrame("swing");
        JButton jButton = new JButton("Should I do it!");
        jButton.addActionListener((e) -> System.out.println("Don't do it, you may regret it!"));
        jButton.addActionListener((e) -> System.out.println("Come on, do it!"));
        jButton.setSize(100,50);
        jButton.setVisible(true);
        jFrame.getContentPane().add(BorderLayout.CENTER, jButton);
        jFrame.setVisible(true);
        jFrame.setSize(500, 350);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
