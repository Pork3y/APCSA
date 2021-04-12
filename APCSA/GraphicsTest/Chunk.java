package GraphicsTest;

public class Chunk {

    Model mesh = new Model(new Tri[0]);
    Block[][][] data = new Block[8][16][8];
    Point3D center;

    public Chunk(Point3D center){
        this.center = center;
        for(int i = -4; i < 4; i++){
            for(int j = -8; j < 0; j++){
                for(int k = -4; k < 4; k++){
                    data[i + 4][j + 8][k + 4] = new Block (new Point3D(center.x() + i , center.y() + j, center.z() + k));
                }
            }
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 16; j++){
                for(int k = 0; k < 8; k++){
                    Block b = data[i][j][k];
                    if(data[i][j][k] == null) continue;
                    if(k != 0 && data[i][j][k - 1] != null) b.removeFace(1);
                    if(i != 0 && data[i - 1][j][k] != null) b.removeFace(2);
                    if(j != 15 && data[i][j + 1][k] != null) b.removeFace(3);
                    if(i != 7 && data[i + 1][j][k] != null) b.removeFace(4);
                    if(j != 0 && data[i][j - 1][k] != null) b.removeFace(5);
                    if(k != 7 && data[i][j][k + 1] != null) b.removeFace(6);
                    b.cleanse();
                    mesh.add(b);

                }
            }
        }
    }

    public Model getMesh(){
        return mesh;
    }
}
