package graphicsTest;

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
}