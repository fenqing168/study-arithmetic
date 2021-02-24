package cn.fenqing.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
        }
        System.out.println("删除前：==================");
        arr.forEach(System.out::println);
        //删除3
        arr = arr.stream().filter(item -> item != 3).collect(Collectors.toList());
        System.out.println("删除后：==================");
        arr.forEach(System.out::println);
    }

}
