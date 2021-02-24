package cn.fenqing.test;

import java.util.function.Function;
import java.util.function.Supplier;

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

    public static <T> T synthesizeTest(Supplier<T> supplier, String message){
        long start = System.currentTimeMillis();
        long startNano = System.nanoTime();
        T t = supplier.get();
        long end = System.currentTimeMillis();
        long endNano = System.nanoTime();
        System.out.printf("%s: %d:ms\n", message, (end - start));
        System.out.printf("%s: %d:ns\n", message, (endNano - startNano));
        return t;
    }

    public static void synthesizeTest(Runnable runnable, String message){
        long start = System.currentTimeMillis();
        long startNano = System.nanoTime();
        runnable.run();
        long end = System.currentTimeMillis();
        long endNano = System.nanoTime();
        System.out.printf("%s: %d:ms\n", message, (end - start));
        System.out.printf("%s: %d:ns\n", message, (endNano - startNano));
    }

}
