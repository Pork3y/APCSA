package SortingAndSearching;
public class ArrayUtils{
  
  public static void swap(int[] arr, int first, int second){
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }

  public static int[] generateRandom(int length, int n){
    int[] arr = new int[length];
    for(int i = 0; i < arr.length; i++){
      arr[i] = (int) (Math.random() * n);
    }
    return arr;
  }

  public static void printArray(int[] arr){
    if(arr.length > 100) return;
    System.out.print("[");
    for(int i = 0; i < arr.length - 1; i++){
      System.out.print(arr[i] + ", ");
    }
    System.out.println(arr[arr.length - 1] + "]");
  }
}