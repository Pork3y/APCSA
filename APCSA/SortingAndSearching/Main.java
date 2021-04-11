package SortingAndSearching;
import java.util.ArrayList;
class Main {
  public static void main(String[] args) {

    Stopwatch timer = new Stopwatch();
    int[] arr = ArrayUtils.generateRandom(1000000, 100);
    ArrayUtils.printArray(arr);
    System.out.println();
    timer.start();
    InsertionSort.sort(arr);
    timer.stop();
    ArrayUtils.printArray(arr);
    System.out.println("Sort Time: " + timer.getElapsedTime());
    System.out.println();

  }
}
