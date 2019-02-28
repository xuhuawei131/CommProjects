package com.xuhuawei.paixu;

public class Main {
    private static final int[] arr = new int[11];

    public static void main(String[] args) {

        PaiXuImp paixus[] = {new PaiXuXuanZe(), new PaiXuMapPao(), new PaiXuChaRu(), new PaiXuKuaiSu()};
        for (PaiXuImp paiXu : paixus) {
            resetList();
            System.out.println("");
            System.out.println("*************************************");
            System.out.println("");
            String name = paiXu.getClassName();
            long start =  System.nanoTime();
            System.out.println("---------" + name + "开始了---------");
            int values[] = paiXu.paiXu(arr);
            paiXu.printAllArray(values);
            long end =  System.nanoTime()-start ;
            System.out.println("---------" + name + "结束:"+end+"---------"  );
        }
    }

    private static void resetList(){
        arr[0]=12;
        arr[1]=2;
        arr[2]=30;
        arr[3]=31;
        arr[4]=45;
        arr[5]=1;
        arr[6]=56;
        arr[7]=1;
        arr[8]=13;
        arr[9]=6;
        arr[10]=8;
    }

}
