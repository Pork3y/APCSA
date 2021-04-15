package SortingAndSearching;
import java.util.Arrays;

public class BinaryInsertionSort {
    public static void sort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int x = arr[i];

            int j = Math.abs(BinarySearch.search(arr, 0, i - 1, x));

            System.arraycopy(arr, j, arr, j + 1, i - j);

            arr[j] = x;
        }
    }
}
