package GraphicsTest;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDrawer implements Runnable{

    Render r;
    BufferedImage nextFrame;

    public ImageDrawer(Render r){
        this.r = r;
        nextFrame = r.nextFrame;
    }
    public void run(){
        nextFrame.setRGB(0, 0, Frame.width, Frame.height, r.bufferRGB, 0, Frame.width);
        for(int i = 0; i < Frame.width; i++){
            for(int j = 0; j < Frame.height; j++){
                r.zBuffer[j][i] = Double.MAX_VALUE;
                r.bufferRGB[j * Frame.width + i] = Render.skyColor.getRGB();
            }
        }
    }
}
