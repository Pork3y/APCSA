package GraphicsTest;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {

    protected ArrayList<Tri> geom;

    public Model(Tri[] geom){
        this.geom = new ArrayList<Tri>(Arrays.asList(geom));
    }
    public Model(ArrayList<Tri> geom){
        this.geom = geom;
    }

    public Tri[] getGeom() {
        Tri[] newGeom = new Tri[geom.size()];
        for(int i = 0; i < geom.size(); i++){
            newGeom[i] = geom.get(i);
        }
        return newGeom;
    }

    public void setGeom(Tri[] geom) {
        this.geom = new ArrayList<Tri>(Arrays.asList(geom));
    }

    public Point3D center(){
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        for (Tri tri : geom) {
            if(tri != null) {
                double[] coords = tri.center().getCoords();
                xSum += coords[0];
                ySum += coords[1];
                zSum += coords[2];
            }
        }
        return new Point3D(xSum / geom.size(), ySum / geom.size(), zSum / geom.size());
    }

    public void add(Model m){
        geom.addAll(m.geom);
    }
}
