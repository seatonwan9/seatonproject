package com.xearth.sp.seatonproject;

import java.util.Arrays;

/**
 * @author wangxudong
 * @date 2020/7/28 11:10
 */
public class SeatonSort {

    /**
     * 二分算法测试
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
     * @param arr
     */
    public static void mpSort(int arr[]) {
        for(int i = 0; i < arr.length - 1; i++) {
            boolean isSort = true;
            for(int j = 0; j < arr.length - 1 - i; j++) {
                int temp = 0;
                if(arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;
                }
            }
            if(isSort) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // 二分算法测试
//        int[] arr = new int[]{1,2,3,4,5,7,8,10,11,12,14,15};
//        System.out.println(binarySearch(arr,7));

        // 冒泡算法测试
        int[] arr = new int[]{3,2,1,5,7,9,6,4};
        mpSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
