package com.decoxy.user.dao;

import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.dao.daoBean.UserDaoBean;

public interface UserDaoIF {

	/**
	 * 新增用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	void insert(DBAccessor dba, UserDaoBean bean, String username) throws DBException;

	/**
	 * 更新用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	void update(DBAccessor dba, UserDaoBean bean, String username) throws DBException;

	/**
	 * 验证用户是否被锁定信息
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int activeAuthUser(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 验证用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int authUser(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 验证用户明文密码是否为空
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	String authPassExpressly(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 查询用户是否存在
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int checkUser(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 查询邮箱是否已经注册
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int checkMail(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 查询更新邮箱是否已经注册
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int checkUpdateMail(DBAccessor dba, UserDaoBean bean) throws DBException;

	/**
	 * 查询用户信息
	 * 
	 * @param dba
	 * @param username
	 * @return
	 * @throws DBException
	 */
	UserDaoBean searchUser(DBAccessor dba, String username) throws DBException;

	/**
	 * 删除用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	void delete(DBAccessor dba, UserDaoBean bean, String username) throws DBException;

	/**
	 * 查询用户角色
	 * 
	 * @param dba
	 * @param username
	 * @return
	 * @throws DBException
	 */
	String getRole(DBAccessor dba, String username) throws DBException;

	/**
	 * 查询用户Init
	 * 
	 * @param dba
	 * @param username
	 * @return
	 * @throws DBException
	 */
	String getInit(DBAccessor dba, String username) throws DBException;

}
