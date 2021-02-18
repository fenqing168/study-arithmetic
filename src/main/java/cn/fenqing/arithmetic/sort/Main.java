package cn.fenqing.arithmetic.sort;

import cn.fenqing.test.RunTime;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Administrator
 */
public class Main {

    static int size = 80000;

    public static void main(String[] args) {
        //冒泡排序
        System.out.println("冒泡排序");
        testBubbleSort();
        System.out.println("系统自带排序");
        testArraysSort();
    }

    /**
     * 冒泡排序
     */
    public static void testBubbleSort(){
        Sort sort = new BubbleSort();
        int[] nums = new int[size];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(size);
        }
        //排序前
//        System.out.println("排序前：" + Arrays.toString(nums));
        RunTime.synthesizeTest(() -> sort.sort(nums), "耗时：");
//        System.out.println("排序后：" + Arrays.toString(nums));
    }

    /**
     * 自带的排序
     */
    public static void testArraysSort(){
        Sort sort = new BubbleSort();
        int[] nums = new int[size];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(size);
        }
        //排序前
//        System.out.println("排序前：" + Arrays.toString(nums));
        RunTime.synthesizeTest(() -> Arrays.sort(nums), "耗时：");
//        System.out.println("排序后：" + Arrays.toString(nums));
    }

}
