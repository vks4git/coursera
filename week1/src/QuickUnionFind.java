/**
 * Created by vks on 07.09.2015.
 */
public class QuickUnionFind {

    public QuickUnionFind(int n) {
        roots = new int[n * n + 2];
        weights = new int[n * n + 2];
        for (int i = 0; i < n * n + 2; i++) {
            roots[i] = i;
            weights[i] = 1;
        }
    }

    public boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    public void connect(int i, int j) {
        int weight_i = weights[i];
        int weight_j = weights[j];
        if (weight_i < weight_j) {
            roots[root(i)] = root(j);
            weights[j] += weights[i];
        } else {
            roots[root(j)] = root(i);
            weights[i] += weights[j];
        }
    }

    private int root(int i) {
        while (i != roots[i]) {
            i = roots[i];
        }
        return i;
    }

    private int[] roots;
    private int[] weights;
}
