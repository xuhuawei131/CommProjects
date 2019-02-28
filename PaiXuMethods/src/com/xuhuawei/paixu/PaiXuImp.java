package com.xuhuawei.paixu;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public abstract  class PaiXuImp {
    public void printAllArray(int array[]){
        StringBuilder sb=new StringBuilder();
        for(int item:array){
            sb.append(item).append(",");
        }
        System.out.println(sb.toString());
    }
    public abstract int[] paiXu(int arr[]);
    public abstract String getClassName();
}
