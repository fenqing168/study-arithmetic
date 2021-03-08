package cn.fenqing.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Theme22 {

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        char[] chars = new char[n << 1];
        generateParenthesis(0, 0, 0, n, list, chars);
        return list;
    }

    /**
     * 1.每一步都只有两种情况，要么填左括号，要么填右括号。
     * 2.先填左括号，在左括号填之前，先判断是否能够填，不能则直接填右括号
     * 3.如果可以填左括号，任然需要在此处填右括号，当然也需要判断是否可以填
     * 4.到最后一步的时候进行判断是否成功，成功的进入结果集
     */
    public static void generateParenthesis(int left, int right, int cur, int n, List<String> list, char[] chars){
        //到了最后，直接判断
        if(cur >= chars.length){
            list.add(new String(chars));
            return;
        }
        if(left < n){
            chars[cur] = '(';
            generateParenthesis(left + 1, right, cur + 1, n, list, chars);
        }
        if(right < left){
            chars[cur] = ')';
            generateParenthesis(left, right + 1, cur + 1, n, list, chars);
        }
    }

}
