package com.decoxy.user.formbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.decoxy.base.AbstractModel;
import com.decoxy.common.CommonConst;
import com.decoxy.common.MessageMap;
import com.decoxy.common.util.StringUtil;
import com.decoxy.user.dao.daoBean.UserDaoBean;

/**
 * 用户FormBean
 * 
 * @author Administrator
 * 
 */
public class UserFormBean extends AbstractModel {

	/** result */
	private String result = "";
	/** msgFlg */
	private boolean msgFlg = true;
	/** 用户名 */
	private String baseInfo_UserName = "";
	/** serviceTicket */
	private String baseInfo_ServiceTicket = "";
	/** baseInfo_CasTicket */
	private String baseInfo_CasTicket = "";
	/** editSource */
	private String editSource = "";
	/** msgList */
	private List<String> msgList = new ArrayList<String>();
	/** updateList */
	private List<UserDaoBean> updateList = new ArrayList<UserDaoBean>();
	/** returnBean */
	private UserDaoBean returnBean = new UserDaoBean();

	/**
	 * @param parm
	 */
	public void parse(int key, String parm) {

		Document xmlDoc = null;
		try {
			parm = parm.replace("\n", "");
			xmlDoc = DocumentHelper.parseText(parm);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element root = xmlDoc.getRootElement();

		// 取得BaseInfo节点
		Element baseInfoElm = root.element("BaseInfo");

		UserDaoBean userbean = new UserDaoBean();

		updateList = new ArrayList<UserDaoBean>();

		switch (key) {
		case CommonConst.LOGIN:
			// 用户名
			userbean = new UserDaoBean();

			userbean.setUsername(StringUtil.getValue(baseInfoElm.elementText("UserName")));
			// 密码
			userbean.setPass_expressly(StringUtil.getValue(baseInfoElm.elementText("PassWord")));

			updateList.add(userbean);

			break;
		case CommonConst.REGISTER:
			// 取得验证用户名
			setBaseInfo_UserName(StringUtil.getValue(baseInfoElm.elementText("UserName")));
			// 取得验证用户串
			setBaseInfo_ServiceTicket(StringUtil.getValue(baseInfoElm.elementText("ServiceTicket")));
			// 取得UpdateInfo节点
			Element updateInfoElm = root.element("UpdateInfo");

			userbean = new UserDaoBean();

			// 用户名
			userbean.setUsername(StringUtil.getValue(updateInfoElm.elementText("UserName")));
			// 密码
			userbean.setPass_expressly(StringUtil.getValue(updateInfoElm.elementText("PassWord")));
			// 邮件
			userbean.setMail(StringUtil.getValue(updateInfoElm.elementText("Mail")));
			// 注册来源
			userbean.setRegister_source(StringUtil.getValue(updateInfoElm.elementText("RegisterSource")));
			// 激活状态
			userbean.setStatus(StringUtil.getValue(updateInfoElm.elementText("Status")));

			updateList.add(userbean);
			break;
		case CommonConst.UPDATE:
		case CommonConst.DELETE:
			// 取得验证用户名
			setBaseInfo_UserName(StringUtil.getValue(baseInfoElm.elementText("UserName")));
			// 取得验证用户串
			setBaseInfo_ServiceTicket(StringUtil.getValue(baseInfoElm.elementText("ServiceTicket")));
			// EditSource
			setEditSource(StringUtil.getValue(baseInfoElm.elementText("EditSource")));

			for (Iterator it = root.elementIterator("UpdateInfo"); it.hasNext();) {

				userbean = new UserDaoBean();

				Element s = (Element) it.next();

				// 用户名
				userbean.setUsername(StringUtil.getValue(s.elementText("UserName")));
				// 密码
				userbean.setPass_expressly(StringUtil.getValue(s.elementText("PassWord")));
				// 邮件
				userbean.setMail(StringUtil.getValue(s.elementText("Mail")));
				// 角色
				userbean.setRole(StringUtil.getValue(s.elementText("Role")));
				// 激活状态
				userbean.setStatus(StringUtil.getValue(s.elementText("Status")));
				// 删除级别
				userbean.setDeleteLevel(StringUtil.getValue(s.elementText("DeleteLevel")));
				// 删除通知
				userbean.setDeleteNotify(StringUtil.getValue(s.elementText("DeleteNotify")));

				updateList.add(userbean);
			}
			break;
		case CommonConst.USERINFO:
			// 取得验证用户名
			setBaseInfo_UserName(StringUtil.getValue(baseInfoElm.elementText("UserName")));
			// 取得Cas验证用户串
			setBaseInfo_CasTicket(StringUtil.getValue(baseInfoElm.elementText("CasTicket")));
			break;
		default:
			break;
		}
	}

	/**
	 * 设置message
	 */
	public void setMessageByKey(String key) {

		if (!StringUtil.isNull(key)) {

			String message = MessageMap.getMessage(key);

			String msg = key + CommonConst.SPLIT_KEY + message;

			getMsgList().add(msg);

		}
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the msgFlg
	 */
	public boolean isMsgFlg() {
		return msgFlg;
	}

	/**
	 * @param msgFlg
	 *            the msgFlg to set
	 */
	public void setMsgFlg(boolean msgFlg) {
		this.msgFlg = msgFlg;
	}

	/**
	 * @return the baseInfo_UserName
	 */
	public String getBaseInfo_UserName() {
		return baseInfo_UserName;
	}

	/**
	 * @param baseInfo_UserName
	 *            the baseInfo_UserName to set
	 */
	public void setBaseInfo_UserName(String baseInfo_UserName) {
		this.baseInfo_UserName = baseInfo_UserName;
	}

	/**
	 * @return the baseInfo_ServiceTicket
	 */
	public String getBaseInfo_ServiceTicket() {
		return baseInfo_ServiceTicket;
	}

	/**
	 * @param baseInfo_ServiceTicket
	 *            the baseInfo_ServiceTicket to set
	 */
	public void setBaseInfo_ServiceTicket(String baseInfo_ServiceTicket) {
		this.baseInfo_ServiceTicket = baseInfo_ServiceTicket;
	}

	/**
	 * @return the msgList
	 */
	public List<String> getMsgList() {
		return msgList;
	}

	/**
	 * @param msgList
	 *            the msgList to set
	 */
	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

	/**
	 * @return the updateList
	 */
	public List<UserDaoBean> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(List<UserDaoBean> updateList) {
		this.updateList = updateList;
	}

	/**
	 * @return the returnBean
	 */
	public UserDaoBean getReturnBean() {
		return returnBean;
	}

	/**
	 * @param returnBean
	 *            the returnBean to set
	 */
	public void setReturnBean(UserDaoBean returnBean) {
		this.returnBean = returnBean;
	}

	/**
	 * @return the editSource
	 */
	public String getEditSource() {
		return editSource;
	}

	/**
	 * @param editSource
	 *            the editSource to set
	 */
	public void setEditSource(String editSource) {
		this.editSource = editSource;
	}

	/**
	 * @return the baseInfo_CasTicket
	 */
	public String getBaseInfo_CasTicket() {
		return baseInfo_CasTicket;
	}

	/**
	 * @param baseInfo_CasTicket
	 *            the baseInfo_CasTicket to set
	 */
	public void setBaseInfo_CasTicket(String baseInfo_CasTicket) {
		this.baseInfo_CasTicket = baseInfo_CasTicket;
	}

}