package com.decoxy.user.dao;

import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.dao.daoBean.OperationLogDaoBean;


public interface OperationLogDaoIF {
	
	/**
	 * 新增用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	void insert(DBAccessor dba, OperationLogDaoBean bean, String username)
			throws DBException;
}