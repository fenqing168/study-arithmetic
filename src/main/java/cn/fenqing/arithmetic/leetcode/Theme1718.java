package cn.fenqing.arithmetic.leetcode;

import java.util.Arrays;

/**
 * 未解决
 * @author fenqing
 */
public class Theme1718 {

    public int[] constructDistancedSequence(int n) {
        int[] res = new int[(n << 1) - 1];
        constructDistancedSequence(n, 0, res);
        return res;
    }

    public boolean constructDistancedSequence(int n, int level, int[] res) {
        for (int i = 1; i <= n; i++) {
            if(verify(level, i, res)){
                res[level] = i;
                if(level + 1 >= res.length && verify(res)){
                    return true;
                }
                constructDistancedSequence(n, level + 1, res);
            }
        }
        return false;
    }

    public boolean verify(int index, int num, int[] arr){
        for (int i = index - 1; i >= 0; i--) {
            //如果num是1，校验是否已经存在1
            if(num == 1 && arr[i] == 1){
                return false;
            }
            //如果num是1以上，校验是否存在第二个num，如果存在，他们之间的距离是否为num
            if(num == arr[i]){
                return index - i == num;
            }
        }
        return true;
    }

    public boolean verify(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if(num == 1){
                for (int j = i + 1; j < arr.length; j++) {
                    if(arr[j] == 1){
                        return false;
                    }
                }
            } else {
                int num1 = 0;
                for (int j = i + 1; j < arr.length; j++) {
                    if(arr[j] == num){
                        num1++;
                        if(j - i != num){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Theme1718().constructDistancedSequence(3)));
    }

}
