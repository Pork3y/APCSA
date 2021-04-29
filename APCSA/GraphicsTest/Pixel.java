package GraphicsTest;

import java.awt.Color;

public class Pixel {
    private Integer color;
    private double dist;

    public Pixel(Color c){
        this.color = c.getRGB();
        dist = Double.MAX_VALUE;
    }

    public Pixel(){
        color = null;
        dist = Double.MAX_VALUE;
    }

    public Color getColor(){
        return new Color(color);
    }

    public void setColor(Color c){
        this.color = c.getRGB();
    }

    public double getDist(){
        return dist;
    }

    public void setDist(double dist){
        this.dist = dist;
    }
}
