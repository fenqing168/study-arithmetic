package cn.fenqing.datastructures.linkedlist;

import java.util.LinkedList;

/**
 * @author fenqing
 */
public class Theme1 {

    public static void main(String[] args) {
        LinkedListNode<Integer> root = new LinkedListNode<>(1, null);
        LinkedListNode<Integer> temp = root;
        for (int i = 2; i < 100; i++) {
            LinkedListNode<Integer> newNode = new LinkedListNode<>(i, null);
            temp.setNext(newNode);
            temp = newNode;
        }
        int length = length(root);
        System.out.println("长度为：" + length);
    }

    /**
     * 获取长度
     * @param root 根节点
     * @return 长度
     */
    public static int length(LinkedListNode<Integer> root){
        int len = 0;
        LinkedListNode<Integer> temp = root;
        while (temp != null){
            temp = temp.getNext();
            len++;
        }
        return len;
    }

}
