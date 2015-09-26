import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vks on 25.09.2015.
 */
public class TestFastCollinearPoints {

    private static Point[] nonCollinearPoints;
    private static Point[] collinearPoints;
    private static Point[] collinearPoints2;

    @BeforeClass
    public static void initPoints() {
        nonCollinearPoints = new Point[10];
        collinearPoints = new Point[12];
        collinearPoints2 = new Point[10];

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
        collinearPoints[11] = new Point(0, 0);

        for (int i = 0; i < 10; i++) {
            collinearPoints2[i] = new Point(i, i);
        }
    }

    @Test
    public void testNonCollinearPoints() {
        FastCollinearPoints points = new FastCollinearPoints(nonCollinearPoints);
        assertThat(points.numberOfSegments(), is(0));
        assertThat(points.segments().length, is(0));
    }

    @Test
    public void testCollinearPoints() {
        FastCollinearPoints points = new FastCollinearPoints(collinearPoints);
        assertThat(points.numberOfSegments(), is(4));
    }

    @Test
    public void testCollinearPoints2() {
        FastCollinearPoints points = new FastCollinearPoints(collinearPoints2);
        assertThat(points.numberOfSegments(), is(1));
    }
}
