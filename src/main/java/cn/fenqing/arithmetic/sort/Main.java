package cn.fenqing.arithmetic.sort;

import cn.fenqing.test.RunTime;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Administrator
 */
public class Main {

    static Sort BUBBLE_SORT = new BubbleSort(),
            SELECT_SORT = new SelectSort(),
            INSERT_SORT = new InsertSort(),
            SHELL_SORT = new ShellSort(),
            QUICK_SORT = new QuickSort(),
            MEGRGE_SORT = new MergeSort(),
            REDIX_SORT = new RedixSort();

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
    static int VERIFY_TIME = 30000;

    public static void main(String[] args) {

        int[] nums = new int[env.size];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(env.size) - env.size / 2;
        }
        if (env == Env.VERIFY) {
            //冒泡排序
            System.out.println("验证冒泡排序正确性：");
            System.out.println(verify(BUBBLE_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证系统自带排序正确性：");
            System.out.println(verify(Arrays::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证选择排序正确性：");
            System.out.println(verify(SELECT_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证插入排序正确性：");
            System.out.println(verify(INSERT_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证希尔排序正确性：");
            System.out.println(verify(SHELL_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证快速排序正确性：");
            System.out.println(verify(QUICK_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证归并排序正确性：");
            System.out.println(verify(MEGRGE_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
            System.out.println("验证基数排序正确性：");
            System.out.println(verify(REDIX_SORT::sort, VERIFY_TIME) + "次正确");
            System.out.println("==============================");
        } else {
            System.out.println("系统自带排序");
            testArraysSort(nums.clone());
            System.out.println("==============================");
            //冒泡排序
//            System.out.println("冒泡排序");
//            testBubbleSort(nums.clone());
//            System.out.println("==============================");
//            System.out.println("选择排序");
//            testSelectSort(nums.clone());
//            System.out.println("==============================");
//            System.out.println("插入排序");
//            testInsertSort(nums.clone());
//            System.out.println("==============================");
//            System.out.println("希尔排序");
//            testShellSort(nums.clone());
//            System.out.println("==============================");
//            System.out.println("快速排序");
//            testQuickSort(nums.clone());
//            System.out.println("==============================");
//            System.out.println("归并排序");
//            testMergeSort(nums.clone());
//            System.out.println("==============================");
            System.out.println("基数排序");
            testRedixSort(nums.clone());
            System.out.println("==============================");
        }
    }

    /**
     * 校验排序的准确性
     *
     * @param sort 排序方法
     * @return
     */
    private static int verify(Consumer<int[]> sort, int time) {
        int res = 0;
        Random random = new Random();
        int[] nums = new int[env.size];
        for (int i = 0; i < time; i++) {
            for (int j = 0; j < nums.length; j++) {
                nums[j] = random.nextInt(env.size);
            }
            int[] clone1 = nums.clone();
            int[] clone2 = nums.clone();
            Arrays.sort(clone1);
            sort.accept(clone2);
            boolean equals = JSON.toJSONString(clone1).equals(JSON.toJSONString(clone2));
            if (equals) {
                res++;
            }
        }
        return res;
    }

    private static void testRedixSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> REDIX_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    private static void testShellSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> SHELL_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    private static void testQuickSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> QUICK_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    private static void testMergeSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> MEGRGE_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 冒泡排序
     */
    public static void testBubbleSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> BUBBLE_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 选择排序
     */
    public static void testSelectSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> SELECT_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 插入排序
     */
    public static void testInsertSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> INSERT_SORT.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

    /**
     * 自带的排序
     */
    public static void testArraysSort(int[] nums) {
        //排序前
        if (env.print) {
            System.out.println("排序前：" + Arrays.toString(nums));
        }
        RunTime.synthesizeTest(() -> Arrays.sort(nums), "耗时：");
        if (env.print) {
            System.out.println("排序后：" + Arrays.toString(nums));
        }
    }

}
