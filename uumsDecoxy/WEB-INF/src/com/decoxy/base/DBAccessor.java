package com.decoxy.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.decoxy.common.CommonConst;
import com.decoxy.common.exception.DBException;
import com.decoxy.common.exception.SystemException;
import com.decoxy.user.bussniss.UserService;

public class DBAccessor {

	// 数据库驱动
	// private static String jdbcDriver = "com.mysql.jdbc.Driver";
	// 数据URL
	// private static String dbUrl = "jdbc:mysql://192.168.1.131:3306/user_center?characterEncoding=utf8";
	// 数据库用户名
	// private static String dbUsername = "root";
	// 数据库用户密码
	// private static String dbPassword = "jintianshigehaorizi";

	// 数据库驱动
	// private static String jdbcDriver = "com.mysql.jdbc.Driver";
	// 数据URL
	// private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/user_center?characterEncoding=utf8";
	// 数据库用户名
	// private static String dbUsername = "root";
	// 数据库用户密码
	// private static String dbPassword = "jintianshigehaorizi";

	private Connection conn = null;
	private PreparedStatement stmt = null;
	static Logger logger = Logger.getLogger(DBAccessor.class.getName());

	public DBAccessor() throws SystemException {
		try {
			// Class.forName(jdbcDriver);
			// conn = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
			// conn.setAutoCommit(false);

			// Obtain our environment naming context
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/uumsDB");
			// Allocate and use a connection from the pool
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new SystemException("SYS_012", e);
		}
	}

	public static DBAccessor getInstance() throws SystemException {
		DBAccessor dba = null;
		for (int i = 1; i <= 20; i++) {
			try {
				dba = new DBAccessor();
				if (dba.isOpenning()) {
					logger.debug("dbCount===" + i);
					break;
				}
			} catch (Exception e) {
			}
		}

		if (!dba.isOpenning()) {
			logger.debug("dbCount===" + 21);
			throw new SystemException("SYS_012");
		}
		return dba;
	}

	public boolean isOpenning() {
		try {
			return !conn.isClosed();
		} catch (Exception e) {
			return false;
		}
	}

	public void commit() throws DBException {
		try {
			conn.commit();
		} catch (SQLException e) {
			// throw new DBException("", e);
		}
	}

	public void rollback() throws DBException {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// throw new DBException("", e);
		}
	}

	public void close() throws DBException {
		try {
			conn.close();
		} catch (SQLException e) {
			// throw new DBException("", e);
		}
	}

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn
	 *            the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @return the stmt
	 */
	public PreparedStatement getStmt() {
		return stmt;
	}

	/**
	 * @param stmt
	 *            the stmt to set
	 */
	public void setStmt(PreparedStatement stmt) {
		this.stmt = stmt;
	}

}