/**
 * Created by Vks on 04.09.2015.
 */
public class Percolation {
    private int[][] matrix;

    private final int blocked = 1;
    private final int open = 2;

    private final int size;


    private final int top;
    private final int bottom;
    private final int N;

    private int[] roots;
    private int[] weights;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = blocked;
            }
        }
        top = N * N;
        bottom = N * N + 1;
        roots = new int[N * N + 2];
        weights = new int[N * N + 2];
        for (int i = 0; i < N * N + 2; i++) {
            roots[i] = i;
            weights[i] = 1;
        }
        this.N = N;
    }

    public void open(int i, int j) {
        checkCornerCase(i, j);
        i--;
        j--;
        if (matrix[i][j] == blocked) {
            matrix[i][j] = open;
            if (i > 0) {
                if (isOpen(i, j + 1)) {
                    connect(i + j * N, (i - 1) + j * N);
                }
            }
            if (i < N - 1) {
                if (isOpen(i + 2, j + 1)) {
                    connect(i + j * N, (i + 1) + j * N);
                }
            }
            if (j > 0) {
                if (isOpen(i + 1, j)) {
                    connect(i + j * N, i + (j - 1) * N);
                }
            }
            if (j < N - 1) {
                if (isOpen(i + 1, j + 2)) {
                    connect(i + j * N, i + (j + 1) * N);
                }
            }
            if (j == 0) {
                connect(i + j * N, top);
            }
            if (j == N - 1) {
                connect(i + j * N, bottom);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        checkCornerCase(i, j);
        i--;
        j--;
        return (matrix[i][j] != blocked);
    }

    public boolean isFull(int i, int j) {
        checkCornerCase(i, j);
        i--;
        j--;
        return (connected(i + j * N, top));
    }

    public boolean percolates() {
        return connected(top, bottom);
    }

    private void checkCornerCase(int i, int j) {
        if (i < 1) {
            throw new IndexOutOfBoundsException();
        }
        if (i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (j < 1) {
            throw new IndexOutOfBoundsException();
        }
        if (j > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    private void connect(int i, int j) {
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

}
