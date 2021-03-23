import java.lang.Math;

public class RectangularPrism extends Solid {

  public double length, width, height;

  public RectangularPrism(String name, double length, double width, double height) {
    super(name);
    this.length = length;
    this.width = width;
    this.height = height;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public double volume() {
    return length * width * height;
  }

  @Override
  public double surfaceArea() {
    return 4 * length * width + 2 * width * height;
  }
}