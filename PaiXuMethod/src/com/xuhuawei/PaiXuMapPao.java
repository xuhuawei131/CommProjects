package com.xuhuawei;
/**
 * 冒泡排序
 * 从素组开始扫描待排序的元素，在扫描中依次对相邻元素进行比较
 * 将数值大的元素后移，每次经过一趟排序后 讲数值最大的元素移到末尾
 * 此时已经记录该元素的位置，下一趟排序只需要比较到此位置为止
 * 直到所有元素都已有序排列
 * Created by Administrator on 2017/2/27 0027.
 */
public class PaiXuMapPao extends PaiXuImp {
    @Override
    public int[] paiXu(int[] arr) {
        int t;
        for (int i = 0; i < arr.length; i++) {
            //循环比较相邻两个元素大小
            for (int j = 0; j < arr.length-i-1; j++) {
                //比较相邻元素大小，小的前移，大的后移
                if (arr[j] > arr[j + 1]) {
                    t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        return arr;
    }
}
