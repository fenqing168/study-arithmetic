package cn.fenqing.test;


import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class App {

    public static void main(String[] args) throws CloneNotSupportedException, IllegalAccessException, InstantiationException {
        for (int i = 0; i < 10; i++) {
            Obj obj = new Obj();
            MyThread runnable = new MyThread();
            MyThread2 runnable2 = new MyThread2();
            runnable.obj = obj;
            runnable2.obj = obj;
            Thread thread1 = new Thread(runnable, "线程1");
            Thread thread2 = new Thread(runnable2, "线程2");
            thread1.start();
            thread2.start();
        }
    }
}

class MyThread implements Runnable {
    Obj obj = null;
    @Override
    public void run() {
        while (obj.flag) {

        }
        System.out.println(obj.str.length());
    }
}
class MyThread2 implements Runnable {
    Obj obj = null;
    @Override
    public void run() {
        obj.str = "sadfasd";
        obj.flag = false;
    }
}


class Obj{
    boolean flag = true;
    String str = null;
}