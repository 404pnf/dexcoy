package com.decoxy.common.exception;

import org.apache.log4j.Logger;

import com.decoxy.base.AbstractException;

public class DBException extends AbstractException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DBException.class.getName());

	public DBException() {
	}

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable e) {
		super(e);
	}

	public DBException(String errID, Throwable e) {
		super(errID, e);
		logger.error("DBException:", e);
		setMessageID(errID);
	}

}
