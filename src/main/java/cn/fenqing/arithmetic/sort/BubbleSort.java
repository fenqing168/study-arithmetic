package cn.fenqing.arithmetic.sort;

/**
 * @author Administrator
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] array) {
        int len = array.length;
        int tail = len;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int p = 0, q = 1; q < tail; p++, q++){
                int pVal = array[p];
                int qVal = array[q];
                if(pVal > qVal){
                    flag = false;
                    array[p] = qVal;
                    array[q] = pVal;
                }
            }
            if(flag){
                return;
            }
            tail--;
        }
    }

}
