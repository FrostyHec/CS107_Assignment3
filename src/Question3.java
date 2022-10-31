import java.util.Scanner;

public class Question3 {
    static Scanner sc = new Scanner(System.in);
    static final int origin = 101;//原点就是(origin,origin)

    public static void main(String[] args) {
        //可以优化的，但是既然数字只有4w次那就暴力点算了
        int size=origin*2+2;
        shapeKind[][] canvas = new shapeKind[size][size];
        double area = 0;
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (sc.nextInt() == 2) {//矩形
                generateSquare(canvas, sc.nextInt(), sc.nextInt());
            } else {//圆形
                generateCircle(canvas, sc.nextInt(), sc.nextInt());
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                shapeKind x = canvas[i][j];
                if (x != null) {
                    area += x.getValue();
                }
            }
        }
        System.out.printf("%.5f", area);
    }

    public interface shapeKind {//用接口管理枚举的类，否则有九个元素
        //实现完发现我就是个小丑，还不如直接塞九个元素

        double getValue();

        enum Square implements shapeKind {
            standard;
            public final static double value = 1;

            @Override
            public double getValue() {
                return value;
            }
        }

        enum SemiCircle implements shapeKind {
            TopLeft, TopRight, BottomLeft, BottomRight;
            public final static double value = Math.PI / 4;

            @Override
            public double getValue() {
                return value;
            }
        }

        enum MultiCircle implements shapeKind {
            Up, Down, Right, Left;
            public final static double value = Math.pow(3, 0.5)/4 + Math.PI / 6;

            @Override
            public double getValue() {
                return value;
            }
        }
    }

    static void generateCircle(shapeKind[][] carve, int x, int y) {
        x += origin;
        y += origin;

        //这四个太容易错了！！！
        {//topright检测
            shapeKind check = carve[x][y];
            if (check == shapeKind.SemiCircle.BottomRight) {//变成3
                carve[x][y] = shapeKind.MultiCircle.Right;

            } else if (check == shapeKind.SemiCircle.TopLeft) {//变成3
                carve[x][y] = shapeKind.MultiCircle.Up;

            } else if (check == shapeKind.MultiCircle.Right
                    || check == shapeKind.MultiCircle.Up
                    || check == shapeKind.SemiCircle.TopRight) {//不变

            } else if (check == null) {
                carve[x][y] = shapeKind.SemiCircle.TopRight;

            } else {//变成方块
                carve[x][y] = shapeKind.Square.standard;

            }
        }

        {//topleft检测
            shapeKind check = carve[x - 1][y];
            if (check == shapeKind.SemiCircle.BottomLeft) {//变成3
                carve[x - 1][y] = shapeKind.MultiCircle.Left;

            } else if (check == shapeKind.SemiCircle.TopRight) {//变成3
                carve[x - 1][y] = shapeKind.MultiCircle.Up;

            } else if (check == shapeKind.MultiCircle.Left
                    || check == shapeKind.MultiCircle.Up
                    || check == shapeKind.SemiCircle.TopLeft) {//不变

            } else if (check == null) {
                carve[x - 1][y] = shapeKind.SemiCircle.TopLeft;

            } else {//变成方块
                carve[x - 1][y] = shapeKind.Square.standard;

            }
        }

        {//bottomleft检测
            shapeKind check = carve[x - 1][y - 1];
            if (check == shapeKind.SemiCircle.TopLeft) {//变成3
                carve[x - 1][y - 1] = shapeKind.MultiCircle.Left;

            } else if (check == shapeKind.SemiCircle.BottomRight) {//变成3
                carve[x - 1][y - 1] = shapeKind.MultiCircle.Down;

            } else if (check == shapeKind.MultiCircle.Right
                    || check == shapeKind.MultiCircle.Down
                    || check == shapeKind.SemiCircle.BottomLeft) {//不变

            } else if (check == null) {
                carve[x - 1][y - 1] = shapeKind.SemiCircle.BottomLeft;

            } else {//变成方块
                carve[x - 1][y - 1] = shapeKind.Square.standard;

            }
        }

        {//bottomright检测
            shapeKind check = carve[x][y - 1];
            if (check == shapeKind.SemiCircle.BottomLeft) {//变成3
                carve[x][y - 1] = shapeKind.MultiCircle.Down;

            } else if (check == shapeKind.SemiCircle.TopRight) {//变成3
                carve[x][y - 1] = shapeKind.MultiCircle.Right;

            } else if (check == shapeKind.MultiCircle.Right
                    || check == shapeKind.MultiCircle.Down
                    || check == shapeKind.SemiCircle.BottomRight) {//不变

            } else if (check == null) {
                carve[x][y - 1] = shapeKind.SemiCircle.BottomRight;

            } else {//变成方块
                carve[x][y - 1] = shapeKind.Square.standard;
            }
        }

    }

    static void generateSquare(shapeKind[][] carve, int x, int y) {
        x += origin;
        y += origin;
        carve[x][y] = shapeKind.Square.standard;
        carve[x - 1][y] = shapeKind.Square.standard;
        carve[x][y - 1] = shapeKind.Square.standard;
        carve[x - 1][y - 1] = shapeKind.Square.standard;
    }
}
