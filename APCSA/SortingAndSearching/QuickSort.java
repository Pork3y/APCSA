package SortingAndSearching;

public class QuickSort {
    public static void sort(int[] arr, int first, int last){
        if (first < last) {
            int p = partition(arr, first, last);
            sort(arr, first, p - 1);
            sort(arr, p + 1, last);
        }
    }

    private static int partition(int[] arr, int first, int last){
        int pivot = arr[last];
        int i = first - 1;
        for(int j = first; j <= last; j++){
            if(arr[j] < pivot){
                i++;
                ArrayUtils.swap(arr, i, j);
            }
        }
        ArrayUtils.swap(arr, i + 1, last);
        return i + 1;
    }
}
