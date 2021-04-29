package GraphicsTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, FontFormatException, IOException, AWTException {

      Render r = new Render(90, 0, 0, new double[]{0, 1.5, 0});
//
      r.environment.add(new Block(new Point3D(0, 0, 10)));

//       for(int i = 0; i < 5; i++){
//             for(int j = 0; j < 5; j++){
//                   for(int k = 0; k < 5; k++) {
//                         r.environment.add(new Block(new Point3D(2 * i, 2 * j, 2 * k)));
//                   }
//             }
//       }

//        Chunk c1 = new Chunk(Point3D.ORIGIN);
//        r.environment.add(c1.getMesh());

      while(true){
        r.refresh();
      }

	}
}