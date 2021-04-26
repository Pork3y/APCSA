package GraphicsTest;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements GLEventListener {

    final static Dimension d = Toolkit. getDefaultToolkit(). getScreenSize();
    final static public int width = (int) d.getWidth();
    final static public int height = (int) d.getHeight();
    public GLCanvas canvas;
    public Graphics g;

    public Frame(int width, int height){
        GLProfile profile = GLProfile.get(GLProfile.GL4);
        GLCapabilities c = new GLCapabilities(profile);

        canvas = new GLCanvas();
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

    @Override
    public void init(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        gl.glClearColor(0.392f, 0.584f, 0.929f, 1.0f);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
