package cn.fenqing.arithmetic.sort;

/**
 * @author Administrator
 */
public class SelectSort implements Sort{
    @Override
    public void sort(int[] array) {
        int len = array.length;
        int tail = len - 1;
        //遍历length - 1次
        for (int i = 0; i < tail; i++) {
            //假设最小的值的下标为当前的 i
            int min = i;
            for (int j = i + 1; j < len; j++) {
                //从i + 1开始，如果发现更小的，则换成新的下标
                if(array[min] > array[j]){
                    min = j;
                }
            }
            //交换彼此的值
            if(min != i){
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }
}
