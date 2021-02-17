package cn.fenqing.test;

/**
 * @author fenqing
 */
public class RunTime {

    public static void nanoTest(Runnable runnable, String message){
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        System.out.printf("%s: %d:ns\n", message, (end - start));
    }

    public static void millisecondTest(Runnable runnable, String message){
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        System.out.printf("%s: %d:ms\n", message, (end - start));
    }

}
