package GraphicsTest;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{

    final static Dimension d = Toolkit. getDefaultToolkit(). getScreenSize();
    final static public int width = (int) d.getWidth();
    final static public int height = (int) d.getHeight();
    public JPanel canvas;
    public Graphics g;

    public Frame(){

        canvas = new JPanel();
        canvas.setPreferredSize(d);

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
