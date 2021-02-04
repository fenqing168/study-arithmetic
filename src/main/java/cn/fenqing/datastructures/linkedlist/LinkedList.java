package cn.fenqing.datastructures.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author fenqing
 */
public class LinkedList<T> {

    /**
     * 头节点
     */
    private LinkedListNode<T> head;

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

    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                '}';
    }

    public static void main(String[] args) {
        LinkedList<Hero> heroLinkedList = new LinkedList<>();
        heroLinkedList.add(new Hero(1, "刘备", "皇叔"));
        heroLinkedList.add(new Hero(2, "关羽", "汉寿亭侯"));
        heroLinkedList.add(new Hero(3, "张飞", "莽撞人"));
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
    private LinkedListNode<T> next;
}

@ToString
@AllArgsConstructor
class Hero {
    private Integer no;
    private String name;
    private String nickName;
}