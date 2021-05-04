package Tetris;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Brick {

    BufferedImage sprite = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);

    public Brick(Color c){

        BufferedImage texture;
        try{
            texture = ImageIO.read(new File("APCSA/Tetris/brick.png"));
        } catch(IOException e){
            System.out.println("Invalid file name");
            return;
        }

        Graphics g = sprite.getGraphics();
        for(int i = 0; i < texture.getWidth(); i++){
            for(int j = 0; j < texture.getHeight(); j++){
                Color pixCol = new Color(texture.getRGB(i, j));
                int red = c.getRed() * pixCol.getRed() / 255;
                int green = c.getGreen() * pixCol.getGreen() / 255;
                int blue = c.getBlue() * pixCol.getBlue() / 255;
                Color newCol = new Color(red, green, blue);
                g.setColor(newCol);
                g.fillRect(i * 16, j * 16, 16, 16);
            }
        }
    }

    private int bound(int x){
        return Math.max(0, Math.min(255, x));
    }
}
