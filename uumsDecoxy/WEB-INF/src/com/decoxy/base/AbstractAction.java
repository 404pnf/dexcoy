package com.decoxy.base;

import java.lang.reflect.Method;
import java.util.Map;

import com.decoxy.common.exception.SystemException;
import com.decoxy.common.util.ExceptionUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class AbstractAction extends ActionSupport implements
		ModelDriven<Object> {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public ActionContext getContext() {
		return ActionContext.getContext();
	}

	/**
	 * 共通方法
	 */
	public String execute() {
		Map<String, Object> session = getContext().getSession();

		AbstractModel model = (AbstractModel) getModel();

		String methodName = model.getMethodName();

		LOG.info(methodName);

		Object result = "";
		try {
			Method method = getClass().getMethod(methodName);
			if (method != null) {
				result = method.invoke(this);
				LOG.info(method.getName());
			}
		} catch (Exception e) {
			AbstractException ex;
			Throwable targetException = ExceptionUtil.getTargetException(e);
			if (targetException != null
					&& targetException instanceof AbstractException) {
				ex = (AbstractException) targetException;
			} else {
				ex = new SystemException("SYS_999", e);
			}
			// session EX
			session.put("ex", ex);
			// 把ex类给错误画面。
			return ERROR;
		}
		LOG.info(methodName);

		return result.toString();
	}

	/**
	 * 设置session信息
	 * 
	 * @param key
	 * @param obj
	 */
	protected void setSessionAttribute(String key, Object obj) {

		getContext().getSession().put(key, obj);
	}

	/**
	 * 取得session信息
	 * 
	 * @param key
	 * @param obj
	 */
	protected Object getSessionAttribute(String key) {

		return (Object) getContext().getSession().get(key);

	}

	/**
	 * 销毁session
	 */
	protected void destroySessionAttribute() {
		getContext().getSession().clear();
	}

}
