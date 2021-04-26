package GraphicsTest;

import java.awt.*;

public class PixelDrawer implements Runnable{

    Tri t;
    Render r;
    Graphics g;
    int offset;
    boolean isRunning;

    public PixelDrawer(Tri t, Render r, int offset){

        this.t = t;
        this.r = r;
        g = r.buffer;
        this.offset = offset;
        }

    public void run() {

        isRunning = true;

        Point3D p13d = t.getPoint(0);
        Point3D p23d = t.getPoint(1);
        Point3D p33d = t.getPoint(2);

        if (p13d.equals(p23d) || p13d.equals(p33d) || p23d.equals(p33d)) return;

        Vector v1 = new Vector(p13d, p23d);
        Vector v2 = new Vector(p33d, p23d);
        Vector normal = v2.crossProduct(v1);

        Point3D cent = t.center();

        if (normal.dotProduct(new Vector(cent.x() - r.xCam, cent.y() - r.yCam, cent.z() - r.zCam)) > 0) return;

        Point vert1 = r.projectPoint(p13d);
        Point vert2 = r.projectPoint(p23d);
        Point vert3 = r.projectPoint(p33d);

        int yMin = Math.min(vert1.y, Math.min(vert2.y, vert3.y));
        int yMax = Math.max(vert1.y, Math.max(vert2.y, vert3.y));

       Vector lightDist = new Vector(cent.x() - r.light.x(), cent.y() - r.light.y(), cent.z() - r.light.z());
       double dist = r.light.distanceTo(cent);
       double darken = Math.pow(((-normal.dotProduct(lightDist) / (normal.magnitude() * lightDist.magnitude()) + 1)/ Math.pow(dist, 2)), 1.0 / 4);

       int color = (int) (255 * darken);
       color = Math.max(0, color);
       color = Math.min(255, color);
       g.setColor(new Color(color, color, color));
       //if(normal.theta() == 0) g.setColor(Color.LIGHT_GRAY);
       //if(normal.theta() == Math.PI / 2) g.setColor(Color.DARK_GRAY);

        for (int i = Math.min(vert1.x, Math.min(vert2.x, vert3.x)) + offset; i <= Math.max(vert1.x, Math.max(vert2.x, vert3.x)); i+= 3) {
            for (int j = yMin; j <= yMax; j++) {
                Point p = new Point(i, j);
                double edgeVal1 = edgeFunc(vert1, vert2, p);
                double edgeVal2 = edgeFunc(vert2, vert3, p);
                double edgeVal3 = edgeFunc(vert3, vert1, p);
                if (edgeVal1 < 0 && edgeVal2 < 0 && edgeVal3 < 0){
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
        isRunning = false;
    }


    private static double edgeFunc(Point v1, Point v2, Point p){
        return (p.x - v1.x) * (v2.y - v1.y) - (p.y - v1.y) * (v2.x - v1.x);
    }
}
