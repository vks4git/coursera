import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by vks on 24.09.2015.
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;
    private Point[] points;
    private int count = 0;

    public BruteCollinearPoints(Point[] data) {
        points = new Point[data.length];
        System.arraycopy(data, 0, points, 0, data.length);
        checkCornerCase(points);
        int len = points.length;
        LinkedList<Pair> pairs = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
                            if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
                                int indices[] = {j, k, l};
                                int minInd = i;
                                int maxInd = i;
                                for (int q = 0; q < 3; q++) {
                                    if (points[minInd].compareTo(points[indices[q]]) > 1) {
                                        minInd = indices[q];
                                    }
                                    if (points[maxInd].compareTo(points[indices[q]]) < 1) {
                                        maxInd = indices[q];
                                    }
                                }
                                pairs.add(new Pair(points[minInd], points[maxInd]));
                                count++;
                            }
                        }
                    }
                }
            }
        }
        segments = new LineSegment[count];
        int ptr = 0;
        for (Pair p : pairs) {
            segments[ptr] = new LineSegment(p.x, p.y);
            ptr++;
        }
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private void checkCornerCase(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException();
            }
        }
        int len = points.length;
        Arrays.sort(points, new PointComparator());
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }


    private class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.compareTo(o2);
        }
    }

    private class Pair {
        public Pair(Point x, Point y) {
            this.x = x;
            this.y = y;
        }

        public Point x;
        public Point y;
    }
}
