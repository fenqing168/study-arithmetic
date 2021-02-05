package cn.fenqing.datastructures.linkedlist;

import cn.fenqing.test.RunTime;

/**
 * @author fenqing
 */
public class Theme2 {

    public static void main(String[] args) {
        LinkedListNode<Integer> root = new LinkedListNode<>(1, null);
        LinkedListNode<Integer> temp = root;
        for (int i = 2; i < 100000; i++) {
            LinkedListNode<Integer> newNode = new LinkedListNode<>(i, null);
            temp.setNext(newNode);
            temp = newNode;
        }
        int index = 50000;
        LinkedListNode<Integer>[] nodes = new LinkedListNode[2];
        RunTime.nanoTest(() -> nodes[0] = findLastIndexNode1(root, index), "findLastIndexNode1");
        RunTime.nanoTest(() -> nodes[1] = findLastIndexNode1(root, index), "findLastIndexNode2");
        System.out.printf("倒数第%d个节点为：%s,%s\n",index, nodes[0], nodes[1]);
    }

    /**
     * 找倒数第几个节点
     * @param root 根节点
     * @param index 倒数第几个
     * @return 长度
     */
    public static LinkedListNode<Integer> findLastIndexNode1(LinkedListNode<Integer> root, int index){
        int len = length(root);

        if(index <= 0 || index > len){
            return null;
        }
        int newIndex = len - index;
        LinkedListNode<Integer> temp = root;
        while (newIndex > 0){
            temp = temp.getNext();
            newIndex--;
        }
        return temp;
    }

    /**
     * 找倒数第几个节点
     * @param root 根节点
     * @param index 倒数第几个
     * @return 长度
     */
    public static LinkedListNode<Integer> findLastIndexNode2(LinkedListNode<Integer> root, int index){
        LinkedListNode<Integer> p = root, q = root;
        for (int i = 0; i < index; i++) {
            if(q == null){
                //说明index大于整个链表的长度
                return null;
            }
            q = q.getNext();
        }
        while (q != null){
            p = p.getNext();
            q = q.getNext();
        }
        return p;
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
