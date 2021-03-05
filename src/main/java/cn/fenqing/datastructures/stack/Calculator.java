package cn.fenqing.datastructures.stack;

import lombok.Cleanup;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Calculator {

    private static Map<Character, Integer> PRIORITY_MAP = new HashMap<>();

    static {
        PRIORITY_MAP.put('*', 1);
        PRIORITY_MAP.put('/', 1);
        PRIORITY_MAP.put('+', 0);
        PRIORITY_MAP.put('-', 0);
    }

    public static void main(String[] args) {
        @Cleanup Scanner in = new Scanner(System.in);
        int i = 0;
        while (i <= 10){
            System.out.println("请输入表达式：");
            String expression = in.next();
            System.out.println("计算结果为：" + calculate(expression));
            if(expression.isEmpty()){
                i = 100;
            }
        }
    }

    public static double calculate(String expression) {
        //2. 创建两个栈，一个存放数（numStack），一个存放符号(operStack)
        LinkedStack<Double> numStack = new LinkedStack<>();
        LinkedStack<Character> operStack = new LinkedStack<>();
        //3. 通过一个index值，遍历表达式
        int index = 0;
        int len = expression.length();
        //
        while (index < len) {
            char ch = expression.charAt(index);
            // 5. 如果发现扫描到时一个符号，就分如下情况

            if (isOper(ch)) {
                if (!operStack.isEmpty()) {
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符，
                    // 就需要从数栈中pop出两个数，再从符号栈中pop一个符号，
                    if (priority(ch) <= priority(operStack.peek())){
                        Double num1 = numStack.pop();
                        Double num2 = numStack.pop();
                        Character pop = operStack.pop();
                        double res = cal(num2, num1, pop);
                        numStack.push(res);
                    }
                }
                //无论如何都需要将当前符号入符号栈
                operStack.push(ch);
            } else {
                int cur = index;
                while (cur < len) {
                    char ch1 = expression.charAt(cur);
                    if (isNum(ch1) || ch1 == '.') {
                        cur++;
                    } else {
                        break;
                    }
                }
                //用来截取数字
                String sub = expression.substring(index, cur);
                numStack.push(Double.parseDouble(sub));
                index = cur - 1;
            }
            index++;
        }
        return windUp(numStack, operStack);
    }

    public static double windUp(LinkedStack<Double> numStack, LinkedStack<Character> operStack){
        while (!operStack.isEmpty()){
            Double num1 = numStack.pop();
            Double num2 = numStack.pop();
            Character oper = operStack.pop();
            double res = cal(num2, num1, oper);
            numStack.push(res);
        }
        return numStack.pop();
    }

    public static int priority(char oper) {
        return PRIORITY_MAP.getOrDefault(oper, -1);
    }

    public static boolean isOper(char oper) {
        return PRIORITY_MAP.containsKey(oper);
    }

    public static double cal(double num1, double num2, char oper) {
        BigDecimal bdNum1 = BigDecimal.valueOf(num1);
        BigDecimal bdNum2 = BigDecimal.valueOf(num2);
        switch (oper) {
            case '+':
                return bdNum1.add(bdNum2).doubleValue();
            case '-':
                return bdNum1.subtract(bdNum2).doubleValue();
            case '*':
                return bdNum1.multiply(bdNum2).doubleValue();
            case '/':
                return bdNum1.divide(bdNum2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            default:
                return 0;
        }
    }

    public static boolean isNum(char num) {
        return num >= '0' && num <= '9';
    }

    public static double char2Num(char ch) {
        return ch - '0';
    }


}
