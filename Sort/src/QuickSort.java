import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        quickSort(0,arr.length-1,arr);

        System.out.println(Arrays.toString(arr));

    }


    public static void quickSort(int left, int right, int[] arr){

        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        int temp = 0;

        while( l < r) {

            while( arr[l] < pivot) {
                l += 1;
            }

            while(arr[r] > pivot) {
                r -= 1;
            }

            if( l >= r) {
                break; }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if(arr[l] == pivot) {
                r -= 1; }

            if(arr[r] == pivot) {
                l += 1; }

        }

        if(l ==r){
            l+=1;
            r-=1;
        }

        if(left < r){
            quickSort(left,r,arr);
        }

        if(right > l){
            quickSort(l,right,arr);
        }
    }

}
