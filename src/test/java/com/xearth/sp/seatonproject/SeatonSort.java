package com.xearth.sp.seatonproject;

import java.util.Arrays;

/**
 * @author wangxudong
 * @date 2020/7/28 11:10
 */
public class SeatonSort {

    /**
     * 二分算法
     * @param arr
     * @param data
     * @return
     */
    public static int binarySearch(int[] arr, int data) {
        int low = 0;
        int height = arr.length - 1;

        while (low <= height) {
            int mid = low + (height - low)/2;

            if(arr[mid] < data) {
                low = mid + 1;
            }else if(arr[mid] == data) {
                return mid;
            }else {
                height = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 归并排序
     *
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2)
            return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }
    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    /**
     * 结果测试
     * @param args
     */
    public static void main(String[] args) {
        // 二分算法测试
//        int[] arr = new int[]{1,2,3,4,5,7,8,10,11,12,14,15};
//        System.out.println(binarySearch(arr,7));


        int[] arr = new int[]{3,2,1,5,7,9,6,4,8};
        // 冒泡算法测试
        System.out.println("冒泡排序：" + Arrays.toString(bubbleSort(arr)) + " 平均时间复杂度：O(n²)");
        // 插入排序
        System.out.println("插入排序：" + Arrays.toString(insertionSort(arr)) + " 平均时间复杂度：O(n²)");
        // 归并排序
        System.out.println("归并排序：" + Arrays.toString(MergeSort(arr)) + " 平均时间复杂度：O(n log n)");
    }

}
