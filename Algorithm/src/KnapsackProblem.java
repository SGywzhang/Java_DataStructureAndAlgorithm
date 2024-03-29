public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};

        int m = 4;
        int n = val.length;
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //根据前面得到公式来动态规划处理
        for (int i = 1; i < v.length; i++) { //不处理第一行 i 是从 1 开始的
            for (int j = 1; j < v[0].length; j++) {//不处理第一列, j 是从 1 开始的
                if (w[i - 1] > j) { // 因为我们程序 i 是从 1 开始的，因此原来公式中的 w[i] 修改成 w[i-1] v[i][j]=v[i-1][j];
                } else {
                    //说明:
                    //因为我们的 i 从 1 开始的， 因此公式需要调整成 //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]); //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用 if-else 来体
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]]; //把当前的情况记录到 path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出一下 v 看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历 path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入


        //动脑筋
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) { //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= w[i - 1]; //w[i-1]
            }
            i--;
        }

    }
}
