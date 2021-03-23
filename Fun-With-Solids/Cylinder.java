import java.lang.Math;

public class Cylinder extends Solid {

    private double height, radius;

    public Cylinder(String name, double height, double radius) {
        super(name);
        this.height = height;
        this.radius = radius;
    }

    public double volume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    public double surfaceArea() {
        return 2 * Math.PI * Math.pow(radius, 2) + height * Math.PI * 2 * radius;
    }
}