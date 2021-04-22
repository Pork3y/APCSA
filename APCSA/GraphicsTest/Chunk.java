package GraphicsTest;

public class Chunk {

    Model mesh = new Model(new Tri[0]);
    Block[][][] data = new Block[16][32][16];
    Point3D center;

    public Chunk(Point3D center){
        this.center = center;
        for(int i = -8; i < 8; i++){
            for(int j = -16; j < 0; j++){
                for(int k = -8; k < 8; k++){
                    data[i + 8][j + 16][k + 8] = new Block (new Point3D(center.x() + i , center.y() + j, center.z() + k));
                }
            }
        }

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 32; j++){
                for(int k = 0; k < 16; k++){
                    Block b = data[i][j][k];
                    if(data[i][j][k] == null) continue;
                    if(k != 0 && data[i][j][k - 1] != null) b.removeFace(1);
                    if(i != 0 && data[i - 1][j][k] != null) b.removeFace(2);
                    if(j != 31 && data[i][j + 1][k] != null) b.removeFace(3);
                    if(i != 15 && data[i + 1][j][k] != null) b.removeFace(4);
                    if(j != 0 && data[i][j - 1][k] != null) b.removeFace(5);
                    if(k != 15 && data[i][j][k + 1] != null) b.removeFace(6);
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
