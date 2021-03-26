package graphicsTest;

public class Tri {

    Point3D[] coords;

    public Tri(Point3D[] coords){
        this.coords = coords;
    }

    public Point3D getPoint(int index){
        return coords[index];
    }
}
