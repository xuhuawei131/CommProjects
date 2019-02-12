package com.xuhuawei;

/**
 * 快速排序
 * 被认为是当今最好的排序法之一，它的基本的思路是：将一个大的数组的排序问题
 * 分解成两个小的数组的排序，而每个小的数组的排序又可以继续分解成更小的2个数组，
 * 这样一直递归分解下去，直到数组的大小最大为2
 * Created by Administrator on 2017/2/27 0027.
 */
public class PaiXuKuaiSu extends PaiXuImp {
    @Override
    public int[] paiXu(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    public int[] quickSort(int[] arr, int left, int right) {

        if (left < right) {
            int compareValue = arr[left];
            int tempLeftIndex = left;
            int tempRightIndex = right + 1;
            while (true) {
                //向右找大于s的数的索引
                while (tempLeftIndex + 1 < arr.length && arr[++tempLeftIndex] < compareValue) ;
                //向左找小于s的数的索引
                while (tempRightIndex > 0 && arr[--tempRightIndex] > compareValue) ;
                //如果i>=j，退出循环
                if (tempLeftIndex >= tempRightIndex) {
                    break;
                } else {
                    //交换i和j的位置的元素
                    int t = arr[tempLeftIndex];
                    arr[tempLeftIndex] = arr[tempRightIndex];
                    arr[tempRightIndex] = t;
                }
            }

            arr[left] = arr[tempRightIndex];
            arr[tempRightIndex] = compareValue;

            //对左边进行递归
            quickSort(arr, left, tempRightIndex - 1);
            //对右边进行递归
            quickSort(arr, tempRightIndex + 1, right);
        }
        return arr;
    }
}