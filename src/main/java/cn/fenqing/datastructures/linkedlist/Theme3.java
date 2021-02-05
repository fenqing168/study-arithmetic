package cn.fenqing.datastructures.linkedlist;

/**
 * @author fenqing
 */
public class Theme3 {

    public static void main(String[] args) {
        LinkedListNode<Integer> root = new LinkedListNode<>(1, null);
        LinkedListNode<Integer> temp = root;
        for (int i = 2; i < 9; i++) {
            LinkedListNode<Integer> newNode = new LinkedListNode<>(i, null);
            temp.setNext(newNode);
            temp = newNode;
        }
        LinkedList.list(root);
        LinkedListNode<Integer> revers = revers(root);
        LinkedList.list(revers);
    }


    public static LinkedListNode<Integer> revers(LinkedListNode<Integer> root){
        //当链表没有节点或者只有一个节点的时候，无需反转
        if(root == null || root.getNext() == null){
            return root;
        }
        LinkedListNode<Integer> revers = root;
        LinkedListNode<Integer> temp = root.getNext();
        LinkedListNode<Integer> next;
        while (temp != null){
            next = temp.getNext();
            temp.setNext(revers);
            revers = temp;
            temp = next;
        }
        return revers;
    }


}
