package cn.fenqing.arithmetic.recursion;

/**
 * @author Administrator
 */
public class RecursionTest {

    public static void main(String[] args) {
//        test(5);
        System.out.println(factorial(3));
    }

    public static void test(int n){
        if(n > 2){
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    public static int factorial(int n){
        if(n == 1){
            return 1;
        }
        return factorial(n - 1) * n;
    }

}
