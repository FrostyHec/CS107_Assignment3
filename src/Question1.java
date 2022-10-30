import java.util.Scanner;

public class Question1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //输入一堆matrix并且拆分
        int size = sc.nextInt();
        int[][] matrix1 = inputMatrix(size);
        int[][] matrix2 = inputMatrix(size);

        int[][] A1 = split(matrix1, 0, 0, size / 2);
        int[][] A2 = split(matrix1, 0, size / 2, size / 2);
        int[][] A3 = split(matrix1, size / 2, 0, size / 2);
        int[][] A4 = split(matrix1, size / 2, size / 2, size / 2);

        int[][] B1 = split(matrix2, 0, 0, size / 2);
        int[][] B2 = split(matrix2, 0, size / 2, size / 2);
        int[][] B3 = split(matrix2, size / 2, 0, size / 2);
        int[][] B4 = split(matrix2, size / 2, size / 2, size / 2);

        //操作
        int[][] answer = new int[size][size];
        fill(answer, sum(A1, B1), 0, 0, size / 2);
        fill(answer, mul(A2, transpose(B2)), 0, size / 2, size / 2);
        fill(answer, mul(B3, transpose(A3)), size / 2, 0, size / 2);
        fill(answer, sub(A4, B4), size / 2, size / 2, size / 2);

        //打印
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                System.out.print(answer[i][j]);
                if(j!=size-1){//若不是倒最后一个
                    System.out.print(" ");
                }else {
                    System.out.print("\n");
                }
            }
        }


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

    static int[][] split(int[][] matrix, int row, int column, int size) {
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                re[i][j] = matrix[row + i][column + j];
            }
        }
        return re;
    }

    static int[][] sum(int[][] matrix1, int[][] matrix2) {
        final int size = matrix1[0].length;
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                re[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return re;
    }

    static int[][] sub(int[][] matrix1, int[][] matrix2) {
        final int size = matrix1[0].length;
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                re[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return re;
    }

    static int[][] transpose(int[][] matrix) {
        final int size = matrix[0].length;
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                re[j][i] = matrix[i][j];
            }
        }
        return re;
    }

    static int[][] mul(int[][] matrix1, int[][] matrix2) {
        final int size = matrix1[0].length;
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                for (int k = 0; k < size; k++) {//index
                    re[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return re;
    }

    static void fill(int[][] beFilled, int[][] matrix, int row, int column, int size) {
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                beFilled[row + i][column + j] = matrix[i][j];
            }
        }
    }

}
