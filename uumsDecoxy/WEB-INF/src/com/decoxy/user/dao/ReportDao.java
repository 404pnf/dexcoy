package com.decoxy.user.dao;

import java.util.List;

import com.decoxy.base.AbstractDAO;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.util.DateUtil;
import com.decoxy.common.util.GetXml;
import com.decoxy.common.util.StringUtil;
import com.decoxy.user.dao.daoBean.UserDaoBean;
import com.decoxy.user.formbean.ReportFormBean;

/**
 * ReportDao
 * 
 * @author liwudong
 * 
 */
@SuppressWarnings("rawtypes")
public class ReportDao extends AbstractDAO implements ReportDaoIF {

	/**
	 * 查询注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int registerUserCount(DBAccessor dba, ReportFormBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		if (!StringUtil.isNull(bean.getYm())) {
			sql.append("and create_Date like '" + bean.getYm() + "%' ");
		}
		if (!StringUtil.isNull(bean.getSite())) {
			sql.append("and register_source = '" + bean.getSite() + "' ");
		}

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 通过用户名或邮箱查询用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public ReportFormBean searchByUserOrMail(DBAccessor dba, ReportFormBean bean) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("username, ");
		sql.append("mail, ");
		sql.append("register_source, ");
		sql.append("substring(create_date,1,19) create_date ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		// sql.append("and status = '1' ");
		sql.append("and username like '" + bean.getUserormail() + "%' ");
		sql.append("or mail like '" + bean.getUserormail() + "%' ");

		List<UserDaoBean> userList = queryList(dba, sql.toString(), UserDaoBean.class.getName());

		bean.setSearchList(userList);

		if (userList.size() > 0) {
			bean.setCountFlg("1");
		} else {
			bean.setCountFlg("2");
		}
		return bean;

	}

	/**
	 * 通过日期站点统计注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean reportByDate(DBAccessor dba, ReportFormBean bean) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("register_source, ");
		sql.append("count(uid) regNum ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		if (!StringUtil.isNull(bean.getStartDate())) {
			sql.append("and substring( create_Date, 1, 10 ) >= '" + bean.getStartDate() + "' ");
		}
		if (!StringUtil.isNull(bean.getEndDate())) {
			sql.append("and substring( create_Date, 1, 10 ) <= '" + bean.getEndDate() + "' ");
		}
		if (!StringUtil.isNull(bean.getSite())) {
			
			String[] obj = bean.getSite().split(",");
			String tempSite = "";
			for(int i = 0; i < obj.length; i++){
				if(StringUtil.isNull(tempSite)){
					tempSite = tempSite + "'" + obj[i].trim()+ "'";
				}else{
					tempSite = tempSite  + "," + "'" + obj[i].trim()+ "'";
				}
			}
			sql.append("and register_source in ( " + tempSite + " ) ");
		}
		sql.append("GROUP BY register_source ");

		List<UserDaoBean> userList = queryList(dba, sql.toString(), UserDaoBean.class.getName());

		bean.setReport1List(userList);

		if (userList.size() > 0) {
			bean.setReport1Flg("1");
		} else {
			bean.setReport1Flg("2");
		}

		return bean;

	}

	/**
	 * 通过日期站点统计注册用户总量
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int reportByDateCount(DBAccessor dba, ReportFormBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		if (!StringUtil.isNull(bean.getStartDate())) {
			sql.append("and substring( create_Date, 1, 10 ) >= '" + bean.getStartDate() + "' ");
		}
		if (!StringUtil.isNull(bean.getEndDate())) {
			sql.append("and substring( create_Date, 1, 10 ) <= '" + bean.getEndDate() + "' ");
		}
		if (!StringUtil.isNull(bean.getSite())) {
			
			String[] obj = bean.getSite().split(",");
			String tempSite = "";
			for(int i = 0; i < obj.length; i++){
				if(StringUtil.isNull(tempSite)){
					tempSite = tempSite + "'" + obj[i].trim()+ "'";
				}else{
					tempSite = tempSite  + "," + "'" + obj[i].trim()+ "'";
				}
			}
			sql.append("and register_source in ( " + tempSite + " ) ");
		}

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 按照年统计各个月注册用户数量
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public ReportFormBean reportByMonth(DBAccessor dba, ReportFormBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("substring(create_Date,1,7) month, ");
		sql.append("count(uid) regNum ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		if (!StringUtil.isNull(bean.getReportYear())) {
			sql.append("and substring( create_Date, 1, 4 ) = '" + bean.getReportYear() + "' ");
		}
		sql.append("GROUP BY substring(create_Date,1,7) ");

		List<UserDaoBean> userList = queryList(dba, sql.toString(), UserDaoBean.class.getName());

		bean.setReport2List(userList);

		if (userList.size() > 0) {
			bean.setReport2Flg("1");
		} else {
			bean.setReport2Flg("2");
		}

		return bean;

	}

	/**
	 * 通过年统计注册用户总量
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int reportByMonthCount(DBAccessor dba, ReportFormBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		if (!StringUtil.isNull(bean.getReportYear())) {
			sql.append("and substring( create_Date, 1, 4 ) = '" + bean.getReportYear() + "' ");
		}

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 最近7天站点注册数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public int lastfor7(DBAccessor dba) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and substring( create_Date, 1, 10 ) >= '" + DateUtil.getStrDate(-7) + "' ");

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 最近30天站点注册数量
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public int lastfor30(DBAccessor dba) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and substring( create_Date, 1, 10 ) >= '" + DateUtil.getStrDate(-30) + "' ");

		count = queryInteger(dba, sql.toString());

		return count;

	}
	
	public String queryPassword(ReportFormBean bean)throws DBException{
		String result = GetXml.getUserXml(bean.getUsername());
		System.out.println(result);
		return result;
		
	}
	
}