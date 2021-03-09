package cn.fenqing.arithmetic.leetcode.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fenqing
 */
public class Theme93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> arr = new ArrayList<>();
        restoreIpAddresses(s, 0, 1, arr, "");
        return arr;
    }

    public void restoreIpAddresses(String s, int start, int level, List<String> res, String up) {
        if(level > 4){
            if(up.length() == s.length() + 3){
                res.add(up);
            }
            return;
        }
        int end = start + 3;
        int num1 = (4 - level) * 3;
        for (int i = start; i < end; i++) {
            //每次取值后，计算剩余的字符够不够分
            int num2 = s.length() - i;
            if(num1 < num2){
                continue;
            }
            String subString;
            if(level == 4){
                subString = s.substring(start);
            }else{
                subString = s.substring(start, i + 1);
            }
            if (verify(subString)) {
                if(level == 1){
                    restoreIpAddresses(s, i + 1, level + 1, res, subString);
                }else{
                    restoreIpAddresses(s, i + 1, level + 1, res, up + "." + subString);
                }
            }
        }
    }

    public boolean verify(String s){
        if(s.isEmpty()){
            return false;
        }
        if (s.charAt(0) == '0') {
            return false;
        }
        int i = Integer.parseInt(s);
        return i >= 1 && i <= 255;
    }

    public static void main(String[] args) {
        List<String> strings = new Theme93().restoreIpAddresses("25525511135");
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
