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
     *  2. 新建两个变量，mer来记录合并后的链表的头节点，merCur用于做游标
     *  3、然后创建两个变量，作用于两个链表的游标，并且遍历，比较两个游标对应的值，比较小的拿出来给merCur的next，并且
     *      merCur向下一个走1，也就是比较小的节点，然后对应的链表游标也向后走1,（谁小就拿谁，然后自己向后走1）第一次循环就能判断出最小的节点，同时需要赋给mer
     *      当做链表头
     *  34 当root1，root2的游标任意一个走完了，则需要知道那个没走完，则将每走完的剩余部分给到merCur.next
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
            LinkedListNode<Integer> temp;
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
