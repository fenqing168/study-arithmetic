package cn.fenqing.arithmetic.search;

/**
 * @author fenqing
 */
public class SeqSearch implements Search {
    @Override
    public int search(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == val){
                return i;
            }
        }
        return -1;
    }
}
