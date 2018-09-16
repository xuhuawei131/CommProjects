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
         ���汣��       ����BUG
* CombinXML.java V1.0 2015��3��26�� ����12:46:25
*
* Copyright JIAYUAN Co. ,Ltd. All rights reserved.
*
* Modification history(By WAH-WAY):
*
* Description:
*/

package com.jiayuan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CombinXML {
	private static final String SRC_PATH="d:/ApkSrc/";
	private static final String DES_PATH="d:/ApkDes/";
//	<channel value="001,41b7be30c9e9f54ff80f02b02afedccbdc5f4ac11417b5dc20935276e708c22363cf4fe3c4d7851c" />
	public static void main(String[] args) {
		File dir=new File(DES_PATH);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file=new File(DES_PATH,"result.txt");
		
		ArrayList<String> arrayList=getChannels();
		try {
			fileWriter(file,arrayList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void fileWriter(File file,ArrayList<String> clist) throws IOException {
    //����һ��FileWriter����
    FileWriter fw = new FileWriter(file);
    //����clist����д�뵽fileName��
    for (String str: clist){
        fw.write("<channel value=\""+str+"\"/>");
        fw.write("\n");
    }
    //ˢ�»�����
    fw.flush();
    //�ر��ļ�������
    fw.close();
}




private static ArrayList<String> getChannels() {
	File file = new File(SRC_PATH,"channel.txt");
	ArrayList<String> channels = new ArrayList<String>();
	try {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String msg = reader.readLine();
		while (msg != null) {
			channels.add(msg);
			msg = reader.readLine();
		}
		reader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return channels;
}

}
