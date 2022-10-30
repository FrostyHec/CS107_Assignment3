import java.util.*;

//糟糕的代码
//一堆史山了，我tm真的吐了
//又要找点，又要去重，又要排序，还好只有两个值可以用map然后开摆，否则我真的要紫砂了
//能不能遍历n^2棋盘上所有点的8个方向啊，感觉这样会比我这种行扫描简单一点？这个方法期中考完再写好了
//回退还要用kmp我不会我直接开摆了
public class Question5 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //思路：暴力扫描行、列、左斜、右斜
        int[][] chessBoard = generateChessBoard(sc.nextInt());

        //离谱啊居然还有一子多杀的鬼情况，我直接用TreeMap开摆了
        Map<Integer, Integer> result = new TreeMap<>();
        rowCheck(chessBoard, result);
        //打印
        for (int x : result.keySet()) {//key是x,value是y（别打我写的真的很臭）
            System.out.println((x + 1) + " " + (result.get(x) + 1));
        }
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

    static void rowCheck(int[][] chessBoard, Map<Integer, Integer> result) {

        int size = chessBoard[0].length;
        for (int i = 0; i < size; i++) {//行
            for (int j = 0; j < size - 3; j++) {//列,只剩三个才黑的话，五连生涯也就结束了罢
                if (chessBoard[i][j] == 1) {
                    int[] temp = new int[2];
                    if (j == size - 4) {//右边被堵住的特殊情况
                        boolean isWins = true;
                        for (int k = j + 1; k < size; k++) {
                            if (chessBoard[i][k] == 0) {
                                isWins = false;
                                break;
                            }
                        }
                        if (isWins) {//赢了
                            result.put(i, j - 1);
                        }

                    } else {//一般情况
                        boolean isWins = true;
                        for (int k = j + 1; k < j + 5; k++) {
                            if (chessBoard[i][k] == 0) {
                                if (temp[1] != 0) {//输入过一次坐标了，由于间断点不可能在边界所以其实不是0就一定输入过一次
                                    isWins = false;
                                    break;
                                } else {
                                    temp[0] = i;
                                    temp[1] = k;
                                }
                            }
                        }

                        if (isWins) {//赢了
                            if (temp[1] != 0) {
                                result.put(temp[0], temp[1]);
                            } else {
                                result.put(i, j - 1);
                                result.put(i, j + 4);
                            }
                        }
                        //真滴烦，要是真要回退就得用kmp了把，我不懂
                        //直接搞个烂方法开摆
                        //复杂度好高，n*n*n*m(五子棋m=5)
                    }
                }
            }
        }
    }

    static void columnCheck(int[][] chessBoard, Set<int[]> result) {
        int size = chessBoard[0].length;
        for (int i = 0; i < size; i++) {//行
            for (int j = 0; j < size - 3; j++) {//列,只剩三个才黑的话，五连生涯也就结束了罢
                if (chessBoard[i][j] == 1) {
                    int[] temp = new int[2];
                    if (j == size - 4) {//右边被堵住的特殊情况
                        boolean isWins = true;
                        for (int k = j + 1; k < size; k++) {
                            if (chessBoard[i][k] == 0) {
                                isWins = false;
                                break;
                            }
                        }
                        if (isWins) {//赢了
                            int[] first = {i, j - 1};
                            result.add(first);
                        }

                    } else {//一般情况
                        boolean isWins = true;
                        for (int k = j + 1; k < j + 5; k++) {
                            if (chessBoard[i][k] == 0) {
                                if (temp[1] != 0) {//输入过一次坐标了，由于间断点不可能在边界所以其实不是0就一定输入过一次
                                    isWins = false;
                                    break;
                                } else {
                                    temp[0] = i;
                                    temp[1] = k;
                                }
                            }
                        }

                        if (isWins) {//赢了
                            if (temp[1] != 0) {
                                result.add(temp);
                            } else {
                                int[] first = {i, j - 1};
                                result.add(first);
                                int[] second = {i, j + 4};
                                result.add(second);
                            }
                        }
                        //真滴烦，要是真要回退就得用kmp了把，我不懂
                        //直接搞个烂方法开摆
                        //复杂度好高，n*n*n*m(五子棋m=5)
                    }
                }
            }
        }
    }

}
