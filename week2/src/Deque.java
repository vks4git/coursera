import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by vks on 13.09.2015.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current;

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return (current == null) && (size != 0) || (size != 0) && (current.next != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = first;
            } else {
                current = current.next;
            }
            return current.item;
        }
    }

    private int size;

    private Node first;
    private Node last;


    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newItem = new Node();
        newItem.item = item;
        if (size == 0) {
            first = newItem;
            last = newItem;
        } else {
            first.prev = newItem;
            newItem.next = first;
            first = newItem;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newItem = new Node();
        newItem.item = item;
        if (size == 0) {
            first = newItem;
            last = newItem;
        } else {
            newItem.prev = last;
            last.next = newItem;
            last = newItem;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
    }// unit testing
}
