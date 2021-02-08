package cn.fenqing.datastructures.linkedlist;

import java.util.stream.Collectors;

/**
 * 约瑟夫
 * @author Administrator
 */
public class Josepfu {

    public static void main(String[] args) {
        CircularLinkedList<Integer> integerNode = addBoy(6);
        countBoy(integerNode, 1, 3, 6);
    }


    public static CircularLinkedList<Integer> addBoy(int num){
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        for (int i = 1; i <= num; i++) {
            linkedList.add(i);
        }
        return linkedList;
    }

    public static void showList(CircularLinkedList<Integer> list){
        String collect = list.stream().map(String::valueOf).collect(Collectors.joining("\n"));
        System.out.println(collect);
    }

    /**
     *
     * @param start 开始位置
     * @param countNum 表示数几下
     * @param nums 表示最初多少小孩在圈
     */
    public static void countBoy(CircularLinkedList<Integer> list, int start, int countNum, int nums){
        CircularLinkedList.Node<Integer> first = list.getFirst();
        //数据校验
        if(first == null || start < 1 || start > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        CircularLinkedList.Node<Integer> helper = list.getLast();
        for (int i = 0; i < start - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (helper != first){
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出圈的小孩是：" + first.getValue());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后的小孩是：" + first.getValue());

    }

}
