package SortingAndSearching;
import java.util.ArrayList;
class Main {
  public static void main(String[] args) {

    Stopwatch timer = new Stopwatch();
    int[] arr = ArrayUtils.generateRandom(100000, 10000);
    ArrayUtils.printArray(arr);
    System.out.println();
    timer.start();
    QuickSort.sort(arr, 0, arr.length - 1);
    timer.stop();
    ArrayUtils.printArray(arr);
    System.out.println(BinarySearch.search(arr, 0, arr.length - 1, 5));
    System.out.println("Sort Time: " + timer.getElapsedTime());
    System.out.println();

  }
}
