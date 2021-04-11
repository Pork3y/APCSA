package GraphicsTest;

public class Model {

    Tri[] geom;

    public Model(Tri[] geom){
        this.geom = geom;
    }

    public Tri[] getGeom() {
        return geom;
    }

    public void setGeom(Tri[] geom) {
        this.geom = geom;
    }

    public Point3D center(){
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        for (Tri tri : geom) {
            double[] coords = tri.center().getCoords();
            xSum += coords[0];
            ySum += coords[1];
            zSum += coords[2];
        }
        return new Point3D(xSum / geom.length, ySum / geom.length, zSum / geom.length);
    }
}
