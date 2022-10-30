import java.util.Scanner;

public class Question4 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] matrix = new int[m][n];

        //同时保持到边界前一位停止，自己手动编译的时候可以看看
        for (int i = 0; i < Math.round(m / 2.0) && i < Math.round(n / 2.0); i++) {//结束条件
            int up = i, down = m - i - 1, right = n - i - 1, left = i;//这个loop所在的一圈
            //其实应该单独写那种能够判断是不是只剩一行或者一列的单拎出来写会方便思考很多，但是我开摆了
            for (int j = up; j < down; j++) {//下
                matrix[j][left] = sc.nextInt();

            }
            for (int j = left; j < right; j++) {//右
                matrix[down][j] = sc.nextInt();
            }
            for (int j = down; j > up; j--) {//上,如果左右同处一列的话，那么上就会覆盖掉，则应该跳出
                matrix[j][right] = sc.nextInt();
                if(left==right){
                    break;
                }
            }
            for (int j = right; j > left; j--) {//左，如果上下同处一列的话，那么上就会覆盖掉，则应该跳出
                matrix[up][j] = sc.nextInt();
                if(up==down){
                    break;
                }
            }
            //完成四个方向的填充后，这个循环将会被填满
        }

        //完全正方体的话，中心格打印不到
        if(m%2==1&&n%2==1){
            matrix[m/2][n/2]=sc.nextInt();
        }

        //打印
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
                if (j != n - 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }

}
