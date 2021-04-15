package SortingAndSearching;

public class MergeSort {

    public static void sort(int[] arr){
        if(arr.length <= 1) return;
        int[] first = new int[arr.length / 2];
        int[] second = new int[arr.length - first.length];
        for(int i = 0; i < first.length; i++){
            first[i] = arr[i];
        }
        for(int i = 0; i < second.length; i++){
            second[i] = arr[i + first.length];
        }
        sort(first);
        sort(second);
        merge(first, second, arr);
    }

    public static void merge(int[] first, int[] second, int[] arr){
        int i = 0;
        int j = 0;
        while(i < first.length && j < second.length){
            if(first[i] < second[j]) {
                arr[i + j] = first[i];
                i++;
            } else{
                arr[i + j] = second[j];
                j++;
            }
        }
        while(i < first.length){
            arr[i + j] = first[i];
            i++;
        }
        while(j < second.length){
            arr[i + j] = second[j];
            j++;
        }
    }
}
