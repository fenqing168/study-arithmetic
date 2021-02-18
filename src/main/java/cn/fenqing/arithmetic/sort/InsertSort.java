package cn.fenqing.arithmetic.sort;

/**
 * @author Administrator
 */
public class InsertSort implements Sort{


    @Override
    public void sort(int[] array) {
        int len = array.length;
        //下标从1开始，默认认为0~1是一个有序表
        for (int i = 1; i < len; i++) {
            int num = array[i];
            //在0~i之间找合适的位置
            int insertIndex = i - 1;
            while (insertIndex >= 0 && num < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = num;
        }
    }


}
