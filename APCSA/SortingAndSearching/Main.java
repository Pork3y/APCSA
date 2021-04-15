package SortingAndSearching;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {

    Stopwatch timer = new Stopwatch();
    int[] arr = ArrayUtils.generateRandom(10000000, 100);
    ArrayUtils.printArray(arr);
    System.out.println();
    timer.start();
    QuickSort.sort(arr, 0, arr.length - 1);
    timer.stop();
    ArrayUtils.printArray(arr);
    System.out.println("Sort Time: " + timer.getElapsedTime());
    System.out.println();

  }
}
