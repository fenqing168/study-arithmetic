package cn.fenqing.arithmetic.leetcode;

import cn.fenqing.arithmetic.leetcode.commons.Transition;
import cn.fenqing.arithmetic.leetcode.commons.TreeNode;

public class Theme98 {

    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean res = true;
        if(root.left != null){
            res = res && isValidBST(root.left, root.val, 1);
        }
        if(root.right != null){
            res = res && isValidBST(root.right, root.val, 2);
        }
        return res && isValidBST(root.left) && isValidBST(root.right);
    }

    public boolean isValidBST(TreeNode root, int val, int type){
        if(root == null){
            return true;
        }
        if(type == 1){
            return root.val < val && isValidBST(root.left, val, type) && isValidBST(root.right, val, type);
        } else {
            return root.val > val && isValidBST(root.left, val, type) && isValidBST(root.right, val, type);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = Transition.string2TreeNode("[5,4,6,null,null,3,7]");
        System.out.println(new Theme98().isValidBST(treeNode));
    }

}
