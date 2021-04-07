package graphicsTest;

import java.awt.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) throws InterruptedException{

      Render r = new Render(90, 0, 0, new double[]{0, 1.5, 0});

      double height = 100 * Math.sqrt(3) / 2;

      Point3D p1 = new Point3D(0, 0, 100);
      Point3D p2 = new Point3D(100, 0, 100);
      Point3D p3 = new Point3D(50, 0, 100 + height);
      Point3D p4 = new Point3D(50, 100 + height, 150);

      Tri t1 = new Tri(new Point3D[]{p1, p2, p3});
      Tri t2 = new Tri(new Point3D[]{p1, p2, p4});
      Tri t3 = new Tri(new Point3D[]{p1, p3, p4});
      Tri t4 = new Tri(new Point3D[]{p2, p3, p4});

      Model m1 = new Model(new Tri[]{t1, t2, t3, t4});
      r.environment.add(m1);

      Block b1 = new Block(Point3D.ORIGIN);
      r.environment.add(b1);

      while(true){
        r.refresh();
      }


	}
}