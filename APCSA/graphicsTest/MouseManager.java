package graphicsTest;

import java.awt.*;

public class MouseManager implements Runnable{

    public int mousePosX;
    public int mousePosY;

    public void run(){
            Point p = MouseInfo.getPointerInfo().getLocation();
            mousePosX = p.x;
            mousePosY = p.y;
            try {
                // These coordinates are screen coordinates
                int xCoord = 1920 / 2;
                int yCoord = 1080 / 2;

                // Move the cursor
                Robot robot = new Robot();
                robot.mouseMove(xCoord, yCoord);
            } catch (AWTException e) {
            }
    }
}
