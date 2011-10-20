package com.decoxy.user.dao;

import com.decoxy.base.AbstractDAO;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.dao.daoBean.OperationLogDaoBean;

@SuppressWarnings("rawtypes")
public class OperationLogDao extends AbstractDAO implements OperationLogDaoIF {

	/**
	 * 新增操作日志表信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	public void insert(DBAccessor dba, OperationLogDaoBean bean, String username)
			throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append("operation_log ( ");
		sql.append("username, ");
		sql.append("type, ");
		sql.append("location, ");
		sql.append("message, ");
		sql.append("{commonFields} ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("'" + bean.getUsername() + "', ");
		sql.append("'" + bean.getType() + "', ");
		sql.append("'" + bean.getLocation() + "', ");
		sql.append("'" + bean.getMessage() + "', ");
		sql.append("{commonFieldsValue} ");
		sql.append(") ");

		String str = sql.toString();

		str = getAddField(str, username);

		updateExecute(dba, str);

	}
}