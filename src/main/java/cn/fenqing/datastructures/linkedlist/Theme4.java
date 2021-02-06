package cn.fenqing.datastructures.linkedlist;

import cn.fenqing.test.RunTime;

import java.util.Stack;

/**
 * @author fenqing
 */
public class Theme4 {

    public static void main(String[] args) {
        LinkedListNode<Integer> root = new LinkedListNode<>(1, null);
        LinkedListNode<Integer> temp = root;
        for (int i = 2; i < 10000; i++) {
            LinkedListNode<Integer> newNode = new LinkedListNode<>(i, null);
            temp.setNext(newNode);
            temp = newNode;
        }
        LinkedList.list(root);


        RunTime.nanoTest(() -> {
            System.out.println();
            reversPrint(root);
        }, "栈打印");
        RunTime.nanoTest(() -> {
            System.out.println();
            reversPrintRecurve(root);
        }, "递归打印");

    }

    /**
     * 反向打印
     * @param root 根节点
     */
    public static void reversPrint(LinkedListNode<Integer> root){
        Stack<LinkedListNode<Integer>> stack = new Stack<>();
        while (root != null){
            stack.push(root);
            root = root.getNext();
        }
        while (!stack.isEmpty()){
            stack.pop().getData();
//            System.out.println();
        }
    }

    /**
     * 反向打印
     * @param root 根节点
     */
    public static void reversPrintRecurve(LinkedListNode<Integer> root){
        if(root == null){
            return;
        }
        Stack<LinkedListNode<Integer>> stack = new Stack<>();
        reversPrintRecurve(root.getNext());
        root.getData();
//        System.out.println();
    }

}
