package Tetris;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Frame f = new Frame(1920, 1080);
        f.g.setColor(Color.BLACK);
        f.g.fillRect(0, 0, 1920, 1080);
        for(double i = 0; i < 999; i += Math.PI / 480){
            Brick b = new Brick(new Color((int) (255 * ((Math.sin(i) + 1) / 2)), (int) (255 * ((Math.sin(i + Math.PI / 3) + 1) / 2)), (int) (255 * ((Math.sin(i + 2 * Math.PI / 3) + 1) / 2))));
            f.g.drawImage(b.sprite, (int) (1920/2 - 64 + 270 * Math.cos(-i)), (int) (1080/2 - 64 + 270 * Math.sin(-i)), f);
            Thread.sleep(2);
        }
    }
}
