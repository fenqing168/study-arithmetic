package cn.fenqing.datastructures.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 使用数组模拟队列
 * 1. 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图，其中maxSize是该队列的最大容量
 * 2. 因为队列的输出，输入是分别从前后端来处理，因此需要两个变量front及rear分别记录队列前后端的下标，front会随着数据输出而变化，而rear则是随着数据输入而变化
 * 3. 当我们将数据存入队列时称为“addQueue” addQueue的处理需要有两个步骤
 *  1. 将尾指针往后移，rear+1, 当front==rear时表示为空
 *  2. 若尾指正rear小于队列的最大下标，maxSize-1，则将数据存入rear所指的数组元素中，否则无法存入数据，rear==maxSize-1【队列满】
 * @author fenqing
 */
public class ArrayQueue<T> {

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

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        values = new Object[arrMaxSize];
        // 指向队列头部，指向队列前一个位置
        front = -1;
        // 队列尾，指向队列最后一个位置
        rear = -1;
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
        values[++rear] = value;
    }

    /**
     * @return 获取数据
     */
    public T getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return (T) values[++front];
    }

    public boolean isEmpty(){
        //当头尾相遇则说明队列里数据已读取完
        return rear == front;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public void showQueue(){
        for(int i = front + 1; i <= rear; i++){
            System.out.printf("%s\t", values[i].toString());
        }
        System.out.println();
    }

    public T headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列尾空");
        }
        return (T) values[front + 1];
    }

    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            System.out.println("请问您要创建多大的队列：");
            int maxSize = input.nextInt();
            ArrayQueue<Integer> queue = new ArrayQueue<>(maxSize);
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
