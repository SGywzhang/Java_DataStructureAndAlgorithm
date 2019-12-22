public class RecursionReview {

    public static void main(String[] args) {

        test(4);
        System.out.println(factorial(4));
    }

    public static int factorial (int n){
        return n==1? 1:factorial(n-1)*n;
    }

    public static void test (int n){

        if(n>2){
            test(n-1);
        }
        System.out.println("n= " + n);
    }
}


