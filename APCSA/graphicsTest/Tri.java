public class Tri {

    double[][] coords;

    public Tri(double[][] coords){
        this.coords = coords;
    }

    public double[] getPoint(int index){
        return coords[index];
    }
}
