/**
 * Created by Vks on 04.09.2015.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double mean = 0;
    private double stddev = 0;
    private final int T;

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }

    public PercolationStats(int N, int T) {
        if ((N <= 0) || (T <= 0)) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        double values[] = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            int count = 0;
            int x = 1;
            int y = 1;
            while (!percolation.percolates()) {
                while (percolation.isOpen(x, y)) {
                    x = StdRandom.uniform(N) + 1;
                    y = StdRandom.uniform(N) + 1;
                }
                percolation.open(x, y);
                count++;
            }
            values[i] = (double) count / ((double) N * N);
        }
        mean = StdStats.mean(values);
        stddev = StdStats.stddev(values);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - (1.96 * stddev) / Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean + (1.96 * stddev) / Math.sqrt(T);
    }

}
