package cn.fenqing.arithmetic.leetcode;

//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例:
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false
//
//
//
// 提示：
//
//
// board 和 word 中只包含大写和小写英文字母。
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// 1 <= word.length <= 10^3
//
// Related Topics 数组 回溯算法
// 👍 820 👎 0

import cn.fenqing.arithmetic.leetcode.commons.Transition;
import cn.fenqing.test.RunTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fenqing
 */
public class Theme79 {

    /**
     * 1. 遍历二维数组，找到第一个，从这个位置出发，采用右下左上的顺序开始寻找
     * 2. 经过的地方，采用Set<String>记录
     * 3. 每次找到正确的地方，先判断是否已经采用过，如果采用过，则继续，如果没有，则记录值，继续往下
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        Set<String> set = new HashSet<>();
        if (word == null || word.isEmpty()) {
            return false;
        }
        char ch = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            char[] chs2 = board[i];
            for (int j = 0; j < chs2.length; j++) {
                boolean exist = exist(board, word, set, 0, i, j);
                if (exist) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, Set<String> set, int cur, int x, int y) {
        if (cur >= word.length()) {
            return true;
        }
        char ch = word.charAt(cur);
        char ch2 = board[x][y];
        if (ch == ch2) {
            String key = x + "-" + y;
            if (set.contains(key)) {
                return false;
            } else {
                if (cur + 1 >= word.length()) {
                    return true;
                }
                set.add(key);
                boolean exist;
                if(x + 1 < board.length){
                    exist = exist(board, word, set, cur + 1, x + 1, y);
                    if(exist){
                        return true;
                    }
                }
                if(y + 1 < board[x].length){
                    exist = exist(board, word, set, cur + 1, x, y + 1);
                    if(exist){
                        return true;
                    }
                }
                if(x - 1 > -1){
                    exist = exist(board, word, set, cur + 1, x - 1, y);
                    if(exist){
                        return true;
                    }
                }
                if(y - 1 > -1){
                    exist = exist(board, word, set, cur + 1, x, y - 1);
                    if(exist){
                        return true;
                    }
                }
                set.remove(key);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] charDoubleArray = Transition.getCharDoubleArray("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]");
        RunTime.synthesizeTest(() -> System.out.println(new Theme79().exist(charDoubleArray, "SEE")), "耗时：");
    }

}
