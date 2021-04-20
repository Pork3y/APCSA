package GraphicsTest;

import java.awt.*;

public class MouseManager implements Runnable{

    public int mousePosX;
    public int mousePosY;
    Robot robot;
    int xCoord = Render.width / 2;
    int yCoord = Render.height / 2;

    public MouseManager() throws AWTException {
        robot = new Robot();
        robot.mouseMove(xCoord, yCoord);
    }

    public void run(){
            Point p = MouseInfo.getPointerInfo().getLocation();
            mousePosX = p.x;
            mousePosY = p.y;
        // These coordinates are screen coordinates
        int xCoord = Render.width / 2;
        int yCoord = Render.height / 2;

        // Move the cursor
        robot.mouseMove(xCoord, yCoord);
    }
}
