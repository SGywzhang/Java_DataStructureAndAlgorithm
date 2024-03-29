import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {


    private List<String> vertexList;
    private int[][] edges;
    private int numOfEdges;

    private boolean[] isVisited;

    public Graph(int n){

        edges = new int[n][n];

        vertexList = new ArrayList<>();

        numOfEdges = 0;
        isVisited = new boolean[5];
    }
    
    public int getFirstNeighor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor (int v1 , int v2){
        for (int i = v2 + 1 ; i <vertexList.size() ; i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    private void bfs(int i){
        int u;
        int w;

        LinkedList<Integer> queue = new LinkedList<>();

        System.out.print(getValueByIndex(i) + "->");

        isVisited[i] = true;

        queue.addLast(i);

        while(!queue.isEmpty()){
            u = queue.removeFirst();

            w = getFirstNeighor(u);

            while(w != -1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(i);
            }
        }
    }

    public void dfs(int i){

        System.out.print(getValueByIndex(i) + "->");

        isVisited[i] = true;

        int w = getFirstNeighor(i);

        while(w != -1){
            if(!isVisited[w]){
                dfs(w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }

    public void  insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdge (int v1, int v2 , int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }


    public int getNumOfVertex(){
        return vertexList.size();
    }

    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    public void showGraph(){

        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


    public static void main(String[] args) {
        int n = 5;
        String[] vertexValue = {"A","B","C","D","E"};

        Graph graph = new Graph(n);
        for (String value:vertexValue){
            graph.insertVertex(value);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        System.out.println("DFS");
        //graph.dfs();
        graph.bfs();
    }
}
