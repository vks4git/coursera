import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vks on 13.09.2015.
 */
public class TestingDeque {

    @Test
    public void testConstructor() {
        Deque<Integer> deque = new Deque<>();
        assertThat(deque.isEmpty(), is(true));
    }

    @Test
    public void testIterator() {
        Deque<Integer> deque = new Deque<>();
        Iterator iterator = deque.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNext() {
        Deque<Integer> deque = new Deque<>();
        Iterator iterator = deque.iterator();
        iterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Deque<Integer> deque = new Deque<>();
        Iterator iterator = deque.iterator();
        iterator.remove();
    }

    @Test
    public void testAddToFront() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        deque.addFirst(10);
        assertThat(deque.removeFirst(), is(10));
    }

    @Test
    public void testAddToBack() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        deque.addLast(10);
        assertThat(deque.removeFirst(), is(5));
    }

    @Test
    public void testRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(5);
        deque.addLast(10);
        assertThat(deque.removeLast(), is(10));
        assertThat(deque.removeFirst(), is(5));
    }

    @Test
    public void testForeach() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        int sum = 0;
        for (int s : deque) {
            sum += s;
        }
        assertThat(sum, is(15));
    }

    @Test
    public void testForeach2() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        int sum = 0;
        for (int s : deque) {
            sum += s;
        }
        assertThat(sum, is(1));
    }

    @Test
    public void testForeach3() {
        Deque<Integer> deque = new Deque<>();
        int sum = 0;
        for (int s : deque) {
            sum += s;
        }
        assertThat(sum, is(0));
    }

    @Test()
    public void testDequeOps() {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10000; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 5000; i++) {
            deque.removeLast();
        }
        for (int i = 0; i < 10000; i++) {
            deque.addLast(i);
        }
        assertThat(deque.isEmpty(), is(false));
        assertThat(deque.size(), is(15000));
    }

    @Test(timeout = 1000)
    public void testTime() {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 900000; i++) {
            deque.addFirst(i);
        }
    }

    @Test(timeout = 5000)
    public void testTime2() {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 4000000; i++) {
            deque.addFirst(i);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }
}
