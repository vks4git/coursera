import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by vks on 13.09.2015.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            int i = StdRandom.uniform(1000);
            if (i < 500) {
                deque.addFirst(s);
            } else {
                deque.addLast(s);
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.println(deque.removeFirst());
        }
    }
}
