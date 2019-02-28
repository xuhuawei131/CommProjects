package com.xuhuawei.paixu;

/**
 * 快速排序
 * 被认为是当今最好的排序法之一，它的基本的思路是：将一个大的数组的排序问题
 * 分解成两个小的数组的排序，而每个小的数组的排序又可以继续分解成更小的2个数组，
 * 这样一直递归分解下去，直到数组的大小最大为2
 * Created by Administrator on 2017/2/27 0027.
 */
public class PaiXuKuaiSu extends PaiXuImp {
    private int index=0;
    @Override
    public int[] paiXu(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        int t;
        if (left < right) {
            //每次的标准都是 第一个元素值
            int standardValue = arr[left];
            int leftIndex = left;
            int rightIndex = right + 1;

            while (true) {
                //向右找大于s的数的索引
                while (leftIndex + 1 < arr.length && arr[++leftIndex] < standardValue) ;
                //向左找小于s的数的索引
                while (rightIndex  > 0 && arr[--rightIndex] > standardValue) ;

                //如果i>=j，退出循环
                if (leftIndex >= rightIndex) {
                    break;
                } else {
                    //交换i和j的位置的元素
                    t = arr[leftIndex];
                    arr[leftIndex] = arr[rightIndex];
                    arr[rightIndex] = t;
                }
            }
            //最后把第一个位置的 标准与最后一次位置的值做一个调换
            arr[left] = arr[rightIndex];
            arr[rightIndex] = standardValue;


            //对左边进行递归
            quickSort(arr, left, rightIndex - 1);
            //对右边进行递归
            quickSort(arr, rightIndex + 1, right);

        }
        return arr;
    }

    @Override
    public String getClassName() {
        return "快速排序";
    }
}
