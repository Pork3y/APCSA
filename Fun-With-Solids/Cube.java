import java.lang.Math;

public class Cube extends RectangularPrism {
    public Cube(String name, double length) {
        super(name, length, length, length);
    }

    @Override
    public double volume() {
        return Math.pow(getLength(), 3);
    }

    @Override
    public double surfaceArea() {
        return 4 * Math.pow(getLength(), 2);
    }
}