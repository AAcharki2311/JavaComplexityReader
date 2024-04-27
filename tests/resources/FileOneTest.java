package resources;

public class FileOneTest {

    public void one() {
        System.out.println("This is a simple method.");
    }

    public void two(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Iteration " + (i + 1));
        }
    }

    public void three(int x) {
        if (x > 0) {
            System.out.println("Positive number");
        } else if (x < 0) {
            System.out.println("Negative number");
        } else {
            System.out.println("Zero");
        }
    }

    public void Four(int rows, int columns) {
        for (int i = 1; i <= rows; i++) {
            System.out.println();
        }
        if (1 == 1) {
        }
        while (1 == 1) {
        }
    }

    public void Five(int rows, int columns) {
        for (int i = 1; i <= rows; i++) {
            System.out.println();
        }
        if (1 == 1) {
            if (21 == 21) {
            }
        }
        while (1 == 1) {
        }
    }

}