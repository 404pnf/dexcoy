package com.decoxy.user.dao;

import java.util.List;

import com.decoxy.base.AbstractDAO;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.user.dao.daoBean.ValidateDaoBean;

@SuppressWarnings("rawtypes")
public class ValidateDao extends AbstractDAO implements ValidateDaoIF {

	/**
	 * 新增Validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public void insert(DBAccessor dba, ValidateDaoBean bean) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append("validate ( ");
		sql.append("username, ");
		sql.append("serviceticket, ");
		sql.append("{commonFields} ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("'" + bean.getUsername() + "', ");
		sql.append("'" + bean.getServiceticket() + "', ");
		sql.append("{commonFieldsValue} ");
		sql.append(") ");

		String str = sql.toString();

		str = getAddField(str, bean.getUsername());

		updateExecute(dba, str);

	}

	/**
	 * 更新Validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public void update(DBAccessor dba, ValidateDaoBean bean) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("update validate set ");
		sql.append("serviceticket = '" + bean.getServiceticket() + "', ");
		sql.append("{commonFields} ");
		sql.append("where username = '" + bean.getUsername() + "' ");
		sql.append("and del_flg = '0' ");

		String str = sql.toString();

		str = getUpdateField(str, bean.getUsername());

		updateExecute(dba, str);

	}

	/**
	 * 查询validate信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public List<ValidateDaoBean> search(DBAccessor dba, ValidateDaoBean bean)
			throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from validate ");
		sql.append("where username = '" + bean.getUsername() + "' ");
		sql.append("and del_flg = '0' ");

		return queryList(dba, sql.toString(), ValidateDaoBean.class.getName());

	}
	
	/**
	 * 查询validate是否存在这个用户
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public String checkUser(DBAccessor dba, ValidateDaoBean bean)
			throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select serviceticket from validate ");
		sql.append("where username = '" + bean.getUsername() + "' ");
		sql.append("and del_flg = '0' ");

		return queryString(dba, sql.toString());

	}
	
	/**
	 * validate用户验证
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int authUser(DBAccessor dba, ValidateDaoBean bean)
			throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select count(vid) from validate ");
		sql.append("where username = '" + bean.getUsername() + "' ");
		sql.append("and serviceticket = '" + bean.getServiceticket() + "' ");
		sql.append("and del_flg = '0' ");

		return queryInteger(dba, sql.toString());

	}

}