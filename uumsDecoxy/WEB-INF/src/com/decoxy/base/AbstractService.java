package com.decoxy.base;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.decoxy.common.exception.BusinessException;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.exception.SystemException;
import com.decoxy.common.util.ExceptionUtil;

public abstract class AbstractService implements ServiceIF {

	/** abstractModel */
	private AbstractModel abstractModel;

	/**
	 * service封装
	 * 
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws DBException
	 */
	public Object service(String methodName) throws AbstractException {
		Object serviceResult = null;
		//DBAccessor dba = new DBAccessor();
		DBAccessor dba = DBAccessor.getInstance();
		try {
			Method method = getClass().getMethod(methodName, DBAccessor.class);
			if (method != null) {
				serviceResult = method.invoke(this, dba);
				dba.commit();
			}
		} catch (Exception e) {
			Throwable targetException = ExceptionUtil.getTargetException(e);
			if (targetException != null
					&& targetException instanceof AbstractException) {
				dba.rollback();
				throw (AbstractException) targetException;
			}
			dba.rollback();
			throw new BusinessException(e);
		} finally {
			dba.close();
		}
		return serviceResult;
	}

	public String getCodeList() {
		return null;

	}

	public Integer conTest() throws AbstractException {
		int resultIngeter = 0;
		DBAccessor dba = DBAccessor.getInstance();
		try {
			Connection conn = dba.getConn();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			sql.append("count(uid) ");
			sql.append("from ");
			sql.append("users ");

			// DB执行
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet result = stmt.executeQuery();
			// 检索结果循环

			if (result.next()) {
				resultIngeter = new Integer(result.getInt(1));
			}
		} catch (Exception e) {
		} finally {
			dba.close();
		}
		return resultIngeter;
	}
	
	/**
	 * abstractModel取得
	 * 
	 * @return
	 */
	public AbstractModel getAbstractModel() {
		return abstractModel;
	}

	/**
	 * abstractModel设定
	 * 
	 * @param abstractModel
	 */
	public void setAbstractModel(AbstractModel abstractModel) {
		this.abstractModel = abstractModel;
	}

}