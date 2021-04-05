package graphicsTest;

import java.awt.*;

public class Vector {

    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point3D p1, Point3D p2){
        x = p2.coords[0] - p1.coords[0];
        y = p2.coords[1] - p1.coords[1];
        z = p2.coords[2] - p1.coords[2];
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public double theta(){
        return Math.atan(z / x);
    }

    public double phi(){
        return Math.acos(y / magnitude());
    }

    public Vector crossProduct(Vector v){
        double x1 = y * v.z - z * v.y;
        double y1 = z * v.x - x * v.z;
        double z1 = x * v.y - y * v.x;
        return new Vector(x1, y1, z1);
    }
}
