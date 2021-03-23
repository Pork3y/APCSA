import java.lang.Math;

public class Sphere extends Solid{

  private double radius;

  public Sphere(String name, double radius){
    super(name);
    this.radius = radius;
  }

  public double getRadius(){
    return radius;
  }

  public void setRadius(double r){
    radius = r;
  }

  public double volume(){
    return 4 / 3 * Math.PI * Math.pow(radius, 3);
  }

  public double surfaceArea(){
    return 4 * Math.PI * Math.pow(radius, 2);
  }
}