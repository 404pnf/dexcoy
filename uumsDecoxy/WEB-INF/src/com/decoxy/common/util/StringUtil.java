package com.decoxy.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * 取得String
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public static String getString(Object obj) {
		String str = "";
		if (obj == null || obj.toString().length() == 0) {
			return str;
		}

		return obj.toString();

	}

	/**
	 * 取得XML节点值
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public static String getValue(Object obj) {
		String str = "";
		if (obj == null || obj.toString().length() == 0) {
			return str;
		}

		return utf8Decode(obj.toString()).replace("'", "''").trim();

	}

	/**
	 * 功能：验证字符串长度是否符合要求，一个汉字等于两个字符
	 * 
	 * @param strParameter
	 *            要验证的字符串
	 * @param limitLength
	 *            验证的长度
	 * @return 符合长度ture 超出范围false
	 */
	public static boolean validateStrByLength(String strParameter,
			int limitLength) {
		int temp_int = strlen(strParameter);
		if (temp_int > limitLength) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 计算字符串长度. 一个汉字的长度按2计算. 如果给定的字符串为null, 返回0.
	 * 
	 * @param str
	 *            待计算长度的字符串
	 * @return 字符串长度
	 */
	public static int strlen(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}

		int len = 0;

		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
		}

		return len;
	}

	/**
	 * 设置XML节点值
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public static String setValue(Object obj) {
		String str = "";
		if (obj == null || obj.toString().length() == 0) {
			return str;
		}

		return utf8Encode(obj.toString());

	}

	/**
	 * UTF8 decode
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8Decode(String str) {
		String returnValue = "";
		try {
			returnValue = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return returnValue;

	}

	/**
	 * UTF8encode
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8Encode(String str) {
		String returnValue = "";
		try {
			returnValue = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnValue;

	}

	/**
	 * 空字符判断
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * NULL判断函数(Object)
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		if (obj == null || obj.toString().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * NULL判断函数(String)
	 * 
	 * @param obj
	 *            String
	 * @return boolean
	 */
	public static boolean isNull(String obj) {
		if (obj == null || obj.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * NULL变换函数
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String changeNullToKara(Object obj) {
		if (isNull(obj)) {
			return "";
		}
		return obj.toString().trim();
	}

	/**
	 * 字符串左边拼接指定位数字符串
	 * 
	 * @param value
	 * @param padStr
	 * @param len
	 * @return
	 */
	public static String padLeft(String value, String padStr, int len) {

		int oldLen = value.length();
		if (len <= oldLen) {
			return value;
		}
		for (int i = 0; i < len - oldLen; i++) {
			value = padStr + value;
		}
		return value;
	}

	/**
	 * 字符串右边拼接指定位数字符串
	 * 
	 * @param value
	 * @param padStr
	 * @param len
	 * @return
	 */
	public static String padRight(String value, String padStr, int len) {

		int oldLen = value.length();
		if (len <= oldLen) {
			return value;
		}
		for (int i = 0; i < len - oldLen; i++) {
			value = value + padStr;
		}
		return value;
	}

	/**
	 * 字符串左边拼接
	 * 
	 * @param value
	 * @param padStr
	 * @return
	 */
	public static String padLeft(String value, String padStr) {

		value = padStr + value;
		return value;
	}

	/**
	 * 字符串右边拼接
	 * 
	 * @param value
	 * @param padStr
	 * @return
	 */
	public static String padRight(String value, String padStr) {

		value = value + padStr;
		return value;
	}

	/**
	 * 
	 * 字符串连接
	 * 
	 * @param repeat
	 * @param cnt
	 * @return String
	 */
	public static String getRepeatString(String repeat, int cnt) {
		String rtn = "";
		for (int i = 0; i < cnt; i++) {
			rtn += repeat;
		}
		return rtn;
	}

	/**
	 * MD5加密值
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {

		String returnValue = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			returnValue = buf.toString();
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return returnValue;
	}

}
