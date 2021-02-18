package cn.fenqing.arithmetic.sort;

import cn.fenqing.test.RunTime;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Administrator
 */
public class Main {

    enum Env {
        /**
         * TEST为测试是否正确
         * RUN_TIME为测试效率
         */
        TEST(8, true),
        RUN_TIME(80000, false);
        int size;
        boolean print;

        Env(int size, boolean print) {
            this.size = size;
            this.print = print;
        }
    }

    static Env env = Env.RUN_TIME;


    public static void main(String[] args) {

        int[] nums = new int[env.size];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(env.size);
        }
        //冒泡排序
        System.out.println("冒泡排序");
        testBubbleSort(nums.clone());
        System.out.println("==============================");
        System.out.println("系统自带排序");
        testArraysSort(nums.clone());
        System.out.println("==============================");
        System.out.println("选择排序");
        testSelectSort(nums.clone());
        System.out.println("==============================");
        System.out.println("插入排序");
        testInsertSort(nums.clone());
        System.out.println("==============================");
    }

    /**
     * 冒泡排序
     */
    public static void testBubbleSort(int[] nums) {
        Sort sort = new BubbleSort();
        //排序前
        if(env.print){
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> sort.sort(nums), "耗时：");
        if(env.print){
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 选择排序
     */
    public static void testSelectSort(int[] nums) {
        Sort sort = new SelectSort();
        //排序前
        if(env.print){
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> sort.sort(nums), "耗时：");
        if(env.print){
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 插入排序
     */
    public static void testInsertSort(int[] nums) {
        Sort sort = new InsertSort();
        //排序前
        if(env.print){
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> sort.sort(nums), "耗时：");
        if(env.print){
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 自带的排序
     */
    public static void testArraysSort(int[] nums) {
        Sort sort = new BubbleSort();
        //排序前
        if(env.print){
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> Arrays.sort(nums), "耗时：");
        if(env.print){
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

}
