public class Queen8 {

    public static int size;
    public static int[] arr;
    public static int count;

    public static void main(String[] args) {

        size = 8;
        arr = new int[size];
        start(0);
        System.out.println(count);
    }

    public static void start(int n){
        if(n == size){
            count++;
            return;
        }
        else{
            for (int i = 0; i < size ; i++) {
                arr[n] = i;
                if(check(n)){
                    start(n +1);
                }
            }
        }
    }

    public static boolean check(int n){

        for (int i = 0; i < n; i++) {

            if(arr[n] == arr[i] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }


}
