import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by vks on 13.09.2015.
 */
public class Main {
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i : queue) {
            System.out.println(i);
        }
    }
}
