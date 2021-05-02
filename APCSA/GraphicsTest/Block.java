package GraphicsTest;

import java.io.IOException;

public class Block extends Model{

    //int blockID;
    //0: Dirt 1: Grass 2: Stone

    public Block(Point3D corner, String blockType) throws IOException {
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

        Tri t1 = new Tri(new Point3D[]{p1 , p3, p2}, blockType + ".png", true);
        Tri t2 = new Tri(new Point3D[]{p5 , p2, p3}, blockType + ".png", false);
        Tri t3 = new Tri(new Point3D[]{p4 , p7, p1}, blockType + ".png", true);
        Tri t4 = new Tri(new Point3D[]{p3 , p1, p7}, blockType + ".png", false);
        Tri t5 = new Tri(new Point3D[]{p3 , p7, p5}, blockType + ".png", true);
        Tri t6 = new Tri(new Point3D[]{p8 , p5, p7}, blockType + ".png", false);
        Tri t7 = new Tri(new Point3D[]{p2 , p5, p6}, blockType + ".png", true);
        Tri t8 = new Tri(new Point3D[]{p8 , p6, p5}, blockType + ".png", false);
        Tri t9 = new Tri(new Point3D[]{p4 , p1, p6}, blockType + ".png", true);
        Tri t10 = new Tri(new Point3D[]{p2 , p6, p1}, blockType + ".png", false);
        Tri t11 = new Tri(new Point3D[]{p6 , p8, p4}, blockType + ".png", true);
        Tri t12 = new Tri(new Point3D[]{p7 , p4, p8}, blockType + ".png", false);

        setGeom(new Tri[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12});
    }

    public Block(Point3D corner) throws IOException {
        this(corner, "stone");
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
