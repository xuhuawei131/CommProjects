package com.xuhuawei;

/**
 * 选择排序
 * 将要排序的数组分成两部分，一部分是从小到大已经排好序的，
 * 一部分是无序，从无序的部分取出最小的数值 放到已经安排好序的部分的最后
 * <p>
 * Created by Administrator on 2017/2/27 0027.
 */
public class PaiXuXuanZe extends PaiXuImp {
    @Override
    public int[] paiXu(int[] arr) {
        int t;
        for (int i = 0; i < arr.length; i++) {
            int m = i;
            for (int j = i + 1; j < arr.length; j++) {
                //如果j元素比m元素小，将j赋值给m
                if (arr[j] < arr[m]) {
                    m = j;
                }
            }
            //交换m和i两个元素的位置
            if (i != m) {
                t = arr[i];
                arr[i] = arr[m];
                arr[m] = t;
            }
        }
        return arr;
    }
}