package funWithSolids;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		ArrayList<Solid> solids = new ArrayList<>();

		// Pyramid constructor should take name, length, width, height
		// in that order
		Pyramid pyramid = new Pyramid("My pyramid", 1, 3, 5);
		solids.add(pyramid);

		// Sphere constructor should take name then radius
		Sphere sphere = new Sphere("My sphere", 4);
		solids.add(sphere);

		// RectangularPrism constructor should take name,
		// length, width, height in that order
		RectangularPrism rectangularPrism = new RectangularPrism("My rectangular prism", 5, 8, 3);
		solids.add(rectangularPrism);

		// Cylinder constructor should take name, radius, height
		// in that order
		Cylinder cylinder = new Cylinder("My cylinder", 4, 9);
		solids.add(cylinder);

		// Cube constructor should take name then side length
		Cube cube = new Cube("My cube", 4);
		solids.add(cube);

		Object o = new Object();

		presentSolids(solids);
	}

	public static void presentSolids(ArrayList<Solid> solids){
		for(Solid s : solids){
			String name = s.getName();
			double volume = round(s.volume(), 2);
			double surfaceArea = round(s.surfaceArea(), 2);
			System.out.println(s.getClass().getSimpleName() + " " + name + " has volume: " + volume +
								" and surface area: " + surfaceArea + ".");
		}
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
