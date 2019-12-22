import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int arr[] = { 8, 4, 5, 7};

        mergeSort(arr,new int[arr.length],0,arr.length-1);

        System.out.println(Arrays.toString(arr));

    }


    public static void mergeSort(int[] arr, int[] temp, int left, int right) {

        if(left >= right) return;

        int mid = (left + right)/2;
        mergeSort(arr,temp,left,mid);
        mergeSort(arr,temp,mid+1,right);
        merge(arr,temp,left,mid,right);
    }


    public static void merge(int[]arr, int[] temp, int left, int mid, int right){


        int t = 0;
        int l = left;
        int r = mid +1;


        while(l <= mid && r <= right){

            if(arr[l] <= arr[r]){
                temp[t] = arr[l];
                t+=1;
                l+=1;
            }
            else{
                temp[t] = arr[r];
                t+=1;
                r+=1;
            }
        }

        while(l<=mid){
            temp[t] = arr[l];
            t+=1;
            l+=1;
        }

        while(r<=right){
            temp[t] = arr[r];
            t+=1;
            r+=1;
        }

        t = 0;
        int tempLeft = left;
        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }


}
