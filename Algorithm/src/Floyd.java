import java.util.Arrays;

public class Floyd {

    public static void main(String[] args) {

        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' }; //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        Graph graph = new Graph(vertex.length, matrix, vertex);

        graph.floyd();

        graph.show();

    }
}

class Graph{
    char[] vertex;
    private int[][] dis;
    private int[][] pre;

    public Graph(int len, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[len][len];

        for (int i = 0; i < len ; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){

        for (int k = 0; k < dis.length; k++) {
            // 先将 pre 数组输出的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出 dis 数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;

        for (int k = 0; k < dis.length; k++) {

            for (int i = 0; i < dis.length; i++) {

                for (int j = 0; j < dis.length ; j++) {

                    len = dis[i][k] + dis[k][j];

                    if(len < dis[i][j]){
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}


