package com.xuhuawei;

public class MainExecute {
    private  int array[]=new int[]{38,5,45,6,98,714,323,12,10,25,78};
    private PaiXuImp[] paiXuImps=new PaiXuImp[]{new PaiXuChaRu(),new PaiXuKuaiSu(),new PaiXuMapPao(),new PaiXuXuanZe()};

    public void startSoirt(){
        PaiXuImp paixu=paiXuImps[0];
        int[] sortList=paixu.paiXu(array);
        System.out.println(paixu+":");
        for (int i = 0; i < sortList.length; i++) {
            System.out.print(sortList[i]+"、");
        }

    }

    public static void main(String args[]){
        MainExecute main=new MainExecute();
        main.startSoirt();
    }

}
