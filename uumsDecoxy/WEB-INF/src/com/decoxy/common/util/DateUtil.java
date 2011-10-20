package com.decoxy.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
	}

	/**
	 * 返回String日期(yyyy-MM-dd HH:mm:ss.sss)
	 * 
	 * @return
	 */
	public static String getStrDate() {

		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.sss");
		
		String str = df.format(new Date());

		return str;
	}
	
	/**
	 * 返回String日期(yyyy-MM-dd)
	 * @param n 日期增减
	 * @return
	 */
	public static String getStrDate(int n) {

		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, n);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);

		String tempMon = String.valueOf(month);
		String tempDay = String.valueOf(day);
		if(month < 10){
			tempMon = "0" + tempMon;
		}
		if(day < 10){
			tempDay = "0" + tempDay;
		}
		String str = String.valueOf(year) + "-" + tempMon + "-" + tempDay;

		return str;
	}

}
