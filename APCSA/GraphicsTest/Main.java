package GraphicsTest;

import java.awt.*;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, FontFormatException, IOException, AWTException {

      Render r = new Render(90, 0, 0, new double[]{0, 1.5, 0});

      double height = 100 * Math.sqrt(3) / 2;

      Point3D p1 = new Point3D(0, 0, 100);
      Point3D p2 = new Point3D(100, 0, 100);
      Point3D p3 = new Point3D(50, 0, 100 + height);
      Point3D p4 = new Point3D(50, 100, 150);

      Tri t1 = new Tri(new Point3D[]{p1, p2, p3});
      Tri t2 = new Tri(new Point3D[]{p1, p4, p2});
      Tri t3 = new Tri(new Point3D[]{p1, p3, p4});
      Tri t4 = new Tri(new Point3D[]{p2, p4, p3});

      Model m1 = new Model(new Tri[]{t1, t2, t3, t4});
      r.environment.add(m1);

          for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++) {
                      for(int k = 0; k < 5; k++) {
                            Block b1 = new Block(new Point3D(0 + 2 * i, 0 + 2 * k, 0 + 2 * j));
                            r.environment.add(b1);
                      }
                }
          }

          Block light = new Block(new Point3D(-4.5, 14.5, -4.5));

          r.environment.add(light);

      while(true){
        r.refresh();
      }


	}
}