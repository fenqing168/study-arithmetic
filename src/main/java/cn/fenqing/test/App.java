package cn.fenqing.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws CloneNotSupportedException, IllegalAccessException, InstantiationException {
        Demo demo1 = new Demo();
        Demo demo2 = Demo.class.newInstance();
        System.out.println("demo1 == demo2 为：" + (demo1 == demo2));
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
