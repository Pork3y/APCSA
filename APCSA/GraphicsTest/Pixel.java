package GraphicsTest;

import java.awt.Color;

public class Pixel {
    private Color c;
    private double dist;

    public Pixel(Color c){
        this.c = c;
        dist = Double.MAX_VALUE;
    }

    public Pixel(){
        c = null;
        dist = Double.MAX_VALUE;
    }

    public Color getColor(){
        return c;
    }

    public void setColor(Color c){
        this.c = c;
    }

    public double getDist(){
        return dist;
    }

    public void setDist(double dist){
        this.dist = dist;
    }
}
