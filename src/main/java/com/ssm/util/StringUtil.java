package com.ssm.util;

public class StringUtil {
	/**
	 * 校验是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		return ((str == null) || str.equals("") || str.equalsIgnoreCase("null"));
	}

}
