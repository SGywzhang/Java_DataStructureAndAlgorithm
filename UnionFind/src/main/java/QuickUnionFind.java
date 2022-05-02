public class QuickUnionFind {
    private final int[] roots;

    public QuickUnionFind(int N){
        roots = new int[N];
        for(int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
    }

    public int findRoot(int i){
        int root = i;
        while (roots[root] != root){
            root = roots[root];
        }
        while(i != roots[i]){
            int temp = roots[i];
            roots[i] = root;
            i = temp;
        }
        return root;
    }

    public void union(int x, int y){
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        roots[rootX] = rootY;
    }
}
