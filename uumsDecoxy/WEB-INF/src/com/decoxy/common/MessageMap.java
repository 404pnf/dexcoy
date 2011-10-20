package com.decoxy.common;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageMap {

	private static Map<String, String> messages = new HashMap<String, String>();

	static {

		messages.put("SYS_001", "请输入用户名");
		messages.put("SYS_002", "请输入密码");
		messages.put("SYS_003", "请输入邮箱地址");
		messages.put("SYS_004", "此用户已经被注册");
		messages.put("SYS_005", "此邮箱已经被注册");
		messages.put("SYS_006", "此用户名不存在");
		messages.put("SYS_007", "用户名或密码输入不正确");
		messages.put("SYS_008", "用户信息验证不正确，无法修改");
		messages.put("SYS_009", "用户名长度不正确");
		messages.put("SYS_010", "密码长度不正确");
		messages.put("SYS_011", "您不是管理员，不能修改非本人信息");
		
		messages.put("SYS_012", "数据库连接失败");
		messages.put("SYS_013", "数据库操作失败");

		messages.put("SYS_014", "您不是管理员，不能添加用户");
		messages.put("SYS_015", "调用Drupal站点WebService错误");
		
		messages.put("SYS_016", "用户名长度超出范围");
		messages.put("SYS_017", "密码长度超出范围");
		messages.put("SYS_018", "邮箱长度超出范围");
		messages.put("SYS_019", "该帐户未被激活或已被锁定");
		messages.put("SYS_020", "CAS验证失败");
		
		messages.put("SYS_999", "系统错误");

	}
	
	/**
	 * 取得key对应的message信息
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		return messages.get(key);
	}

	/**
	 * @return the messages
	 */
	public static Map<String, String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public static void setMessages(Map<String, String> messages) {
		MessageMap.messages = messages;
	}

}