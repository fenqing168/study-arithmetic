package cn.fenqing.arithmetic.leetcode;

import cn.fenqing.arithmetic.leetcode.commons.Transition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class Theme1512 {

    public static void main(String[] args) {
        int[] intArray = Transition.getIntArray("[1,2,3]");
        System.out.println(new Solution().numIdenticalPairs(intArray));
    }

}

class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(8);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            res += (1 + value - 1) * (value - 1) / 2;
        }
        return res;
    }
}