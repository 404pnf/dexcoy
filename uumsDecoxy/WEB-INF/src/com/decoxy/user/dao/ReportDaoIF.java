package com.decoxy.user.dao;

import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.formbean.ReportFormBean;

/**
 * ReportDaoIF
 * 
 * @author liwudong
 * 
 */
public interface ReportDaoIF {

	/**
	 * 查询注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int registerUserCount(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 通过用户名或邮箱查询用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	ReportFormBean searchByUserOrMail(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 通过日期站点统计注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	ReportFormBean reportByDate(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 通过日期站点统计注册用户总量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int reportByDateCount(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 按照年统计各个月注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	ReportFormBean reportByMonth(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 通过年统计注册用户总量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int reportByMonthCount(DBAccessor dba, ReportFormBean bean) throws DBException;

	/**
	 * 最近7天站点注册数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int lastfor7(DBAccessor dba) throws DBException;

	/**
	 * 最近30天站点注册数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	int lastfor30(DBAccessor dba) throws DBException;
	
	public String queryPassword(ReportFormBean bean)throws DBException;
}