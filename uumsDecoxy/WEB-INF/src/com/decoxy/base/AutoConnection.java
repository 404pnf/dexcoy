package com.decoxy.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.decoxy.common.util.StringUtil;

public class AutoConnection extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AutoConnection.class.getName());

	@Override
	public void init() throws ServletException {
		long runTime = 3600000;
		String str = this.getInitParameter("runTime");
		if(!StringUtil.isNull(str)){
			runTime = Long.valueOf(str);
		}
		Timer timer = new Timer();
		timer.schedule(new ConnTest(), 1000, runTime);
	}

	class ConnTest extends TimerTask {
		@Override
		public void run() {

			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				// Look up our data source
				DataSource ds = (DataSource) envCtx.lookup("jdbc/uumsDB");
				// Allocate and use a connection from the pool
				Connection conn = ds.getConnection();
				conn.setAutoCommit(false);
				String sql = "select count(uid) from users ";

				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					logger.info("UUMS Connection test ok : " + result.getInt(1));
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("UUMS Connection test error : ", e);
			}
		}
	}

}
