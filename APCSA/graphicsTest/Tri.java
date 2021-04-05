package graphicsTest;

public class Tri {

    Point3D[] coords;

    public Tri(Point3D[] coords){
        this.coords = coords;
    }

    public Tri(Point3D p1, Point3D p2, Point3D p3){
        this(new Point3D[]{p1, p2, p3});
    }

    public Point3D getPoint(int index){
        return coords[index];
    }
}
