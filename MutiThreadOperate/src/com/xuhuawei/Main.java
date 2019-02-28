package com.xuhuawei;

import java.util.Scanner;

public class Main  implements Runnable{

    public static void main(String[] args) {

        ServiceQueue thread=new ServiceQueue();
        thread.start();

        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("请输入一个数：输入0退出！");
            int length=input.nextInt();//输入一个正整数
            System.out.println("输入的数字是："+length);
            if(length==0){
                break;
            }else{
                thread.addNewTask(""+length);
            }
        }

    }

    @Override
    public void run() {
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("请输入一个数：输入0退出！");
            int length=input.nextInt();//输入一个正整数
            System.out.println("输入的数字是："+length);
            if(length==0){
                break;
            }else{
                thread.addNewTask(""+length);
            }
        }
    }
    }
}
