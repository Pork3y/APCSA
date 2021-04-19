package GraphicsTest;

import java.awt.Color;

public class Pixel {
    Color c;
    double dist;

    public Pixel(Color c){
        this.c = c;
        dist = Double.MAX_VALUE;
    }

    public Pixel(){
        c = null;
        dist = Double.MAX_VALUE;
    }

    public void setColor(Color c){
        this.c = c;
    }

    public void setDist(double dist){
        this.dist = dist;
    }
}
