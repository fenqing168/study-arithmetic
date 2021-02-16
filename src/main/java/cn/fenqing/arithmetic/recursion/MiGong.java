package cn.fenqing.arithmetic.recursion;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class MiGong {

    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下设置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右设置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            int[] row = map[i];
            System.out.println(Arrays.toString(row));
        }
    }

}
