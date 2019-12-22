import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        insertSort2(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void insertSort(int[] arr){

        int insertVal = 0;
        int index =0;

        for (int i = 1; i < arr.length ; i++) {

            insertVal = arr[i];
            index = i -1;

            while(index >= 0 && insertVal < arr[index]){
                arr[index + 1] = arr[index];
                index --;
            }
            if(index + 1 != i){
                arr[index + 1] = insertVal;
            }
        }

    }

    public static void insertSort2(int[] arr){

        int temp = 0;
        for (int i = 1; i < arr.length ; i++) {

                for (int j = i -1; j >= 0 ; j--) {

                    if(arr[j] > arr[j+1]){
                        temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp ;
                    }
                }
        }
    }
}
