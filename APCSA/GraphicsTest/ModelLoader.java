package GraphicsTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModelLoader {
    public static Model load_model(String filename){
        ArrayList<Tri> geom = new ArrayList<>();
        ArrayList<Point3D> points = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("APCSA/GraphicsTest/" + filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {

                if(!line.isEmpty()){
                    if(line.charAt(0) == 'v'){
                        String[] val_text = line.split(" ");
                        double[] vals = new double[3];
                        for(int i = 0; i < 3; i++){
                            vals[i] = 50 * Double.parseDouble(val_text[i + 1]) / 10.;
                        }
                        points.add(new Point3D(vals));
                    } else if(line.charAt(0) == 'f'){
                        String[] val_text = line.split(" ");
                        Point3D[] vals = new Point3D[3];
                        for(int i = 0; i < 3; i++){
                            vals[i] = points.get(Integer.parseInt(val_text[i + 1]) - 1);
                        }
                        Tri t = new Tri(vals);
                        geom.add(t);
                    }
                }

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Model(geom);
    }
}
