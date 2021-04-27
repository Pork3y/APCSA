package GraphicsTest;

import java.awt.*;

public class ImageDrawer implements Runnable{

    Render r;
    Graphics g;

    public ImageDrawer(Render r){
        this.r = r;
        g = r.buffer;
    }
    public void run(){
        for(int i = 0; i < Frame.width; i++){
            for(int j = 0; j < Frame.height; j++){
                if(r.zBuffer[j][i].getColor() != null){
                    g.setColor(r.zBuffer[j][i].getColor());
                    g.drawRect(i, j ,1, 1);
                }
            }
        }
    }
}
