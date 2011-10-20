package com.decoxy.user.dao;

import java.util.List;

import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.dao.daoBean.ValidateDaoBean;

public interface ValidateDaoIF {

	/**
	 * 新增Validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	void insert(DBAccessor dba, ValidateDaoBean bean) throws DBException;

	/**
	 * 更新Validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	void update(DBAccessor dba, ValidateDaoBean bean) throws DBException;

	/**
	 * 查询validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	List<ValidateDaoBean> search(DBAccessor dba, ValidateDaoBean bean)
			throws DBException;

	/**
	 * 查询validate是否存在这个用户
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	String checkUser(DBAccessor dba, ValidateDaoBean bean) throws DBException;

	/**
	 * validate用户验证
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int authUser(DBAccessor dba, ValidateDaoBean bean) throws DBException;
}