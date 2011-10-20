package com.decoxy.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.decoxy.common.exception.DBException;
import com.decoxy.common.util.DateUtil;

public abstract class AbstractDAO<T extends AbstractModel> {

	private PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(AbstractDAO.class.getName());

	/**
	 * DB检索结果List返回
	 * 
	 * @param conn
	 * @param stmt
	 * @param modelClsName
	 * @return
	 * @throws DBException
	 * @throws Exception
	 */
	public List<T> queryList(DBAccessor dba, String sql, String modelClsName) throws DBException {

		List<T> listResult = new ArrayList<T>();
		ResultSet result = null;

		logger.debug("queryList:" + sql);
		try {
			// DB检索
			stmt = dba.getConn().prepareStatement(sql);
			result = stmt.executeQuery();
			// 检索结果MetaData取得
			ResultSetMetaData rsMetaData = result.getMetaData();
			// 检索结果项目数取得
			int numberOfColumns = rsMetaData.getColumnCount();

			// 检索结果处理
			while (result.next()) {
				T resultModel = getInfoModelInstance(modelClsName);

				// 检索结果的项目名处理
				for (int fc = 1; fc <= numberOfColumns; fc++) {
					// 项目名取得
					String fieldName = rsMetaData.getColumnName(fc);
					String fieldValue = result.getString(fieldName);
					if (fieldValue == null) {
						fieldValue = "";
					}
					setFieldValueByName(resultModel, fieldName, fieldValue);
				}

				listResult.add((T) resultModel);
			}
		} catch (Exception e) {
			throw new DBException("SYS_013", e);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new DBException("SYS_013", e);
			}
		}
		return listResult;
	}

	/**
	 * 根据modelClass名取得Instance
	 * 
	 * @param modelClsName
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private T getInfoModelInstance(String modelClsName) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return (T) Class.forName(modelClsName).newInstance();
	}

	/**
	 * Object的field值设定
	 * 
	 * @param object
	 * @param fieldName
	 * @param fieldValue
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setFieldValueByName(AbstractModel object, String fieldName, Object fieldValue)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if (object == null) {
			return;
		}

		Class<? extends AbstractModel> cls = object.getClass(); // Object对象取得
		// Field field = cls.getDeclaredField(fieldName);
		// 文字列取得
		// Class fieldType = field.getType(); //类型取得
		Class<?>[] argTypes = { String.class };
		Object[] argValues = { fieldValue };

		setFieldValueByName(cls, object, fieldName, argTypes, argValues);
	}

	/**
	 * Object的field值设定
	 * 
	 * @param cls
	 * @param object
	 * @param fieldName
	 * @param argTypes
	 * @param argValues
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void setFieldValueByName(Class<? extends AbstractModel> cls, Object object, String fieldName,
			Class<?>[] argTypes, Object[] argValues) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (object == null)
			return;

		String setterMethod = "SET" + fieldName;

		for (Method m : cls.getMethods()) {

			if (m.getName().toUpperCase().equals(setterMethod.toUpperCase())) {

				try {
					// set方法调用
					Method method = cls.getMethod(m.getName(), argTypes);

					// set方法实行（Object，parma）
					method.invoke(object, argValues);

				} catch (Exception e) {
					;
				}
			}
		}
	}

	/**
	 * DB检索结果Int型返回
	 * 
	 * @param conn
	 * @param stmt
	 * @return
	 * @throws DBException
	 */
	public Integer queryInteger(DBAccessor dba, String sql) throws DBException {

		Integer resultIngeter = 0;
		ResultSet result = null;
		logger.debug("queryInteger:" + sql);
		try {
			// DB执行
			stmt = dba.getConn().prepareStatement(sql);
			result = stmt.executeQuery();
			// 检索结果循环

			while (result.next()) {
				resultIngeter = new Integer(result.getInt(1));
			}

		} catch (SQLException e) {
			throw new DBException("SYS_013", e);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new DBException("SYS_013", e);
			}
		}
		return resultIngeter;
	}

	/**
	 * DB检索结果String型返回
	 * 
	 * @param conn
	 * @param stmt
	 * @return
	 * @throws DBException
	 * @throws Exception
	 */
	public String queryString(DBAccessor dba, String sql) throws DBException {

		String resultString = null;
		ResultSet result = null;
		logger.debug("queryString:" + sql);
		try {
			// DB执行
			stmt = dba.getConn().prepareStatement(sql);
			result = stmt.executeQuery();
			if (result == null) {
				return null;
			}

			// 检索结果循环
			while (result.next()) {
				if (result.getString(1) == null) {
					resultString = null;
				} else {
					resultString = new String(result.getString(1));
				}
			}
		} catch (SQLException e) {
			throw new DBException("SYS_013", e);
		} catch (Exception e) {
			throw new DBException("SYS_013", e);
		} finally {
			try {
				if (result != null) {
					result.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new DBException("SYS_013", e);
			}
		}
		return resultString;
	}

	/**
	 * DB检索结果Int型返回
	 * 
	 * @param conn
	 * @param stmt
	 * @return
	 * @throws DBException
	 */
	public Integer updateExecute(DBAccessor dba, String sql) throws DBException {

		Integer resultIngeter = 0;
		logger.debug("updateExecute:" + sql);
		try {
			// DB执行
			stmt = dba.getConn().prepareStatement(sql);
			resultIngeter = stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DBException("SYS_013", e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new DBException("SYS_013", e);
			}
		}
		return resultIngeter;
	}

	/**
	 * 取得InsertSQL
	 * 
	 * @param base_sql
	 * @param userID
	 * @return
	 */
	protected String getAddField(String base_sql, String userName) {

		String strDate = DateUtil.getStrDate();

		String commonFields = "del_flg,create_date,create_user,update_date,update_user";
		String commonFieldsValue = "0,'" + strDate + "','" + userName + "','" + strDate + "','" + userName + "'";
		String pSql = base_sql.replace("{commonFields}", commonFields);
		pSql = pSql.replace("{commonFieldsValue}", commonFieldsValue);

		return pSql;
	}

	/**
	 * 取得UpdateSQL
	 * 
	 * @param base_sql
	 * @param userID
	 * @return
	 */
	protected String getUpdateField(String base_sql, String userName) {

		String strDate = DateUtil.getStrDate();

		String commonFields = "update_date = '" + strDate + "' ,update_user = '" + userName + "'";
		String pSql = base_sql.replace("{commonFields}", commonFields);

		return pSql;
	}

	/**
	 * function 方法调用
	 * 
	 * @param dba
	 * @param seqName
	 * @return
	 * @throws DBException
	 */
	protected String getSequences(DBAccessor dba, String seqName) throws DBException {
		CallableStatement proc = null;
		String resultValue = null;
		try {
			proc = dba.getConn().prepareCall("{? = call f_getnextvalue(?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, seqName);
			proc.execute(); // 在此处出错。
			resultValue = proc.getString(1);

		} catch (SQLException e) {
			throw new DBException("SYS_013", e);
		} finally {
			try {
				if (proc != null) {
					proc.close();
				}
				// if (dba != null) {
				// dba.close();
				// }
			} catch (SQLException e) {
				throw new DBException("SYS_013", e);
			}
		}

		return resultValue;
	}

	public PreparedStatement getStmt() {
		return stmt;
	}

	public void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}

}