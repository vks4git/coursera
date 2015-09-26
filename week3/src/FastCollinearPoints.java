import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by vks on 25.09.2015.
 */
public class FastCollinearPoints {

    private LineSegment[] segments;
    private Point[] points;
    private int count;


    public FastCollinearPoints(Point[] data) {
        points = new Point[data.length];
        System.arraycopy(data, 0, points, 0, data.length);
        checkCornerCase(points);
        int cnt = 0;
        int len = points.length;
        Point[] pts = new Point[len];
        System.arraycopy(points, 0, pts, 0, len);
        LinkedList<Pair> pairs = new LinkedList<>();

        for (Point p : points) {
            Arrays.sort(pts, p.slopeOrder());
            int left = 1;
            int right = 1;
            while (right < len) {
                int length = 2;
                double target = p.slopeTo(pts[left]);
                while ((right < len - 1) && (p.slopeTo(pts[right + 1]) == target)) {
                    right++;
                    length++;
                }
                int min = 0;
                int max = 0;
                if (length >= 4) {
                    for (int i = left; i <= right; i++) {
                        if (pts[min].compareTo(pts[i]) > 0) {
                            min = i;
                        }
                        if (pts[max].compareTo(pts[i]) < 0) {
                            max = i;
                        }
                    }
                    cnt++;
                    pairs.add(new Pair(pts[min], pts[max]));
                }
                right++;
                left = right;
            }
        }
        Pair[] segs = new Pair[cnt];
        int ptr = 0;
        for (Pair p : pairs) {
            segs[ptr] = p;
            ptr++;
        }
        PairComparator comparator = new PairComparator();
        Arrays.sort(segs, comparator);
        count = cnt;
        for (int i = 0; i < cnt - 1; i++) {
            if (comparator.compare(segs[i], segs[i + 1]) == 0) {
                segs[i] = null;
                count--;
            }
        }
        segments = new LineSegment[count];
        ptr = 0;
        for (int i = 0; i < cnt; i++) {
            if (segs[i] != null) {
                segments[ptr] = new LineSegment(segs[i].x, segs[i].y);
                ptr++;
            }
        }
        segs = null;
        pairs = null;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
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

    private class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.compareTo(o2);
        }
    }

    private class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            if (o1.x.compareTo(o2.x) < 0) {
                return -1;
            }
            if (o1.x.compareTo(o2.x) > 0) {
                return 1;
            }
            if (o1.y.compareTo(o2.y) < 0) {
                return -1;
            }
            if (o1.y.compareTo(o2.y) > 0) {
                return 1;
            }
            return 0;
        }

    }

}
