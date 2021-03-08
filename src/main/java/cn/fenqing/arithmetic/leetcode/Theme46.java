package cn.fenqing.arithmetic.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fenqing
 */
public class Theme46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        permute(nums, 0, res, list);
        return res;
    }

    public void permute(int[] nums, int index, List<List<Integer>> res, List<Integer> list) {
        if(index >= nums.length){
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!list.contains(nums[i])){
                list.add(nums[i]);
                permute(nums, index + 1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new Theme46().permute(new int[]{1, 2, 3});
        for (List<Integer> list : permute) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

}
