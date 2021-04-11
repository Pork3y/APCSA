package SortingAndSearching;
public class LinearSearch{

  //Best: O(1) Average: O(n)

  public static int search(int[] arr, int val){
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == val) return i;
    }
    return -1;
  }
}