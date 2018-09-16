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
 * PassGenerate.java V1.0 2016-9-26 下午10:03:34
 *
 * Copyright JIAYUAN Co. ,Ltd. All rights reserved.
 *
 * Modification history(By WAH-WAY):
 *
 * Description:
 */

package com.jiayuan.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiayuan.util.passwords.DateUtils;

public class PassGenerate {
	public static List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
	// public static String[] chars =
	// "0123456789abcdefghijklmnopkrstuvwxyzABCDEFGHIJKLMNOPKRSTUVWXYZ!@#$%^&*()_+-=`~?><,.".split("");

	public String[] chars = "0123456".split("");
	public int size = chars.length;
	public String testArray[] = new String[4];
	public static final long target = 9663676416L;//1024*1024*1024
	private int INDEX_LEN;
	private String Str;
	public int[] indexArray;
	private int times=0;
	public static void main(String[] args) {
		
		
		PassGenerate mPassGenerate = new PassGenerate();
		mPassGenerate.SetStr("0123456789");
		mPassGenerate.SetBitNum(8);
//		mPassGenerate.setArrayValue(new int[]{0,1,17,9,30,1,17,11});
		long startTime = System.currentTimeMillis();
		System.out.println("结束排列！"+DateUtils.convertTimeFormat(startTime));
		System.out.println("开始排列！");
		try {
			mPassGenerate.GetDic();
		} catch (IOException e) {
			e.printStackTrace();

		}
		long endTime = System.currentTimeMillis();
		long during = (endTime - startTime) / 60000;
		System.out.println("结束排列！"+DateUtils.convertTimeFormat(endTime));
		System.out.println("时间共用（分）：" + during);
	}

	public void SetBitNum(int num) {
		INDEX_LEN = num;
	}

	public void SetStr(String str) {
		Str = str;
	}

	public void setArrayValue(int values[]) {
		indexArray = values;
	}

	public int GetBitNum() {
		return INDEX_LEN;
	}

	public String GetStr() {
		return Str;
	}

	private void writeTxt(String content) {

	}

	public List<String> GetDic() throws IOException {
		double pow=Math.pow(36,INDEX_LEN);
		System.out.println(INDEX_LEN+"位密码，需要的次数："+pow);
		
		
		
		File file = new File("G://password.txt");
		if (!file.exists()) {
			file.createNewFile();
		}else{
			System.out.println("文件已存在！");
			return null;
		}
		
		FileWriter fw = new FileWriter(file, true);

		if (indexArray == null) {
			indexArray = new int[INDEX_LEN];// 用来存储 字符所在的index
		}

		int lastIndex = INDEX_LEN - 1;

		int strLength = Str.length();
		int strLastIndex = strLength - 1;

		List<String> final_list = new ArrayList<String>();
		String result = "";
		for (int i = 0; i < indexArray.length; i++)
			indexArray[i] = 0;

		long nCount = 0;
		while (true) {
			result = "";
			for (int i = 0; i < indexArray.length; i++) {
				result += Str.charAt(indexArray[i]);
			}
			nCount++;
			
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
	
			// System.out.println(result);
			// final_list.add(result);
			fw.write(result);

			// 开始进行下一轮循环
			int mark = 0;
			for (int j = lastIndex; j >= 0; j--) {
				if (indexArray[j] == strLastIndex) {// 如果数组中是最后一个字符的索引 那么就表示
													// 要进位了
					if (j != 0) {// 如果数组值 不是第一个的话 那么就继续走，
						continue;
					} else {// 如果是第一个 表示 结束了
						mark = 1;
						break;
					}
				} else {// 如果数组值没有达到最高索引的话 就自增一个
					indexArray[j]++;
					for (int k = j + 1; k < INDEX_LEN; k++) {
						indexArray[k] = 0;
					}
					break;
				}
			}
			if (mark == 1) {
				break;
			} else {
				fw.write("\r\n");
			}
		}

		fw.close();
		System.out.println("一共输出密码个数：" + nCount + "---lastValue:" + result);
		return final_list;
	}
}
