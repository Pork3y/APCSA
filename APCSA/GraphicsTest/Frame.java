package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{

    protected JPanel canvas;
    protected Graphics g;

    public Frame(int width, int height){
        canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(width, height));
        Container cp = getContentPane();
        cp.add(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Graphics");
        setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        g = canvas.getGraphics();
    }

}
