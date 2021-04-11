package SortingAndSearching;
public class BubbleSort{

  //Best:O(n^2) Average:O(n^2)
  
  public static void sort(int[] arr){
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr.length - i - 1; j++){
        if(arr[j] > arr[j+1])
          ArrayUtils.swap(arr, j, j + 1);
      }
    }
  }

}