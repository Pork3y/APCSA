package GraphicsTest;

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

    public double x(){
        return coords[0];
    }

    public double y(){
        return coords[1];
    }

    public double z(){
        return coords[2];
    }

    public void moveX(double dist){
        coords[0] += dist;
    }

    public void moveY(double dist){
        coords[1] += dist;
    }

    public void moveZ(double dist){
        coords[2] += dist;
    }

    public double distanceTo(Point3D p){
        double x = p.x() - x();
        double y = p.y() - y();
        double z = p.z() - z();
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }
    public String toString(){
        return coords[0] + " " + coords[1] + " " + coords[2];
    }

    public boolean equals(Point3D p){
        return (x() == p.x() && y() == p.y() && z() == p.z());
    }
}
