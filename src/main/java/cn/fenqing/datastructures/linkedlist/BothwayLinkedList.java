package cn.fenqing.datastructures.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

/**
 * @author fenqing
 */
public class BothwayLinkedList<T extends Comparable<T>> {

    /**
     * 头节点
     */
    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public void add(T value){
        Node<T> newNode = new Node<>(value, null, null);
        Node<T> headTemp = head;
        if(head == null){
            head = newNode;
            return;
        }
        while (true){
            if(headTemp.getNext() == null){
                break;
            }
            headTemp = headTemp.getNext();
        }
        newNode.setPre(headTemp);
        headTemp.setNext(newNode);
    }

    public void list(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Node<T> headTemp = head;
        while (headTemp != null){
            System.out.println(headTemp.getData());
            headTemp = headTemp.getNext();
        }
    }

    public void update(T value){
        if(head == null){
            return;
        }
        Node<T> headTemp = head;
        while (headTemp != null){
            if(headTemp.getData().compareTo(value) == 0){
                headTemp.setData(value);
                break;
            }
            headTemp = headTemp.getNext();
        }
    }

    /**
     * 删除
     * @param value
     */
    public void del(T value){
        if(head == null){
            return;
        }
        if(head.getData().compareTo(value) == 0){
            head = head.getNext();
            return;
        }
        Node<T> temp = head;
        while (temp != null){
            if(temp.getData().compareTo(value) == 0){
                Node<T> pre = temp.getPre();
                Node<T> next = temp.getNext();
                if(pre != null){
                    pre.setNext(next);
                }
                if(next != null){
                    next.setPre(pre);
                }
                break;
            }
            temp = temp.getNext();
        }
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Node<T extends Comparable<T>> {

        /**
         * 数据域
         */
        private T data;

        /**
         * 下一个节点指针
         */
        @ToString.Exclude
        private Node<T> pre;

        /**
         * 下一个节点指针
         */
        @ToString.Exclude
        private Node<T> next;
    }

    public static void main(String[] args) {
        BothwayLinkedList<Hero> heroLinkedList = new BothwayLinkedList<>();
        Random random = new Random();
//        for (int i = 0; i < 999; i++) {
//            heroLinkedList.addByOrder(new Hero(random.nextInt(9999999), "刘备" + i, "皇叔" + i));
//        }

        heroLinkedList.add(new Hero(2, "关羽", "汉寿亭侯"));
        heroLinkedList.add(new Hero(1, "张飞", "莽撞人"));
        heroLinkedList.add(new Hero(99, "张飞", "莽撞人"));
        heroLinkedList.add(new Hero(45, "张飞", "莽撞人"));
        heroLinkedList.list();
        System.out.println();
        heroLinkedList.update(new Hero(45, "张飞", "莽撞人+粗汉"));
        heroLinkedList.list();
        System.out.println();
        heroLinkedList.del(new Hero(45, "张飞", "莽撞人+粗汉"));
        heroLinkedList.list();
    }


}

