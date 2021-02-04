package cn.fenqing.datastructures.queue;

import java.util.Scanner;

/**
 * @author fenqing
 */
public class CircleArrayQueue<T> {

    /**
     * 队列存放数据
     */
    private final Object[] values;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 队列最大容量
     */
    private final int maxSize;

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        values = new Object[arrMaxSize];
    }

    /**
     * 添加队列数据
     * @param value 数据
     */
    public void addQueue(T value){
        //若尾指正rear小于队列的最大下标，maxSize-1，则将数据存入rear所指的数组元素中，否则无法存入数据，rear==maxSize-1【队列满】
        if(isFull()){
            System.out.println("队列满了");
            return;
        }
        //将尾指针往后移，rear+1, 当front==rear时表示为空
        values[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    /**
     * @return 获取数据
     */
    public T getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        T t = (T) values[front];
        front = (front + 1) % maxSize;
        return t;

    }

    public boolean isEmpty(){
        //当头尾相遇则说明队列里数据已读取完
        return rear == front;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public void showQueue(){
        int end = front + size();
        for(int i = front; i < end; i++){
            int index = i % maxSize;
            System.out.printf("%s\t", values[index].toString());
        }
        System.out.println();
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public T headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列尾空");
        }
        return (T) values[front];
    }

    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            System.out.println("请问您要创建多大的队列：");
            int maxSize = input.nextInt();
            CircleArrayQueue<Integer> queue = new CircleArrayQueue<>(maxSize);
            boolean loop = true;
            while (loop){
                System.out.println("s(show): 显示队列");
                System.out.println("e(exit): 退出程序");
                System.out.println("a(add): 添加数据到队列");
                System.out.println("g(get): 从队列取出数据");
                System.out.println("h(head): 查看队列头的数据");
                String next = input.next();
                switch (next){
                    case "s":
                        queue.showQueue();
                        break;
                    case "e":
                        loop = false;
                        break;
                    case "a":
                        System.out.println("请输入：");
                        int num = input.nextInt();
                        queue.addQueue(num);
                        break;
                    case "g":
                        System.out.println(queue.getQueue());
                        break;
                    case "h":
                        System.out.println(queue.headQueue());
                        break;
                    default:
                        System.out.println("输入有误！");
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
