
public class Point3D {

    final double[] ORIGIN = new double[]{0, 0, 0};
    public double[] coords;

    public Point3D(double[] coords){
        this.coords = coords;
    }

    public Point3D(double x, double y, double z){
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
    }

    public double[] getCoords() {
        return coords;
    }
}
