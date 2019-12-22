import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};

        bubbleSort(arr);

        System.out.println(Arrays.toString(arr));

        int[] testArr = new int[80000];

        for (int i = 0; i < 80000 ; i++) {
            testArr[i] = (int) (Math.random()*8000000);
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println(df.format(new Date()));

        bubbleSort(testArr);

        System.out.println(df.format(new Date()));
    }

    public static void bubbleSort(int arr[]) {

        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
