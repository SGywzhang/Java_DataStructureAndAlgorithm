import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HorseChessboard {

    private static int X; //col
    private static int Y; //row
    private static boolean visited[];
    private static boolean finished;

    /**
     * 完成骑士周游问题的算法
     * @param chessboard 棋盘
     * @param row 马儿当前的位置的行 从 0 开始
     * @param col 马儿当前的位置的列 从 0 开始 * @param step 是第几步 ,初始位置就是第 1 步
     */
    public static void traversalChessboard(int[][]chessboard, int row, int col, int step){
        chessboard[row][col] = step;
        visited[row * X + col] = true;

        List<Point> ps = next(new Point(col,row));

        sort(ps);

        while(!ps.isEmpty()){

            Point p = ps.remove(0);

            if(!visited[p.y * X + p.x]){
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断马儿是否完成了任务，使用 step 和应该走的步数比较 ，
        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // 说明: step < X * Y 成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程

        if(step < X * Y && !finished){
            chessboard[row][col] = 0;
            visited[row * X + col] =false;
        }
        else{
            finished = true;
        }

    }

    public static List<Point> next (Point curPoint){

        List<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        //表示马儿可以走 5 这个位置
        if((p1.x = curPoint.x -2) >=0 && (p1.y = curPoint.y - 1) >=0){
            ps.add(new Point(p1));
        }
        //判断马儿可以走 6 这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1)); }
        //判断马儿可以走 7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1)); }
        //判断马儿可以走 0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1)); }
        //判断马儿可以走 1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1)); }

        //判断马儿可以走 2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void sort(List<Point> ps){
        ps.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {

                int count1 = next(o1).size();
                int count2 = next(o2).size();

                if(count1 < count2){
                    return -1;
                }else if(count1 == count2){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
    }

//    public static void sort(List<Point> ps){
//        ps.sort((o1,o2) ->{
//            int count1 = next(o1).size();
//            int count2 = next(o2).size();
//
//            if(count1 < count2){
//                return -1;
//            }else if(count1 == count2){
//                return 0;
//            }
//            else{
//                return 1;
//            }
//        });
//    }

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");

        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;

        int row = 1; //马儿初始位置的行，从 1 开始编号
        int column = 1; //马儿初始位置的列，从 1 开始编号

        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是 false


        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");

        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
}

