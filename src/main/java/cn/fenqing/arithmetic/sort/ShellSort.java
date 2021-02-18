package cn.fenqing.arithmetic.sort;

/**
 * @author Administrator
 */
public class ShellSort implements Sort{

    /**
     * 1. 先计算length / 2得到初始组数
     * 2. group >= 1 就对每一组进行插入排序
     * 3. 每一组的对应的值为，i * group，且不可超过数组长度,由于第一位默认为有序，则改成i + group
     * 4. 然后此后以group为步长自增
     * 5. 进行每一组插入排序，只是得按步长的长度来位移
     * 6. 减少分组数量至原先的一半，重复次操作，直到只有一个分组时，并且执行完插入排序后，算法才算完成
     * @param array a
     */
    @Override
    public void sort(int[] array) {
        int len = array.length;
        //1. 先计算得到初始组数
        for (int group = len / 2; group >= 1; group /= 2){
            for (int i = 0; i < array.length; i++) {
                int insertNum = array[i];
                int insertIndex = i - group;
                while (insertIndex >= i % group && insertNum < array[insertIndex]){
                    array[insertIndex + group] = array[insertIndex];
                    insertIndex -= group;
                }
                if(insertIndex != i - group){
                    array[insertIndex + group] = insertNum;
                }
            }
        }

    }

}
