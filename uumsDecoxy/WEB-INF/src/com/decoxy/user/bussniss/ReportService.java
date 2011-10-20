package com.decoxy.user.bussniss;

import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.decoxy.base.AbstractService;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.CommonConst;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.util.StringUtil;
import com.decoxy.user.dao.ReportDao;
import com.decoxy.user.dao.ReportDaoIF;
import com.decoxy.user.dao.daoBean.UserDaoBean;
import com.decoxy.user.formbean.ReportFormBean;

public class ReportService extends AbstractService {

	static Logger logger = Logger.getLogger(UserService.class.getName());

	private ReportFormBean bean = new ReportFormBean();
	
	public ReportService(ReportFormBean bean) {
		this.bean = bean;
	}
	
	/**
	 * 查询注册用户数量
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean registerUserCount(DBAccessor dba) throws DBException {
		
		logger.debug("registerUserCount:" + CommonConst.STARTLOG);

		ReportDaoIF dao = new ReportDao();
		int num = dao.registerUserCount(dba, bean);
		bean.setUserCount(String.valueOf(num));
		logger.debug("registerUserCount:" + CommonConst.ENDLOG);
		return bean;
	}
	
	/**
	 * 通过用户名或邮箱查询用户信息
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean search(DBAccessor dba) throws DBException {
		
		logger.debug("search:" + CommonConst.STARTLOG);

		ReportDaoIF dao = new ReportDao();
		bean = dao.searchByUserOrMail(dba, bean);
		logger.debug("search:" + CommonConst.ENDLOG);
		int n = bean.getSearchList().size();
		if(n > 0){
			for(int i = 0;i < n; i++){
				UserDaoBean temp = bean.getSearchList().get(i);
				String editUrl = temp.getRegister_source() + "/search/user/" + StringUtil.utf8Encode(temp.getUsername());
				bean.getSearchList().get(i).setEditURL(editUrl);
			}
		}
		return bean;
	}
	
	/**
	 * 通过日期站点统计注册用户数量
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean reportByDate(DBAccessor dba) throws DBException {
		
		logger.debug("reportByDate:" + CommonConst.STARTLOG);

		ReportDaoIF dao = new ReportDao();
		bean = dao.reportByDate(dba, bean);
		int num = dao.reportByDateCount(dba, bean);
		bean.setUserCount1(String.valueOf(num));
		int num1 = dao.lastfor7(dba);
		int num2 = dao.lastfor30(dba);
		bean.setLastfor7(String.valueOf(num1));
		bean.setLastfor30(String.valueOf(num2));
		logger.debug("reportByDate:" + CommonConst.ENDLOG);
		return bean;
	}
	
	/**
	 * 统计每年各个月用户注册数量
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean reportByMonth(DBAccessor dba) throws DBException {
		
		logger.debug("reportByMonth:" + CommonConst.STARTLOG);
		ReportDaoIF dao = new ReportDao();
		bean = dao.reportByMonth(dba, bean);
		int num = dao.reportByMonthCount(dba, bean);
		bean.setUserCount2(String.valueOf(num));
		int num1 = dao.lastfor7(dba);
		int num2 = dao.lastfor30(dba);
		bean.setLastfor7(String.valueOf(num1));
		bean.setLastfor30(String.valueOf(num2));
		logger.debug("reportByMonth:" + CommonConst.ENDLOG);
		return bean;
	}

	/**
	 * 统计每年各个月用户注册数量
	 * @param dba
	 * @return
	 * @throws DBException
	 */
	public ReportFormBean report(DBAccessor dba) throws DBException {
		
		logger.debug("report:" + CommonConst.STARTLOG);

		ReportDaoIF dao = new ReportDao();
		int num1 = dao.lastfor7(dba);
		int num2 = dao.lastfor30(dba);
		bean.setLastfor7(String.valueOf(num1));
		bean.setLastfor30(String.valueOf(num2));
		logger.debug("report:" + CommonConst.ENDLOG);
		return bean;
	}
	
	public String checkPass(DBAccessor dba) throws DBException{
		ReportDaoIF dao = new ReportDao();
		String tpassword = dao.queryPassword(bean);
		return tpassword;
		
	}
	

	public Object getModel() {
		return bean;
	}

	/**
	 * @return the bean
	 */
	public ReportFormBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(ReportFormBean bean) {
		this.bean = bean;
	}
}