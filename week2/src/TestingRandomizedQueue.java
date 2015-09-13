import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by vks on 13.09.2015.
 */
public class TestingRandomizedQueue {
    @Test
    public void testConstructor() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assertThat(queue.isEmpty(), is(true));
    }

    @Test
    public void testIterator() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Iterator iterator = queue.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNext() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Iterator iterator = queue.iterator();
        iterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Iterator iterator = queue.iterator();
        iterator.remove();
    }

    @Test
    public void testEnqueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertThat(queue.size(), is(3));
    }

    @Test
    public void testEnqueue2() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        assertThat(queue.size(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void testSample() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.sample();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.dequeue();
    }

    @Test
    public void testForeach() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        int sum = 0;
        for (int s : queue) {
            sum += s;
        }
        assertThat(sum, is(4950));
    }

    @Test
    public void testForeach2() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        int sum = 0;
        for (int s : queue) {
            sum += s;
        }
        assertThat(sum, is(1));
    }

    @Test
    public void testForeach3() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int sum = 0;
        for (int s : queue) {
            sum += s;
        }
        assertThat(sum, is(0));
    }

    @Test
    public void testEmpty() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        assertThat(queue.isEmpty(), is(false));
    }

    @Test
    public void testEmpty2() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            queue.dequeue();
        }
        assertThat(queue.isEmpty(), is(true));
    }


}
