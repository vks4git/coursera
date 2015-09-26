import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vks on 24.09.2015.
 */

public class TestBruteCollinearPoints {

    private static Point[] nonCollinearPoints;
    private static Point[] collinearPoints;
    private static Point[] collinearPoints2;
    private static Point[] cornerCase1;
    private static Point[] cornerCase2;

    @BeforeClass
    public static void initPoints() {
        nonCollinearPoints = new Point[10];
        collinearPoints = new Point[11];
        collinearPoints2 = new Point[10];
        cornerCase1 = new Point[10];
        cornerCase2 = new Point[10];
        nonCollinearPoints[0] = new Point(1, 1);
        nonCollinearPoints[1] = new Point(3, 0);
        nonCollinearPoints[2] = new Point(6, 1);
        nonCollinearPoints[3] = new Point(9, 2);
        nonCollinearPoints[4] = new Point(5, 3);
        nonCollinearPoints[5] = new Point(3, 3);
        nonCollinearPoints[6] = new Point(2, 4);
        nonCollinearPoints[7] = new Point(5, 5);
        nonCollinearPoints[8] = new Point(7, 5);
        nonCollinearPoints[9] = new Point(10, 6);
        for (int i = 0; i < 10; i++) {
            cornerCase1[i] = new Point(1, 1);
            cornerCase2[i] = new Point(1, 1);
        }
        cornerCase2[5] = null;
        collinearPoints[0] = new Point(1, 1);
        collinearPoints[1] = new Point(2, 2);
        collinearPoints[2] = new Point(3, 3);
        collinearPoints[3] = new Point(4, 4);
        collinearPoints[4] = new Point(3, 2);
        collinearPoints[5] = new Point(5, 3);
        collinearPoints[6] = new Point(7, 4);
        collinearPoints[7] = new Point(4, 2);
        collinearPoints[8] = new Point(7, 3);
        collinearPoints[9] = new Point(10, 4);
        collinearPoints[10] = new Point(1, 2);
        for (int i = 0; i < 10; i++) {
            collinearPoints2[i] = new Point(i, i);
        }
    }

    @Test
    public void testNonCollinearPoints() {
        BruteCollinearPoints points = new BruteCollinearPoints(nonCollinearPoints);
        assertThat(points.numberOfSegments(), is(0));
        assertThat(points.segments().length, is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCorners() {
        BruteCollinearPoints points = new BruteCollinearPoints(cornerCase1);
    }

    @Test(expected = NullPointerException.class)
    public void testCorners2() {
        BruteCollinearPoints points = new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testCorners3() {
        BruteCollinearPoints points = new BruteCollinearPoints(cornerCase2);
    }

    @Test
    public void testCollinearPoints() {
        BruteCollinearPoints points = new BruteCollinearPoints(collinearPoints);
        assertThat(points.numberOfSegments(), is(4));
    }

    @Test
    public void testCollinearPoints2() {
        BruteCollinearPoints points = new BruteCollinearPoints(collinearPoints2);
        assertThat(points.numberOfSegments(), is(210));
    }
}
