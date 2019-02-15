package com.xuhuawei.animals;


import java.util.*;

public class AnimalMain {
    private static final int SUM=8;
    private static final int TOTAL_SUM=SUM+SUM;
    private AnimalCellBean[] totalAnimals=new AnimalCellBean[TOTAL_SUM];
    private AnimalCellBean[] sortTotalAnimals=new AnimalCellBean[TOTAL_SUM];
    private Map<Integer,AnimalCellBean> leftAnimalsCellMap=new HashMap<>();

    private Random random =new Random();
    public AnimalMain() {
        init();
    }

    private void init(){
        totalAnimals[0]=new AnimalCellBean(0,"老鼠");
        totalAnimals[1]=new AnimalCellBean(1,"小猫");
        totalAnimals[2]=new AnimalCellBean(2,"猎狗");
        totalAnimals[3]=new AnimalCellBean(3,"豺狼");
        totalAnimals[4]=new AnimalCellBean(4,"猎豹");
        totalAnimals[5]=new AnimalCellBean(5,"老虎");
        totalAnimals[6]=new AnimalCellBean(6,"狮子");
        totalAnimals[7]=new AnimalCellBean(7,"大象");


        totalAnimals[SUM+0]=new AnimalCellBean(0,"老鼠");
        totalAnimals[SUM+1]=new AnimalCellBean(1,"小猫");
        totalAnimals[SUM+2]=new AnimalCellBean(2,"猎狗");
        totalAnimals[SUM+3]=new AnimalCellBean(3,"豺狼");
        totalAnimals[SUM+4]=new AnimalCellBean(4,"猎豹");
        totalAnimals[SUM+5]=new AnimalCellBean(5,"老虎");
        totalAnimals[SUM+6]=new AnimalCellBean(6,"狮子");
        totalAnimals[SUM+7]=new AnimalCellBean(7,"大象");

        for (int i = 0; i <TOTAL_SUM; i++) {
            if (i<SUM){
                totalAnimals[i].isRed=true;
            }else{
                totalAnimals[i].isRed=false;
            }
            totalAnimals[i].isOpen=false;
            totalAnimals[i].id=i;
        }
    }
    public void reset(){
        for (int i = 0; i <TOTAL_SUM ; i++) {
            sortTotalAnimals[i]=null;
            totalAnimals[i].isOpen=false;
            leftAnimalsCellMap.put(i,totalAnimals[i]);
        }
        sortCardCells();
    }

    /**
     * 给卡牌
     */
    private void sortCardCells(){
        for (int i = 0; i < TOTAL_SUM; i++) {
            int id=getRandomId();
            System.out.println("random:"+id);
            AnimalCellBean bean=leftAnimalsCellMap.remove(id);
            if (bean!=null){
                sortTotalAnimals[i]=bean;
            }
        }
    }

    private int getRandomId(){
        int id=random.nextInt(TOTAL_SUM);
        if (leftAnimalsCellMap.containsKey(id)){
            return id;
        }else{
            return getRandomId();
        }
    }

    /**
     * 打印所有的卡牌
     */
    public void printAllCell(){
        for (int i = 0; i < TOTAL_SUM; i++) {
            if (i%4==0){
                System.out.println();
            }
            String corlor=sortTotalAnimals[i].isRed?"红":"蓝";
            System.out.print(corlor+"-"+sortTotalAnimals[i].name+"    ");
        }
    }
}
