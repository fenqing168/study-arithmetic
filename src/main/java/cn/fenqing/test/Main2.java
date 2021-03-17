package cn.fenqing.test;

import java.util.LinkedList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long start, end;
        long sum = 0;
        start = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        end = System.nanoTime();

        System.out.println(sum);
        System.out.println("下标：" + (end - start));
        sum = 0;
        start = System.nanoTime();
        for (Integer integer : list) {
            sum += list.get(integer);
        }
        end = System.nanoTime();
        System.out.println(sum);
        System.out.println("foreach：" + (end - start));
    }

}
