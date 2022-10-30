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

        //离谱啊居然还有一子多杀的鬼情况，我直接用HashSet开摆了
        //判断与添加
        Set<int[]> result = new HashSet<>();
        rowCheck(chessBoard, result);
        columnCheck(chessBoard, result);

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


        //打印
        for (int[] x : arr) {//key是x,value是y（别打我写的真的很臭）
            System.out.println((x[0] + 1) + " " + (x[1] + 1));
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

    static void rowCheck(int[][] chessBoard, Set<int[]> result) {
        int size = chessBoard.length;
        for (int x = 0; x < size; x++) {//行
            List<Integer> list = checker(chessBoard[x]);
            for (int y : list) {
                result.add(new int[]{x, y});
            }
        }

    }
    static void columnCheck(int[][] chessBoard, Set<int[]> result) {
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



}
