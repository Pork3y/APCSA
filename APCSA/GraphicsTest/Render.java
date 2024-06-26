package GraphicsTest;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class Render extends Frame{
  public ArrayList<Model> environment = new ArrayList<>();
  public ArrayList<Thread> threads = new ArrayList<>();
  final public BufferedImage nextFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  public int[] bufferRGB = new int[width * height];
  public double[][] zBuffer = new double[height][width];
  final public Graphics buffer = nextFrame.getGraphics();
  final private MouseManager m = new MouseManager();
  public double xCam;
  public double yCam;
  public double zCam;
  private double fieldOfView;
  private double baseFieldOfView;
  private double camAngleX;
  private double camAngleY;
  final private double camRoll = 0;
  private double depth;
  private double dx = 0.15;
  final static private double sensitivity = 80;
  private boolean forwardMove, leftMove, rightMove, backMove, upMove, downMove, pause;
  private boolean debug = false;
  private int count = 0;
  private double FPS;
  public Point3D light = new Point3D(2.5, 2.5, 0);
  final static Color skyColor = new Color(148, 222, 255);
  Stopwatch timer = new Stopwatch();

  public Render(double fieldOfView, double angleX, double angleY, double[] camCoords) throws FontFormatException, IOException, AWTException {
    super();
    for(int i = 0; i < width; i++){
      for(int j = 0; j < height; j++){
        zBuffer[j][i] = Double.MAX_VALUE;
      }
    }
    xCam = camCoords[0];
    yCam = camCoords[1];
    zCam = camCoords[2];
    this.fieldOfView = toRadians(fieldOfView);
    baseFieldOfView = fieldOfView;
    camAngleX = toRadians(angleX);
    camAngleY = toRadians(angleY);
    depth = width / (2 * Math.tan(toRadians(fieldOfView)/2));
    final Font f = Font.createFont(Font.TRUETYPE_FONT, new File("APCSA/GraphicsTest/Minecraft.ttf")).deriveFont(Font.PLAIN, 24);
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(f);
    buffer.setFont(f);
    buffer.setColor(Color.BLACK);
    buffer.fillRect(0, 0, width, height);
    buffer.setColor(Color.WHITE);
    buffer.drawString("Loading...", width / 2, height / 2);
    g.drawImage(nextFrame, 0, 0, this);
    MouseWheelListener m = e -> {
      int num = e.getWheelRotation();
      System.out.println(num);
      addFOV(num * 5);
    };
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
          case 'p':
            pause = !pause;
        }

        switch (e.getKeyCode()) {
          case KeyEvent.VK_SPACE:
            upMove = true;
            break;
          case KeyEvent.VK_SHIFT:
            downMove = true;
            break;
          case KeyEvent.VK_UP:
            light.moveY(0.25);
            break;
          case KeyEvent.VK_DOWN:
            light.moveY(-0.25);
            break;
          case KeyEvent.VK_LEFT:
            light.moveX(-0.25);
            break;
          case KeyEvent.VK_RIGHT:
            light.moveX(0.25);
            break;
          case KeyEvent.VK_NUMPAD8:
            light.moveZ(0.25);
            break;
          case KeyEvent.VK_NUMPAD2:
            light.moveZ(-0.25);
            break;
          case KeyEvent.VK_ESCAPE:
            System.exit(0);
          case KeyEvent.VK_CONTROL:
            dx = 10;
            setFOV(toDegrees(fieldOfView) + 5);
            break;
          case KeyEvent.VK_F3:
            debug = !debug;
            break;
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
          case KeyEvent.VK_CONTROL:
            setFOV(baseFieldOfView);
            dx = 0.25;
            break;
        }
      }
    };

    this.addMouseWheelListener(m);
    this.addKeyListener(k);
    this.setCursor( canvas.getToolkit().createCustomCursor(
            new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
            new Point(),
            null ) );
  }

  public Render(double fieldOfView, double[] camCoords) throws FontFormatException, IOException, AWTException{
    this(fieldOfView, 0, 0, camCoords);
  }

  public void refresh() throws InterruptedException {
    if(pause) return;
    if(debug) timer.start();
    Point3D cam = new Point3D(xCam, yCam, zCam);
    //sort(environment, 0, environment.size() - 1);
    for(Model mod : environment){
      drawModel(mod);
    }
    for(Thread t : threads){
      t.join();
    }

    Thread m1 = new Thread(m);
    m1.start();

    Thread t = new Thread(new ImageDrawer(this));
    t.start();

    if(forwardMove) {
      xCam += Math.sin(camAngleX) * dx;
      zCam += Math.cos(camAngleX) * dx;
    }
    if(leftMove){
      xCam += Math.sin(camAngleX - Math.PI / 2) * dx;
      zCam += Math.cos(camAngleX - Math.PI / 2) * dx;
    }
    if(rightMove){
      xCam += Math.sin(camAngleX + Math.PI / 2) * dx;
      zCam += Math.cos(camAngleX + Math.PI / 2) * dx;
    }
    if(backMove){
      xCam += Math.sin(camAngleX + Math.PI) * dx;
      zCam += Math.cos(camAngleX + Math.PI) * dx;
    }
    if(upMove){
      yCam += 2 * dx;
    }
    if(downMove){
      yCam -= 2 * dx;
    }

    m1.join();
    addHorizontalAngle(sensitivity * Math.tan((m.mousePosX - width * 1.0 / 2) / depth));
    addVerticalAngle(sensitivity * Math.tan((m.mousePosY - height * 1.0 / 2) / depth));

    t.join();

    if(debug){
      timer.stop();
      buffer.setColor(Color.WHITE);
      buffer.drawString("Cam Horizontal Angle: " + toDegrees(camAngleX), 0, 20);
      buffer.drawString("Cam Vertical Angle: " + toDegrees(camAngleY), 0, 40);
      buffer.drawString("Cam Roll: " + toDegrees(camRoll), 0, 60);
      buffer.drawString("Cam X: " + xCam, 0, 80);
      buffer.drawString("Cam Y: " + yCam, 0, 100);
      buffer.drawString("Cam Z: " + zCam, 0, 120);
      count++;
      if(count == 10){
        FPS = 10000.0 / (timer.getElapsedTime());
        timer.reset();
        count = 0;
      }
      buffer.drawString("FPS: " + FPS, 0, 140);
    }
    g.drawImage(nextFrame, 0, 0, this);
    buffer.setColor(Color.BLACK);
    buffer.fillRect(0,0, 1920, 1080);
  }

  public void addHorizontalAngle(double degrees){
    camAngleX += toRadians(degrees);
  }

  public void addVerticalAngle(double degrees){
    camAngleY += toRadians(degrees);
  }

  public void addFOV(double degrees){
    fieldOfView = fieldOfView + toRadians(degrees);
    fieldOfView = Math.min(fieldOfView, 5 * Math.PI / 6);
    fieldOfView = Math.max(fieldOfView, Math.PI / 12);
    baseFieldOfView = fieldOfView;
    depth = width / (2 * Math.tan(fieldOfView/2));
  }

  public void setFOV(double degrees){
    fieldOfView = toRadians(degrees);
    fieldOfView = Math.min(fieldOfView, 5 * Math.PI / 6);
    fieldOfView = Math.max(fieldOfView, Math.PI / 12);
    depth = width / (2 * Math.tan(fieldOfView/2));
  }

  public void setPosition(double[] camCoords){
    xCam = camCoords[0];
    yCam = camCoords[1];
    zCam = camCoords[2];
  }

  private void sort(Model m, int first, int last){
    if(first < last){
      int p = partition(m, first, last);
      sort(m, first, p - 1);
      sort(m, p + 1, last);
    }
  }

  private int partition(Model m, int first, int last){
    Point3D cam = new Point3D(xCam, yCam, zCam);
    double pivot = m.geom.get(last).center().distanceTo(cam);
    int i = first - 1;
    for(int j = first; j <= last; j++){
      if(m.geom.get(j).center().distanceTo(cam) < pivot){
        i++;
        Tri t = m.geom.set(i, m.geom.get(j));
        m.geom.set(j, t);
      }
    }
    Tri t = m.geom.set(i + 1, m.geom.get(last));
    m.geom.set(last, t);
    return i + 1;
  }

  @Deprecated
  //Included for compatibility with old drawTri method
  public Point projectIntPoint(Point3D p){
    Point2D.Double pDoub = projectPoint(p);
    return new Point((int) pDoub.x, (int) pDoub.y);
  }

  public Point2D.Double projectPoint(Point3D p){
    Point3D p1 = toRelative(p);
    double[] point = p1.coords;

    //System.out.println(point[0] + " " + point[1] + " " + point[2]);
    if(point[2] > 0){
      double t = depth / point[2];
      double xProj = (width * 1.0 / 2 + t * (point[0]));
      double yProj = (height * 1.0 / 2 - t * (point[1]));
      buffer.setColor(Color.BLACK);
      //g2.fillRect(xProj, yProj, 1, 1);
      return new Point2D.Double(xProj, yProj);
    } else{
      return new Point2D.Double(-1, -1);
    }
  }

  @Deprecated
  public void drawTri(Tri t){
    if(toRelative(t.center()).z() > 0) {
      Point3D p13d = t.getPoint(0);
      Point3D p23d = t.getPoint(1);
      Point3D p33d = t.getPoint(2);

      if (p13d.equals(p23d) || p13d.equals(p33d) || p23d.equals(p33d)) return;

      Vector v1 = new Vector(p13d, p23d);
      Vector v2 = new Vector(p33d, p23d);
      Vector normal = v2.crossProduct(v1);

      Point3D cent = t.center();
      if (normal.dotProduct(new Vector(cent.x() - xCam, cent.y() - yCam, cent.z() - zCam)) <= 0) {
        Point p1 = projectIntPoint(p13d);
        Point p2 = projectIntPoint(p23d);
        Point p3 = projectIntPoint(p33d);
        if(p1.x == -1 || p2.x == -1 || p3.x == -1) return;

        Vector lightDist = new Vector(cent.x() - light.x(), cent.y() - light.y(), cent.z() - light.z());
        double dist = light.distanceTo(cent);
        double darken = Math.pow(((-normal.dotProduct(lightDist) / (normal.magnitude() * lightDist.magnitude()) + 1)/ Math.pow(dist, 2)), 1.0 / 4);

        int color = (int) (255 * darken);
        color = Math.max(0, color);
        color = Math.min(255, color);
        buffer.setColor(new Color(color, color, color));
        //buffer.setColor(Color.GRAY);
        //if(normal.theta() == 0) buffer.setColor(Color.LIGHT_GRAY);
        //if(normal.theta() == Math.PI / 2) buffer.setColor(Color.DARK_GRAY);

        buffer.fillPolygon(new int[]{p1.x, p2.x, p3.x}, new int[]{p1.y, p2.y, p3.y}, 3);
        if (debug) {
          //drawLine(cent, new Point3D(cent.x() + normal.x, cent.y() + normal.y, cent.z() + normal.z));
          buffer.setColor(Color.GREEN);
          buffer.drawString("" + color, p1.x, p1.y);
        }
      }
    }
  }

  public void drawModel(Model m) throws InterruptedException {
    Tri[] tris = m.getGeom();

    for(Tri tri : tris){
      for(int i = 0; i < 30; i++) {
        TriDrawer p1 = new TriDrawer(tri, this, i);
        Thread t1 = new Thread(p1);
        t1.setDaemon(true);
        threads.add(t1);
        t1.start();
      }
    }
  }

  private double toRadians(double degrees){
    return degrees * Math.PI / 180;
  }

  private double toDegrees(double radians){
    return radians * 180 / Math.PI;
  }

  public Point3D toRelative(Point3D p){
    double x = p.x() - xCam;
    double y = p.y() - yCam;
    double z = p.z() - zCam;

    double v = sin(camRoll) * y + cos(camRoll) * x;
    double x1 = cos(camAngleX) * v - sin(camAngleX) * z;
    double y1 = sin(camAngleY) * (cos(camAngleX) * z + sin(camAngleX) * v) + cos(camAngleY) * (cos(camRoll) * y - sin(camRoll) * x);
    double z1 = cos(camAngleY) * (cos(camAngleX) * z + sin(camAngleX) * v) - sin(camAngleY) * (cos(camRoll) * y - sin(camRoll) * x);

    return new Point3D(x1, y1, z1);
  }


}