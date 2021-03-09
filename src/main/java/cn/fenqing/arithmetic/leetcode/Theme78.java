package cn.fenqing.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
//
// Related Topics 位运算 数组 回溯算法
// 👍 1042 👎 0
/**
 * @author fenqing
 */
public class Theme78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        subsets(nums, new Integer[0], res, 0, 0);
        return res;
    }

    public void subsets(int[] nums, Integer[] temp, List<List<Integer>> res, int cur, int level) {
        res.add(Arrays.asList(temp.clone()));
        for (int i = cur; i < nums.length; i++) {
            Integer[] arrTemp = Arrays.copyOf(temp, level + 1);
            arrTemp[level] = nums[i];
            subsets(nums, arrTemp, res, i + 1, level + 1);
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> subsets = new Theme78().subsets(new int[]{1, 2, 3});
        for (List<Integer> subset : subsets) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
    }

}
