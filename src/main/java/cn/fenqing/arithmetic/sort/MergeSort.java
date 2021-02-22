package cn.fenqing.arithmetic.sort;

/**
 * @author fenqing
 */
public class MergeSort implements Sort{
    @Override
    public void sort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.arraycopy(temp, 0, array, 0, array.length);
    }

    public void mergeSort(int[] array, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //先向左递归进行分解
            mergeSort(array, left, mid, temp);
            //向右递归
            mergeSort(array, mid + 1, right, temp);

            merge(array, left, mid + 1, right, temp);
        }
    }

    /**
     * 合并
     * @param array 原始数组
     * @param left 左边索引
     * @param mid 中间索引（左右分界点）
     * @param right 右边索引
     * @param temp 临时数组
     */
    public void merge(int[] array, int left, int mid, int right, int[] temp){
        int l = left;
        int r = mid;
        int end = right + 1;
        int cur = left;
        while (l < mid || r < end){
            int min = 0;
            if(l >= mid || (r < end && array[l] > array[r])){
                min = array[r];
                r++;
            }else if(r >= end || array[l] <= array[r]){
                min = array[l];
                l++;
            }
            temp[cur] = min;
            cur++;
        }
        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }

}
