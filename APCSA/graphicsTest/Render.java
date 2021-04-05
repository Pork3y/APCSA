package graphicsTest;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Render extends DrawingPanel{
  public ArrayList<Model> environment = new ArrayList<>();
  private MouseManager m = new MouseManager();
  private double xCam;
  private double yCam;
  private double zCam;
  private double camAngleX;
  private double camAngleY;
  private double camRoll = 0;
  private double depth;
  final private double dx = 0.25;
  final static private int width = 1920;
  final static private int height = 1080;
  private boolean forwardMove, leftMove, rightMove, backMove, upMove, downMove;
  final Color skyColor = new Color(148, 222, 255);

  public Render(double fieldOfView, double angleX, double angleY, double[] camCoords){
    super(width, height);
    xCam = camCoords[0];
    yCam = camCoords[1];
    zCam = camCoords[2];
    camAngleX = toRadians(angleX);
    camAngleY = toRadians(angleY);
    depth = width / (2 * Math.tan(toRadians(fieldOfView)/2));
    g2.setPaint(skyColor);
    g2.fillRect(0, 0, 852, 480);
    KeyListener k = new KeyListener() {
      public void keyTyped(KeyEvent e) {

      }

      public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
          case 'w':
            forwardMove = true;
            break;
          case 'a':
            leftMove = true;
            break;
          case 's':
            backMove = true;
            break;
          case 'd':
            rightMove = true;
            break;
        }

        switch (e.getKeyCode()) {
          case KeyEvent.VK_SPACE:
            upMove = true;
            break;
          case KeyEvent.VK_SHIFT:
            downMove = true;
            break;
          case KeyEvent.VK_ESCAPE:
            System.exit(0);
        }
      }

      public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
          case 'w':
            forwardMove = false;
            break;
          case 'a':
            leftMove = false;
            break;
          case 's':
            backMove = false;
            break;
          case 'd':
            rightMove = false;
            break;
        }
        switch (e.getKeyCode()) {
          case KeyEvent.VK_SPACE:
            upMove = false;
            break;
          case KeyEvent.VK_SHIFT:
            downMove = false;
            break;
        }
      }
    };

    frame.addKeyListener(k);
    frame.setCursor( frame.getToolkit().createCustomCursor(
            new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
            new Point(),
            null ) );
  }

  public Render(double fieldOfView, double[] camCoords){
    this(fieldOfView, 0, 0, camCoords);
  }

  public void refresh() throws InterruptedException{
    m.run();
    if(forwardMove) {
      xCam += sin(camAngleX) * dx;
      zCam += cos(camAngleX) * dx;
    }
    if(leftMove){
      xCam += sin(camAngleX - Math.PI / 2) * dx;
      zCam += cos(camAngleX - Math.PI / 2) * dx;
    }
    if(rightMove){
      xCam += sin(camAngleX + Math.PI / 2) * dx;
      zCam += cos(camAngleX + Math.PI / 2) * dx;
    }
    if(backMove){
      xCam += sin(camAngleX + Math.PI) * dx;
      zCam += cos(camAngleX + Math.PI) * dx;
    }
    if(upMove){
      yCam += 0.5;
    }
    if(downMove){
      yCam -= 0.5;
    }
    addHorizontalAngle(35 * Math.tan((m.mousePosX - width * 1.0 / 2) / depth));
    addVerticalAngle(35 * Math.tan((m.mousePosY - height * 1.0 / 2) / depth));

    for(Model m : environment){
      drawModel(m);
    }
    Thread.sleep(50);
    g2.setPaint(skyColor);
    g2.fillRect(0, 0, getWidth(), getHeight());
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
      //g2.fillRect(xProj, yProj, 1, 1);
      //System.out.println("" + xProj + " " + yProj);
      return new int[]{xProj, yProj};
    } else{
      return new int[]{-1, -1};
    }
  }

  public int[] drawPoint(Point3D p1){
    return drawPoint(p1.getCoords());
  }

  public void drawLine(double x1, double y1, double z1, double x2, double y2, double z2){
    int[] p1 = drawPoint(new double[] {x1, y1, z1});
    int[] p2 = drawPoint(new double[] {x2, y2, z2});
    g2.setPaint(Color.BLACK);
    if((p1[0] != -1 && p1[1] != -1) && (p2[0] != -1 && p2[1] != -1)) g2.draw(new Line2D.Float(p1[0], p1[1], p2[0], p2[1]));
  }

  public void drawLine(Point3D p13d, Point3D p23d){
    int[] p1 = drawPoint(p13d);
    int[] p2 = drawPoint(p23d);
    g2.setPaint(Color.BLACK);
    if((p1[0] != -1 && p1[1] != -1) && (p2[0] != -1 && p2[1] != -1)) g2.draw(new Line2D.Float(p1[0], p1[1], p2[0], p2[1]));
  }

  public void drawTri(Tri t){
    Point3D p13d = t.getPoint(0);
    Point3D p23d = t.getPoint(1);
    Point3D p33d = t.getPoint(2);

    Vector v1 = new Vector(p13d, p23d);
    Vector v2 = new Vector(p33d, p23d);
    Vector normal = v1.crossProduct(v2);

    int[] p1 = drawPoint(p13d);
    int[] p2 = drawPoint(p23d);
    int[] p3 = drawPoint(p33d);
    Polygon p = new Polygon(new int[]{p1[0], p2[0], p3[0]}, new int[]{p1[1], p2[1], p3[1]}, 3);
    double darken = (1 - Math.sin(normal.theta())) / 2;
    int color = (int) (255 * darken);
    if(color < 0) color = 0;
    g2.setPaint(new Color(color, color, color));
    g2.fill(p);
  }

  public void drawModel(Model m){
    Tri[] tris = m.getGeom();
    for(Tri t : tris){
      drawTri(t);
    }
  }

  private double toRadians(double degrees){
    return degrees * Math.PI / 180;
  }

  private double toDegrees(double radians){
    return radians * 180 / Math.PI;
  }

  public double[] toRelative(double[] point){
    double x = point[0] - xCam;
    double y = point[1] - yCam;
    double z = point[2] - zCam;

    double x1 = cos(camAngleX) * (sin(camRoll) * y + cos(camRoll) * x) - sin(camAngleX) * z;
    double y1 = sin(camAngleY) * (cos(camAngleX) * z + sin(camAngleX) * (sin(camRoll) * y + cos(camRoll) * x)) + cos(camAngleY) * (cos(camRoll) * y - sin(camRoll) * x);
    double z1 = cos(camAngleY) * (cos(camAngleX) * z + sin(camAngleX) * (sin(camRoll) * y + cos(camRoll) * x)) - sin(camAngleY) * (cos(camRoll) * y - sin(camRoll) * x);

    return new double[] {x1, y1, z1};
  }
}