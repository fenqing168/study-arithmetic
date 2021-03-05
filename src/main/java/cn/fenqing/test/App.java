package cn.fenqing.test;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws CloneNotSupportedException, IllegalAccessException, InstantiationException {

        each(new File("D:\\file\\code\\java\\study-arithmetic"));


    }

    public static void each(File file){
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while (!stack.isEmpty()){
            File pop = stack.pop();
            System.out.println(pop.getAbsolutePath());
            if(pop.isDirectory()){
                File[] files = pop.listFiles();
                for (File file1 : files) {
                    stack.push(file1);
                }
            }
        }
    }

    /**
     * 用来存局部变量
     */
    static class  LocalVariable {
        int index;
    }

}

class Demo implements Cloneable{
    Demo(){
        System.out.println("调用了构造函数！");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
