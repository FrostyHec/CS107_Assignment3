import java.util.Scanner;

public class Question2 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {//Another thought
        int[][] matrix = inputMatrix(sc.nextInt());
        int result = 0;

        for (int i = 1; i < matrix.length-1; i++) {//row
            for (int j = 1; j < matrix.length-1; j++) {
                if (matrix[i][j] == 1) {
                    result += 4 - matrix[i + 1][j] - matrix[i - 1][j] - matrix[i][j + 1] - matrix[i][j - 1];
                }
            }
        }
        //Print
        System.out.println(result);

    }

    static int[][] inputMatrix(int size) {//多搞周围一圈
        int[][] matrix = new int[size + 2][size + 2];
        for (int i = 1; i < matrix.length - 1; i++) {//row
            for (int j = 1; j < matrix.length - 1; j++) {//column
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }
}
