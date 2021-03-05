package cn.fenqing.arithmetic.search;

import java.util.ArrayList;

public class InterpolationSearch implements Search{
    @Override
    public int search(int[] array, int val) {
        return recursionSort(array, val, 0, array.length - 1);
    }


    public int recursionSort(int[] array, int val, int start, int end){
        if(start > end){
            return -1;
        }
        int mid = start + (end - start) * (val - array[start]) / (array[end] - array[start]);
        int midVal = array[mid];
        if(midVal == val){
            return mid;
        }else if(midVal > val){
            return recursionSort(array, val, start, mid - 1);
        }else {
            return recursionSort(array, val, mid + 1, end);
        }
    }

}
