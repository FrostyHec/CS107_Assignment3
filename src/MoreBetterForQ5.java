import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoreBetterForQ5 {
    //完全使用writeup3的方法
    //之前一直想着怎么映射行列左右斜到矩阵，现在看不用这么麻烦
    //不要为了复用而复用……投影再还回其实比直接复制四遍稍作修改更麻烦……
    static int[][] chessBoard;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        chessBoard = generateChessBoard(sc.nextInt());
        List<int[]> ans = new ArrayList<>();

        for (int x = 1; x < chessBoard.length; x++) {
            for (int y = 1; y < chessBoard.length; y++) {
                if (Checker(x, y)) {
                    ans.add(new int[]{x, y});
                }
            }
        }

        for (int[] x : ans) {
            System.out.println((x[0] + 1) + " " + (x[1] + 1));
        }
    }

    static int[][] generateChessBoard(int size) {
        int[][] re = new int[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {//边界改为-1，这样后面就不用判断边界了
            re[size + 1][i] = re[0][i] = re[i][size + 1] = re[i][0] = -1;
        }
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                re[i][j] = sc.nextInt();
            }
        }
        return re;
    }

    static boolean Checker(int x, int y) {
        return rowChecker(x, y) || columnChecker(x, y) || mainDiagonal(x, y) || subDiagonal(x, y);
    }

    static boolean rowChecker(int x, int y) {
        if (chessBoard[x][y] == 0) {
            int left = y - 1, right = y + 1;
            while (right - left <= 5) {
                if (chessBoard[x][left] != 1 && chessBoard[x][right] != 1) {
                    return false;
                }
                if (chessBoard[x][left] == 1) {
                    left--;
                }
                if (chessBoard[x][right] == 1) {
                    right++;
                }
            }
            return true;
        }
        return false;
    }

    static boolean columnChecker(int x, int y) {
        if (chessBoard[x][y] == 0) {
            int up = x - 1, down = x + 1;
            while (down - up <= 5) {
                if (chessBoard[up][x] != 1 && chessBoard[down][x] != 1) {
                    return false;
                }
                if (chessBoard[up][x] == 1) {
                    up--;
                }
                if (down < chessBoard.length && chessBoard[down][x] == 1) {
                    down++;
                }
            }
            return true;
        }
        return false;
    }

    static boolean mainDiagonal(int x, int y) {
        if (chessBoard[x][y] == 0) {
            int left = 1, right = 1;
            while (right - left <= 5) {
                if (chessBoard[x - left][y - left] != 1 && chessBoard[x + right][y + right] != 1) {
                    return false;
                }
                if (chessBoard[x - left][y - left] == 1) {
                    left--;
                }
                if (chessBoard[x + right][y + right] == 1) {
                    right++;
                }
            }
            return true;
        }
        return false;
    }

    static boolean subDiagonal(int x, int y) {
        if (chessBoard[x][y] == 0) {
            int up = 1, down = 1;
            while (down - up <= 5) {
                if (chessBoard[x - up][y + up] != 1 && chessBoard[x + down][y - down] != 1) {
                    return false;
                }
                if (chessBoard[x - up][y + up] == 1) {
                    up--;
                }
                if (chessBoard[x + down][y - down] == 1) {
                    down++;
                }
            }
            return true;
        }
        return false;
    }


}
