package cn.fenqing.arithmetic.leetcode;//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//
// 示例:
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// Related Topics 回溯算法
// 👍 515 👎 0
/**
 * @author fenqing
 */
public class Theme77{
    public static void main(String[] args) {
        List<List<Integer>> combine = new Theme77().combine(4, 2);
        for (List<Integer> list : combine) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Integer[] temp = new Integer[k];
        combine(n, k, 0, 0, res, temp);
        return res;
    }

    public void combine(int n, int k, int level, int cur, List<List<Integer>> arr, Integer[] temp) {
        if(level >= k){
            arr.add(Arrays.asList(temp.clone()));
            return;
        }
        for (int i = cur; i < n; i++) {
            temp[level] = i + 1;
            combine(n, k, level + 1, i + 1, arr, temp);
        }
    }
}