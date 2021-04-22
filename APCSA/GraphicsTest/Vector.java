package GraphicsTest;

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
        x = p2.x() - p1.x();
        y = p2.y() - p1.y();
        z = p2.z() - p1.z();
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public double theta(){
        if(x == 0) return z / Math.abs(z) * Math.PI + Math.PI;
        return x > 0 ? Math.atan(z / x) : Math.PI + Math.atan(z / x);
    }

    public double phi(){
        return Math.asin(y / magnitude());
    }

    public Vector crossProduct(Vector v){
        double x1 = y * v.z - z * v.y;
        double y1 = z * v.x - x * v.z;
        double z1 = x * v.y - y * v.x;
        return new Vector(x1, y1, z1);
    }

    public double dotProduct(Vector v){
        return x * v.x + y * v.y + z * v.z;
    }

    public Vector asUnit(){
        return new Vector(x / magnitude(), y / magnitude(), z / magnitude());
    }

    public Point3D asPoint(){
        return new Point3D(x, y, z);
    }
}
