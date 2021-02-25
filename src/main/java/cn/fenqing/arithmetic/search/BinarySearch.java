package cn.fenqing.arithmetic.search;

import cn.fenqing.test.RunTime;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @author fenqing
 */
public class BinarySearch implements Search{
    @Override
    public int search(int[] array, int val) {
//        return recursionSort(array, val, 0, array.length - 1);
        return doublePointerSort(array, val);
    }

    /**
     * 查询
     * @param array array
     * @param val val
     * @param start start
     * @param end end
     * @return return a
     */
    public int recursionSort(int[] array, int val, int start, int end){
        if(start > end){
            return -1;
        }
        int mid = (end - start) / 2 + start;
        int midVal = array[mid];
        if(midVal == val){
            return mid;
        }else if(midVal > val){
            return recursionSort(array, val, start, mid - 1);
        }else {
            return recursionSort(array, val, mid + 1, end);
        }
    }

    public int doublePointerSort(int[] array, int val){
        int p = 0, q = array.length - 1;
        while (p <= q){
            int mid = (p + q) / 2;
            int midVal = array[mid];
            if(midVal == val){
                return mid;
            }else if(midVal > val){
                q = mid - 1;
            }else {
                p = mid + 1;
            }
        }
        return -1;
    }

    public int[] search2(int[] array, int val) {
        List<Integer> indexs = new LinkedList<>();
        recursionSort2(array, val, 0, array.length, indexs);
        return indexs.stream().mapToInt(e -> e).toArray();
    }

    private void recursionSort2(int[] array, int val, int start, int end, List<Integer> indexs) {
        if(start > end){
            return;
        }
        int mid = (end - start) / 2 + start;
        int midVal = array[mid];
        if(midVal == val){
            indexs.add(mid);
            recursionSort2(array, val, start, mid - 1, indexs);
            recursionSort2(array, val, mid + 1, end, indexs);
        }else if(midVal > val){
            recursionSort2(array, val, start, mid - 1, indexs);
        }else {
            recursionSort2(array, val, mid + 1, end, indexs);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(8000) - 8000 / 2;
        }
        int index = random.nextInt(80000);
        System.out.println("=====================");
        int[] arrTemp = arr.clone();
        int val = arrTemp[index];
        Arrays.sort(arrTemp);
        System.out.println("二分查找");
        System.out.println("查找的值为" + val);
        int[] search = RunTime.synthesizeTest(() -> new BinarySearch().search2(arrTemp, val), "耗时");
        System.out.println("查找到的值为" + "" + ",下标为" + Arrays.toString(search));
        for (int i : search) {
            System.out.println(arrTemp[i]);
        }
    }
}
