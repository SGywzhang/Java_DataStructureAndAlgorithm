import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123,2,15,0,9};

        //heapSort(arr);

        //int arr[] = {4, 6, 8, 5, 9};
        //adjustHeap(arr,4,11);

//        for (int i = arr.length / 2 - 1; i >= 0; i--) {
//            adjustHeap(arr, i, arr.length);
//        }
//
//        System.out.println(Arrays.toString(arr));

        heapSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr) {

        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能: 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例 int arr[]={4,6,8,5,9};=>i=1=>adjustHeap=> 得到 {4,9,8,5,6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */

    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {

            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
                arr[i] = temp;
            }
            else {
               break;
            }
            arr[i] = temp;
        }

    }
}
