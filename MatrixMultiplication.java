import java.util.Random;
import java.util.*;

public class MatrixMultiplication {

    // Creating the matrix
    static int[][] mat;
    static int[][] mat2;
    static int[][] result;

    public static void main(String[] args) {

        // Creating the object of random class
        Scanner sc = new Scanner(System.in);
        int n = 1;
        try {
            while (true) {
                System.out.println("Enter the order for matrices :");
                n = sc.nextInt();
                if (n < 1) {
                    System.out.println("\nOrder of matrices can not be less than 1.\nEnter again");
                    continue;
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input for order of matrices.");
            System.exit(0);
        }
        mat = new int[n][n];
        mat2 = new int[n][n];
        result = new int[n][n];
        System.out.println("\t\t\tEnter the First Matrix : \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    System.out.println("Enter the element at row " + (i + 1) + " column " + (j + 1));
                    mat[i][j] = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("\nInvalid input for an element of matrix.");
                    System.exit(0);
                }
            }
        }
        System.out.println("\n\t\t\tEnter the Second Matrix : \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    System.out.println("Enter the element at row " + (i + 1) + " column " + (j + 1));
                    mat2[i][j] = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("\nInvalid input for an element of matrix.");
                    System.exit(0);
                }
            }
        }

        // Printing the first matrix
        System.out.println("This is first matrix:");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        // Printing the second matrix
        System.out.println("\nThis is second matrix:");
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[i].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println();
        }

        try {
            // Object of multiply Class
            Multiply multiply = new Multiply(n, n);
            for (int i = 0; i < n; i++) {
                Thread object = new Thread(new MatrixMultiplier(multiply));
                object.start();
                object.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Printing the result
        System.out.println("\n\nResult:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }// End main

}// End Class

// Multiply Class
class Multiply extends MatrixMultiplication {

    private int i;
    private int j;
    private int chance;

    public Multiply(int i, int j) {
        this.i = i;
        this.j = j;
        chance = 0;
    }

    // Matrix Multiplication Function
    public synchronized void multiplyMatrix() {

        int sum = 0;
        int a = 0;
        for (a = 0; a < i; a++) {
            sum = 0;
            for (int b = 0; b < j; b++) {
                sum = sum + mat[chance][b] * mat2[b][a];
            }
            result[chance][a] = sum;
        }

        if (chance >= i)
            return;
        chance++;
    }
}// End multiply class

// Thread Class
class MatrixMultiplier implements Runnable {

    private final Multiply mul;

    public MatrixMultiplier(Multiply mul) {
        this.mul = mul;
    }

    @Override
    public void run() {
        mul.multiplyMatrix();
    }
}