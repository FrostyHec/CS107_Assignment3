import java.util.Scanner;

public class Question2 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int size = sc.nextInt();
        int[][] matrix = inputMatrix(size);
        int result = 0;

        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 1) {
                    result += 4;
                    if (rightExistence(matrix, i, j)) {
                        result--;
                    }
                    if (downExistence(matrix, i, j)) {
                        result--;
                    }
                }
            }
        }

        //Print
        System.out.println(result);
    }

    static int[][] inputMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {//row
            for (int j = 0; j < matrix.length; j++) {//column
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    static boolean rightExistence(int[][] matrix, int row, int column) {
        if (row == matrix[0].length - 1 || column == matrix[0].length - 1) {
            return false;
        } else if (matrix[row + 1][column] == 1) {
            return true;
        } else {
            return false;
        }
    }

    static boolean downExistence(int[][] matrix, int row, int column) {
        if (row == matrix[0].length - 1 || column == matrix[0].length - 1) {
            return false;
        } else if (matrix[row][column + 1] == 1) {
            return true;
        } else {
            return false;
        }
    }
}
