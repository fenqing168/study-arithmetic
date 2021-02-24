package cn.fenqing.arithmetic.search;

import cn.fenqing.arithmetic.sort.Main;
import cn.fenqing.test.RunTime;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fenqing
 */
public class SearchMain {

    static Search SEQ = new SeqSearch();

    enum Env {
        /**
         * TEST为测试是否正确
         * RUN_TIME为测试效率
         */
        TEST(8, true),
        RUN_TIME(8000000, false),
        VERIFY(80, false);
        int size;
        boolean print;

        Env(int size, boolean print) {
            this.size = size;
            this.print = print;
        }
    }

    static Env env = Env.RUN_TIME;

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[env.size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(env.size) - env.size / 2;
        }
        int val = arr[random.nextInt(env.size)];
        System.out.println("=====================");
        System.out.println("线性查找");
        if(env.print){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("查找的值为" + val);
        int search = RunTime.synthesizeTest(() -> SEQ.search(arr, val), "耗时");
        System.out.println("查找到的值为" + arr[search] + ",下标为" + search);
    }

}
