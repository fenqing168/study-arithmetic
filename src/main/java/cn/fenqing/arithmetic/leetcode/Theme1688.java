package cn.fenqing.arithmetic.leetcode;

public class Theme1688 {

    public int numberOfMatches(int n) {
        if(n == 1){
            return 0;
        }
        int num = n / 2;
        if(n % 2 == 0){
            return num + numberOfMatches(num);
        }else{
            return num + numberOfMatches(num + 1);
        }
    }

}
