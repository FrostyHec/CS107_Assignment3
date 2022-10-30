import java.util.*;

//糟糕的代码
//一堆史山了，我tm真的吐了
//又要找点，又要去重，又要排序，还好只有两个值可以用map然后开摆，否则我真的要紫砂了
//能不能遍历n^2的棋盘上所有点的8个方向啊，感觉这样会比我这种行扫描简单一点？这个方法期中考完再写好了
//回退还要用kmp我不会我直接开摆了
public class Question5 {

    //注意行为x列为y
    //感觉chessboard这些可以直接搞在这里作为类的公共变量，不用无语地传来传去
    private static int[][] chessBoard;

    //离谱啊居然还有一子多杀的鬼情况，我直接用HashSet开摆了
    //摆不了大无语，貌似数组哈希出来居然是不一样的，我不懂了
    //private static Set<int[]> result = new HashSet<>();
    private static List<int[]> result = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //思路：暴力扫描行、列、左斜、右斜
        chessBoard = generateChessBoard(sc.nextInt());


        //判断与添加
        rowCheck();
        columnCheck();
        MainDiagonal.check();
        SubDiagonal.check();

        //提前返回
        if (result.isEmpty()) {
            System.out.println("-1");
            return;
        }


        //排序
        int[][] arr = new int[result.size()][2];
        int counter = 0;
        for (int[] x : result) {//set转数组
            arr[counter][0] = x[0];
            arr[counter][1] = x[1];
            counter++;
        }
        Arrays.sort(arr, (e1, e2) -> (e1[0] == e2[0] ? (e1[1] - e2[1]) : (e1[0] - e2[0])));//正则表达式排序

        //打印(打印的时候跳过重复的)
        int[] lastOne = new int[2];
        for (int[] x : arr) {
            if (Arrays.equals(x, lastOne)) {
                continue;
            }
            System.out.println((x[0] + 1) + " " + (x[1] + 1));
            lastOne = x;
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

    static void rowCheck() {
        int size = chessBoard.length;
        for (int x = 0; x < size; x++) {//行
            List<Integer> list = checker(chessBoard[x]);
            for (int y : list) {
                result.add(new int[]{x, y});
            }
        }

    }

    static void columnCheck() {
        int size = chessBoard.length;

        //想不到居然是从question1抄的转置
        int[][] transpose = new int[size][size];
        for (int i = 0; i < size; i++) {//row
            for (int j = 0; j < size; j++) {//column
                transpose[j][i] = chessBoard[i][j];
            }
        }

        for (int y = 0; y < size; y++) {//行
            List<Integer> list = checker(transpose[y]);
            for (int x : list) {//返回的是一堆x值
                result.add(new int[]{x, y});
            }
        }

    }

    static List<Integer> checker(int[] line) {//返回有多少个y是杀点
        int size = line.length;
        List<Integer> re = new ArrayList<>();
        for (int j = 0; j < size - 3; j++) {//列,只剩三个才黑的话，五连生涯也就结束了罢
            if (line[j] == 0) {
                continue;
            }//后面的就是==1，实在太大一个了，这样整理代码干净一点
            int black = 1;
            int interval = 0;
            for (int k = j + 1; k < j + 5 && k < size && black < 4; k++) {
                if (line[k] == 0) {
                    interval = k;
                } else {
                    black++;
                }
            }

            if (black == 4) {//赢了
                if (interval != 0) {//有间断点不可能为0，为0说明没有间断点
                    re.add(interval);
                } else {
                    if (j > 0) {//说明不从边界开始
                        re.add(j - 1);
                    }
                    if (j < size - 4) {//说明不在边界结束
                        re.add(j + 4);
                    }
                }
            }
        }
        return re;
    }

    static class MainDiagonal {
        static public void check() {
            for (int y = chessBoard.length - 1; y >= 0; y--) {
                List<Integer> list = checker(split(0, y));
                for (int i : list) {//返回的是一堆x值
                    result.add(new int[]{i, y + i});
                }
            }
            for (int x = 1; x < chessBoard.length; x++) {
                List<Integer> list = checker(split(x, 0));
                for (int i : list) {//返回的是一堆x值
                    result.add(new int[]{x + i, i});
                }
            }
        }

        static private int[] split(int startX, int startY) {
            int[] re = new int[chessBoard.length - Math.max(startX, startY)];
            for (int i = 0; i < re.length; i++) {
                re[i] = chessBoard[startX + i][startY + i];
            }
            return re;
        }
    }

    static class SubDiagonal {
        static public void check() {
            for (int y = 0; y < chessBoard.length; y++) {
                List<Integer> list = checker(split(0, y));
                for (int i : list) {//返回的是一堆x值
                    result.add(new int[]{i, y - i});
                }
            }
            for (int x = 1; x < chessBoard.length; x++) {
                List<Integer> list = checker(split(x, chessBoard.length - 1));
                for (int i : list) {//返回的是一堆x值
                    result.add(new int[]{i + x, chessBoard.length - 1 - i});
                }
            }
        }

        static private int[] split(int startX, int startY) {
            int[] re = new int[startY - startX + 1];
            for (int i = 0; i < re.length; i++) {
                re[i] = chessBoard[startX + i][startY - i];
            }
            return re;
        }
    }


}
