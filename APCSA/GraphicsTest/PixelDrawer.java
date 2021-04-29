package GraphicsTest;

import java.awt.*;

public class PixelDrawer implements Runnable{

    Tri t;
    Render r;
    int offset;

    public PixelDrawer(Tri t, Render r, int offset){

        this.t = t;
        this.r = r;
        this.offset = offset;
        }

    public void run() {

        Point3D p13d = t.getPoint(0);
        Point3D p23d = t.getPoint(1);
        Point3D p33d = t.getPoint(2);

        if (p13d.equals(p23d) || p13d.equals(p33d) || p23d.equals(p33d)) return;

        Vector normal = t.normal();

        Point3D cent = t.center();

        if (normal.dotProduct(new Vector(cent.x() - r.xCam, cent.y() - r.yCam, cent.z() - r.zCam)) > 0) return;

        Point vert1 = r.projectPoint(p13d);
        Point vert2 = r.projectPoint(p23d);
        Point vert3 = r.projectPoint(p33d);

        int yMax = Math.max(0, Math.min(Frame.height - 1, (Math.max(vert1.y, Math.max(vert2.y, vert3.y)))));
        if(yMax <= 0) return;
        int yMin = Math.min(Frame.height - 1, Math.max(0, (Math.min(vert1.y, Math.min(vert2.y, vert3.y)))));
        if(yMin >= Frame.height) return;

        int xMax = Math.max(0, Math.min(Frame.width - 1, (Math.max(vert1.x, Math.max(vert2.x, vert3.x)))));
        if(xMax <= 0) return;
        int xMin = Math.min(Frame.width - 1, Math.max(0, ((Math.min(vert1.x, Math.min(vert2.x, vert3.x))))));
        if(xMin >= Frame.width) return;

//        System.out.println("yMin - yMax: " + yMin + " - " + yMax);
//        System.out.println("xMin - xMax: " + xMin + " - " + xMax);

        double darken1 = darken(p13d);
        double darken2 = darken(p23d);
        double darken3 = darken(p33d);
//       int color = (int) (255 * darken);
//       color = Math.max(0, color);
//       color = Math.min(255, color);
//       g.setColor(new Color(color, color, color));

        Point3D cam = new Point3D(r.xCam, r.yCam, r.zCam);


        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin + offset; j <= yMax; j+= 30) {
                Point p = new Point(i, j);
                double area = edgeFunc(vert1, vert2, vert3);
                double edgeVal1 = edgeFunc(vert1, vert2, p);
                double edgeVal2 = edgeFunc(vert2, vert3, p);
                double edgeVal3 = edgeFunc(vert3, vert1, p);
                double z = edgeVal1 * p33d.distanceTo(cam) / area + edgeVal2 * p13d.distanceTo(cam) / area + edgeVal3 * p23d.distanceTo(cam) / area;
                if (z < r.zBuffer[j][i] && edgeVal1 < 0 && edgeVal2 < 0 && edgeVal3 < 0){
                    Color color = t.getPixel((int) (edgeVal3 / area * 16), (int) (edgeVal1 / area * 16));
                    double darken = darken1 * (edgeVal2 / area) + darken2 * (edgeVal3 / area) + darken3 * (edgeVal1 / area);
                    r.bufferRGB[j * Frame.width + i] = (new Color((int) (color.getRed() * darken), (int) (color.getGreen() * darken), (int) (color.getBlue() * darken))).getRGB();
                    //r.bufferRGB[j * Frame.width + i] = color.getRGB();
                    r.zBuffer[j][i] = z;
                }
            }
        }
    }


    private static double edgeFunc(Point v1, Point v2, Point p){
        return (p.x - v1.x) * (v2.y - v1.y) - (p.y - v1.y) * (v2.x - v1.x);
    }

    private double darken(Point3D p){
        Vector lightDist = new Vector(p.x() - r.light.x(), p.y() - r.light.y(), p.z() - r.light.z());
        double dist = r.light.distanceTo(p);
        return Math.pow(((-t.normal().dotProduct(lightDist) / (t.normal().magnitude() * lightDist.magnitude()) + 1)/ Math.pow(dist, 2)), 1.0 / 4);
    }
}
