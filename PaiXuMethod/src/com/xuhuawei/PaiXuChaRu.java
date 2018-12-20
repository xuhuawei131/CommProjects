package com.xuhuawei;

public class PaiXuChaRu extends PaiXuImp {

    @Override
    public int[] paiXu(int[] arr) {
        //把第一个元素看作一部分，第二个元素看作另一部分，从第二部分中依次取出元素插入到第一部分中
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            //依次和i前面的元素比较，寻找合适插入位置
            while (j>=0&&temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            //将插入元素插入到合适位置
            arr[j + 1] = temp;
        }
        return arr;
    }
}