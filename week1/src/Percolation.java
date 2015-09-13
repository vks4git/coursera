import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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

    private WeightedQuickUnionUF unionUF;

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
        unionUF = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {
        checkCornerCase(i, j);
        i--;
        j--;
        if (matrix[i][j] == blocked) {
            matrix[i][j] = open;
            int cell = i + j * size;
            if (i > 0) {
                if (matrix[i - 1][j] != blocked) {
                    unionUF.union(cell, cell - 1);
                }
            }
            if (i < size - 1) {
                if (matrix[i + 1][j] != blocked) {
                    unionUF.union(cell, cell + 1);
                }
            }
            if (j > 0) {
                if (matrix[i][j - 1] != blocked) {
                    unionUF.union(cell, cell - size);
                }
            }
            if (j < size - 1) {
                if (matrix[i][j + 1] != blocked) {
                    unionUF.union(cell, cell + size);
                }
            }
            if (i == 0) {
                unionUF.union(cell, top);
            }
            if (i == size - 1) {
                unionUF.union(cell, bottom);
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
        return (unionUF.connected(i + j * size, top));
    }

    public boolean percolates() {
        return unionUF.connected(top, bottom);
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


}
