import java.util.Arrays;

public class FibonacciSearch {

        public static int maxSize = 20;

        public static void main(String[] args) {

            int [] arr = {1,8,10,89,1000,1234};
            System.out.println("index=" + fibSearch(arr, 1234));// 0

        }

    public static int[] fib(){
        int[] f = new int[maxSize];

        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < maxSize; i++) {

            f[i] = f[i-1] +f[i-2];
        }
        return f;
    }


    public static int fibSearch(int[] arr, int key){

        int k = 0;
        int l = 0;
        int r = arr.length-1;
        int mid = 0;
        int[] f = fib();

        while(r > f[k]-1){
            k++;
        }

        int[] temp = Arrays.copyOf(arr,f[k]);

        for (int i = r +1 ; i < temp.length; i++) {
            temp[i] = arr[r];
        }

        System.out.println(Arrays.toString(temp));

        while(l <= r){

            mid = l + f[k-1] - 1;

            if(key < temp[mid]){
                r = mid - 1;
                k--;
            }
            else if(key > temp[mid]){
                l = mid + 1;
                k-=2;
            }
            else{
                if (mid <= r) return mid;
                else return r;
            }
        }

        return -1;
    }
}
