package com.xuhuawei.paixu;

/**
 * 插入排序法
 * 将要排序的数组分成两部分，每次从后面的数组部分中取出索引最小的数组元素
 * 插入到前面数组部分的适当位置中，通常在开始排序时，将数组的第一个元素
 * 作为一组，后面额所有元素被当成另外一组，
 * Created by Administrator on 2017/2/27 0027.
 */
public class PaiXuChaRu extends PaiXuImp {
    @Override
    public int[] paiXu(int[] arr) {
        //把第一个元素看作一部分，第二个元素看作另一部分，从第二部分中依次取出元素插入到第一部分中
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            //依次和i前面的元素比较，寻找合适插入位置
            while (temp < arr[i]) {
                arr[j + 1] = arr[i];
                j--;
                if (j == -1) {
                    break;
                }
            }
            //将插入元素插入到合适位置
            arr[j + 1] = temp;
        }


        return arr;
    }
    @Override
    public String getClassName() {
        return "插入排序";
    }
}
