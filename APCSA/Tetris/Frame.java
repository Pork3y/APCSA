package Tetris;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    public JPanel canvas;
    public Graphics g;

    public Frame(int width, int height){

        canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(width, height));

        Container cp = getContentPane();
        cp.add(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Tetris");
        setVisible(true);
        setResizable(false);
        g = canvas.getGraphics();
    }
}
