package com.simon.datastructures.sparse;

import sun.misc.Unsafe;

/**
 * 稀疏数组
 *
 * @author simon
 * @date 2020/5/1 10:20 上午
 */
public class SparseArray {

    /**
     * 稀疏数组
     */
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[2][3] = 1;
        array[3][4] = 2;
        int[][] sparseArray = convert2SparseArray(array);
        for (int[] rows : sparseArray) {
            for (int row : rows) {
                System.out.printf("%d\t", row);
            }
            System.out.println();
        }
        System.out.println("================");
        for (int[] rows : convert2RawArray(sparseArray)) {
            for (int row : rows) {
                System.out.printf("%d\t", row);
            }
            System.out.println();
        }
    }

    /**
     *  根据稀疏数组的第一行的数据来创建二维数组
     *  稀疏数组第一行存的是原始数组的行列
     *  从第1行开始遍历稀疏数组将数据恢复到原始数组中
     */
    public static int[][] convert2RawArray(int[][] sparse) {
        int[][] rawArray = new int[sparse[0][0]][sparse[0][1]];
        for (int i = 1; i < sparse.length; i++)
            rawArray[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        return rawArray;
    }

    /**
     * 稀疏数组的row = 原始数组的有效数据量+1
     * 稀疏数组的column = 3
     * <p>
     * row(元素数组中的行) column(原始数组中的列) data(原始数组中的时机数据)
     * <p>
     * 1.遍历原始数组，得到有效个数validCount
     * 2.根据validCount创建稀疏数组 sparse[validCount+1][3]
     * 3.将二维数组的有效数据存入到稀疏数组
     */
    public static int[][] convert2SparseArray(int[][] array) {
        int validCount = 0;
        for (int[] rows : array) {
            for (int row : rows) {
                if (row != 0)
                    validCount++;
            }
        }
        int[][] sparseArray = new int[validCount + 1][3];
        int row = array.length;
        int column = array[0].length;
        sparseArray[0][0] = row;
        sparseArray[0][1] = column;
        sparseArray[0][2] = validCount;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        return sparseArray;
    }
}
