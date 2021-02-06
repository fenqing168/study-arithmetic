package cn.fenqing.datastructures.linkedlist;

import lombok.*;

import java.util.Random;

/**
 * @author fenqing
 */
public class LinkedList<T extends Comparable<T>> {

    /**
     * 头节点
     */
    private LinkedListNode<T> head;

    public LinkedListNode<T> getHead() {
        return head;
    }

    public void add(T value){
        LinkedListNode<T> newNode = new LinkedListNode<>(value, null);
        LinkedListNode<T> headTemp = head;
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
        headTemp.setNext(newNode);
    }

    public void addByOrder(T value){
        LinkedListNode<T> newNode = new LinkedListNode<>(value, null);
        if(head == null){
            head = newNode;
            return;
        }
        int compareTo = value.compareTo(head.getData());
        if(compareTo < 0){
            LinkedListNode<T> headTemp = head;
            head = newNode;
            newNode.setNext(headTemp);
            return;
        }else if(compareTo == 0){
            System.out.println("出现重复");
            return;
        }
        LinkedListNode<T> headTemp = head;
        boolean flag = false;
        while (true){
            if(headTemp.getNext() == null){
                break;
            }
            int compareTo1 = value.compareTo(headTemp.getNext().getData());
            if(compareTo1 < 0){
                break;
            }else if(compareTo1 == 0){
                flag = true;
                break;
            }
            headTemp = headTemp.getNext();
        }
        if(flag){
            System.out.println("出现重复");
        }else{
            newNode.setNext(headTemp.getNext());
            headTemp.setNext(newNode);
        }
    }

    public void update(T value){
        if(head == null){
            return;
        }
        LinkedListNode<T> headTemp = head;
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
        LinkedListNode<T> temp = head;
        while (temp.getNext() != null){
            if(temp.getNext().getData().compareTo(value) == 0){
                temp.setNext(temp.getNext().getNext());
            }
            temp = temp.getNext();

        }
    }

    public void list(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        LinkedListNode<T> headTemp = head;
        while (headTemp != null){
            System.out.println(headTemp.getData());
            headTemp = headTemp.getNext();
        }
    }

    public static void list(LinkedListNode<?> root){
        if(root == null){
            System.out.println("链表为空");
            return;
        }
        LinkedListNode<?> headTemp = root;
        while (headTemp != null){
            System.out.println(headTemp.getData());
            headTemp = headTemp.getNext();
        }
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                '}';
    }

    public static void main(String[] args) {
        LinkedList<Hero> heroLinkedList = new LinkedList<>();
        Random random = new Random();
//        for (int i = 0; i < 999; i++) {
//            heroLinkedList.addByOrder(new Hero(random.nextInt(9999999), "刘备" + i, "皇叔" + i));
//        }

        heroLinkedList.addByOrder(new Hero(2, "关羽", "汉寿亭侯"));
        heroLinkedList.addByOrder(new Hero(1, "张飞", "莽撞人"));
        heroLinkedList.addByOrder(new Hero(99, "张飞", "莽撞人"));
        heroLinkedList.addByOrder(new Hero(45, "张飞", "莽撞人"));
        heroLinkedList.list();
        heroLinkedList.del(new Hero(2, "关羽", "汉寿亭侯+二弟"));
        System.out.println();
        heroLinkedList.list();
    }


}

@ToString
@Getter
@Setter
@AllArgsConstructor
class LinkedListNode<T> {
    /**
     * 数据域
     */
    private T data;
    /**
     * 下一个节点指针
     */
    @ToString.Exclude
    private LinkedListNode<T> next;
}

@ToString
@AllArgsConstructor
class Hero implements Comparable<Hero>{
    private Integer no;
    private String name;
    private String nickName;

    @Override
    public int compareTo(Hero o) {
        return no.compareTo(o.no);
    }
}