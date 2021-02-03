package cn.fenqing.datastructures.sparse;

import java.io.*;
import java.util.Arrays;

/**
 * 思路
 *         1. 二维数组转稀疏数组
 *             1. 先遍历得到有效数据个数sum;
 *             2. 根据sum创建稀疏数组 int[sum + 1][3]
 *             3. 将第一行数据填入数组一维长度，和二维长度
 *             4. 将有效数据填入稀疏数组
 *         2. 恢复成二维数组
 *             1. 读取稀疏数组第一行，创建出二维数组
 *             2. 读取剩下的数据，赋值给创建的数组
 * @author fenqing
 */
public class App {

    public static void main(String[] args) {
        //创建一个元素的二维数组
        int[][] original = new int[11][11];
        original[1][2] = 1;
        original[2][3] = 2;
        System.out.println("========原始的二维数组========");
        for (int[] ints : original) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("============================");
        int[][] sparseArr = arr2Sparse(original);
        System.out.println("========转换成稀疏数组========");
        for (int[] ints : sparseArr) {
            System.out.println(Arrays.toString(ints));
        }
        writeArr(sparseArr, "data/datastructures/sparse/map.data");
        System.out.println("============================");
        int[][] sparseArrByDisk = readArr("data/datastructures/sparse/map.data");
        int[][] originalRenew = sparse2Arr(sparseArrByDisk);
        System.out.println("========转换回原始数组========");
        for (int[] ints : originalRenew) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("============================");
    }

    /**
     * 1. 二维数组转稀疏数组
     * @param arr 原始数组
     * @return 稀疏数组
     */
    public static int[][] arr2Sparse(int[][] arr){
        //1. 先遍历得到有效数据个数sum;
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if(anInt != 0){
                    sum++;
                }
            }
        }
        //2. 根据sum创建稀疏数组 int[sum + 1][3]
        int[][] sparseArr = new int[sum + 1][3];
        //3. 将第一行数据填入数组一维长度，和二维长度
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = sum;
        //4. 将有效数据填入稀疏数组
        int index = 1;
        for (int i = 0; i < arr.length; i++) {
            int[] orArr = arr[i];
            for (int j = 0; j < orArr.length; j++) {
                int data = orArr[j];
                if(data != 0){
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = data;
                    index++;
                }
            }
        }
        return sparseArr;
    }

    /**
     * 2. 恢复成二维数组
     * @param sparse 稀疏数组
     * @return 原始数组
     */
    public static int[][] sparse2Arr(int[][] sparse){
        //1. 读取稀疏数组第一行，创建出二维数组
        int[][] originalRecover = new int[sparse[0][0]][sparse[0][1]];
        //2. 读取剩下的数据，赋值给创建的数组
        for (int i = 1; i < sparse.length; i++) {
            int[] ints = sparse[i];
            for (int j = 0; j < ints.length; j++) {
                originalRecover[ints[0]][ints[1]] = ints[2];
            }
        }
        return originalRecover;
    }

    /**
     * 将数组写入到磁盘
     * @param arr 数组
     * @param path 目录
     */
    public static void writeArr(int[][] arr, String path){
        try (
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
        ) {
            for (int[] ints : arr) {
                StringBuilder sb = new StringBuilder();
                for (int anInt : ints) {
                    sb.append(anInt).append(",");
                }
                writer.write(sb.substring(0, sb.length() - 1));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将磁盘数据读取回数组
     * @param path
     * @return
     */
    public static int[][] readArr(String path){
        try (
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
        ) {
            String temp;
            StringBuilder sb = new StringBuilder();
            while ((temp = reader.readLine()) != null){
                sb.append(temp).append(":");
            }
            String text = sb.toString();
            String[] datas = text.split(":");
            if(datas.length == 0){
                return new int[0][0];
            }
            String[] data = datas[0].split(",");
            int[][] arr = new int[datas.length][data.length];
            for (int i = 0; i < data.length; i++) {
                arr[0][i] = Integer.parseInt(data[i]);
            }
            for (int i = 1; i < datas.length; i++) {
                String[] dataTemp = datas[i].split(",");
                for (int j = 0; j < dataTemp.length; j++) {
                    arr[i][j] = Integer.parseInt(dataTemp[j]);
                }
            }
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[0][0];
    }

}
