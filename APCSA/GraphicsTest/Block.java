package GraphicsTest;

import java.io.IOException;

public class Block extends Model{

    //int blockID;
    //0: Dirt 1: Grass 2: Stone

    public Block(Point3D corner) throws IOException {
        super(new Tri[12]);
        Point3D p1 = corner;
        double[] coords = corner.getCoords();
        Point3D p2 = new Point3D(coords[0] + 1, coords[1], coords[2]);
        Point3D p3 = new Point3D(coords[0], coords[1] + 1, coords[2]);
        Point3D p4 = new Point3D(coords[0], coords[1], coords[2] + 1);
        Point3D p5 = new Point3D(coords[0] + 1, coords[1] + 1, coords[2]);
        Point3D p6 = new Point3D(coords[0] + 1, coords[1], coords[2] + 1);
        Point3D p7 = new Point3D(coords[0], coords[1] + 1, coords[2] + 1);
        Point3D p8 = new Point3D(coords[0] + 1, coords[1] + 1, coords[2] + 1);

        Tri t1 = new Tri(p1 , p3, p2);
        Tri t2 = new Tri(p2, p3, p5);
        Tri t3 = new Tri(p4, p7, p1);
        Tri t4 = new Tri(p1, p7, p3);
        Tri t5 = new Tri(p3, p7, p5);
        Tri t6 = new Tri(p5, p7, p8);
        Tri t7 = new Tri(p2, p5, p6);
        Tri t8 = new Tri(p5, p8, p6);
        Tri t9 = new Tri(p1, p2, p4);
        Tri t10 = new Tri(p2, p6, p4);
        Tri t11 = new Tri(p4, p6, p7);
        Tri t12 = new Tri(p6, p8, p7);

        setGeom(new Tri[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12});
    }

    public void removeFace(int face){
        for(int i = 0; i < geom.size(); i++){
            int num = (i + 2) / 2;
            if(num == face){
                geom.set(i, null);
            }
        }
    }

    public void cleanse(){
        for(int i = 0; i < geom.size(); i++){
            if(geom.get(i) == null){
                geom.remove(i);
                i--;
            }
        }
    }
}
