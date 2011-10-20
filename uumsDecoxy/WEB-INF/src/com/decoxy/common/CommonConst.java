package com.decoxy.common;


public interface CommonConst {

	// 登录
	int LOGIN = 0;	
	// 注册
	int REGISTER = 1;
	// 更新
	int UPDATE = 2;
	// 删除
	int DELETE = 3;
	// 用户信息
	int USERINFO = 4;
	
	// 分割符
	String SPLIT_KEY = "waiyanshe";
	
	// 系统管理员
	String SYSTEM_USER = "1";
	// 普通用户
	String ORDINARY_USER = "2";
	// 开始
	String STARTLOG = "-start-";
	// 结束
	String ENDLOG = "-end-";
	
	// 登录
	String STR_LOGIN = "LOGIN";	
	// 注册
	String STR_REGISTER = "REGISTER";
	// 更新
	String STR_UPDATE = "UPDATE";
	// 删除
	String STR_DELETE = "DELETE";
	// 取得用户信息
	String STR_USERINFO = "USERINFO";

	// SubSiteWsXmlFile
	String SubSiteWsXmlFile = "/config/subsite-ws.xml";
	// WebServiceUrl
	String WebServiceUrl = "/SubSite/webservice/url";
//	// ID
//	String ID = "ID";
//	// type
//	String Type = "type";
//	// url
//	String Url = "url";
//	// method
//	String Method = "method";
	
	String operation_delete = "delete";

	String operation_update = "update";
	
	String ws_type_soap = "soap";
	
	String ws_type_xmlrpc = "xmlrpc";

	// 用户名长度
	int USERNAME_LEN = 60;
	// 密码长度
	int PASSWORD_LEN = 32;
	// 邮箱长度
	int MAIL_LEN = 64;
	
	// ID
	String user_cancel_block = "user_cancel_block";
	// user_cancel_block_unpublish
	String user_cancel_block_unpublish = "user_cancel_block_unpublish";
	// user_cancel_reassign
	String user_cancel_reassign = "user_cancel_reassign";
	// user_cancel_delete
	String user_cancel_delete = "user_cancel_delete";
}