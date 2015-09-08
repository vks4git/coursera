import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by vks on 07.09.2015.
 */
public class Main {

    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_RESET = "\u001b[0m";

    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);

        for (int k = 1; k <= 5; k++) {
            for (int l = 1; l <= 5; l++) {
                if (percolation.isOpen(k, l)) {
                    if (percolation.isFull(k, l)) {
                        System.out.print(ANSI_BLUE + "(f)" + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_GREEN + "(o)" + ANSI_RESET);
                    }
                } else {
                    System.out.print(ANSI_PURPLE + "(c)" + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < 25; i++) {
            System.out.println("Iteration " + (i + 1));
            int x = 1;
            int y = 1;
            while (percolation.isOpen(x, y)) {
                x = StdRandom.uniform(5) + 1;
                y = StdRandom.uniform(5) + 1;
            }
            System.out.println("Attempting to open cell (" + x + " " + y + ")");
            percolation.open(x, y);
            for (int k = 1; k <= 5; k++) {
                for (int l = 1; l <= 5; l++) {
                    if (percolation.isOpen(k, l)) {
                        if (percolation.isFull(k, l)) {
                            System.out.print(ANSI_BLUE + "(f)" + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_GREEN + "(o)" + ANSI_RESET);
                        }
                    } else {
                        System.out.print(ANSI_PURPLE + "(c)" + ANSI_RESET);
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Percolates: " + percolation.percolates());
            System.out.println();
            System.out.println("=====================================================================================");

        }
    }
}
