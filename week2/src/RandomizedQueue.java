import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by vks on 13.09.2015.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private int count;
    private Item[] array;

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] iteratorArray;
        private int iteratorElementsCount;

        public RandomizedQueueIterator() {
            iteratorArray = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                iteratorArray[i] = array[i];
            }
            iteratorElementsCount = count;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return (iteratorElementsCount != 0);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            swap();
            Item item = iteratorArray[iteratorElementsCount - 1];
            iteratorArray[iteratorElementsCount - 1] = null;
            iteratorElementsCount--;
            return item;
        }

        private void swap() {
            int i = StdRandom.uniform(iteratorElementsCount);
            Item randomItem = iteratorArray[i];
            Item last = iteratorArray[iteratorElementsCount - 1];
            iteratorArray[i] = last;
            iteratorArray[iteratorElementsCount - 1] = randomItem;
        }
    }

    public RandomizedQueue() {
        size = 1;
        count = 0;
        array = (Item[]) new Object[1]; //feel really sorry for that
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == count) {
            size *= 2;
            Item[] newArray = (Item[]) new Object[size];
            for (int i = 0; i < count; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[count++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        swap();
        Item randomItem = array[count - 1];
        array[count - 1] = null;
        count--;
        if (count * 4 == size) {
            size /= 2;
            Item[] newArray = (Item[]) new Object[size];
            for (int i = 0; i < count; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        return randomItem;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(count);
        return array[randomIndex];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void swap() {
        int i = StdRandom.uniform(count);
        Item randomItem = array[i];
        Item last = array[count - 1];
        array[i] = last;
        array[count - 1] = randomItem;
    }

    public static void main(String[] args) {
    }
}
