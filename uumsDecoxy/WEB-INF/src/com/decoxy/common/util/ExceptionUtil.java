package com.decoxy.common.util;

import java.lang.reflect.InvocationTargetException;

public final class ExceptionUtil {
	
	/**
	 * 取得Exception子集
	 * @param ex
	 * @return
	 */
	public static Throwable getTargetException(Throwable ex) {
		if (ex instanceof InvocationTargetException) {
			return ((InvocationTargetException) ex).getTargetException();
		}
		return null;
	}
	
}