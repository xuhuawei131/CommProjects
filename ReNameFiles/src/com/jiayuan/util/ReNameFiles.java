/**

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
* MainClass.java V1.0 2015年3月25日 下午4:27:38
*
* Copyright JIAYUAN Co. ,Ltd. All rights reserved.
*
* Modification history(By WAH-WAY):
*
* Description:
*/

package com.jiayuan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReNameFiles {
	private static final String SRC_PATH="d:/ApkSrc/";
	private static final String DES_PATH="d:/ApkDes/";
	private static final boolean ISCOVER=false;
	public static void main(String[] args) {
		
		File dirFile =new File(SRC_PATH);
		File srcFile=new File(DES_PATH);
		if(!dirFile.exists()){
			System.out.println("文件不存在");
		}
		if(!srcFile.exists()){
			srcFile.mkdirs();
		}
		File files[]=dirFile.listFiles();
		System.out.println("重新命名开始");
		int total=files.length;
		int index=0;
		for(File file:files){
			index++;
			System.out.println("进度："+index+"/"+total);
			String fileAllName=file.getName();
//			世纪佳缘_031,87a977177cecea65842f5e4aaa998cff640a8d4a64383a7b12828868869340eb63cf4fe3c4d7851c
			int startIndex=fileAllName.indexOf("_");
			int endIndex=fileAllName.indexOf(",");
			String fileName=fileAllName.substring(startIndex+1, endIndex);
			
			File dFile=new File(DES_PATH,"jiayuan_android_2_1_"+fileName+"_6_1.apk");
			if(dFile.exists()){
				if(ISCOVER){
					continue;
				}
			}
			
			copyFile(file,dFile);
			
		}
		System.out.println("重新命名结束");
		
	}

	@SuppressWarnings("resource")
	private static void copyFile(File src, File des) {
		try {
			FileInputStream input = new FileInputStream(src);// 可替换为任何路径何和文件名
			FileOutputStream output = new FileOutputStream(des);// 可替换为任何路径何和文件名
			int length=0;
			byte array[]=new byte[4096];
			while ((length=input.read(array))!=-1) {
				output.write(array,0,length);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
