package com.decoxy.user.dao;

import java.util.List;

import com.decoxy.base.AbstractDAO;
import com.decoxy.base.DBAccessor;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.util.DateUtil;
import com.decoxy.common.util.StringUtil;
import com.decoxy.user.dao.daoBean.UserDaoBean;
import com.decoxy.user.formbean.UserFormBean;

@SuppressWarnings("rawtypes")
public class UserDao extends AbstractDAO implements UserDaoIF {

	/**
	 * 新增用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	public void insert(DBAccessor dba, UserDaoBean bean, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append("users ( ");
		sql.append("username, ");
		sql.append("password, ");
		sql.append("pass_expressly, ");
		sql.append("encryotion_type, ");
		sql.append("init, ");
		sql.append("mail, ");
		sql.append("role, ");
		sql.append("status, ");
		sql.append("register_source, ");
		sql.append("deleteLevel, ");
		sql.append("{commonFields} ");
		sql.append(") ");
		sql.append("values ");
		sql.append("( ");
		sql.append("'" + bean.getUsername() + "', ");
		sql.append("'" + bean.getPassword() + "', ");
		sql.append("'" + bean.getPass_expressly() + "', ");
		sql.append("'MD5', ");
		sql.append("'" + bean.getMail() + "', ");
		sql.append("'" + bean.getMail() + "', ");
		sql.append("'" + bean.getRole() + "', ");
		sql.append("'" + bean.getStatus() + "', ");
		sql.append("'" + bean.getRegister_source() + "', ");
		sql.append("'" + bean.getDeleteLevel() + "', ");
		sql.append("{commonFieldsValue} ");
		sql.append(") ");

		String str = sql.toString();

		str = getAddField(str, username);

		updateExecute(dba, str);

	}

	/**
	 * 更新用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	public void update(DBAccessor dba, UserDaoBean bean, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("update users set ");
		if (!StringUtil.isNull(bean.getPass_expressly())) {
			sql.append("password = '" + bean.getPassword() + "', ");
			sql.append("pass_expressly = '" + bean.getPass_expressly() + "', ");
		}
		if (!StringUtil.isNull(bean.getMail())) {
			sql.append("mail = '" + bean.getMail() + "', ");
		}
		if (!StringUtil.isNull(bean.getDel_flg())) {
			sql.append("del_flg = '" + bean.getDel_flg() + "', ");
		}
		if (!StringUtil.isNull(bean.getStatus())) {
			sql.append("status = '" + bean.getStatus() + "', ");
		}
		if (!StringUtil.isNull(bean.getRole())) {
			sql.append("role = '" + bean.getRole() + "', ");
		}
		sql.append("{commonFields} ");
		sql.append("where username = '" + bean.getUsername() + "' ");
		sql.append("and del_flg = '0' ");

		String str = sql.toString();

		str = getUpdateField(str, username);

		updateExecute(dba, str);
	}

	/**
	 * 验证用户是否被锁定信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int activeAuthUser(DBAccessor dba, UserDaoBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '0' ");
		sql.append("and username = '" + bean.getUsername() + "' ");
		sql.append("and password = '" + bean.getPassword() + "' ");

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 验证用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public int authUser(DBAccessor dba, UserDaoBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		// sql.append("and status = '1' ");
		sql.append("and username = '" + bean.getUsername() + "' ");
		sql.append("and password = '" + bean.getPassword() + "' ");

		count = queryInteger(dba, sql.toString());

		return count;

	}

	/**
	 * 验证用户明文密码是否为空
	 * 
	 * @param dba
	 * @param bean
	 * @throws DBException
	 */
	public String authPassExpressly(DBAccessor dba, UserDaoBean bean) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("pass_expressly ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and username = '" + bean.getUsername() + "' ");

		return queryString(dba, sql.toString());

	}

	/**
	 * 查询用户是否存在
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public int checkUser(DBAccessor dba, UserDaoBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and username = '" + bean.getUsername() + "' ");

		count = queryInteger(dba, sql.toString());

		return count;
	}

	/**
	 * 查询邮箱是否已经注册
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public int checkMail(DBAccessor dba, UserDaoBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and mail = '" + bean.getMail() + "' ");

		count = queryInteger(dba, sql.toString());

		return count;
	}

	/**
	 * 查询更新邮箱是否已经注册
	 * 
	 * @param dba
	 * @param bean
	 * @return
	 * @throws DBException
	 */
	public int checkUpdateMail(DBAccessor dba, UserDaoBean bean) throws DBException {

		int count = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("count(uid) ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and mail = '" + bean.getMail() + "' ");
		sql.append("and username <> '" + bean.getUsername() + "' ");

		count = queryInteger(dba, sql.toString());

		return count;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param dba
	 * @param username
	 * @return
	 * @throws DBException
	 */
	@SuppressWarnings("unchecked")
	public UserDaoBean searchUser(DBAccessor dba, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("* ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		// sql.append("and status = '1' ");
		sql.append("and username = '" + username + "' ");

		List<UserDaoBean> userList = queryList(dba, sql.toString(), UserDaoBean.class.getName());

		int num = userList.size();

		UserDaoBean bean = new UserDaoBean();

		if (num > 0) {
			bean.setUid(userList.get(0).getUid());
			bean.setUsername(userList.get(0).getUsername());
			bean.setPassword(userList.get(0).getPassword());
			bean.setPass_expressly(userList.get(0).getPass_expressly());
			bean.setEncryotion_type(userList.get(0).getEncryotion_type());
			bean.setInit(userList.get(0).getInit());
			bean.setMail(userList.get(0).getMail());
			bean.setRole(userList.get(0).getRole());
			bean.setStatus(userList.get(0).getStatus());
			bean.setRegister_source(userList.get(0).getRegister_source());
			bean.setDeleteLevel(userList.get(0).getDeleteLevel());
		}
		return bean;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param dba
	 * @param bean
	 * @param username
	 * @throws DBException
	 */
	public void delete(DBAccessor dba, UserDaoBean bean, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("update users set ");
		sql.append("deleteLevel = '" + bean.getDeleteLevel() + "', ");
		sql.append("status = '" + bean.getStatus() + "', ");
		sql.append("del_flg = '" + bean.getDel_flg() + "', ");
		sql.append("{commonFields} ");
		sql.append("where username = '" + bean.getUsername() + "' ");

		String str = sql.toString();

		str = getUpdateField(str, username);

		updateExecute(dba, str);
	}

	/**
	 * 查询用户角色
	 * 
	 * @param dba
	 * @param username
	 * @throws DBException
	 */
	public String getRole(DBAccessor dba, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("role ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and username = '" + username + "' ");

		return queryString(dba, sql.toString());

	}

	/**
	 * 查询用户init
	 * 
	 * @param dba
	 * @param username
	 * @throws DBException
	 */
	public String getInit(DBAccessor dba, String username) throws DBException {

		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("init ");
		sql.append("from ");
		sql.append("users ");
		sql.append("where del_flg = '0' ");
		sql.append("and status = '1' ");
		sql.append("and username = '" + username + "' ");

		return queryString(dba, sql.toString());

	}

}
