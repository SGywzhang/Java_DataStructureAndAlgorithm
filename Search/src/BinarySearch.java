import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = {1,8,9,1000,1000,1234};

        List<Integer> list = new ArrayList<>();

        System.out.println(binarySearchRecursive2(arr,0,arr.length-1,1000,list));
    }


    public static int binarySearchRecursive(int[] arr, int left, int right, int findVal){

        if(left > right) return -1;

        int mid = (left + right)/2;


        if(findVal < arr[mid]){
            return  binarySearchRecursive(arr,left,mid -1, findVal);
        }
        else if(findVal > arr[mid]){
            return binarySearchRecursive(arr,mid + 1,right, findVal);
        }
        else{
            return mid;
        }
    }


    public static List<Integer> binarySearchRecursive2(int[] arr, int left, int right, int findVal, List<Integer>list){

        if(left > right) return new ArrayList<Integer>();

        int mid = (left + right)/2;

        if(findVal < arr[mid]){
            return  binarySearchRecursive2(arr,left,mid -1, findVal,list);
        }
        else if(findVal > arr[mid]){
            return binarySearchRecursive2(arr,mid + 1,right, findVal,list);
        }
        else{
            int temp = mid -1;

            while(temp >= 0 && arr[temp] == findVal){
                list.add(arr[temp]);
                temp--;
            }

            list.add(arr[mid]);

            temp = mid + 1;

            while(temp <= arr.length-1 && arr[temp] == findVal){
                list.add(arr[temp]);
                temp++;
            }

            return  list;
        }
    }


    public static int binarySearchIterative(int[] arr, int findVal){

        int left = 0;
        int right = arr.length - 1;
        int mid = 0;

        while(left <= right){

            mid = left + (right - left)/2;
            if(findVal < arr[mid]){
                right = mid - 1;
            }
            else if(findVal > arr[mid]){
                left = mid + 1;
            }
            else{
                return mid;
            }
        }
        return -1;
    }
}
