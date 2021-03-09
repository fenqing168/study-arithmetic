package cn.fenqing.arithmetic.leetcode.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author fenqing
 */
public class Transition {

    public static char[][] getCharDoubleArray(String text){
        JSONArray objects = JSON.parseArray(text);
        char[][] res = new char[objects.size()][];
        for (int i = 0; i < res.length; i++) {
            JSONArray jsonArray = objects.getJSONArray(i);
            char[] item = new char[jsonArray.size()];
            for (int j = 0; j < jsonArray.size(); j++) {
                item[j] = jsonArray.getString(j).charAt(0);
            }
            res[i] = item;
        }
        return res;
    }

}
