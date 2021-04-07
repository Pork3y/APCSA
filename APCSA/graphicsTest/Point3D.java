package graphicsTest;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Point3D {

    final static Point3D ORIGIN = new Point3D(new double[]{0, 0, 0});
    public double[] coords = new double[3];

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

    public double distanceTo(Point3D p){
        double x = p.coords[0] - coords[0];
        double y = p.coords[1] - coords[1];
        double z = p.coords[2] - coords[2];
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }
    public String toString(){
        return coords[0] + " " + coords[1] + " " + coords[2];
    }
}
