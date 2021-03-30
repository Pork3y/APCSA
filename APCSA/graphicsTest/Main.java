package graphicsTest;
import java.awt.*;

public class Main {
	public static void main(String[] args) throws InterruptedException{
    	//construct DrawingPanel, and get its Graphics context
      Render r = new Render(90, 0, 0, new double[]{0, 0, 0});
      Graphics g = r.getGraphics();

      double height = 100 * Math.sqrt(3) / 2;

      Point3D p1 = new Point3D(0, 0, 100);
      Point3D p2 = new Point3D(100, 0, 100);
      Point3D p3 = new Point3D(50, 0, 100 + height);
      Point3D p4 = new Point3D(50, 200, 150);

      Tri t1 = new Tri(new Point3D[]{p1, p2, p3});
      Tri t2 = new Tri(new Point3D[]{p1, p2, p4});
      Tri t3 = new Tri(new Point3D[]{p1, p3, p4});
      Tri t4 = new Tri(new Point3D[]{p2, p3, p4});

      Model m1 = new Model(new Tri[]{t1, t2, t3, t4});


      while(true){
        /*r.drawPoint(new double[]{0, 0, 50});
        for(double i = 0; i < 5; i++){
          r.drawCube(-50, -150 + 100 * i, 250,
                      50, -50 + 100 * i, 350);
        }
        Thread.sleep(50);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 852, 480);*/

        r.drawModel(m1);
        Thread.sleep(10);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, r.getWidth(), r.getHeight());

          try {
              // These coordinates are screen coordinates
              int xCoord = 500;
              int yCoord = 500;

              // Move the cursor
              Robot robot = new Robot();
              robot.mouseMove(xCoord, yCoord);
          } catch (AWTException e) {
          }
      }

	}
}