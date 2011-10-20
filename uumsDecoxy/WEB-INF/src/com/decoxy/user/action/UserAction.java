package com.decoxy.user.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.decoxy.base.AbstractAction;
import com.decoxy.base.ServiceIF;
import com.decoxy.user.bussniss.UserService;
import com.decoxy.user.formbean.UserFormBean;

/**
 * <code>Set welcome message.</code>
 */
public class UserAction extends AbstractAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	protected Log log = LogFactory.getLog(getClass());

	UserFormBean bean = new UserFormBean();
	
	/**
	 * 新增用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerUser() throws Exception {

//		// 用户名
//		bean.setUsername("test1");
//		// 密码
//		bean.setPass_expressly("test");
//		// 邮箱
//		bean.setMail("test@test.com");

		ServiceIF service = new UserService(bean);

		bean = (UserFormBean) service.service("registerUser");

		return SUCCESS;
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUser() throws Exception {

//		// 用户名
//		bean.setUsername("test1");
//		// 密码
//		bean.setPass_expressly("test");
//		// 邮箱
//		bean.setMail("test@test.com");

		ServiceIF service = new UserService(bean);

		bean = (UserFormBean) service.service("updateUser");

		return SUCCESS;
	}

	/**
	 * 验证用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String authUser() throws Exception {

//		// 用户名
//		bean.setUsername("test1");
//		// 密码
//		bean.setPass_expressly("test");

		ServiceIF service = new UserService(bean);

		bean = (UserFormBean) service.service("authUser");

		return SUCCESS;
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