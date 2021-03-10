package cn.fenqing.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 未完成
 * @author fenqing
 */
public class Theme1291 {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        for (int i = low; i < high; i++) {
            String str = String.valueOf(i);
            int j = 0, z = 1;
            boolean flag = true;
            while (z < str.length()){
                if(str.charAt(j) + 1 != str.charAt(z)){
                    flag = false;
                    break;
                }
                j++;
                z++;
            }
            if(flag){
                list.add(i);
                char[] chars = str.toCharArray();
                for (int i1 = 0; i1 < chars.length - 1; i1++) {
                    chars[i1]++;
                }
                i = Integer.parseInt(new String(chars));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new Theme1291().sequentialDigits(10, 1000000000);
        list.forEach(System.out::println);
    }

}
