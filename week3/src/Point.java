import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by vks on 22.09.2015.
 */


public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    private class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            double a = slopeTo(o1);
            double b = slopeTo(o2);
            if (a > b) {
                return 1;
            }
            if (a < b) {
                return -1;
            }
            return 0;
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.y == that.y) {
            if (this.x != that.x) {
                return +0.0;
            } else {
                return Double.NEGATIVE_INFINITY;
            }
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return (dy / dx);
    }

    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }
        return 0;
    }

    public Comparator<Point> slopeOrder() {
        return new PointComparator();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}

