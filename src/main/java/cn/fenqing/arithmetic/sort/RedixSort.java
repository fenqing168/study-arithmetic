package cn.fenqing.arithmetic.sort;


/**
 * @author fenqing
 */
public class RedixSort implements Sort {

    @Override
    public void sort(int[] array) {
        int[][] buckets = new int[10][array.length];
        int[] curs = new int[10];
        int cur1 = 1;
        while (true){
            for (int val : array) {
                int r = val % (int) Math.pow(10, cur1) / (int) Math.pow(10, cur1 - 1);
                buckets[r][curs[r]++] = (val);
            }
            int blen = curs[0];
            if(blen >= array.length){
                break;
            }
            int cur = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < curs[i]; j++) {
                    array[cur++] = buckets[i][j];
                }
                curs[i] = 0;
            }
            cur1++;
        }
    }

}
