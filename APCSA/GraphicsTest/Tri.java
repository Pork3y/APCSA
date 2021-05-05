package GraphicsTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tri {

    Point3D[] vertices;
    //Must be clockwise oriented when viewed from outside the object for surface normal to be calculated correctly
    BufferedImage texture;
    boolean corner;
    //true : bottom left, false : top right

    public Tri(Point3D[] vertices) throws IOException {
        this.vertices = vertices;
        this.texture = ImageIO.read(new File("APCSA/GraphicsTest/textures/stone.png"));
        this.corner = true;
    }

    public Tri(Point3D p1, Point3D p2, Point3D p3) throws IOException {
        this(new Point3D[]{p1, p2, p3});
    }

    public Tri(Point3D[] vertices, String textureName, boolean corner) throws IOException {
        this.vertices = vertices;
        this.texture = ImageIO.read(new File("APCSA/GraphicsTest/textures/" + textureName));
        this.corner = corner;
    }

    public Point3D center(){
        double[] coords = new double[3];
        for(int i = 0; i < 3; i++){
            double total = 0;
            for(int j = 0; j < 3; j++){
                total += vertices[j].coords[i];
            }
            coords[i] = total / 3;
        }
        return new Point3D(coords);
    }

    public Point3D getPoint(int index){
        return vertices[index];
    }

    public Color getPixel(int y, int x){
        if(corner){
            return new Color(texture.getRGB(x, texture.getHeight() - 1 - y), false);
        } else{
            return new Color(texture.getRGB(texture.getWidth() - 1 - x, y), false);
        }
    }

    public Vector normal(){
        Vector v1 = new Vector(vertices[0], vertices[1]);
        Vector v2 = new Vector(vertices[2], vertices[1]);
        return v2.crossProduct(v1);
    }
}

