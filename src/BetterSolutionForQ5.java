import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BetterSolutionForQ5 {
    //我是小丑
    //writeUp3的遍历空点方法//我突然意识到这个还是Question5的优化罢了
    //第一次用github回滚回了上一次的版本
    //整个程序都好梦幻

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

        //MainDiagonalChecker
        for (int yi = 0; yi < size; yi++) {
            //Generate row
            int[] mDMatrix = new int[size];
            Arrays.fill(mDMatrix, -1);//-1为边界
            for (int i = 0; i + yi < size; i++) {//yi是初始y的意思
                mDMatrix[i + yi] = chessBoard[i][i + yi];
            }
            for (int d = 0; d < size; d++) {
                if (Checker(mDMatrix, d)) {
                    ans.add(new int[]{d - yi, d});
                }
            }
        }
        for (int xi = 1; xi < size; xi++) {
            //Generate row
            int[] mDMatrix = new int[size];
            Arrays.fill(mDMatrix, -1);//-1为边界
            for (int i = 0; i + xi < size; i++) {
                mDMatrix[i] = chessBoard[i + xi][i];
            }
            for (int d = 0; d < size; d++) {
                if (Checker(mDMatrix, d)) {
                    ans.add(new int[]{d + xi, d});
                }
            }
        }

        //SubDiagonalChecker
        for (int yi = 0; yi < size; yi++) {
            //Generate row
            int[] mDMatrix = new int[size];
            Arrays.fill(mDMatrix, -1);//-1为边界
            for (int i = 0; yi - i >= 0; i++) {
                mDMatrix[yi - i] = chessBoard[i][yi - i];
            }
            for (int d = 0; d < size; d++) {
                if (Checker(mDMatrix, d)) {
                    ans.add(new int[]{yi - d, d});
                }
            }
        }
        for (int xi = 1; xi < size; xi++) {
            //Generate row
            int[] mDMatrix = new int[size];
            Arrays.fill(mDMatrix, -1);//-1为边界
            for (int i = 0; i + xi < size; i++) {
                mDMatrix[size - i - 1] = chessBoard[i + xi][size - i - 1];
            }
            for (int d = 0; d < size; d++) {
                if (Checker(mDMatrix, d)) {
                    ans.add(new int[]{size - d + xi - 1, d});//这个太梦幻了
                }
            }
        }

        //Print
        {
            if (ans.isEmpty()) {
                System.out.println("-1");
                return;
            }
            int[][] arr = new int[ans.size()][2];
            int counter = 0;
            for (int[] x : ans) {//set转数组
                arr[counter][0] = x[0];
                arr[counter][1] = x[1];
                counter++;
            }
            Arrays.sort(arr, (e1, e2) -> (e1[0] == e2[0] ? (e1[1] - e2[1]) : (e1[0] - e2[0])));//正则表达式排序
            int[] lastOne = new int[]{-1, -1};
            for (int[] x : arr) {
                if (Arrays.equals(x, lastOne)) {
                    continue;
                }
                System.out.println((x[0] + 1) + " " + (x[1] + 1));
                lastOne = x;
            }
        }
    }

    static boolean Checker(int[] row, int index) {
        if (row[index] == 0) {
            int left = index - 1, right = index + 1;
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
