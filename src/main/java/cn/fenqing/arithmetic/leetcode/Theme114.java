package cn.fenqing.arithmetic.leetcode;


import cn.fenqing.arithmetic.leetcode.commons.Transition;
import cn.fenqing.arithmetic.leetcode.commons.TreeNode;


/**
 * @author fenqing
 */
public class Theme114 {

    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        if(left != null){
            set(left, root.right);
            root.right = left;
            root.left = null;
        }

    }

    public void set(TreeNode left, TreeNode right){
        while (left.right != null){
            left = left.right;
        }
        left.right = right;
    }

    public static void main(String[] args) {
        TreeNode treeNode = Transition.string2TreeNode("[1,2,5,3,4,null,6]");
        Transition.print(treeNode);
        System.out.println("========");
        new Theme114().flatten(treeNode);
        Transition.print(treeNode);
    }

}
