package com.decoxy.user.dao.daoBean;

import com.decoxy.base.AbstractModel;

/**
 * 用户BEAN
 * 
 * @author Administrator
 * 
 */
public class UserDaoBean extends AbstractModel {

	/** uid */
	private String uid = "";
	/** 名称 */
	private String username = "";
	/** 密码 */
	private String password = "";
	/** 明文密码 */
	private String pass_expressly = "";
	/** 加密类型 */
	private String encryotion_type = "";
	/** init邮箱 */
	private String init = "";
	/** 邮箱 */
	private String mail = "";
	/** 角色 */
	private String role = "";
	/** 激活状态 */
	private String status = "";
	/** 注册来源 */
	private String register_source = "";
	/** 删除等级 */
	private String deleteLevel = "";
	/** 删除通知 */
	private String deleteNotify = "";
	/** 创建者 */
	private String create_user = "";
	/** 创建日期 */
	private String create_date = "";
	/** 更新者 */
	private String update_user = "";
	/** 更新日期 */
	private String update_date = "";
	/** 删除标志 */
	private String del_flg = "";
	/** 数量 */
	private String regNum = "";
	/** 月份 */
	private String month = "";
	/** 编辑URL */
	private String editURL = "";

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the pass_expressly
	 */
	public String getPass_expressly() {
		return pass_expressly;
	}

	/**
	 * @param pass_expressly
	 *            the pass_expressly to set
	 */
	public void setPass_expressly(String pass_expressly) {
		this.pass_expressly = pass_expressly;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the create_user
	 */
	public String getCreate_user() {
		return create_user;
	}

	/**
	 * @param create_user
	 *            the create_user to set
	 */
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	/**
	 * @return the create_date
	 */
	public String getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date
	 *            the create_date to set
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	/**
	 * @return the update_user
	 */
	public String getUpdate_user() {
		return update_user;
	}

	/**
	 * @param update_user
	 *            the update_user to set
	 */
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	/**
	 * @return the update_date
	 */
	public String getUpdate_date() {
		return update_date;
	}

	/**
	 * @param update_date
	 *            the update_date to set
	 */
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	/**
	 * @return the del_flg
	 */
	public String getDel_flg() {
		return del_flg;
	}

	/**
	 * @param del_flg
	 *            the del_flg to set
	 */
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	/**
	 * @return the encryotion_type
	 */
	public String getEncryotion_type() {
		return encryotion_type;
	}

	/**
	 * @param encryotion_type
	 *            the encryotion_type to set
	 */
	public void setEncryotion_type(String encryotion_type) {
		this.encryotion_type = encryotion_type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the register_source
	 */
	public String getRegister_source() {
		return register_source;
	}

	/**
	 * @param register_source
	 *            the register_source to set
	 */
	public void setRegister_source(String register_source) {
		this.register_source = register_source;
	}

	/**
	 * @return the init
	 */
	public String getInit() {
		return init;
	}

	/**
	 * @param init
	 *            the init to set
	 */
	public void setInit(String init) {
		this.init = init;
	}

	/**
	 * @return the deleteLevel
	 */
	public String getDeleteLevel() {
		return deleteLevel;
	}

	/**
	 * @param deleteLevel
	 *            the deleteLevel to set
	 */
	public void setDeleteLevel(String deleteLevel) {
		this.deleteLevel = deleteLevel;
	}

	/**
	 * @return the deleteNotify
	 */
	public String getDeleteNotify() {
		return deleteNotify;
	}

	/**
	 * @param deleteNotify
	 *            the deleteNotify to set
	 */
	public void setDeleteNotify(String deleteNotify) {
		this.deleteNotify = deleteNotify;
	}

	/**
	 * @return the regNum
	 */
	public String getRegNum() {
		return regNum;
	}

	/**
	 * @param regNum
	 *            the regNum to set
	 */
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the editURL
	 */
	public String getEditURL() {
		return editURL;
	}

	/**
	 * @param editURL the editURL to set
	 */
	public void setEditURL(String editURL) {
		this.editURL = editURL;
	}

}