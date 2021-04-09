package graphicsTest;

public class Tri {

    Point3D[] vertices;
    //Must be clockwise oriented when viewed from outside the object

    public Tri(Point3D[] vertices){
        this.vertices = vertices;
    }

    public Tri(Point3D p1, Point3D p2, Point3D p3){
        this(new Point3D[]{p1, p2, p3});
    }

    public Point3D center(){
        double[] coords = new double[3];
        for(int i = 0; i < 3; i++){
            double total = 0;
            for(int j = 0; j < 3; j++){
                total += vertices[j].coords[i];
            }
            coords[i] = total / 3;
        }
        return new Point3D(coords);
    }

    public Point3D getPoint(int index){
        return vertices[index];
    }

}
