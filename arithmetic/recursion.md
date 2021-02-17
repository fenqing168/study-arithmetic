# 递归
1. 递归的应用场景
    1. 迷宫问题（回溯），递归（Recursion）
    
2. 概念
    1. 简单的说，递归就是方法自己调用自己，每次调用时传入不同的变量，递归有助于编程者解决复杂的问题，同时可以让代码变的简洁
    
3. 案例：
    1. 打印问题,代码见：cn.fenqing.arithmetic.recursion.RecursionTest.test
    2. 阶乘问题,代码见：cn.fenqing.arithmetic.recursion.RecursionTest.factorial
    
4. 调用规则：
    1. 当程序执行到一个方式时，就会开辟一个独立的空间（栈）
    
5. 递归能解决什么样的问题
    1. 各种数学问题如：八皇后问题。汉罗塔，阶乘问题，迷宫问题，球和篮子问题
    2. 各种算法中也会使用到递归，比如快排，归并排序，二分查找，分治算法
    3. 将用栈解决的问题，递归代码比较简洁
    
6. 重要规则
    1. 执行方法时，就创建一个新的受保护的独立空间（栈空间）
    2. 方法的局部变量是独立的，不会互相影响
    3. 递归必须向退出递归的条件逼近，否则就无限递归
    4. 当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同事当方法执行完毕或者返回时，该方法也就执行完毕
    
7. 迷宫问题
   1. 说明
      1. 小球得到的路径，和程序员设置的找路策略有关，即找路的上下左右顺序有关
      2. 再得到小球路径时，可以先使用下右上左，再改成上右下左，看看路径是不是有变化
      3. 测试回溯现象
      4. 代码见：src/main/resources/view/arithmetic/recursion/mi-gong（最短路径还为搞定）
   
8. 八皇后问题
   1. 介绍:
      1. 八皇后问题，是一个古老而著名的问题，是回溯算法的经典案例，该问题是国际西洋棋手马克斯.贝瑟尔于1848年提出，在8*8格的国际象棋上摆放八个皇后，使其
      不能相互攻击，即：任意两个皇后都不能处于同一行，同一列，或者同一斜线上，问有多少种摆法
         
   2. 算法思路分析：
      1. 第一个皇后先放第一行第一列
      2. 第二个皇后放在第二行第一列，然后判断是否ok，如果不ok，继续放在第二列，第三列，一次吧所有的列都放完，找到一个合适
      3. 继续第三个皇后，还是第一列，第二列，第三列，直到第八个皇后也能放在一个不冲突的位置，算是找到了一个正确解
      4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后放在第一列的所有正确解，全部得到，
      5. 然后回头继续第一个皇后放第二列，后面继续循环1,2,3的步骤
      6. 代码见：view/arithmetic/recursion/eight-eueen
   
9. 小总结：上述都利用了回溯算法的思想，即模拟穷举每一种情况，如果发现不对，则立刻返回上一级，直到全部走通才是正确结果