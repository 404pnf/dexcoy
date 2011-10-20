package com.decoxy.user.bussniss;

import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import redstone.xmlrpc.XmlRpcClient;

import com.decoxy.base.AbstractService;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.CommonConst;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.util.StringUtil;
import com.decoxy.common.webservice.WebServiceMap;
import com.decoxy.common.webservice.WebserviceItem;
import com.decoxy.user.dao.OperationLogDao;
import com.decoxy.user.dao.OperationLogDaoIF;
import com.decoxy.user.dao.UserDao;
import com.decoxy.user.dao.UserDaoIF;
import com.decoxy.user.dao.ValidateDao;
import com.decoxy.user.dao.ValidateDaoIF;
import com.decoxy.user.dao.daoBean.OperationLogDaoBean;
import com.decoxy.user.dao.daoBean.UserDaoBean;
import com.decoxy.user.dao.daoBean.ValidateDaoBean;
import com.decoxy.user.formbean.UserFormBean;

public class UserService extends AbstractService {

	private UserFormBean bean = new UserFormBean();

	static Logger logger = Logger.getLogger(UserService.class.getName());

	public UserService(UserFormBean bean) {
		this.bean = bean;
	}

	/**
	 * 登录用户
	 * 
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public UserFormBean loginUser(DBAccessor dba) throws DBException {

		logger.debug(CommonConst.STR_LOGIN + CommonConst.STARTLOG);

		UserDaoIF dao = new UserDao();

		UserDaoBean tempBean = bean.getUpdateList().get(0);
		// // 查询用户是否存在
		// int num = dao.checkUser(dba, bean);
		//
		// if (num == 0) {
		// bean.setResult("1");
		// bean.setMessageByKey("SYS_006");
		// bean.setMsgFlg(false);
		// }

		// 用户验证
		String password = StringUtil.md5(tempBean.getPass_expressly());
		tempBean.setPassword(password);

		int count = dao.activeAuthUser(dba, tempBean);

		if (count > 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_019");
			bean.setMsgFlg(false);
			return bean;
		}

		int num = dao.authUser(dba, tempBean);

		if (num == 0) {

			// 调用激活站点登录接口 begin
			// try {
			// XmlRpcClient client = new XmlRpcClient(
			// "http://v.chinadebate.org/userinfo", true);
			// Object retObj = client.invoke(
			// "user_services.authUser",
			// new Object[] { tempBean.getUsername(),
			// tempBean.getPass_expressly() });
			// logger.debug(CommonConst.STR_LOGIN + "token===" + retObj);
			//
			// if (num == 0) {
			// bean.setResult("1");
			// bean.setMessageByKey("SYS_007");
			// bean.setMsgFlg(false);
			// } else {
			// UserDaoBean insertBean = new UserDaoBean();
			// insertBean.setUsername("");
			// insertBean.setPass_expressly("");
			// insertBean.setPassword("");
			// insertBean.setMail("");
			// insertBean.setRegister_source("");
			// insertBean.setStatus("");
			// dao.insert(dba, insertBean, insertBean.getUsername());
			// }
			//
			// } catch (Exception e) {
			// bean.setResult("1");
			// bean.setMessageByKey("SYS_015");
			// bean.setMsgFlg(false);
			// return bean;
			// }
			// 调用激活站点登录接口 end

			bean.setResult("1");
			bean.setMessageByKey("SYS_007");
			bean.setMsgFlg(false);
		}

		if (bean.isMsgFlg()) {

			// 验证用户明文密码是否为空
			String str = dao.authPassExpressly(dba, tempBean);

			if (StringUtil.isNull(str)) {
				dao.update(dba, tempBean, tempBean.getUsername());
			}

			// 查询用户信息
			bean.setReturnBean(dao.searchUser(dba, tempBean.getUsername()));

			String temp = UUID.randomUUID().toString();

			// 用户验证
			ValidateDaoIF validateDao = new ValidateDao();
			ValidateDaoBean validateDaoBean = new ValidateDaoBean();
			validateDaoBean.setUsername(bean.getUpdateList().get(0).getUsername());
			validateDaoBean.setServiceticket(temp);

			String serviceticket = validateDao.checkUser(dba, validateDaoBean);

			if (StringUtil.isNull(serviceticket)) {
				validateDao.insert(dba, validateDaoBean);
				bean.setBaseInfo_ServiceTicket(temp);
			} else {
				bean.setBaseInfo_ServiceTicket(serviceticket);
			}

			bean.setResult("0");
			bean.setMessageByKey("");
		}

		logger.debug(CommonConst.STR_LOGIN + CommonConst.ENDLOG);

		return bean;

	}

	/**
	 * 新增用户信息
	 * 
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public UserFormBean registerUser(DBAccessor dba) throws DBException {

		logger.debug(CommonConst.STR_REGISTER + CommonConst.STARTLOG);

		UserDaoIF dao = new UserDao();

		UserDaoBean tempBean = bean.getUpdateList().get(0);

		// 用户名是否输入
		if (StringUtil.strlen(tempBean.getUsername()) == 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_001");
			bean.setMsgFlg(false);
			return bean;
		}
		// 邮箱是否输入
		if (StringUtil.strlen(tempBean.getMail()) == 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_002");
			bean.setMsgFlg(false);
			return bean;
		}
		// 用户名长度验证
		if (!StringUtil.validateStrByLength(tempBean.getUsername(), CommonConst.USERNAME_LEN)) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_016");
			bean.setMsgFlg(false);
			return bean;
		}
		// 密码长度验证
		if (!StringUtil.validateStrByLength(tempBean.getPass_expressly(), CommonConst.PASSWORD_LEN)) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_017");
			bean.setMsgFlg(false);
			return bean;
		}
		// 邮箱长度验证
		if (!StringUtil.validateStrByLength(tempBean.getMail(), CommonConst.MAIL_LEN)) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_018");
			bean.setMsgFlg(false);
			return bean;
		}

		// 用户验证
		int count = dao.checkUser(dba, tempBean);

		if (count > 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_004");
			bean.setMsgFlg(false);
			return bean;
		}

		// 邮箱验证
		count = dao.checkMail(dba, tempBean);

		if (count > 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_005");
			bean.setMsgFlg(false);
			return bean;
		}

		// 调用激活站点注册接口 begin
		// try {
		// XmlRpcClient client = new XmlRpcClient(
		// "http://v.chinadebate.org/userinfo", true);
		// Object retObj = client
		// .invoke("user_services.checkUser",
		// new Object[] { tempBean.getUsername(),
		// tempBean.getMail() });
		// logger.debug(CommonConst.STR_LOGIN + "token===" + retObj);
		//
		// // 用户名已经被注册
		// if (count == 1) {
		// bean.setResult("1");
		// bean.setMessageByKey("SYS_004");
		// bean.setMsgFlg(false);
		// return bean;
		// }
		// // 邮箱已经被注册
		// if (count == 2) {
		// bean.setResult("1");
		// bean.setMessageByKey("SYS_005");
		// bean.setMsgFlg(false);
		// return bean;
		// }
		//
		// } catch (Exception e) {
		// bean.setResult("1");
		// bean.setMessageByKey("SYS_015");
		// bean.setMsgFlg(false);
		// return bean;
		// }
		// 调用激活站点注册接口 end

		// 角色验证
		String username = bean.getUpdateList().get(0).getUsername();

		if (!bean.getBaseInfo_UserName().equals(username)) {

			// // 是否有权限添加用户
			// String role = dao.getRole(dba, bean.getBaseInfo_UserName());

			// if (!StringUtil.isNull(role)
			// && CommonConst.SYSTEM_USER.equals(role)) {
			// 用户验证
			ValidateDaoIF validateDao = new ValidateDao();
			ValidateDaoBean validateDaoBean = new ValidateDaoBean();
			validateDaoBean.setUsername(bean.getBaseInfo_UserName());
			validateDaoBean.setServiceticket(bean.getBaseInfo_ServiceTicket());
			int num = validateDao.authUser(dba, validateDaoBean);

			if (num == 0) {
				// bean.setResult("1");
				// bean.setMessageByKey("SYS_008");
				// bean.setMsgFlg(false);

				String init = dao.getInit(dba, bean.getBaseInfo_UserName());

				String md5 = StringUtil.md5(bean.getBaseInfo_UserName() + StringUtil.getString(init));

				logger.debug(CommonConst.STR_REGISTER + "md5====" + md5);
				logger.debug(CommonConst.STR_REGISTER + "ServiceTicket====" + bean.getBaseInfo_ServiceTicket());
				// 验证initMD5
				if (!md5.equals(bean.getBaseInfo_ServiceTicket())) {
					bean.setResult("1");
					bean.setMessageByKey("SYS_008");
					bean.setMsgFlg(false);
					return bean;
				}
			}
			// } else {
			// bean.setResult("1");
			// bean.setMessageByKey("SYS_014");
			// bean.setMsgFlg(false);
			//
			// }
		}

		if (bean.isMsgFlg()) {
			// 密码MD5加密
			String password = StringUtil.md5(bean.getUpdateList().get(0).getPass_expressly());
			bean.getUpdateList().get(0).setPassword(password);

			dao.insert(dba, bean.getUpdateList().get(0), bean.getBaseInfo_UserName());

			if (bean.getBaseInfo_UserName().equals(username)) {

				String temp = UUID.randomUUID().toString();

				// //新增验证信息
				ValidateDaoIF validateDao = new ValidateDao();
				ValidateDaoBean validateDaoBean = new ValidateDaoBean();
				validateDaoBean.setUsername(bean.getUpdateList().get(0).getUsername());
				validateDaoBean.setServiceticket(temp);
				validateDao.insert(dba, validateDaoBean);

				bean.setBaseInfo_ServiceTicket(temp);
			}

			// 查询用户信息
			bean.setReturnBean(dao.searchUser(dba, bean.getBaseInfo_UserName()));

			bean.setResult("0");
			bean.setMessageByKey("");
		}

		logger.debug(CommonConst.STR_REGISTER + CommonConst.ENDLOG);

		return bean;

	}

	/**
	 * 更新用户信息
	 * 
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public UserFormBean updateUser(DBAccessor dba) throws DBException {
		logger.debug(CommonConst.STR_UPDATE + CommonConst.STARTLOG);

		UserDaoIF dao = new UserDao();

		// 用户验证
		ValidateDaoIF validateDao = new ValidateDao();
		ValidateDaoBean validateDaoBean = new ValidateDaoBean();
		validateDaoBean.setUsername(bean.getBaseInfo_UserName());
		validateDaoBean.setServiceticket(bean.getBaseInfo_ServiceTicket());
		int num = validateDao.authUser(dba, validateDaoBean);

		if (num == 0) {

			String init = dao.getInit(dba, bean.getBaseInfo_UserName());

			String md5 = StringUtil.md5(bean.getBaseInfo_UserName() + StringUtil.getString(init));

			logger.debug(CommonConst.STR_UPDATE + "md5====" + md5);
			logger.debug(CommonConst.STR_UPDATE + "ServiceTicket====" + bean.getBaseInfo_ServiceTicket());
			// 验证initMD5
			if (!md5.equals(bean.getBaseInfo_ServiceTicket())) {
				bean.setResult("1");
				bean.setMessageByKey("SYS_008");
				bean.setMsgFlg(false);

				logger.debug(CommonConst.STR_UPDATE + CommonConst.ENDLOG);

				return bean;
			}
		}

		logger.debug("update_Count======" + bean.getUpdateList().size());

		for (int i = 0; i < bean.getUpdateList().size(); i++) {

			UserDaoBean tempBean = new UserDaoBean();

			tempBean = bean.getUpdateList().get(i);

			if (!StringUtil.isNull(bean.getBaseInfo_UserName()) && !StringUtil.isNull(tempBean.getUsername())) {

				if (!StringUtil.isNull(tempBean.getMail())) {
					// 邮箱验证
					num = dao.checkUpdateMail(dba, tempBean);

					if (num > 0) {
						bean.setResult("1");
						bean.setMessageByKey("SYS_005");
						bean.setMsgFlg(false);

						logger.debug(CommonConst.STR_UPDATE + CommonConst.ENDLOG + "SYS_005");

						return bean;
					}
				}

				// if (!bean.getBaseInfo_UserName().equals(tempBean.getUsername())) {

				// 是否有权限添加用户
				// String role = dao.getRole(dba,
				// bean.getBaseInfo_UserName());

				// if (StringUtil.isNull(role)
				// || CommonConst.ORDINARY_USER.equals(role)) {
				// bean.setResult("1");
				// bean.setMessageByKey("SYS_011");
				// bean.setMsgFlg(false);
				//
				// logger.debug(CommonConst.STR_UPDATE
				// + CommonConst.ENDLOG + "SYS_005");
				//
				// return bean;
				// }
				// }

				if (bean.isMsgFlg()) {

					if (!StringUtil.isNull(tempBean.getPass_expressly())) {
						// 密码MD5加密
						String password = StringUtil.md5(tempBean.getPass_expressly());
						tempBean.setPassword(password);
					}

					dao.update(dba, tempBean, bean.getBaseInfo_UserName());

					List<WebserviceItem> wsUrlList = WebServiceMap.getList("update");

					Call call;
					Service service = new Service();
					String url = "";
					logger.debug("xml_Count======" + wsUrlList.size());
					for (int n = 0; n < wsUrlList.size(); n++) {

						try {
							WebserviceItem tempItem = wsUrlList.get(n);

							url = tempItem.getUrl();

							if (url.indexOf(bean.getEditSource()) > -1) {
								continue;
							}

							UserDaoBean wsBean = dao.searchUser(dba, tempBean.getUsername());

							String init = wsBean.getInit();
							String tempMail = tempBean.getMail();
							if (StringUtil.isNull(tempMail)) {
								tempMail = wsBean.getMail();
							}

							String signature = StringUtil.md5(tempBean.getUsername() + init);

							logger.debug(CommonConst.STR_UPDATE + "::url===" + url);
							logger.debug(CommonConst.STR_UPDATE + "::username===" + wsBean.getUsername());
							logger.debug(CommonConst.STR_UPDATE + "::password===" + tempBean.getPass_expressly());
							logger.debug(CommonConst.STR_UPDATE + "::in_mail===" + tempBean.getMail());
							logger.debug(CommonConst.STR_UPDATE + "::out_mail===" + tempMail);
							logger.debug(CommonConst.STR_UPDATE + "::status===" + tempBean.getStatus());
							logger.debug(CommonConst.STR_UPDATE + "::signature===" + signature);
							if (CommonConst.ws_type_soap.equals(tempItem.getType().toLowerCase())) {
								call = (Call) service.createCall();
								call.setTargetEndpointAddress(new java.net.URL(url));
								call.setUseSOAPAction(true);
								call.setOperationName(new QName(tempItem.getAddress(), tempItem.getMethod()));

								Object token = (String) call.invoke(new Object[] {
										StringUtil.utf8Decode(wsBean.getUsername()), tempBean.getPass_expressly(),
										tempBean.getMail(), tempBean.getStatus(), signature });
								logger.debug(CommonConst.STR_UPDATE + "::returnValue===" + token);
							} else if (CommonConst.ws_type_xmlrpc.equals(tempItem.getType().toLowerCase())) {

								XmlRpcClient client = new XmlRpcClient(url, true);
								Object token = client
										.invoke(tempItem.getMethod(),
												new Object[] { StringUtil.utf8Decode(wsBean.getUsername()),
														tempBean.getPass_expressly(), tempMail, tempBean.getStatus(),
														signature });

								logger.debug(CommonConst.STR_UPDATE + "::url===" + url);
								logger.debug(CommonConst.STR_UPDATE + "::returnValue===" + token);

							}
						} catch (Exception e) {
							try {
								OperationLogDaoBean olBean = new OperationLogDaoBean();
								olBean.setUsername(StringUtil.utf8Decode(tempBean.getUsername()));
								olBean.setType("update");
								olBean.setLocation(url);
								olBean.setMessage(StringUtil.utf8Decode(e.toString()));
								OperationLogDaoIF olDao = new OperationLogDao();
								olDao.insert(dba, olBean, bean.getBaseInfo_UserName());
								// bean.setResult("1");
								// bean.setMessageByKey("SYS_015");
								// bean.setMsgFlg(false);
								logger.error(CommonConst.STR_UPDATE, e);
								// return bean;
							} catch (Exception e1) {
								logger.error(CommonConst.STR_UPDATE, e1);
							}
						}
					}

				}

			} else {
				bean.setResult("1");
				bean.setMessageByKey("SYS_011");
				bean.setMsgFlg(false);
				return bean;
			}
		}

		if (bean.isMsgFlg()) {
			// 查询用户信息
			bean.setReturnBean(dao.searchUser(dba, bean.getBaseInfo_UserName()));
			bean.setResult("0");
			bean.setMessageByKey("");
		}

		logger.debug(CommonConst.STR_UPDATE + CommonConst.ENDLOG);

		return bean;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public UserFormBean deleteUser(DBAccessor dba) throws DBException {

		logger.debug(CommonConst.STR_DELETE + CommonConst.STARTLOG);

		UserDaoIF dao = new UserDao();

		// // 是否有权限添加用户
		// String role = dao.getRole(dba, bean.getBaseInfo_UserName());
		//
		// if (StringUtil.isNull(role) ||
		// CommonConst.ORDINARY_USER.equals(role)) {
		// bean.setResult("1");
		// bean.setMessageByKey("SYS_011");
		// bean.setMsgFlg(false);
		//
		// logger.debug(CommonConst.STR_DELETE + CommonConst.ENDLOG + "SYS_005");
		//
		// return bean;
		// }

		// 用户验证
		ValidateDaoIF validateDao = new ValidateDao();
		ValidateDaoBean validateDaoBean = new ValidateDaoBean();
		validateDaoBean.setUsername(bean.getBaseInfo_UserName());
		validateDaoBean.setServiceticket(bean.getBaseInfo_ServiceTicket());
		int num = validateDao.authUser(dba, validateDaoBean);

		if (num == 0) {
			// bean.setResult("1");
			// bean.setMessageByKey("SYS_008");
			// bean.setMsgFlg(false);

			String init = dao.getInit(dba, bean.getBaseInfo_UserName());

			String md5 = StringUtil.md5(bean.getBaseInfo_UserName() + StringUtil.getString(init));

			logger.debug(CommonConst.STR_DELETE + "md5====" + md5);
			logger.debug(CommonConst.STR_DELETE + "ServiceTicket====" + bean.getBaseInfo_ServiceTicket());
			// 验证initMD5
			if (!md5.equals(bean.getBaseInfo_ServiceTicket())) {
				bean.setResult("1");
				bean.setMessageByKey("SYS_008");
				bean.setMsgFlg(false);
				logger.debug(CommonConst.STR_DELETE + CommonConst.ENDLOG);
				return bean;
			}

		}

		logger.debug("delete_Count======" + bean.getUpdateList().size());
		for (int i = 0; i < bean.getUpdateList().size(); i++) {

			UserDaoBean tempBean = new UserDaoBean();

			tempBean = bean.getUpdateList().get(i);

			if (bean.isMsgFlg()) {

				if (CommonConst.user_cancel_block.equals(tempBean.getDeleteLevel())
						|| CommonConst.user_cancel_block_unpublish.equals(tempBean.getDeleteLevel())) {
					tempBean.setDel_flg("0");
				} else {
					tempBean.setDel_flg("1");
				}

				logger.debug("Username======" + tempBean.getUsername());
				tempBean.setUsername(StringUtil.utf8Decode(tempBean.getUsername()));
				tempBean.setStatus("0");
				dao.delete(dba, tempBean, bean.getBaseInfo_UserName());

				logger.debug("Username======" + tempBean.getUsername());
				List<WebserviceItem> wsUrlList = WebServiceMap.getList("delete");

				Call call;
				Service service = new Service();
				String url = "";
				logger.debug("xml_Count======" + wsUrlList.size());
				for (int n = 0; n < wsUrlList.size(); n++) {

					try {
						WebserviceItem tempItem = wsUrlList.get(n);

						url = tempItem.getUrl();

						if (url.indexOf(bean.getEditSource()) != -1) {
							continue;
						}

						if (CommonConst.ws_type_soap.equals(tempItem.getType().toLowerCase())) {
							call = (Call) service.createCall();
							call.setTargetEndpointAddress(new java.net.URL(url));
							call.setUseSOAPAction(true);
							call.setOperationName(new QName(tempItem.getAddress(), tempItem.getMethod()));

							Object token = (String) call.invoke(new Object[] {
									StringUtil.utf8Decode(tempBean.getUsername()), tempBean.getDeleteLevel(),
									tempBean.getDeleteNotify() });
							logger.debug(CommonConst.STR_DELETE + "::returnValue===" + token);
						} else if (CommonConst.ws_type_xmlrpc.equals(tempItem.getType().toLowerCase())) {
							XmlRpcClient client = new XmlRpcClient(url, true);
							Object token = client.invoke(
									tempItem.getMethod(),
									new Object[] { StringUtil.utf8Decode(tempBean.getUsername()),
											tempBean.getDeleteLevel(), tempBean.getDeleteNotify() });
							logger.debug(CommonConst.STR_DELETE + "::url===" + url);
							logger.debug(CommonConst.STR_DELETE + "::returnValue===" + token);

						}
					} catch (Exception e) {

						try {
							OperationLogDaoBean olBean = new OperationLogDaoBean();
							olBean.setUsername(StringUtil.utf8Decode(tempBean.getUsername()));
							olBean.setType("delete");
							olBean.setLocation(url);
							olBean.setMessage(StringUtil.utf8Decode(e.toString()));
							OperationLogDaoIF olDao = new OperationLogDao();
							olDao.insert(dba, olBean, bean.getBaseInfo_UserName());
							// bean.setResult("1");
							// bean.setMessageByKey("SYS_015");
							// bean.setMsgFlg(false);
							// return bean;
							logger.error(CommonConst.STR_DELETE, e);
						} catch (Exception e1) {
							logger.error(CommonConst.STR_DELETE, e1);
						}

					}
				}

			}
		}

		if (bean.isMsgFlg()) {
			// 查询用户信息
			bean.setReturnBean(dao.searchUser(dba, bean.getBaseInfo_UserName()));
			bean.setResult("0");
			bean.setMessageByKey("");
		}

		logger.debug(CommonConst.STR_DELETE + CommonConst.ENDLOG);

		return bean;
	}

	/**
	 * 取得用户信息
	 * 
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public UserFormBean getUserInfo(DBAccessor dba) throws DBException {

		logger.debug(CommonConst.STR_USERINFO + CommonConst.STARTLOG);

		UserDaoIF dao = new UserDao();

		boolean flg = true;
		// 调用CAS站点
		if (!flg) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_020");
			bean.setMsgFlg(false);
			return bean;
		}

		// 查询用户是否存在
		UserDaoBean userBean = new UserDaoBean();
		userBean.setUsername(bean.getBaseInfo_UserName());
		int num = dao.checkUser(dba, userBean);

		if (num == 0) {
			bean.setResult("1");
			bean.setMessageByKey("SYS_006");
			bean.setMsgFlg(false);
			return bean;
		}

		// 查询用户信息
		bean.setReturnBean(dao.searchUser(dba, bean.getBaseInfo_UserName()));
		bean.setResult("0");
		bean.setMessageByKey("");

		logger.debug(CommonConst.STR_DELETE + CommonConst.ENDLOG);

		return bean;

	}

	public Object getModel() {
		return bean;
	}

	/**
	 * @return the bean
	 */
	public UserFormBean getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(UserFormBean bean) {
		this.bean = bean;
	}

}
