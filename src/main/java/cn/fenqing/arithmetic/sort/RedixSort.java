package cn.fenqing.arithmetic.sort;


/**
 * @author fenqing
 */
public class RedixSort implements Sort {

    @Override
    public void sort(int[] array) {

        if(array.length <= 0){
            return;
        }

        int[][] buckets = new int[10][array.length];
        int[][] minusBuckets = new int[10][array.length];
        int[] curs = new int[10];
        int[] minusCurs = new int[10];
        //求最多的数长度
        int max = array[0];
        for (int i : array) {
            int item = Math.abs(i);
            if(max < item){
                max = item;
            }
        }
        int maxLen = String.valueOf(max).length();
        for (int cur1 = 1; cur1 <= maxLen; cur1++){
            for (int val : array) {
                if(val < 0){
                    int valTemp = Math.abs(val);
                    int r = valTemp % (int) Math.pow(10, cur1) / (int) Math.pow(10, cur1 - 1);
                    minusBuckets[r][minusCurs[r]++] = val;
                } else {
                    int r = val % (int) Math.pow(10, cur1) / (int) Math.pow(10, cur1 - 1);
                    buckets[r][curs[r]++] = val;
                }

            }
            int cur = 0;
            for (int i = minusBuckets.length - 1; i >= 0; i--) {
                for (int j = 0; j < minusCurs[i]; j++) {
                    array[cur++] = minusBuckets[i][j];
                }
                minusCurs[i] = 0;
            }
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < curs[i]; j++) {
                    array[cur++] = buckets[i][j];
                }
                curs[i] = 0;
            }
        }
    }

}
