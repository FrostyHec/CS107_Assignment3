import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BetterSolutionForQ5 {
    //我是小丑
    //writeUp3的遍历空点方法

    static int[][] chessBoard;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int size = sc.nextInt();
        chessBoard = generateChessBoard(size);
        List<int[]> ans = new ArrayList<>();

        //rowChecker
        for (int row = 0; row < size; row++) {
            int[] rowMatrix = chessBoard[row];
            for (int index = 0; index < size; index++) {
                if (Checker(rowMatrix, index)) {
                    ans.add(new int[]{row, index});
                }
            }
        }

        //columnChecker
        for (int column = 0; column < size; column++) {
            //Generate row
            int[] columnMatrix = new int[size];
            for (int i = 0; i < size; i++) {
                columnMatrix[i] = chessBoard[i][column];
            }

            for (int index = 0; index < size; index++) {
                if (Checker(columnMatrix, index)) {
                    ans.add(new int[]{index, column});
                }
            }
        }


    }

    static boolean Checker(int[] row, int index) {
        if (row[index] == 0) {
            int left = index, right = index;
            while (right - left <= 5) {
                boolean changed = false;
                if (left >= 0 && row[left] == 1) {
                    left--;
                    changed = true;
                }
                if (right < row.length && row[right] == 1) {
                    right++;
                    changed = true;
                }
                if (!changed) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    static int[][] generateChessBoard(int size) {
        int[][] re = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                re[i][j] = sc.nextInt();
            }
        }
        return re;
    }

}
