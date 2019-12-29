import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Kruskal {

    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };

        Kruskal k = new Kruskal(vertexs,matrix);

        k.kruskal();

    }

    public void kruskal(){

        int index = 0;
        int[] ends = new int[edgeNum];

        EData[] res = new EData[edgeNum];

        EData[] edges = getEdges();

        Arrays.sort(edges,(a,b)->a.weight - b.weight);

        for (int i = 0; i < edgeNum; i++) {

            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);

            if(m != n){
                ends[m] = n;
                res[index++] = edges[i];
            }
        }
        System.out.println(Arrays.toString(res));
    }

    public Kruskal(char[] vertex, int[][] matrix){
        int vLen = vertex.length;
        this.vertex = new char[vLen];
        for (int i = 0; i < vertex.length ; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vLen][vLen];

        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void print(){
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(EData[] edges){

        for (int i = 0; i < edges.length -1 ; i++) {
            for (int j = 0; j < edges.length - i; j++) {
                if(edges[j].weight > edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j +1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    private int getPosition(char ch){
        for (int i = 0; i < vertex.length ; i++) {
            if(vertex[i] == ch){
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges(){
        int index = 0;

        EData[] edges = new EData[edgeNum];

        for (int i = 0; i < vertex.length; i++) {

            for (int j = i+1; j < vertex.length ; j++) {

                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertex[i],vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

class EData{
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

