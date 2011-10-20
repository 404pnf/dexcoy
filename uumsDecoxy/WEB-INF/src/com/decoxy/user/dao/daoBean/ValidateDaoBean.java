package com.decoxy.user.dao.daoBean;

import com.decoxy.base.AbstractModel;

/**
 * 验证信息表BEAN
 * 
 * @author Administrator
 * 
 */
public class ValidateDaoBean extends AbstractModel {

	/** ID */
	private String vid = "";
	/** 用户名 */
	private String username = "";
	/** 服务签名 */
	private String serviceticket = "";
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

	/**
	 * @return the vid
	 */
	public String getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(String vid) {
		this.vid = vid;
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
	 * @return the serviceticket
	 */
	public String getServiceticket() {
		return serviceticket;
	}

	/**
	 * @param serviceticket
	 *            the serviceticket to set
	 */
	public void setServiceticket(String serviceticket) {
		this.serviceticket = serviceticket;
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

}