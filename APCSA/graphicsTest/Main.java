import java.awt.*;

public class Main {
	public static void main(String[] args) throws InterruptedException{
    	//construct DrawingPanel, and get its Graphics context
      Render r = new Render(90, 0, 0, new double[]{0, 0, 0});
      Graphics g = r.getGraphics();
      g.setColor(new Color(255, 255, 255));

      while(true){
        /*r.drawPoint(new double[]{0, 0, 50});
        for(double i = 0; i < 5; i++){
          r.drawCube(-50, -150 + 100 * i, 250,
                      50, -50 + 100 * i, 350);
        }
        Thread.sleep(50);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 852, 480);*/
          Thread.sleep(100);
          g.setColor(Color.WHITE);
          g.fillRect(0, 0, 852, 480);
      }

	}
}