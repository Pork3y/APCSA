package SortingAndSearching;
public class SelectionSort{

  //Best:O(n^2) Average:O(n^2)

  public static int[] sort(int[] arr){
    for(int i = 0; i < arr.length - 1; i++){
      int ind = getMin(arr, i);
      ArrayUtils.swap(arr, i, ind);
    }
    return arr;
  }

  public static int getMin(int[] arr, int start){
    int minInd = start;
    for(int j = start; j < arr.length; j++){
      if(arr[j] < arr[minInd]) minInd = j;
    }
    return minInd;
  }

}