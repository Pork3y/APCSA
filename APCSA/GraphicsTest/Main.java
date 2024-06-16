package GraphicsTest;

import java.awt.*;
import java.io.IOException;

//  TODO: Improve performance a lot. Actually drawing to frame seems like a major bottleneck, so porting to OpenGL might improve it.

public class Main {
	public static void main(String[] args) throws InterruptedException, FontFormatException, IOException, AWTException {

      Render r = new Render(90, 180, 0, new double[]{0, 0.5, 2});

//      Block b1 = new Block(new Point3D(0, 0, 5));
//      r.environment.add(b1);
//      r.environment.add(new Block(new Point3D(0, 1, 5), "cheese"));

//       for(int i = 0; i < 5; i++){
//             for(int j = 0; j < 5; j++){
//                   for(int k = 0; k < 5; k++) {
//                         r.environment.add(new Block(new Point3D(2 * i, 2 * j, 2 * k)));
//                   }
//             }
//       }

          //b1.add(m1);

//        Chunk c1 = new Chunk(Point3D.ORIGIN);
//        r.environment.add(c1.getMesh());

//      Chunk c2 = new Chunk(new Point3D(0, 0, 16));
//      r.environment.add(c2.getMesh());

        Model bunny = ModelLoader.load_model("bunnygtest.obj");
        r.environment.add(bunny);
        while(true){
            r.refresh();
        }


	}
}