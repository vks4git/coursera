import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
/**
 * Created by vks on 24.09.2015.
 */
public class TestPoint {
    @Test
    public void testConstructor() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        assertThat(a.slopeTo(b), is(1.0));
        assertThat(a.slopeTo(a), is(Double.NEGATIVE_INFINITY));
    }
}
