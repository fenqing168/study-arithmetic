package cn.fenqing.arithmetic.search;

import cn.fenqing.arithmetic.sort.Main;
import cn.fenqing.arithmetic.sort.Sort;
import cn.fenqing.test.RunTime;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fenqing
 */
public class SearchMain {

    static Search SEQ = new SeqSearch(), BINARY = new BinarySearch(), INTERPOLATION = new InterpolationSearch();

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
            arr[i] = random.nextInt(env.size);
        }
        int index = random.nextInt(env.size);
        int[] arrTemp = arr.clone();
        int val = arrTemp[index];
        System.out.println("=====================");
        System.out.println("线性查找");
        if(env.print){
            System.out.println(Arrays.toString(arrTemp));
        }
        System.out.println("查找的值为" + val);
        int[] arrTemp1 = arrTemp;
        int search = RunTime.synthesizeTest(() -> SEQ.search(arrTemp1, val), "耗时");
        System.out.println("查找到的值为" + arrTemp[search] + ",下标为" + search);


        System.out.println("=====================");
        arrTemp = arr.clone();
        Arrays.sort(arrTemp);
        System.out.println("二分查找");
        if(env.print){
            System.out.println(Arrays.toString(arrTemp));
        }
        System.out.println("查找的值为" + val);
        int[] arrTemp2 = arrTemp;
        search = RunTime.synthesizeTest(() -> BINARY.search(arrTemp2, val), "耗时");
        System.out.println("查找到的值为" + arrTemp[search] + ",下标为" + search);

        System.out.println("=====================");
        System.out.println("插值查找");
        int[] arrTemp3 = new int[env.size];
        for (int i = 0; i < arrTemp3.length; i++) {
            arrTemp3[i] = i + 1;
        }
        if(env.print){
            System.out.println(Arrays.toString(arrTemp3));
        }
        int val1 = arrTemp3[random.nextInt(env.size)];
        System.out.println("查找的值为" + val1);

        search = RunTime.synthesizeTest(() -> INTERPOLATION.search(arrTemp3, val1), "耗时");
        System.out.println("查找到的值为" + arrTemp[search] + ",下标为" + search);

    }

}
