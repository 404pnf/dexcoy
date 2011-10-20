package com.decoxy.user.formbean;

import java.util.ArrayList;
import java.util.List;

import com.decoxy.base.AbstractModel;
import com.decoxy.common.CodeListKeyBean;
import com.decoxy.user.dao.daoBean.UserDaoBean;

/**
 * ReportFormBean
 * 
 * @author Administrator
 * 
 */
public class ReportFormBean extends AbstractModel {

	/** 年月 */
	private String ym = "";
	/** 站点 */
	private String site = "";
	/** 统计数量 */
	private String userCount = "";
	/** 统计数量 */
	private String userCount1 = "";

	/** 用户名或邮箱 */
	private String userormail = "";
	/** searchList */
	private List<UserDaoBean> searchList = new ArrayList<UserDaoBean>();
	/** countFlg */
	private String countFlg = "";
	/** startDate */
	private String startDate = "";
	/** endDate */
	private String endDate = "";
	/** searchList */
	private List<UserDaoBean> report1List = new ArrayList<UserDaoBean>();
	/** countFlg */
	private String report1Flg = "";
	/** report2Flg */
	private String report2Flg = "";
	/** reportYear */
	private String reportYear = "";
	/** searchList */
	private List<UserDaoBean> report2List = new ArrayList<UserDaoBean>();
	/** 统计数量 */
	private String userCount2 = "";
	/** lastfor7 */
	private String lastfor7 = "";
	/** lastfor30 */
	private String lastfor30 = "";
	/** urlList */
	private List<CodeListKeyBean> urlList = new ArrayList<CodeListKeyBean>();
	/** username */
	private String username;
	/** password */
	private String password;
	/** tpassword */
	private String tpassword;
	/** tpassword */
	private String message;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTpassword() {
		return tpassword;
	}

	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	/**
	 * @return the ym
	 */
	public String getYm() {
		return ym;
	}

	/**
	 * @param ym
	 *            the ym to set
	 */
	public void setYm(String ym) {
		this.ym = ym;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the userCount
	 */
	public String getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount
	 *            the userCount to set
	 */
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	/**
	 * @return the userCount1
	 */
	public String getUserCount1() {
		return userCount1;
	}

	/**
	 * @param userCount1
	 *            the userCount1 to set
	 */
	public void setUserCount1(String userCount1) {
		this.userCount1 = userCount1;
	}

	/**
	 * @return the userormail
	 */
	public String getUserormail() {
		return userormail;
	}

	/**
	 * @param userormail
	 *            the userormail to set
	 */
	public void setUserormail(String userormail) {
		this.userormail = userormail;
	}

	/**
	 * @return the searchList
	 */
	public List<UserDaoBean> getSearchList() {
		return searchList;
	}

	/**
	 * @param searchList
	 *            the searchList to set
	 */
	public void setSearchList(List<UserDaoBean> searchList) {
		this.searchList = searchList;
	}

	/**
	 * @return the countFlg
	 */
	public String getCountFlg() {
		return countFlg;
	}

	/**
	 * @param countFlg
	 *            the countFlg to set
	 */
	public void setCountFlg(String countFlg) {
		this.countFlg = countFlg;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the report1List
	 */
	public List<UserDaoBean> getReport1List() {
		return report1List;
	}

	/**
	 * @param report1List
	 *            the report1List to set
	 */
	public void setReport1List(List<UserDaoBean> report1List) {
		this.report1List = report1List;
	}

	/**
	 * @return the report1Flg
	 */
	public String getReport1Flg() {
		return report1Flg;
	}

	/**
	 * @param report1Flg
	 *            the report1Flg to set
	 */
	public void setReport1Flg(String report1Flg) {
		this.report1Flg = report1Flg;
	}

	/**
	 * @return the report2Flg
	 */
	public String getReport2Flg() {
		return report2Flg;
	}

	/**
	 * @param report2Flg
	 *            the report2Flg to set
	 */
	public void setReport2Flg(String report2Flg) {
		this.report2Flg = report2Flg;
	}

	/**
	 * @return the reportYear
	 */
	public String getReportYear() {
		return reportYear;
	}

	/**
	 * @param reportYear
	 *            the reportYear to set
	 */
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	/**
	 * @return the report2List
	 */
	public List<UserDaoBean> getReport2List() {
		return report2List;
	}

	/**
	 * @param report2List
	 *            the report2List to set
	 */
	public void setReport2List(List<UserDaoBean> report2List) {
		this.report2List = report2List;
	}

	/**
	 * @return the userCount2
	 */
	public String getUserCount2() {
		return userCount2;
	}

	/**
	 * @param userCount2
	 *            the userCount2 to set
	 */
	public void setUserCount2(String userCount2) {
		this.userCount2 = userCount2;
	}

	/**
	 * @return the lastfor7
	 */
	public String getLastfor7() {
		return lastfor7;
	}

	/**
	 * @param lastfor7
	 *            the lastfor7 to set
	 */
	public void setLastfor7(String lastfor7) {
		this.lastfor7 = lastfor7;
	}

	/**
	 * @return the lastfor30
	 */
	public String getLastfor30() {
		return lastfor30;
	}

	/**
	 * @param lastfor30
	 *            the lastfor30 to set
	 */
	public void setLastfor30(String lastfor30) {
		this.lastfor30 = lastfor30;
	}

	/**
	 * @return the urlList
	 */
	public List<CodeListKeyBean> getUrlList() {
		return urlList;
	}

	/**
	 * @param urlList
	 *            the urlList to set
	 */
	public void setUrlList(List<CodeListKeyBean> urlList) {
		this.urlList = urlList;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}