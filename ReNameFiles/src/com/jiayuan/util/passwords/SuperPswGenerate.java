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
 * PassGenerate.java V1.0 2016-9-26 ����10:03:34
 *
 * Copyright JIAYUAN Co. ,Ltd. All rights reserved.
 *
 * Modification history(By WAH-WAY):
 *
 * Description:
 */

package com.jiayuan.util.passwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuperPswGenerate {
	// public static String[] chars =
	// "0123456789abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPKRSTUVWXYZ!@#$%^&*()_+-=`~?><,.".split("");

	public String testArray[] = new String[4];
	public static final long target = 12884901888L;//1024*1024*1024
	private static final String CHARTER_NUM="0123456789abcdefghijklmnopkrstuvwxyz";
	private static final String NUM="0123456789";
	private int pswArrayLength;
	private String pswCharter;
	public int[] indexArray;
	private int times=0;
	public static void main(String[] args) {
		
		SuperPswGenerate mPassGenerate = new SuperPswGenerate();
		mPassGenerate.setPswLength(11);
		mPassGenerate.setPswCharter(NUM);
//		mPassGenerate.setPswCharter(CHARTER_NUM);
//		mPassGenerate.setArrayValue(new int[]{10,0,0,0,0,0,0,0});

		try {
			mPassGenerate.GetDic();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setPswLength(int num) {
		pswArrayLength = num;
	}

	public void setPswCharter(String str) {
		pswCharter = str;
	}

	public void setArrayValue(int values[]) {
		indexArray = values;
	}


	public void GetDic() throws IOException {
		long startTime = System.currentTimeMillis();
		
		double pow=Math.pow(36,pswArrayLength);
		System.out.println(pswArrayLength+"λ���룬��Ҫ�Ĵ�����"+pow);
		System.out.println("��ʼ���У�"+DateUtils.convertTimeFormat(startTime));
		
	
		SaveFileThread thread=new SaveFileThread(startTime);
		thread.startRunning();

		if (indexArray == null) {
			indexArray = new int[pswArrayLength];// �����洢 �ַ����ڵ�index
		}

		int lastIndex = pswArrayLength - 1;

		int strLength = pswCharter.length();
		int strLastIndex = strLength - 1;

		String result = "";
//		for (int i = 0; i < indexArray.length; i++)
//			indexArray[i] = 0;

		long nCount = 0;
		StringBuilder sbBuilder=new StringBuilder();
		int lines=0;
		while (true) {
			result = "";
			for (int i = 0; i < indexArray.length; i++) {
				result += pswCharter.charAt(indexArray[i]);
			}
			nCount++;
			lines++;
			
			if(nCount%33554432==0){
				times++;
				String arrayKey="";
				for (int i = 0; i < indexArray.length; i++) {
					arrayKey+=indexArray[i]+"-";
				}
				System.out.println("times: "+times+"---nCount:"+nCount+"---result:"+result+"---arrayKey:"+arrayKey);
			}
			
			if (nCount >= target) {
				break;
			}
			
			sbBuilder.append(result);
			if(lines==100){
				lines=0;
				thread.addString2Queue(sbBuilder.toString());
				sbBuilder=new StringBuilder();
			}
			

			// ��ʼ������һ��ѭ��
			int mark = 0;
			for (int j = lastIndex; j >= 0; j--) {
				if (indexArray[j] == strLastIndex) {// ��������������һ���ַ������� ��ô�ͱ�ʾ
													// Ҫ��λ��
					if (j != 0) {// �������ֵ ���ǵ�һ���Ļ� ��ô�ͼ����ߣ�
						continue;
					} else {// ����ǵ�һ�� ��ʾ ������
						mark = 1;
						break;
					}
				} else {// �������ֵû�дﵽ��������Ļ� ������һ��
					indexArray[j]++;
					for (int k = j + 1; k < pswArrayLength; k++) {
						indexArray[k] = 0;
					}
					break;
				}
			}
			
			if (mark == 1) {
				break;
			} else {
				sbBuilder.append("\r\n");
			}
		}
		
		thread.addString2Queue(sbBuilder.toString());
		thread.stopRunning(nCount,result,indexArray);

	}
}
