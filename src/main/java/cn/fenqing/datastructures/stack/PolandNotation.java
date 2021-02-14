package cn.fenqing.datastructures.stack;

import lombok.Cleanup;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class PolandNotation {

    private static Map<Character, Integer> PRIORITY_MAP = new HashMap<>();

    static {
        PRIORITY_MAP.put('*', 1);
        PRIORITY_MAP.put('/', 1);
        PRIORITY_MAP.put('+', 0);
        PRIORITY_MAP.put('-', 0);
    }

    public static void main(String[] args) {
        @Cleanup Scanner in = new Scanner(System.in);
        while (!PRIORITY_MAP.isEmpty()) {
            System.out.println("请输入一个中缀表达式表达式：");
            String infixExpression = in.nextLine();
            String expression = infix2Suffix(infixExpression);
            System.out.printf("%s的后缀表达式为:%s\n", infixExpression, expression);
            double calculate = calculate(expression);
            System.out.println("计算结果为：" + calculate);
        }
    }

    public static List<String> getListString(String expression) {
        return Arrays.stream(expression.split(" ")).collect(Collectors.toList());
    }

    public static double calculate(String expression) {
        List<String> listString = getListString(expression);
        Stack<Double> stack = new Stack<>();
        for (String item : listString) {
            boolean matches = item.matches("^-?\\d+\\.?\\d*$");
            if (matches) {
                stack.push(Double.parseDouble(item));
            } else {
                if (isOper(item)) {
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
        return oper.matches("^[+\\-*/]$");
    }

    public static boolean isOper(char oper) {
        return isOper(String.valueOf(oper));
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

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infixExpression 中缀表达式
     * @return 后缀表达式
     */
    public static String infix2Suffix(String infixExpression) {
        //初始化两个栈，运算符栈s1和存储中间结果的栈s2
        Stack<String> s1 = new Stack<>(), s2 = new Stack<>();
        int len = infixExpression.length(), index = 0;
        //从左至右扫描中缀表达式
        while (index < len) {
            char ch = infixExpression.charAt(index);
            if (isNum(ch)) {
                //遇到操作数时，将其压入s2
                int cur = index;
                while (cur < len) {
                    char ch1 = infixExpression.charAt(cur);
                    if (isNum(ch1)) {
                        cur++;
                    } else {
                        break;
                    }
                }
                String num = infixExpression.substring(index, cur);
                s2.push(num);
                index = cur - 1;
            } else if (isOper(ch)) {
                boolean bool = true;
                while (bool){
                    if(s1.isEmpty() || "(".equals(s1.peek())){
                        //如果s1为空，或栈顶运算符为左括号"("将直接将此运算符入栈
                        s1.push(String.valueOf(ch));
                        bool = false;
                        continue;
                    }
                    String peek = s1.peek();
                    int nowPriority = priority(ch);
                    int peekPriority = priority(peek);
                    if(nowPriority > peekPriority){
                        //否则，若优先级比栈顶运算符高，也将运算符压入s1
                        s1.push(String.valueOf(ch));
                        bool = false;
                    } else {
                        //否则，将s1栈顶的运算符弹出，并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符比较
                        s2.push(s1.pop());
                    }
                }
            }else if(ch == '(' || ch == ')'){
                if(ch == '('){
                    // 如果是左括号"(" 则直接压入s1
                    s1.push(String.valueOf(ch));
                }else {
                    //如果是右括号")" 则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止,此时将这一对括号丢弃
                    boolean bool = true;
                    while (bool){
                        String top = s1.pop();
                        if("(".equals(top)){
                            bool = false;
                        }else{
                            s2.push(top);
                        }
                    }
                }
            }
            index++;
        }
        //将s1中剩余的运算符依次弹出并压入s2
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        // 依次弹出s2中元素并输出,结果的逆序纪委中缀表达式对应的后缀表达式
        return reversedToString(s2);
    }

    public static String reversedToString(Stack<String> stack){
        if(stack.isEmpty()){
            return "";
        }
        String pop = stack.pop();
        String s = reversedToString(stack);
        if(s.isEmpty()){
            return pop;
        }
        return s + " " + pop;
    }

    public static boolean isNum(char num) {
        return (num >= '0' && num <= '9') || num == '.';
    }

    public static int priority(char oper) {
        return PRIORITY_MAP.getOrDefault(oper, -1);
    }

    public static int priority(String oper) {
        return PRIORITY_MAP.getOrDefault(oper.charAt(0), -1);
    }

}
