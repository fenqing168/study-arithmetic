package cn.fenqing.datastructures.stack;

import lombok.*;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author Administrator
 */
public class LinkedStack<T> {

    private Node<T> top;

    /**
     * 栈满
     * @return 是否栈满
     */
    public boolean isFull(){
        return false;
    }

    /**
     * 栈空
     * @return 是否栈空
     */
    public boolean isEmpty(){
        return top == null;
    }

    /**
     * 压栈
     * @param value 值
     */
    public void push(T value){
        Node<T> newNode = new Node<>(value, null);
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        if (top != null) {
            newNode.next = top;
        }
        top = newNode;
    }

    public T pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        T data = top.getData();
        top = top.next;
        return data;
    }

    public T peek(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        return top.getData();
    }

    public void list(Consumer<T> consumer){
        Node<T> cur = top;
        while (cur != null){
            consumer.accept(cur.data);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> tNode = top;
        while (tNode != null){
            sb.append(tNode.getData());
            if(tNode.getNext() != null){
                sb.append(",");
            }
            tNode = tNode.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void list(){
        list(System.out::println);
    }


    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    class Node<T> {
        /**
         * 数据域
         */
        private T data;
        /**
         * 下一个节点指针
         */
        @ToString.Exclude
        private Node<T> next;
    }


    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        @Cleanup Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈（入栈）");
            System.out.println("pop: 表示栈取出数据（出栈）");
            String key = scanner.next();
            switch (key) {
                case "show":
                    System.out.println("输出栈：");
                    stack.list();
                    break;
                case "exit":
                    System.out.println("退出程序！");
                    loop = false;
                    break;
                case "push":
                    System.out.println("添加数据到栈（入栈）,请输入一个数字:");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        System.out.println("栈取出数据（出栈）：");
                        System.out.println(stack.pop());
                    }catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入有误！");
            }
        }
        System.out.println("退出成功！");
    }

}
