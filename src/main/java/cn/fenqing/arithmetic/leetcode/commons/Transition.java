package cn.fenqing.arithmetic.leetcode.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author fenqing
 */
public class Transition {

    public static char[][] getCharDoubleArray(String text) {
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

    public static int[] getIntArray(String text) {
        JSONArray objects = JSON.parseArray(text);
        int[] res = new int[objects.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = objects.getInteger(i);
        }
        return res;
    }

    public static Integer[] getIntegerArray(String text) {
        JSONArray objects = JSON.parseArray(text);
        Integer[] res = new Integer[objects.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = objects.getInteger(i);
        }
        return res;
    }

    public static TreeNode string2TreeNode(String text){
        Integer[] intArray = getIntegerArray(text);
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(intArray[0]);
        deque.add(root);
        int i = 1;
        while (i < intArray.length){
            TreeNode poll = deque.poll();
            Integer num1 = intArray[i];
            i++;
            Integer num2 = intArray[i];
            i++;
            if(num1 != null){
                poll.left = new TreeNode(num1);
            }
            deque.add(poll.left);
            if(num2 != null){
                poll.right = new TreeNode(num2);
            }
            deque.add(poll.right);
        }
        return root;
    }

    public static void print(TreeNode treeNode){
        print(treeNode, "");
    }

    private static void print(TreeNode treeNode, String parent){
        String text = parent + (treeNode == null ? null : treeNode.val);
        System.out.println(text);
        if(treeNode != null && (treeNode.left != null || treeNode.right != null)){
            print(treeNode.left, text + "-");
            print(treeNode.right, text + "-");
        }
    }



}
