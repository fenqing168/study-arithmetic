package cn.fenqing.arithmetic.leetcode;

import java.util.*;

public class Theme40 {

    static Set<String> set = new HashSet<>();

    /**
     * 1. 先排序
     * 2. 开始遍历取值，从0开始取值，如果小于值，然后再开一个循环，再从0开始取，反复如此，直到等于target
     * 3. 如果等于target，就保存数据，并返回上一个循环，比较
     * 4. 如果大于target，则结束此循环，返回上一个循环
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new LinkedList<>();
        combinationSum(0, candidates, 0, null, res, target);
        return res;
    }

    public static void combinationSum(int off, int[] candidates, int sum,  List<Integer> temp, List<List<Integer>> list,  int target) {
        for (int i = off; i < candidates.length; i++) {
            int candidate = candidates[i];
            int sumTemp = sum + candidate;
            if(sumTemp < target){
                if(temp == null){
                    temp = new LinkedList<>();
                }
                temp.add(candidate);
                combinationSum(i + 1, candidates, sumTemp, temp, list, target);
                temp.remove(temp.size() - 1);
            } else if (sumTemp == target){
                if(temp == null){
                    temp = new LinkedList<>();
                }
                String str = Arrays.toString(temp.toArray());
                if(!set.contains(str)){
                    temp.add(candidate);
                    set.add(str);
                    list.add(new LinkedList<>(temp));
                    temp.remove(temp.size() - 1);
                }
                break;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{10,1,2,7,6,1,5}, 8);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

}
