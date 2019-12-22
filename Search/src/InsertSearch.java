import java.util.ArrayList;
import java.util.Arrays;

public class InsertSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println(Arrays.toString(arr));

        System.out.println(insertSearch(arr,100));
    }

    public static int insertSearch(int[] arr, int val) {

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;

        while (l <= r && arr[mid] >= arr[0] && arr[mid] <= arr[arr.length-1] ) {
            System.out.println("count");
            mid = l + (r - l) * (val - arr[l]) / (arr[r] - arr[l]);
            if (val < arr[mid]) {
                r = mid - 1;
            } else if (val > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
