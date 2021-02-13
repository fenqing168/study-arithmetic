package cn.fenqing.datastructures.stack;

import lombok.Cleanup;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class PolandNotation {

    public static void main(String[] args) {
        @Cleanup Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("请输入一个逆波兰表达式：");
            String expression = in.nextLine();
            double calculate = calculate(expression);
            System.out.println("计算结果为：" + calculate);
        }
    }

    public static List<String> getListString(String expression){
        return Arrays.stream(expression.split(" ")).collect(Collectors.toList());
    }

    public static double calculate(String expression) {
        List<String> listString = getListString(expression);
        Stack<Double> stack = new Stack<>();
        for (String item : listString) {
            boolean matches = item.matches("^-?\\d+\\.?\\d*$");
            if(matches){
                stack.push(Double.parseDouble(item));
            } else {
              if(isOper(item)){
                  Double num1 = stack.pop();
                  Double num2 = stack.pop();
                  double cal = cal(num2, num1, item.charAt(0));
                  stack.push(cal);
              }
            }
        }
        return stack.pop();
    }

    public static boolean isOper(String oper) {
        return oper.matches("^[+\\-×/]$");
    }

    public static double cal(double num1, double num2, char oper) {
        BigDecimal bdNum1 = BigDecimal.valueOf(num1);
        BigDecimal bdNum2 = BigDecimal.valueOf(num2);
        switch (oper) {
            case '+':
                return bdNum1.add(bdNum2).doubleValue();
            case '-':
                return bdNum1.subtract(bdNum2).doubleValue();
            case '×':
                return bdNum1.multiply(bdNum2).doubleValue();
            case '/':
                return bdNum1.divide(bdNum2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            default:
                return 0;
        }
    }

}
