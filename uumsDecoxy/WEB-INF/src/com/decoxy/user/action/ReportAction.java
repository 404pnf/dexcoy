package com.decoxy.user.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.decoxy.base.AbstractAction;
import com.decoxy.base.ServiceIF;
import com.decoxy.common.CodeListKeyBean;
import com.decoxy.common.util.StringUtil;
import com.decoxy.common.webservice.WebServiceMap;
import com.decoxy.user.bussniss.ReportService;
import com.decoxy.user.formbean.ReportFormBean;

public class ReportAction extends AbstractAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	protected Log log = LogFactory.getLog(getClass());

	ReportFormBean bean = new ReportFormBean();

	public String index() throws Exception {
		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}
		return SUCCESS;
	}

	/**
	 * 通过用户名或邮箱查询用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchInit() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		return SUCCESS;
	}

	/**
	 * 通过用户名或邮箱查询用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchByUserOrMail() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		ServiceIF service = new ReportService(bean);
		if (!StringUtil.isNull(bean.getUserormail())) {
			bean = (ReportFormBean) service.service("search");
		} else {
			bean.setCountFlg("");
		}

		return SUCCESS;
	}

	/**
	 * 清除查询页面条件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resetSearch() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		bean.setUserormail("");
		bean.setStartDate("");
		bean.setEndDate("");
		bean.setSite("");
		bean.setReportYear("");
		return SUCCESS;
	}

	/**
	 * 统计最近7天和最近30天注册用户数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reportInit() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		ServiceIF service = new ReportService(bean);

		bean = (ReportFormBean) service.service("report");

		List<String> tempList = WebServiceMap.getAllUrlList();
		List<CodeListKeyBean> urlList = new ArrayList<CodeListKeyBean>();
		for (int i = 0; i < tempList.size(); i++) {
			CodeListKeyBean keyBean = new CodeListKeyBean();
			keyBean.setKey(tempList.get(i).trim());
			keyBean.setValue(tempList.get(i).trim());
			keyBean.setStatus("");
			urlList.add(keyBean);
		}

		bean.setUrlList(urlList);

		return SUCCESS;
	}

	/**
	 * 通过日期站点统计注册用户数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reportByDate() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		ServiceIF service = new ReportService(bean);

		bean = (ReportFormBean) service.service("reportByDate");

		List<String> tempList = WebServiceMap.getAllUrlList();
		List<CodeListKeyBean> urlList = new ArrayList<CodeListKeyBean>();
		for (int i = 0; i < tempList.size(); i++) {
			CodeListKeyBean keyBean = new CodeListKeyBean();
			keyBean.setKey(tempList.get(i).trim());
			keyBean.setValue(tempList.get(i).trim());
			keyBean.setStatus("");
			urlList.add(keyBean);
		}

		bean.setUrlList(urlList);

		if (!StringUtil.isNull(bean.getSite())) {
			String[] obj = bean.getSite().split(",");
			for (int n = 0; n < urlList.size(); n++) {
				for (int i = 0; i < obj.length; i++) {
					if (bean.getUrlList().get(n).getKey().trim().equals(obj[i].trim())) {
						bean.getUrlList().get(n).setStatus("checked");
					}
				}
			}
		}

		return SUCCESS;
	}

	/**
	 * 统计每年各个月用户注册数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reportByMonth() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		if (StringUtil.isNull(bean.getReportYear())) {
			return SUCCESS;
		}
		ServiceIF service = new ReportService(bean);

		bean = (ReportFormBean) service.service("reportByMonth");

		List<String> tempList = WebServiceMap.getAllUrlList();
		List<CodeListKeyBean> urlList = new ArrayList<CodeListKeyBean>();
		for (int i = 0; i < tempList.size(); i++) {
			CodeListKeyBean keyBean = new CodeListKeyBean();
			keyBean.setKey(tempList.get(i).trim());
			keyBean.setValue(tempList.get(i).trim());
			keyBean.setStatus("");
			urlList.add(keyBean);
		}

		bean.setUrlList(urlList);

		return SUCCESS;
	}

	/**
	 * 清除报表页面查询条件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resetReport() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		bean.setUserormail("");
		bean.setStartDate("");
		bean.setEndDate("");
		bean.setSite("");
		bean.setReportYear("");
		List<String> tempList = WebServiceMap.getAllUrlList();
		List<CodeListKeyBean> urlList = new ArrayList<CodeListKeyBean>();
		for (int i = 0; i < tempList.size(); i++) {
			CodeListKeyBean keyBean = new CodeListKeyBean();
			keyBean.setKey(tempList.get(i).trim());
			keyBean.setValue(tempList.get(i).trim());
			keyBean.setStatus("");
			urlList.add(keyBean);
		}
		return SUCCESS;
	}

	/**
	 * 查询注册用户数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public String registerUserCount() throws Exception {

		Object userInfo = getSessionAttribute("userInfo");
		if (StringUtil.isNull(userInfo)) {
			return "welcome";
		}

		ServiceIF service = new ReportService(bean);

		bean = (ReportFormBean) service.service("registerUserCount");

		return SUCCESS;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toLogin() throws Exception {

		return SUCCESS;
	}

	/**
	 * 验证用户名密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkPass() throws Exception {

		ServiceIF service = new ReportService(bean);
		String tpassword = (String) service.service("checkPass");

		// Verify the legitimacy of landing
		if (bean.getUsername() == null || bean.getPassword() == null) {
			return "error";
		}
		if (tpassword == null) {
			System.out.println("return password is null");
			return "error";

		} else {
			if (tpassword.equals(bean.getPassword())) {
				System.out.println("1T:" + tpassword + "O:" + bean.getPassword());
				this.setSessionAttribute("userInfo", bean.getUsername());
				bean.setMessage("");
				return "success";
			} else {
				System.out.println("2T:" + tpassword + "O:" + bean.getPassword());
				bean.setMessage("用户名或密码不正确");
				return "error";
			}
		}
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toExit() throws Exception {
		destroySessionAttribute();
		return "welcome";
	}

	public Object getModel() {
		return bean;
	}

}