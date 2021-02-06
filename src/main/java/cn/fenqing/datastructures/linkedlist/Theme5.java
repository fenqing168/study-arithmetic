package cn.fenqing.datastructures.linkedlist;

import java.util.Random;
import java.util.Stack;

/**
 * 合并两个有序链表，合并之后的链表依然有序
 * @author fenqing
 */
public class Theme5 {

    public static void main(String[] args) {
        Random random = new Random();
        int l = 10000;
        LinkedList<Integer> linkedList1 = new LinkedList<>();
        LinkedList<Integer> linkedList2 = new LinkedList<>();
        for (int i = 1; i <= l; i++) {
            int i1 = random.nextInt(l);
            if(i1 % 2 != 0){
                linkedList1.addByOrder(i1);
            }else {
                linkedList2.addByOrder(random.nextInt(l));
            }
        }
        LinkedListNode<Integer> root1 = linkedList1.getHead();
        LinkedListNode<Integer> root2 = linkedList2.getHead();
        System.out.println("链表1");
        LinkedList.list(root1);
        System.out.println("链表2");
        LinkedList.list(root2);
        LinkedListNode<Integer> merge = merge(root1, root2);
        System.out.println("合并后");
        LinkedList.list(merge);
    }


    /**
     * 反合并两个链表
     * 思路
     *  1. 如果其中一个链表为空，则返回另外一个链表，
     *  2. 如果都不为空，则遍历root2，遇到的每一个节点与root1的当前节点比较，只有在小于或者等于的情况下，将其取出，并插入到root1的头部
     *  3、 如果大于，则root1后退一步，在与剩下的root2节点比较，直到root2或者root1其中一个遍历完，如果是root1遍历完，则讲剩下的root2追加到root1的尾部
     *  34 当root1开始向后移动，说明最小值已经确认，则可以保存链表头
     * @param root1 根节点1
     * @param root2 根节点2
     */
    public static LinkedListNode<Integer> merge(LinkedListNode<Integer> root1, LinkedListNode<Integer> root2){
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }

        LinkedListNode<Integer> mer = null, merCur = null;
        LinkedListNode<Integer> cur1 = root1;
        LinkedListNode<Integer> cur2 = root2;
        while (cur1 != null && cur2 != null){
            Integer data1 = cur1.getData();
            Integer data2 = cur2.getData();
            LinkedListNode<Integer> temp = null;
            if(data1.compareTo(data2) <= 0){
                temp = cur1;
                cur1 = cur1.getNext();
            }else {
                temp = cur2;
                cur2 = cur2.getNext();
            }
            if(mer == null){
                mer = temp;
                merCur = temp;
            }else {
                merCur.setNext(temp);
                merCur = temp;
            }
        }
        if(cur1 != null){
            merCur.setNext(cur1);
        }
        if(cur2 != null){
            merCur.setNext(cur2);
        }
        return mer;
    }

}
