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
 * DateUtils.java V1.0 2016-9-29 ����10:01:54
 *
 * Copyright JIAYUAN Co. ,Ltd. All rights reserved.
 *
 * Modification history(By WAH-WAY):
 *
 * Description:
 */

package com.jiayuan.util.passwords;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String convertTimeFormat(long time) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy��MM��dd��   HH:mm:ss");
		Date curDate = new Date(time);// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

}
