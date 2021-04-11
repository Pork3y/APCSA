package FunWithSolids;

import java.lang.Math;

public class Pyramid extends Solid {

    public double baseLength, baseWidth, height;

    public Pyramid(String name, double baseLength, double baseWidth, double height) {
        super(name);
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.height = height;
    }

    public double getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(double baseLength) {
        this.baseLength = baseLength;
    }

    public double getBaseWidth() {
        return baseWidth;
    }

    public void setBaseWidth(double baseWidth) {
        this.baseWidth = baseWidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double volume(){
        return 1.0 / 3 * baseLength * baseWidth * height;
    }

    public double surfaceArea(){
        return baseLength * baseWidth + baseLength * Math.hypot(baseWidth / 2, height) + baseWidth * Math.hypot(baseLength / 2, height);
    }
}