import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Render extends DrawingPanel{
  private double xCam;
  private double yCam;
  private double zCam;
  private double camAngleX;
  private double camAngleY;
  private double depth;
  private double dx;
  private int width = 852;
  private int height = 480;

  public Render(double fieldOfView, double angleX, double angleY, double[] camCoords){
    super(852, 480);
    xCam = camCoords[0];
    yCam = camCoords[1];
    zCam = camCoords[2];
    camAngleX = toRadians(angleX);
    camAngleY = toRadians(angleY);
    depth = width / (2 * Math.tan(toRadians(fieldOfView)/2));
    dx = 15;
    g2.setPaint(Color.white);
    g2.fillRect(0, 0, 852, 480);
    frame.addKeyListener(new KeyListener(){
      public void keyTyped(KeyEvent e){
          
      }
      public void keyPressed(KeyEvent e){
        switch(e.getKeyChar()){
          case 'w':
            xCam += Math.sin(camAngleX) * dx;
            zCam += Math.cos(camAngleX) * dx;
            break;
          case 'a':
            xCam += Math.sin(camAngleX - Math.PI / 2) * dx;
            zCam += Math.cos(camAngleX - Math.PI / 2) * dx;
            break;
          case 's':
            xCam += Math.sin(camAngleX + Math.PI) * dx;
            zCam += Math.cos(camAngleX + Math.PI) * dx;
            break;
          case 'd':
            xCam += Math.sin(camAngleX + Math.PI / 2) * dx;
            zCam += Math.cos(camAngleX + Math.PI / 2) * dx;
            break;
        }
        
        switch(e.getKeyCode()){
          case KeyEvent.VK_UP:
            addVerticalAngle(5);
            break;
          case KeyEvent.VK_DOWN:
            addVerticalAngle(-5);
            break;
          case KeyEvent.VK_LEFT:
            addHorizontalAngle(-5);
            break;
          case KeyEvent.VK_RIGHT:
            addHorizontalAngle(5);
            break;
          case KeyEvent.VK_SPACE:
            yCam += 10;
            break;
          case KeyEvent.VK_SHIFT:
            yCam -= 10;
            break;
        }
      }

      public void keyReleased(KeyEvent e){
      }
    });
  }

  public Render(double fieldOfView, double[] camCoords){
    this(fieldOfView, 0, 0, camCoords);
  }

  public void addHorizontalAngle(double degrees){
    camAngleX += toRadians(degrees);
  }

  public void addVerticalAngle(double degrees){
    camAngleY += toRadians(degrees);
  }

  public void setPosition(double[] camCoords){
    xCam = camCoords[0];
    yCam = camCoords[1];
    zCam = camCoords[2];
  }

  public int[] drawPoint(double[] point){
    point = toRelative(point);
    //System.out.println(point[0] + " " + point[1] + " " + point[2]);
    if(point[2] > 0){
      double t = depth / point[2];
      int xProj = (int) Math.round(width * 1.0 / 2 + t * (point[0]));
      int yProj = (int) Math.round(height * 1.0 / 2 - t * (point[1]));
      g2.setPaint(Color.BLACK);
      g2.fillRect(xProj, yProj, 1, 1);
      //System.out.println("" + xProj + " " + yProj);
      return new int[]{xProj, yProj};
    } else{
      return new int[]{-1, -1};
    }
  }

  public void drawLine(double x1, double y1, double z1, double x2, double y2, double z2){
    int[] p1 = drawPoint(new double[] {x1, y1, z1});
    int[] p2 = drawPoint(new double[] {x2, y2, z2});
    g2.setPaint(Color.BLACK);
    if((p1[0] != -1 && p1[1] != -1) && (p2[0] != -1 && p2[1] != -1)) g2.draw(new Line2D.Float(p1[0], p1[1], p2[0], p2[1]));
  }

  public void drawTri(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3){
    drawLine(x1, y1, z1, x2, y2, z2);
    drawLine(x2, y2, z2, x3, y3, z3);
    drawLine(x3, y3, z3, x1, y1, z1);
  }

  public void drawCube(double x1, double y1, double z1, double x2, double y2, double z2){
    drawLine(x1, y1, z1, x1, y2, z1);
    drawLine(x1, y1, z1, x2, y1, z1);
    drawLine(x2, y1, z1, x2, y2, z1);
    drawLine(x1, y2, z1, x2, y2, z1);

    drawLine(x1, y1, z1, x1, y1, z2);
    drawLine(x2, y2, z2, x2, y2, z1);
    drawLine(x1, y2, z1, x1, y2, z2);
    drawLine(x2, y1, z1, x2, y1, z2);
    
    drawLine(x1, y1, z2, x1, y2, z2);
    drawLine(x1, y1, z2, x2, y1, z2);
    drawLine(x2, y2, z2, x2, y1, z2);
    drawLine(x2, y2, z2, x1, y2, z2);
  }

  private double toRadians(double degrees){
    return degrees * Math.PI / 180;
  }

  private double toDegrees(double radians){
    return radians * 180 / Math.PI;
  }

  private double[] toRelative(double[] point){
    double x = point[0] - xCam;
    double y = point[1] - yCam;
    double z = point[2] - zCam;
    double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    double horizontalAngle = z >= 0 ? Math.atan(x / z) : Math.PI + Math.atan(x / z);
    double verticalAngle = Math.asin(y / r);
    horizontalAngle -= camAngleX;    
    verticalAngle -= camAngleY;
    z = r * Math.cos(verticalAngle) * Math.cos(horizontalAngle);
    x = r * Math.cos(verticalAngle) * Math.sin(horizontalAngle);
    y = r * Math.sin(verticalAngle);
    return new double[] {x, y, z};
  }
}