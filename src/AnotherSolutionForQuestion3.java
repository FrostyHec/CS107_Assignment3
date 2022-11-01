import java.util.Scanner;

public class AnotherSolutionForQuestion3 {
    static Scanner sc = new Scanner(System.in);
    static final int origin = 101, size = origin * 2;
    //原点就是(origin,origin)
    static Area[][] canvas = new Area[size][size];

    public static void main(String[] args) {
        //initialize
        double area = 0;

        //input
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int shape = sc.nextInt(), x = sc.nextInt() + origin, y = sc.nextInt() + origin;
            if (shape == 2) {//矩形
                generateSquare(x, y);
            } else {//圆形
                generateCircle(x, y);
            }
        }

        //getValue
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (canvas[i][j] != null) {
                    area += canvas[i][j].getValue();
                }
            }
        }

        //output
        System.out.printf("%.5f", area);
    }

    static void generateSquare(int startX, int startY) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (canvas[startX - i][startY - j] == null) {
                    canvas[startX - i][startY - j] = new Area();
                }
                canvas[startX - i][startY - j].generateSquare();
            }
        }

    }

    static void generateCircle(int startX, int startY) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (canvas[startX - i][startY - j] == null) {
                    canvas[startX - i][startY - j] = new Area();
                }
            }
        }
        canvas[startX][startY].generateCircle(2);
        canvas[startX][startY - 1].generateCircle(3);
        canvas[startX - 1][startY].generateCircle(1);
        canvas[startX - 1][startY - 1].generateCircle(4);
    }
}

class Area {
    public Shape shape = Shape.Empty;
    //1是左上/上，2是右上/右，3是右下/下，4是左下/左
    public int direction;

    enum Shape {
        SemiCircle, MultiCircle, Square, Empty
    }

    public double getValue() {
        return switch (shape) {
            case Square -> 1;
            case SemiCircle -> Math.PI / 4;
            case MultiCircle -> Math.pow(3, 0.5) / 4 + Math.PI / 6;
            default -> 0;
        };
    }

    public void generateSquare() {
        shape = Shape.Square;
    }

    public void generateCircle(int direction) {
        switch (shape) {
            case SemiCircle:
                //半圆-重叠圆；12->1,23->2,34->3,41->4
                switch (Math.abs(this.direction - direction)) {
                    //对角互补为正方形
                    case 2:
                        generateSquare();
                        break;
                    //其它三个变成Multicircle的情况
                    case 3://4-1=3
                        shape = Shape.MultiCircle;
                        this.direction = 4;
                        break;
                    case 1:
                        shape = Shape.MultiCircle;
                        this.direction = Math.min(direction, this.direction);
                        break;
                    //又是自己
                    default:
                        break;
                }
                break;
            case MultiCircle:
                int temp = Math.abs(this.direction - direction);
                if (!(temp == 3 || temp == 1)) {//不是相邻的
                    generateSquare();
                }
                break;
            case Empty:
                shape = Shape.SemiCircle;
                this.direction = direction;
            default:
                break;
        }
    }

}
