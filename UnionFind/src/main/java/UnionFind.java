public class UnionFind {
    private final int[] roots;
    private final int[] rank;

    public UnionFind(int N){
        roots = new int[N];
        rank = new int[N];
        for(int i = 0; i < roots.length; i++) {
            roots[i] = i;
            rank[i] = 0;
        }
    }

    public int findRoot(int i){
        while (roots[i] != i){
            i = roots[i];
        }
        return i;
    }

    public void union(int x, int y){
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if(rootX != rootY){
            if(rank[rootY] > rank[rootX]){
                roots[rootX] = rootY;
            }
            else if(rank[rootY] < rank[rootX]){
                roots[rootY] = rootX;
            }
            else{
                roots[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
