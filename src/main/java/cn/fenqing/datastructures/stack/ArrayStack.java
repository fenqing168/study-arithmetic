package cn.fenqing.datastructures.stack;

import lombok.Cleanup;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author Administrator
 */
public class ArrayStack<T> {

    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数据
     */
    private Object[] data;

    /**
     * 栈顶
     */
    private int top = -1;

    /**
     * 构造器
     * @param maxSize 栈的最大值
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        data = new Object[maxSize];
    }

    /**
     * 栈满
     * @return 是否栈满
     */
    public boolean isFull(){
        return top >= maxSize - 1;
    }

    /**
     * 栈空
     * @return 是否栈空
     */
    public boolean isEmpty(){
       return top <= -1;
    }

    /**
     * 压栈
     * @param value 值
     */
    public void push(T value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        data[top] = value;
    }

    public T pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        T res = (T) data[top];
        top--;
        return res;
    }

    public void list(Consumer<T> consumer){
        for (int i = top; i >= 0; i--) {
            consumer.accept((T) data[i]);
        }
    }

    public void list(){
        list(System.out::println);
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(10);
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
