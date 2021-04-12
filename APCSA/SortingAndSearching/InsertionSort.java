package SortingAndSearching;
public class InsertionSort{

  //Best:O(n) Average:O(n^2)

  public static void sort(int[] arr){
    for(int i = 1; i < arr.length; i++){
      int j = i;
      int next = arr[i];
      while(j != 0 && next > arr[j-1]) {
        arr[j] = arr[j-1];
        j--;
      }
      arr[j] = next;
    }
  }
}