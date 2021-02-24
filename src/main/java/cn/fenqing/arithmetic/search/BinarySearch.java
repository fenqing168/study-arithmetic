package cn.fenqing.arithmetic.search;

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
}
