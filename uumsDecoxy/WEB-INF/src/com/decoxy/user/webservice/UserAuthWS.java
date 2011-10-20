package com.decoxy.user.webservice;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.decoxy.base.AbstractException;
import com.decoxy.base.AbstractWebService;
import com.decoxy.base.ServiceIF;
import com.decoxy.common.CommonConst;
import com.decoxy.common.util.XmlUtil;
import com.decoxy.user.bussniss.UserService;
import com.decoxy.user.formbean.UserFormBean;

/**
 * 
 * @author Administrator
 * 
 */
public class UserAuthWS extends AbstractWebService {

	private static Logger logger = Logger.getLogger(UserAuthWS.class.getName());

	/**
	 * 登录用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginUser(String parm) {

		logger.debug(CommonConst.STR_LOGIN + CommonConst.STARTLOG);

		logger.debug("parm===" + parm);

		Document doc = null;

		UserFormBean bean = new UserFormBean();

		try {

			bean.parse(CommonConst.LOGIN, parm);

			ServiceIF service = new UserService(bean);

			bean = (UserFormBean) service.service("loginUser");

			try {
				doc = XmlUtil.buildXMLDoc(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(CommonConst.STR_LOGIN, e);
			}

		} catch (AbstractException e) {
			bean.setResult("1");
			bean.setMessageByKey(e.getMessageID());
			try {
				doc = XmlUtil.buildXMLDoc(bean);
				logger.error(CommonConst.STR_LOGIN, e);
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error(CommonConst.STR_LOGIN, e);
			}
		}

		logger.debug("returnXML===" + doc.asXML());

		if ("0".equals(bean.getResult())) {
			logger.info(CommonConst.STR_LOGIN + ":Success");
		} else {
			logger.info(CommonConst.STR_LOGIN + ":Failure");
		}
		logger.debug(CommonConst.STR_LOGIN + CommonConst.ENDLOG);

		return doc.asXML();
	}

	/**
	 * 注册用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerUser(String parm) throws Exception {

		logger.debug(CommonConst.STR_REGISTER + CommonConst.STARTLOG);

		logger.debug("parm===" + parm);

		Document doc = null;

		UserFormBean bean = new UserFormBean();

		try {

			bean.parse(CommonConst.REGISTER, parm);

			ServiceIF service = new UserService(bean);

			bean = (UserFormBean) service.service("registerUser");

			try {
				doc = XmlUtil.buildXMLDoc(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(CommonConst.STR_REGISTER, e);
			}

		} catch (AbstractException e) {
			bean.setResult("1");
			bean.setMessageByKey(e.getMessageID());
			try {
				doc = XmlUtil.buildXMLDoc(bean);
				logger.error(CommonConst.STR_REGISTER, e);
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error(CommonConst.STR_REGISTER, e);
			}
		}

		logger.debug("returnXML===" + doc.asXML());
		if ("0".equals(bean.getResult())) {
			logger.info(CommonConst.STR_REGISTER + ":Success");
		} else {
			logger.info(CommonConst.STR_REGISTER + ":Failure");
		}
		logger.debug(CommonConst.STR_REGISTER + CommonConst.ENDLOG);

		return doc.asXML();

	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUser(String parm) throws Exception {

		logger.debug(CommonConst.STR_UPDATE + CommonConst.STARTLOG);

		logger.debug("parm===" + parm);

		Document doc = null;

		UserFormBean bean = new UserFormBean();

		try {

			bean.parse(CommonConst.UPDATE, parm);

			ServiceIF service = new UserService(bean);

			bean = (UserFormBean) service.service("updateUser");

			try {
				doc = XmlUtil.buildXMLDoc(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(CommonConst.STR_UPDATE, e);
			}

		} catch (AbstractException e) {
			bean.setResult("1");
			bean.setMessageByKey(e.getMessageID());
			try {
				doc = XmlUtil.buildXMLDoc(bean);
				logger.error(CommonConst.STR_UPDATE, e);
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error(CommonConst.STR_UPDATE, e);
			}
		}

		logger.debug("returnXML===" + doc.asXML());
		if ("0".equals(bean.getResult())) {
			logger.info(CommonConst.STR_UPDATE + ":Success");
		} else {
			logger.info(CommonConst.STR_UPDATE + ":Failure");
		}
		logger.debug(CommonConst.STR_UPDATE + CommonConst.ENDLOG);

		return doc.asXML();

	}

	/**
	 * 删除用户信息
	 * 
	 * @param parm
	 * @return
	 * @throws Exception
	 */
	public String deleteUser(String parm) throws Exception {

		logger.debug(CommonConst.STR_DELETE + CommonConst.STARTLOG);

		logger.debug("parm===" + parm);

		Document doc = null;

		UserFormBean bean = new UserFormBean();

		try {

			bean.parse(CommonConst.DELETE, parm);

			ServiceIF service = new UserService(bean);

			bean = (UserFormBean) service.service("deleteUser");

			try {
				doc = XmlUtil.buildXMLDoc(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(CommonConst.STR_DELETE, e);
			}

		} catch (AbstractException e) {
			bean.setResult("1");
			bean.setMessageByKey(e.getMessageID());
			try {
				doc = XmlUtil.buildXMLDoc(bean);
				logger.error(CommonConst.STR_DELETE, e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		logger.debug("returnXML===" + doc.asXML());
		if ("0".equals(bean.getResult())) {
			logger.info(CommonConst.STR_DELETE + ":Success");
		} else {
			logger.info(CommonConst.STR_DELETE + ":Failure");
		}
		logger.debug(CommonConst.STR_DELETE + CommonConst.ENDLOG);

		return doc.asXML();
	}

	/**
	 * 取得用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUserInformation(String parm) {

		logger.debug(CommonConst.STR_USERINFO + CommonConst.STARTLOG);

		logger.debug("parm===" + parm);

		Document doc = null;

		UserFormBean bean = new UserFormBean();

		try {

			bean.parse(CommonConst.USERINFO, parm);

			ServiceIF service = new UserService(bean);

			bean = (UserFormBean) service.service("getUserInfo");

			try {
				doc = XmlUtil.buildXMLDoc(bean);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(CommonConst.STR_USERINFO, e);
			}

		} catch (AbstractException e) {
			bean.setResult("1");
			bean.setMessageByKey(e.getMessageID());
			try {
				doc = XmlUtil.buildXMLDoc(bean);
				logger.error(CommonConst.STR_USERINFO, e);
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error(CommonConst.STR_USERINFO, e);
			}
		}

		logger.debug("returnXML===" + doc.asXML());

		if ("0".equals(bean.getResult())) {
			logger.info(CommonConst.STR_USERINFO + ":Success");
		} else {
			logger.info(CommonConst.STR_USERINFO + ":Failure");
		}
		logger.debug(CommonConst.STR_USERINFO + CommonConst.ENDLOG);

		return doc.asXML();
	}

}