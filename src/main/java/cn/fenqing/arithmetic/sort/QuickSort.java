package cn.fenqing.arithmetic.sort;

/**
 * 快速排序
 *
 * @author Administrator
 */
public class QuickSort implements Sort {

    @Override
    public void sort(int[] array) {
        quick_sort(array, 0, array.length - 1);
    }

    /**
     * 快速排序
     * 1. 在数组中选取一个适当的值，将比这个值小的数放数组左边，比这个值大的放数组右边
     *  1.1 挖坑
     * 2. 然后分别在分别将左边和右边重复第一步，直到最后无法分解，则能得到一个有序列表
     *
     * @param array 数组
     * @param left  左边的边界
     * @param right 右边的边界
     */
    private void sort(int[] array, int left, int right) {
        if(left >= right){
            return;
        }
        int l = left + 1;
        int r = right;
        int pivot = array[left];
        while (l < r){
            while (array[l] <= pivot){
                l++;
                if (l == right) {
                    break;
                }
            }
            while (array[r] > pivot){
                r--;
                if (r == left) {
                    break;
                }
            }
            if(l >= r){
                break;
            }
            int temp = array[r];
            array[r] = array[l];
            array[l] = temp;
        }
        array[left] = array[r];
        array[r] = pivot;
        sort(array, left, r - 1);
        sort(array, r + 1, right);
    }
    void quick_sort(int[] nums, int l,int r){
        if(l>=r) {
            return;
        }
        int i = l-1,j=r+1;
        int mid = l+r>>1;
        int x = nums[mid];
        while(i<j){
            while(nums[++i]<x) {
            }
            while(nums[--j]>x) {
            }
            if(i<j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        quick_sort(nums,l,j);
        quick_sort(nums,j+1,r);
    }

}
