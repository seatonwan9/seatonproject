package com.xearth.sp.seatonproject;

import java.util.Arrays;

/**
 * @author wangxudong
 * @date 2020/7/28 11:10
 */
public class ListSort {


    /**
     * 二分算法
     * @param arr
     * @param data
     * @return
     */
    public static int binarySearch(int[] arr, int data) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if(arr[mid] < data) {
                low = mid + 1;
            }else if(arr[mid] == data) {
                return mid;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 冒泡排序
     * 算法循环n-1次，在未排好序的区域中从后往前比较相邻两个元素，大数在后，小数在前。
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
     * 从数组的第二个元素开始循环，将选中的元素与之前的元素一一比较，
     * 如果选中的元素小于之前的元素，将之前的元素后移，最后选中的元素放到合适的位置。
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        if (array.length == 0)
            return array;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            temp = array[i + 1];
            int j = i;
            while (j >= 0 && temp < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }


    /**
     * 归并排序
     * 将数组拆分成较小的数组，排序后再合并。数组长度不大于1，不再拆分。
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2)
            return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0, len = result.length; index < len; index++) {
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
     * 快速排序
     * 利用分治的思想，将大问题拆分成若干相似的子问题，递归解决这些子问题，然后将这些子问题的解组合成原问题。
     * @param array
     */
    public static int[] quickSort(int[] array, int low, int high) {
        if(high - low > 1) {
            int index = partion(array, low, high);
            quickSort(array, low, index);
            quickSort(array, index+1, high);
        }
        return array;
    }
    
    public static int partion(int[] array, int low, int high) {
        //取数组最后一个元素作为参考值
        int indexValue = array[high-1];
        int index = low;
        for (int i = low, j = low; i < high - 1; i++) {
            if(array[i] < indexValue) {
                if(i > j) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
                index = ++j;
            }
        }
        array[high-1] = array[index];
        array[index] = indexValue;
        return index;
    }


    /**
     * 结果测试
     *
     * @param args
     */
    public static void main(String[] args) {
        // 二分算法
//        int[] arr = new int[]{1,2,3,4,5,7,8,10,11,12,14,15};
//        System.out.println(binarySearch(arr,7));

        int[] arr = new int[]{2,4,5,1,6,3};
        // 冒泡排序
//        System.out.println("冒泡排序：" + Arrays.toString(insertSort(arr)) + " 平均时间复杂度：O(n²)");
//        // 插入排序
//        System.out.println("插入排序：" + Arrays.toString(insertSort(arr)) + " 平均时间复杂度：O(n²)");
//        // 归并排序
//        System.out.println("归并排序：" + Arrays.toString(mergeSort(arr)) + " 平均时间复杂度：O(n log n)");
        // 快速排序
//        System.out.println("快速排序：" + Arrays.toString(quickSort(arr, 0, arr.length)));
    }

}
