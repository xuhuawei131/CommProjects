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
 * SaveFileThread.java V1.0 2016-9-29 ����5:44:31
 *
 * Copyright JIAYUAN Co. ,Ltd. All rights reserved.
 *
 * Modification history(By WAH-WAY):
 *
 * Description:
 */

package com.jiayuan.util.passwords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SaveFileThread extends Thread {
	private long totalLines;
	private String lastValues;
	private ConcurrentLinkedQueue<String> queue;
	private boolean isRunning = false;
	private long startTime;
	private int[] indexArray;
	
	public SaveFileThread(long startTime) {
		this.startTime=startTime;
		queue = new ConcurrentLinkedQueue<String>();
	}

	@Override
	public void run() {
		isRunning = true;
		File file = new File("G://password.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while (isRunning) {
				if (queue.isEmpty()) {// ����Ϊ�յĻ� �Ǿ���ͣһ��
					Thread.sleep(20);
				} else {
					String content = queue.poll();
					fos.write(content.getBytes("iso8859-1"));
					fos.flush();
				}
			}
			while (!queue.isEmpty()) {
				String content = queue.poll();
				fos.write(content.getBytes("iso8859-1"));
				fos.flush();
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				queue.clear();
				fos.close();
				long endTime=System.currentTimeMillis();
				
				String arrayKey="";
				for (int i = 0; i < indexArray.length; i++) {
					arrayKey+=indexArray[i]+"-";
				}
				
				System.out.println("һ��������������" + totalLines + "---lastValue:" + lastValues+"---arrayKey"+arrayKey);
			
				
				System.out.println("�������У�"+DateUtils.convertTimeFormat(endTime));
				long during = ( endTime- startTime) / 60000;
				System.out.println("ʱ�乲�ã��֣���" + during);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addString2Queue(String content) {
		queue.add(content);
	}

	public void startRunning() {
		if (isRunning) {
			System.out.println("�߳�����������");
		} else {
			this.start();
		}
	}

	public void stopRunning(long totalLines,String lastValues,int []indexArray) {
		isRunning = false;
		this.totalLines=totalLines;
		this.lastValues=lastValues;
		this.indexArray=indexArray;
	}
}
