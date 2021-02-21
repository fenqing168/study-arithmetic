package cn.fenqing.arithmetic.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author fenqing
 */
public class MergeSort implements Sort{
    @Override
    public void sort(int[] array) {

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
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 2, 3, 4};
        HashMap
        int[] temp = new int[arr.length];
        new MergeSort().merge(arr, 2, 3, 3, temp);
        System.out.println(Arrays.toString(temp));
    }
}
