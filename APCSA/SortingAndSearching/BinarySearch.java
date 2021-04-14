package SortingAndSearching;

public class BinarySearch {
    public static int search(int[] arr, int low, int high, int val){
        if(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] == val){
                return mid;
            } else if(arr[mid] > val){
                return search(arr, low, mid - 1, val);
            } else{
                return search(arr, mid + 1, high, val);
            }
        } else{
            return -1;
        }
    }
}
