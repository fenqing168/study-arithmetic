package cn.fenqing.datastructures.linkedlist;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 用于构建环形链表
 * @author Administrator
 */
public class CircularLinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;

    public void add(T value){
        Node<T> newNode = new Node<>();
        newNode.value = value;
        if(first == null){
            first = newNode;
            last = newNode;
            newNode.next = first;
            return;
        }
        last.next = newNode;
        newNode.next = first;
        last = newNode;
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T t : this) {
            action.accept(t);
        }
    }

    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();
        forEach(builder::add);
        return builder.build();
    }


    public class MyIterator implements Iterator<T> {

        private Node<T> cursor = first;
        private boolean start = false;

        @Override
        public boolean hasNext() {
            return !start || cursor != first;
        }

        @Override
        public T next() {
            if(!start){
                start = true;
            }
            Node<T> res = cursor;
            cursor = cursor.next;
            return res.value;
        }
    }

    @Getter
    @Setter
    public static class Node<T>{
        private T value;

        private Node<T> next;
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
        for (int i = 0; i < 10; i++) {
            circularLinkedList.add(i);
        }
        circularLinkedList.forEach(System.out::println);
    }

}
