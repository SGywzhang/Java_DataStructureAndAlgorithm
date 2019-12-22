import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {
        int[] testArr = new int[8000000];

        for (int i = 0; i < 8000000 ; i++) {
            testArr[i] = (int) (Math.random()*8000000);
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println(df.format(new Date()));

        shellSort(testArr);

        System.out.println(df.format(new Date()));

    }

    public static void shellSort(int[] arr){

        for(int gap = arr.length/2; gap > 0; gap/=2){

            int insertVal = 0;
            int index =0;

            for (int i = gap; i < arr.length ; i++) {

                insertVal = arr[i];
                index = i -gap;

                while(index >= 0 && insertVal < arr[index]){
                    arr[index + gap] = arr[index];
                    index = index -gap;
                }
                if(index + gap != i){
                    arr[index + gap] = insertVal;
                }
            }
        }

    }
}
